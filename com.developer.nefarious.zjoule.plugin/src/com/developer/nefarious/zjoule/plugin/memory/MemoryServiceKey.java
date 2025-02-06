package com.developer.nefarious.zjoule.plugin.memory;

import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.ServiceKey;

/**
 * Manages the storage and retrieval of service key information in memory.
 * <p>
 * The {@code MemoryServiceKey} class provides methods to save, load, and check
 * the validity of service key data stored in Eclipse preferences. It implements {@link IMemoryObject<ServiceKey>}.
 */
public class MemoryServiceKey implements IMemoryObject<ServiceKey> {
	
    /** Key used for storing and retrieving the service key information in memory. */
    public static final String KEY = "service-key";

    /** Singleton instance of {@code MemoryServiceKey}. */
    private static MemoryServiceKey instance;

    /** Serializer for converting objects to and from serialized formats. */
    private IObjectSerializer objectSerializer;

    /** Manages interactions with Eclipse's preferences storage. */
    private IEclipseMemory eclipseMemory;

    /**
     * Retrieves the singleton instance of {@code MemoryServiceKey}.
     *
     * @return the singleton instance.
     * @throws IllegalStateException if the instance has not been initialized.
     */
    public static MemoryServiceKey getInstance() {
        if (instance == null) {
            throw new IllegalStateException("MemoryServiceKey not initialized. Call initialize() first.");
        }
        return instance;
    }

    /**
     * Initializes the {@code MemoryServiceKey} singleton with the specified dependencies.
     *
     * @param objectSerializer the serializer for handling object serialization and deserialization.
     * @param eclipseMemory the manager for Eclipse preferences storage.
     */
    public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
        if (instance == null) {
            instance = new MemoryServiceKey(objectSerializer, eclipseMemory);
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
    private MemoryServiceKey(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
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
    public void save(final ServiceKey serviceKey) {
        String serializedObject = objectSerializer.serialize(serviceKey);
        eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
    	eclipseMemory.deleteFromEclipsePreferences(KEY);
    }
}
