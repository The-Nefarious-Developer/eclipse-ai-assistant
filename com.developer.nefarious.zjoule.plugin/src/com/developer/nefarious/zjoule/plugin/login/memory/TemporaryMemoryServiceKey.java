package com.developer.nefarious.zjoule.plugin.login.memory;

import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryServiceKey;
import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.ServiceKey;

/**
 * Manages temporary storage and retrieval of service key information during the login process.
 * <p>
 * The {@code TemporaryMemoryServiceKey} class provides methods to save, load, and persist
 * temporary service key data using Eclipse preferences. It implements {@link IMemoryServiceKey}
 * and {@link ITemporaryMemoryObject}.
 */
public class TemporaryMemoryServiceKey implements IMemoryServiceKey, ITemporaryMemoryObject {

    /** Singleton instance of {@code TemporaryMemoryServiceKey}. */
    private static TemporaryMemoryServiceKey instance;

    /** Key used for storing and retrieving the temporary service key in Eclipse preferences. */
    public static final String KEY = "tmp-" + IMemoryServiceKey.KEY;

    /** Serializer for converting objects to and from serialized formats. */
    private IObjectSerializer objectSerializer;

    /** Manages interactions with Eclipse's preferences storage. */
    private IEclipseMemory eclipseMemory;

    /**
     * Retrieves the singleton instance of {@code TemporaryMemoryServiceKey}.
     *
     * @return the singleton instance.
     * @throws IllegalStateException if the instance has not been initialized.
     */
    public static TemporaryMemoryServiceKey getInstance() {
        if (instance == null) {
            throw new IllegalStateException("TemporaryMemoryServiceKey not initialized. Call initialize() first.");
        }
        return instance;
    }

    /**
     * Initializes the {@code TemporaryMemoryServiceKey} singleton with the specified dependencies.
     *
     * @param objectSerializer the serializer for handling object serialization and deserialization.
     * @param eclipseMemory the manager for Eclipse preferences storage.
     */
    public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
        if (instance == null) {
            instance = new TemporaryMemoryServiceKey(objectSerializer, eclipseMemory);
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
    private TemporaryMemoryServiceKey(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
        this.objectSerializer = objectSerializer;
        this.eclipseMemory = eclipseMemory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isEmpty() {
        ServiceKey serviceKey = load();
        if ((serviceKey == null) || (serviceKey.getClientId() == null) || serviceKey.getClientId().isEmpty()
                || serviceKey.getClientId().isBlank()) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceKey load() {
        try {
            String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
            return objectSerializer.deserialize(serializedObject, ServiceKey.class);
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
        eclipseMemory.saveOnEclipsePreferences(IMemoryServiceKey.KEY, serializedObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final ServiceKey serviceKey) {
        String serializedObject = objectSerializer.serialize(serviceKey);
        eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
    }
}
