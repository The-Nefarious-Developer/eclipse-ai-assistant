package com.developer.nefarious.zjoule.memory;

import com.developer.nefarious.zjoule.auth.AccessToken;

public class MemoryAccessToken implements IMemoryAccessToken {

	IObjectSerializer objectSerializer;
	
	IEclipseMemory eclipseMemory;
	
	public MemoryAccessToken() {
		objectSerializer = new ObjectSerializer();
		eclipseMemory = new EclipseMemory();
	}
	
	public MemoryAccessToken(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		this.objectSerializer = objectSerializer;
		this.eclipseMemory = eclipseMemory;
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
