package com.developer.nefarious.zjoule.plugin.memory.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Singleton implementation of {@link IObjectSerializer} that provides methods
 * for serializing objects to JSON and deserializing JSON strings into objects.
 * <p>
 * This class uses the Google Gson library for JSON processing and ensures thread safety
 * for singleton instance retrieval.
 */
public class ObjectSerializer implements IObjectSerializer {

    /** Singleton instance of {@link ObjectSerializer}. */
    private static ObjectSerializer instance;

    /** Gson instance with pretty printing enabled. */
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Retrieves the singleton instance of {@link ObjectSerializer}.
     * <p>
     * If the instance has not been initialized, it creates a new one.
     *
     * @return the singleton instance of {@link ObjectSerializer}.
     */
    public static ObjectSerializer getInstance() {
        if (instance == null) {
            instance = new ObjectSerializer();
        }
        return instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T deserialize(final String jsonString, final Class<T> clazz) {
        if (jsonString == null || jsonString.isEmpty()) {
            throw new IllegalArgumentException("JSON string cannot be null or empty");
        }
        return GSON.fromJson(jsonString, clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String serialize(final Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Object to be serialized cannot be null");
        }
        return GSON.toJson(object);
    }
}
