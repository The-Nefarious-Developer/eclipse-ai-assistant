package com.developer.nefarious.zjoule.auth;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JsonValidator {
	
	public boolean isValidJson(String json) {
        try {
            JsonParser.parseString(json); // Attempts to parse the JSON string
            return true; // No exception means it's valid JSON
        } catch (JsonSyntaxException e) {
            return false; // Exception indicates invalid JSON syntax
        }
    }

}
