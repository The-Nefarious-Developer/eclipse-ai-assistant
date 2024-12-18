package com.developer.nefarious.zjoule.plugin.login.memory;

import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;
import com.developer.nefarious.zjoule.plugin.memory.MemoryDeployment;
import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.Deployment;

/**
 * Manages temporary storage and retrieval of deployment information during the login process.
 * <p>
 * The {@code TemporaryMemoryDeployment} class provides methods to save, load, and persist
 * temporary deployment data using Eclipse preferences. It implements {@link IMemoryObject<Deployment>}
 * and {@link ITemporaryMemoryObject}.
 */
public class TemporaryMemoryDeployment implements IMemoryObject<Deployment>, ITemporaryMemoryObject {

    /** Singleton instance of {@code TemporaryMemoryDeployment}. */
    private static TemporaryMemoryDeployment instance;

    /** Key used for storing and retrieving the temporary deployment in Eclipse preferences. */
    public static final String KEY = "tmp-" + MemoryDeployment.KEY;

    /** Serializer for converting objects to and from serialized formats. */
    private IObjectSerializer objectSerializer;

    /** Manages interactions with Eclipse's preferences storage. */
    private IEclipseMemory eclipseMemory;

    /**
     * Retrieves the singleton instance of {@code TemporaryMemoryDeployment}.
     *
     * @return the singleton instance.
     * @throws IllegalStateException if the instance has not been initialized.
     */
    public static TemporaryMemoryDeployment getInstance() {
        if (instance == null) {
            throw new IllegalStateException("TemporaryMemoryDeployment not initialized. Call initialize() first.");
        }
        return instance;
    }

    /**
     * Initializes the {@code TemporaryMemoryDeployment} singleton with the specified dependencies.
     *
     * @param objectSerializer the serializer for handling object serialization and deserialization.
     * @param eclipseMemory the manager for Eclipse preferences storage.
     */
    public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
        if (instance == null) {
            instance = new TemporaryMemoryDeployment(objectSerializer, eclipseMemory);
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
    private TemporaryMemoryDeployment(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
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
    public void persist() {
        String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
        eclipseMemory.saveOnEclipsePreferences(MemoryDeployment.KEY, serializedObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final Deployment deployment) {
        String serializedObject = objectSerializer.serialize(deployment);
        eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
    }
}
