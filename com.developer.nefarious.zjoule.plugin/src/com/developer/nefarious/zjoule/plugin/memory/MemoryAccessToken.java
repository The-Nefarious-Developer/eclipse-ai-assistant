package com.developer.nefarious.zjoule.plugin.memory;

import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.AccessToken;

public class MemoryAccessToken implements IMemoryAccessToken {

	private static MemoryAccessToken instance;

	public static MemoryAccessToken getInstance() {
		if (instance == null) {
			throw new IllegalStateException("MemoryAccessToken not initialized. Call initialize() first.");
		}
		return instance;
	}

	public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new MemoryAccessToken(objectSerializer, eclipseMemory);
		}
	}

	public static void resetInstance() {
		instance = null;
	}

	private IObjectSerializer objectSerializer;

	private IEclipseMemory eclipseMemory;

	private MemoryAccessToken(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		this.objectSerializer = objectSerializer;
		this.eclipseMemory = eclipseMemory;
	}

	@Override
	public Boolean isEmpty() {
		AccessToken accessToken = load();
		if ((accessToken == null) || (accessToken.getAccessToken() == null) || accessToken.getAccessToken().isEmpty() ||
				accessToken.getAccessToken().isBlank()) {
			return true;
		}
		return false;
	}

	@Override
	public AccessToken load() {
		try {
			String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
			return objectSerializer.deserialize(serializedObject, AccessToken.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void save(final AccessToken accesstoken) {
		String serializedObject = objectSerializer.serialize(accesstoken);
		eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
	}

}
