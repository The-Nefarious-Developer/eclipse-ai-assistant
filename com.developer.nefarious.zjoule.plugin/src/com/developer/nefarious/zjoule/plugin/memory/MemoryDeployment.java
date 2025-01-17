package com.developer.nefarious.zjoule.plugin.memory;

import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.Deployment;

/**
 * Manages the storage and retrieval of deployment information in memory.
 * <p>
 * The {@code MemoryDeployment} class provides methods to save, load, and check
 * the validity of deployment data stored in Eclipse preferences. It implements {@link IMemoryObject<Deployment>}.
 */
public class MemoryDeployment implements IMemoryObject<Deployment> {
	
	/** Key used for storing and retrieving deployment information in memory. */
    public static final String KEY = "deployment";

    /** Singleton instance of {@code MemoryDeployment}. */
    private static MemoryDeployment instance;

    /** Serializer for converting objects to and from serialized formats. */
    private IObjectSerializer objectSerializer;

    /** Manages interactions with Eclipse's preferences storage. */
    private IEclipseMemory eclipseMemory;

    /**
     * Retrieves the singleton instance of {@code MemoryDeployment}.
     *
     * @return the singleton instance.
     * @throws IllegalStateException if the instance has not been initialized.
     */
    public static MemoryDeployment getInstance() {
        if (instance == null) {
            throw new IllegalStateException("MemoryDeployment not initialized. Call initialize() first.");
        }
        return instance;
    }

    /**
     * Initializes the {@code MemoryDeployment} singleton with the specified dependencies.
     *
     * @param objectSerializer the serializer for handling object serialization and deserialization.
     * @param eclipseMemory the manager for Eclipse preferences storage.
     */
    public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
        if (instance == null) {
            instance = new MemoryDeployment(objectSerializer, eclipseMemory);
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
    private MemoryDeployment(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
        this.objectSerializer = objectSerializer;
        this.eclipseMemory = eclipseMemory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isEmpty() {
        Deployment deployment = load();
        if ((deployment == null) || (deployment.getConfigurationName() == null) || deployment.getConfigurationName().isEmpty()
                || deployment.getConfigurationName().isBlank()) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deployment load() {
        try {
            String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
            return objectSerializer.deserialize(serializedObject, Deployment.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final Deployment deployment) {
        String serializedObject = objectSerializer.serialize(deployment);
        eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
    }
    
    @Override
    public void clear() {
    	eclipseMemory.deleteFromEclipsePreferences(KEY);
    }
}
