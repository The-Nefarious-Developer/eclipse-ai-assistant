package com.developer.nefarious.zjoule.memory;

import com.developer.nefarious.zjoule.auth.ServiceKey;

public class TemporaryMemoryServiceKey implements IMemoryServiceKey {
	
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

}
