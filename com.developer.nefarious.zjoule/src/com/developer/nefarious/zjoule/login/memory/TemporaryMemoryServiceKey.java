package com.developer.nefarious.zjoule.login.memory;

import com.developer.nefarious.zjoule.auth.ServiceKey;
import com.developer.nefarious.zjoule.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.memory.IMemoryServiceKey;
import com.developer.nefarious.zjoule.utils.IObjectSerializer;

public class TemporaryMemoryServiceKey implements IMemoryServiceKey, ITemporaryMemoryServiceKey {
	
	private static TemporaryMemoryServiceKey instance;
	
	public static final String KEY = "tmp-" + IMemoryServiceKey.KEY;
	
	IObjectSerializer objectSerializer;
	
	IEclipseMemory eclipseMemory;
	
	private TemporaryMemoryServiceKey(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		this.objectSerializer = objectSerializer;
		this.eclipseMemory = eclipseMemory;
	}
	
	public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new TemporaryMemoryServiceKey(objectSerializer, eclipseMemory);
		}
	}

	public static TemporaryMemoryServiceKey getInstance() {
		if (instance == null) {
			throw new IllegalStateException("TemporaryMemoryAccessToken not initialized. Call initialize() first.");
		}
		return instance;
	}

	public static void resetInstance() {
		instance = null;
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
