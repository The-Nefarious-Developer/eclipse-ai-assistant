package com.developer.nefarious.zjoule.memory;

import com.developer.nefarious.zjoule.auth.AccessToken;
import com.developer.nefarious.zjoule.utils.IObjectSerializer;

public class MemoryAccessToken implements IMemoryAccessToken {
	
	private static MemoryAccessToken instance;

	private IObjectSerializer objectSerializer;
	
	private IEclipseMemory eclipseMemory;
	
	private MemoryAccessToken(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		this.objectSerializer = objectSerializer;
		this.eclipseMemory = eclipseMemory;
	}
	
	public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new MemoryAccessToken(objectSerializer, eclipseMemory);
		}
	}

	public static MemoryAccessToken getInstance() {
		if (instance == null) {
			throw new IllegalStateException("MemoryAccessToken not initialized. Call initialize() first.");
		}
		return instance;
	}

	public static void resetInstance() {
		instance = null;
	}

	@Override
	public void save(final AccessToken accesstoken) {
		String serializedObject = objectSerializer.serialize(accesstoken);
		eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
	}

	@Override
	public AccessToken load() {
		String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
		return objectSerializer.deserialize(serializedObject, AccessToken.class);
	}

}
