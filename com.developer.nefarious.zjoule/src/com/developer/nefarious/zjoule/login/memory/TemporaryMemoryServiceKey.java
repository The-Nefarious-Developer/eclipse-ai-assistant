package com.developer.nefarious.zjoule.login.memory;

import com.developer.nefarious.zjoule.auth.ServiceKey;
import com.developer.nefarious.zjoule.memory.EclipseMemory;
import com.developer.nefarious.zjoule.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.memory.IMemoryServiceKey;
import com.developer.nefarious.zjoule.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.utils.ObjectSerializer;

public class TemporaryMemoryServiceKey implements IMemoryServiceKey, ITemporaryMemoryServiceKey {
	
	public static final String KEY = "tmp-" + IMemoryServiceKey.KEY;
	
	IObjectSerializer objectSerializer;
	
	IEclipseMemory eclipseMemory;
	
	public TemporaryMemoryServiceKey() {
		objectSerializer = new ObjectSerializer();
		eclipseMemory = new EclipseMemory();
	}
	
	public TemporaryMemoryServiceKey(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		this.objectSerializer = objectSerializer;
		this.eclipseMemory = eclipseMemory;
	}

	@Override
	public void save(final ServiceKey serviceKey) {
		String serializedObject = objectSerializer.serialize(serviceKey);
		eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
	}

	@Override
	public ServiceKey load() {
		String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
		return objectSerializer.deserialize(serializedObject, ServiceKey.class);
	}

	@Override
	public void persist(final ServiceKey temporaryServiceKey) {
		String serializedObject = objectSerializer.serialize(temporaryServiceKey);
		eclipseMemory.saveOnEclipsePreferences(IMemoryServiceKey.KEY, serializedObject);
	}

}
