package com.developer.nefarious.zjoule.memory.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectSerializer implements IObjectSerializer {
	
	private static ObjectSerializer instance;

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	
	public static ObjectSerializer getInstance() {
		if (instance == null) {
            instance = new ObjectSerializer();
        }
        return instance;
	}

	public String serialize(final Object object) {
		if (object == null) {
			throw new IllegalArgumentException("Object to be serialized cannot be null");
		}
		return GSON.toJson(object);

	}

	public <T> T deserialize(final String jsonString, final Class<T> clazz) {
		if (jsonString == null || jsonString.isEmpty()) {
			throw new IllegalArgumentException("JSON string cannot be null or empty");
		}
		return GSON.fromJson(jsonString, clazz);
	}

}
