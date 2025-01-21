package com.developer.nefarious.zjoule.plugin.chat.memory;

import com.developer.nefarious.zjoule.plugin.chat.models.MessageHistory;
import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;

/**
 * Manages the storage and retrieval of chat message history in Eclipse preferences.
 * This class provides functionality to persist, retrieve, and manage
 * {@link MessageHistory} objects using an object serializer and Eclipse's preferences API.
 * 
 * Implements the {@link IMemoryMessageHistory} interface.
 */
public class MemoryMessageHistory implements IMemoryMessageHistory {

    /** Singleton instance of {@code IMemoryMessageHistory}. */
    private static MemoryMessageHistory instance;

    /** Serializer for converting objects to and from serialized formats. */
    private IObjectSerializer objectSerializer;

    /** Handles interactions with Eclipse's preferences system. */
    private IEclipseMemory eclipseMemory;

    /**
     * Retrieves the singleton instance of {@code IMemoryMessageHistory}.
     *
     * @return the singleton instance of {@code IMemoryMessageHistory}.
     * @throws IllegalStateException if the instance is not initialized.
     */
    public static MemoryMessageHistory getInstance() {
        if (instance == null) {
            throw new IllegalStateException("MemoryMessageHistory not initialized. Call initialize() first.");
        }
        return instance;
    }

    /**
     * Initializes the singleton instance of {@code MemoryMessageHistory}.
     *
     * @param objectSerializer the serializer for handling object serialization and deserialization.
     * @param eclipseMemory the handler for managing Eclipse preferences storage.
     */
    public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
        if (instance == null) {
            instance = new MemoryMessageHistory(objectSerializer, eclipseMemory);
        }
    }

    /**
     * Resets the singleton instance of {@code MemoryMessageHistory}.
     * This is useful for testing or reinitializing the instance.
     */
    public static void resetInstance() {
        instance = null;
    }

    /**
     * Private constructor to enforce singleton behavior.
     *
     * @param objectSerializer the serializer for handling object serialization and deserialization.
     * @param eclipseMemory the handler for managing Eclipse preferences storage.
     */
    private MemoryMessageHistory(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
        this.objectSerializer = objectSerializer;
        this.eclipseMemory = eclipseMemory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        eclipseMemory.deleteFromEclipsePreferences(KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isEmpty() {
        MessageHistory messageHistory = load();
        return (messageHistory == null) || (messageHistory.getMessages() == null) || messageHistory.getMessages().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessageHistory load() {
        try {
            String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
            return objectSerializer.deserialize(serializedObject, MessageHistory.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final MessageHistory messageHistory) {
        String serializedObject = objectSerializer.serialize(messageHistory);
        eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
    }
    
}