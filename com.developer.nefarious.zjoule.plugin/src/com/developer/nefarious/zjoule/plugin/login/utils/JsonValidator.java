package com.developer.nefarious.zjoule.plugin.login.utils;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public abstract class JsonValidator {
	
	private JsonValidator() { }
	
	public static boolean isValidJson(final String json) {
        try {
            JsonParser.parseString(json); // Attempts to parse the JSON string
            return true; // No exception means it's valid JSON
        } catch (JsonSyntaxException e) {
            return false; // Exception indicates invalid JSON syntax
        }
    }

}
