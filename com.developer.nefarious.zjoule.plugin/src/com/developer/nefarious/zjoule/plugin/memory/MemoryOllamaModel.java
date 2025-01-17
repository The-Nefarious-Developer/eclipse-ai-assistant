package com.developer.nefarious.zjoule.plugin.memory;

import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.OllamaModel;

/**
 * Manages the storage and retrieval of ollamaModel information in memory.
 * <p>
 * The {@code MemoryOllamaModel} class provides methods to save, load, and check
 * the validity of ollamaModel data stored in Eclipse preferences. It implements {@link IMemoryObject<OllamaModel>}.
 */
public class MemoryOllamaModel implements IMemoryObject<OllamaModel> {
	
	/** Key used for storing and retrieving ollamaModel information in memory. */
    public static final String KEY = "ollama-model";

    /** Singleton instance of {@code MemoryOllamaModel}. */
    private static MemoryOllamaModel instance;

    /** Serializer for converting objects to and from serialized formats. */
    private IObjectSerializer objectSerializer;

    /** Manages interactions with Eclipse's preferences storage. */
    private IEclipseMemory eclipseMemory;

    /**
     * Retrieves the singleton instance of {@code MemoryOllamaModel}.
     *
     * @return the singleton instance.
     * @throws IllegalStateException if the instance has not been initialized.
     */
    public static MemoryOllamaModel getInstance() {
        if (instance == null) {
            throw new IllegalStateException("MemoryOllamaModel not initialized. Call initialize() first.");
        }
        return instance;
    }

    /**
     * Initializes the {@code MemoryOllamaModel} singleton with the specified dependencies.
     *
     * @param objectSerializer the serializer for handling object serialization and deserialization.
     * @param eclipseMemory the manager for Eclipse preferences storage.
     */
    public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
        if (instance == null) {
            instance = new MemoryOllamaModel(objectSerializer, eclipseMemory);
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
    private MemoryOllamaModel(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
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
    public void save(final OllamaModel ollamaModel) {
        String serializedObject = objectSerializer.serialize(ollamaModel);
        eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
    }
}
