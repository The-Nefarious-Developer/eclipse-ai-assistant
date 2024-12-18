package com.developer.nefarious.zjoule.plugin.memory;

import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.ServiceKey;

public class MemoryServiceKey implements IMemoryServiceKey {

	private static MemoryServiceKey instance;

	public static MemoryServiceKey getInstance() {
		if (instance == null) {
			throw new IllegalStateException("MemoryServiceKey not initialized. Call initialize() first.");
		}
		return instance;
	}

	public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new MemoryServiceKey(objectSerializer, eclipseMemory);
		}
	}

	public static void resetInstance() {
		instance = null;
	}

	private IObjectSerializer objectSerializer;

	private IEclipseMemory eclipseMemory;

	private MemoryServiceKey(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		this.objectSerializer = objectSerializer;
		this.eclipseMemory = eclipseMemory;
	}

	@Override
	public Boolean isEmpty() {
		ServiceKey serviceKey = load();
		if ((serviceKey == null) || (serviceKey.getClientId() == null) || serviceKey.getClientId().isEmpty() ||
				serviceKey.getClientId().isBlank()) {
			return true;
		}
		return false;
	}

	@Override
	public ServiceKey load() {
		try {
			String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
			return objectSerializer.deserialize(serializedObject, ServiceKey.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void save(final ServiceKey serviceKey) {
		String serializedObject = objectSerializer.serialize(serviceKey);
		eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
	}

}
