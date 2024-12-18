package com.developer.nefarious.zjoule.plugin.login.utils;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * Utility class for validating JSON strings.
 * <p>
 * This class provides a static method to check whether a given string is valid JSON.
 * It uses the Google Gson library to attempt parsing the string and determine its validity.
 * <p>
 * This class is abstract to prevent instantiation.
 */
public abstract class JsonValidator {

    /**
     * Validates whether a given string is a well-formed JSON.
     *
     * @param json the string to validate.
     * @return {@code true} if the string is valid JSON, {@code false} otherwise.
     */
    public static boolean isValidJson(final String json) {
        try {
            JsonParser.parseString(json); // Attempts to parse the JSON string
            return true; // No exception means it's valid JSON
        } catch (JsonSyntaxException e) {
            return false; // Exception indicates invalid JSON syntax
        }
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private JsonValidator() { }
    
}
