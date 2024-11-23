package com.developer.nefarious.zjoule.memory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ObjectSerializer implements IObjectSerializer {

	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public String serialize(Object object) {
		if (object == null)
			throw new IllegalArgumentException("Object to be serialized cannot be null");
		return gson.toJson(object);

	}

	public <T> T deserialize(String jsonString, Class<T> clazz) {
		 if (jsonString == null || jsonString.isEmpty()) {
	            throw new IllegalArgumentException("JSON string cannot be null or empty");
	        }
	        return gson.fromJson(jsonString, clazz);
	}

}
