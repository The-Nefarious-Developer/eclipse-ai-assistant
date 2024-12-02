package com.developer.nefarious.zjoule.login.memory;

import com.developer.nefarious.zjoule.auth.AccessToken;
import com.developer.nefarious.zjoule.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.memory.IMemoryAccessToken;
import com.developer.nefarious.zjoule.utils.IObjectSerializer;

public class TemporaryMemoryAccessToken implements IMemoryAccessToken, ITemporaryMemoryObject {

	private static TemporaryMemoryAccessToken instance;

	public static final String KEY = "tmp-" + IMemoryAccessToken.KEY;

	IObjectSerializer objectSerializer;

	IEclipseMemory eclipseMemory;

	private TemporaryMemoryAccessToken(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		this.objectSerializer = objectSerializer;
		this.eclipseMemory = eclipseMemory;
	}

	public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new TemporaryMemoryAccessToken(objectSerializer, eclipseMemory);
		}
	}

	public static TemporaryMemoryAccessToken getInstance() {
		if (instance == null) {
			throw new IllegalStateException("TemporaryMemoryAccessToken not initialized. Call initialize() first.");
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

	@Override
	public void persist() {
		String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
		eclipseMemory.saveOnEclipsePreferences(IMemoryAccessToken.KEY, serializedObject);
	}

}
