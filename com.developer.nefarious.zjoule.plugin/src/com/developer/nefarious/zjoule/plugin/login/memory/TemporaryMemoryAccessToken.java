package com.developer.nefarious.zjoule.plugin.login.memory;

import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryAccessToken;
import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.AccessToken;

/**
 * Manages temporary storage and retrieval of an access token during the login process.
 * <p>
 * The {@code TemporaryMemoryAccessToken} class provides methods to save, load, and persist
 * temporary access tokens using Eclipse preferences. It implements {@link IMemoryAccessToken}
 * and {@link ITemporaryMemoryObject}.
 */
public class TemporaryMemoryAccessToken implements IMemoryAccessToken, ITemporaryMemoryObject {

    /** Singleton instance of {@code TemporaryMemoryAccessToken}. */
    private static TemporaryMemoryAccessToken instance;

    /** Key used for storing and retrieving the temporary access token in Eclipse preferences. */
    public static final String KEY = "tmp-" + IMemoryAccessToken.KEY;

    /** Serializer for converting objects to and from serialized formats. */
    private IObjectSerializer objectSerializer;

    /** Manages interactions with Eclipse's preferences storage. */
    private IEclipseMemory eclipseMemory;

    /**
     * Retrieves the singleton instance of {@code TemporaryMemoryAccessToken}.
     *
     * @return the singleton instance.
     * @throws IllegalStateException if the instance has not been initialized.
     */
    public static TemporaryMemoryAccessToken getInstance() {
        if (instance == null) {
            throw new IllegalStateException("TemporaryMemoryAccessToken not initialized. Call initialize() first.");
        }
        return instance;
    }

    /**
     * Initializes the {@code TemporaryMemoryAccessToken} singleton with the specified dependencies.
     *
     * @param objectSerializer the serializer for handling object serialization and deserialization.
     * @param eclipseMemory the manager for Eclipse preferences storage.
     */
    public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
        if (instance == null) {
            instance = new TemporaryMemoryAccessToken(objectSerializer, eclipseMemory);
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
    private TemporaryMemoryAccessToken(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
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
    public void persist() {
        String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
        eclipseMemory.saveOnEclipsePreferences(IMemoryAccessToken.KEY, serializedObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final AccessToken accessToken) {
        String serializedObject = objectSerializer.serialize(accessToken);
        eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
    }
}
