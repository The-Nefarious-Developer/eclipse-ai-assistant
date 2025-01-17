package com.developer.nefarious.zjoule.plugin.login.memory;

import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;
import com.developer.nefarious.zjoule.plugin.memory.MemoryOllamaModel;
import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.OllamaModel;

/**
 * Manages temporary storage and retrieval of ollamaModel information during the login process.
 * <p>
 * The {@code TemporaryMemoryOllamaModel} class provides methods to save, load, and persist
 * temporary ollamaModel data using Eclipse preferences. It implements {@link IMemoryObject<OllamaModel>}
 * and {@link ITemporaryMemoryObject}.
 */
public class TemporaryMemoryOllamaModel implements IMemoryObject<OllamaModel>, ITemporaryMemoryObject {

    /** Singleton instance of {@code TemporaryMemoryOllamaModel}. */
    private static TemporaryMemoryOllamaModel instance;

    /** Key used for storing and retrieving the temporary ollamaModel in Eclipse preferences. */
    public static final String KEY = "tmp-" + MemoryOllamaModel.KEY;

    /** Serializer for converting objects to and from serialized formats. */
    private IObjectSerializer objectSerializer;

    /** Manages interactions with Eclipse's preferences storage. */
    private IEclipseMemory eclipseMemory;

    /**
     * Retrieves the singleton instance of {@code TemporaryMemoryOllamaModel}.
     *
     * @return the singleton instance.
     * @throws IllegalStateException if the instance has not been initialized.
     */
    public static TemporaryMemoryOllamaModel getInstance() {
        if (instance == null) {
            throw new IllegalStateException("TemporaryMemoryOllamaModel not initialized. Call initialize() first.");
        }
        return instance;
    }

    /**
     * Initializes the {@code TemporaryMemoryOllamaModel} singleton with the specified dependencies.
     *
     * @param objectSerializer the serializer for handling object serialization and deserialization.
     * @param eclipseMemory the manager for Eclipse preferences storage.
     */
    public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
        if (instance == null) {
            instance = new TemporaryMemoryOllamaModel(objectSerializer, eclipseMemory);
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
    private TemporaryMemoryOllamaModel(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
        this.objectSerializer = objectSerializer;
        this.eclipseMemory = eclipseMemory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isEmpty() {
        OllamaModel ollamaModel = load();
        if ((ollamaModel == null) || (ollamaModel.getName() == null) || ollamaModel.getName().isEmpty()
                || ollamaModel.getName().isBlank()) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OllamaModel load() {
        try {
            String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
            return objectSerializer.deserialize(serializedObject, OllamaModel.class);
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
        eclipseMemory.saveOnEclipsePreferences(MemoryOllamaModel.KEY, serializedObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final OllamaModel ollamaModel) {
        String serializedObject = objectSerializer.serialize(ollamaModel);
        eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
    }
}
