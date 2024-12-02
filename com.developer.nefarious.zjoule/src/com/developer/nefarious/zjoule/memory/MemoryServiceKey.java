package com.developer.nefarious.zjoule.memory;

import com.developer.nefarious.zjoule.auth.ServiceKey;
import com.developer.nefarious.zjoule.utils.IObjectSerializer;

public class MemoryServiceKey implements IMemoryServiceKey {
	
	private static MemoryServiceKey instance;
	
	private IObjectSerializer objectSerializer;
	
	private IEclipseMemory eclipseMemory;
	
	private MemoryServiceKey(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		this.objectSerializer = objectSerializer;
		this.eclipseMemory = eclipseMemory;
	}
	
	public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new MemoryServiceKey(objectSerializer, eclipseMemory);
		}
	}

	public static MemoryServiceKey getInstance() {
		if (instance == null) {
			throw new IllegalStateException("MemoryServiceKey not initialized. Call initialize() first.");
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

}
