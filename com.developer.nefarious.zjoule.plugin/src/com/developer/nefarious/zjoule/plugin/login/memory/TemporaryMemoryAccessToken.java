package com.developer.nefarious.zjoule.plugin.login.memory;

import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryAccessToken;
import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.AccessToken;

public class TemporaryMemoryAccessToken implements IMemoryAccessToken, ITemporaryMemoryObject {

	private static TemporaryMemoryAccessToken instance;

	public static final String KEY = "tmp-" + IMemoryAccessToken.KEY;

	public static TemporaryMemoryAccessToken getInstance() {
		if (instance == null) {
			throw new IllegalStateException("TemporaryMemoryAccessToken not initialized. Call initialize() first.");
		}
		return instance;
	}

	public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new TemporaryMemoryAccessToken(objectSerializer, eclipseMemory);
		}
	}

	public static void resetInstance() {
		instance = null;
	}

	IObjectSerializer objectSerializer;

	IEclipseMemory eclipseMemory;

	private TemporaryMemoryAccessToken(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
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
	public void persist() {
		String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
		eclipseMemory.saveOnEclipsePreferences(IMemoryAccessToken.KEY, serializedObject);
	}

	@Override
	public void save(final AccessToken accesstoken) {
		String serializedObject = objectSerializer.serialize(accesstoken);
		eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
	}

}
