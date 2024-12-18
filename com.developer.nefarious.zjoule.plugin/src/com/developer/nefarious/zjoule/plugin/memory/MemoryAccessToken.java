package com.developer.nefarious.zjoule.plugin.memory;

import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.AccessToken;

/**
 * Manages the storage and retrieval of access tokens in memory.
 * <p>
 * The {@code MemoryAccessToken} class provides methods to save, load, and check
 * the validity of access tokens stored in Eclipse preferences. It implements {@link IMemoryAccessToken}.
 */
public class MemoryAccessToken implements IMemoryAccessToken {

    /** Singleton instance of {@code MemoryAccessToken}. */
    private static MemoryAccessToken instance;

    /** Serializer for converting objects to and from serialized formats. */
    private IObjectSerializer objectSerializer;

    /** Manages interactions with Eclipse's preferences storage. */
    private IEclipseMemory eclipseMemory;

    /**
     * Retrieves the singleton instance of {@code MemoryAccessToken}.
     *
     * @return the singleton instance.
     * @throws IllegalStateException if the instance has not been initialized.
     */
    public static MemoryAccessToken getInstance() {
        if (instance == null) {
            throw new IllegalStateException("MemoryAccessToken not initialized. Call initialize() first.");
        }
        return instance;
    }

    /**
     * Initializes the {@code MemoryAccessToken} singleton with the specified dependencies.
     *
     * @param objectSerializer the serializer for handling object serialization and deserialization.
     * @param eclipseMemory the manager for Eclipse preferences storage.
     */
    public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
        if (instance == null) {
            instance = new MemoryAccessToken(objectSerializer, eclipseMemory);
        }
    }

    /**
     * Resets the singleton instance. Useful for testing or reinitialization.
     */
    public static void resetInstance() {
        instance = null;
    }

    /**
     * Private constructor to enforce singleton behavior.
     *
     * @param objectSerializer the serializer for handling object serialization and deserialization.
     * @param eclipseMemory the manager for Eclipse preferences storage.
     */
    private MemoryAccessToken(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
        this.objectSerializer = objectSerializer;
        this.eclipseMemory = eclipseMemory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isEmpty() {
        AccessToken accessToken = load();
        if ((accessToken == null) || (accessToken.getAccessToken() == null) || accessToken.getAccessToken().isEmpty()
                || accessToken.getAccessToken().isBlank()) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessToken load() {
        try {
            String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
            return objectSerializer.deserialize(serializedObject, AccessToken.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final AccessToken accesstoken) {
        String serializedObject = objectSerializer.serialize(accesstoken);
        eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
    }
}
