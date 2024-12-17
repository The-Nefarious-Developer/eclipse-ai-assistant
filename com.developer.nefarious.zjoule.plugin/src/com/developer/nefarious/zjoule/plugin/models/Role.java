package com.developer.nefarious.zjoule.plugin.models;

import java.lang.reflect.Type;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public enum Role {

	USER("user"), 
	ASSISTANT("assistant"), 
	SYSTEM("system");

	private final String value;

	Role(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
	
	public static class RoleSerializer implements JsonSerializer<Role> {
        @Override
        public JsonElement serialize(final Role src, final Type typeOfSrc, final JsonSerializationContext context) {
            return new JsonPrimitive(src.getValue());
        }
    }

}
