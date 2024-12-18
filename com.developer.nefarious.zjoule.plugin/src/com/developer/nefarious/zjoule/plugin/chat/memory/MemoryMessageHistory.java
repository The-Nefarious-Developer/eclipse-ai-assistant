package com.developer.nefarious.zjoule.plugin.chat.memory;

import com.developer.nefarious.zjoule.plugin.chat.models.MessageHistory;
import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;

/**
 * Manages the storage and retrieval of chat message history in memory.
 * This class uses the Eclipse preferences system and an object serializer to
 * persist and retrieve {@link MessageHistory} objects.
 * 
 * Implements the {@link IMemoryMessageHistory} interface for memory management
 * of chat messages.
 */
public class MemoryMessageHistory implements IMemoryMessageHistory {

    /** Singleton instance of {@code MemoryMessageHistory}. */
    private static MemoryMessageHistory instance;

    /** Key used for storing and retrieving data from Eclipse preferences. */
    private static final String KEY = "memory_message_history";

    /** Serializer for converting objects to and from serialized formats. */
    private IObjectSerializer objectSerializer;

    /** Handles interactions with Eclipse's preferences system. */
    private IEclipseMemory eclipseMemory;

    /**
     * Retrieves the singleton instance of {@code MemoryMessageHistory}.
     *
     * @return the singleton instance of {@code MemoryMessageHistory}.
     * @throws IllegalStateException if the instance is not initialized.
     */
    public static MemoryMessageHistory getInstance() {
        if (instance == null) {
            throw new IllegalStateException("MemoryMessageHistory not initialized. Call initialize() first.");
        }
        return instance;
    }

    /**
     * Initializes the {@code MemoryMessageHistory} singleton with the specified
     * object serializer and Eclipse memory handler.
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
     * Useful for testing or reinitializing the class.
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
     * Clears the stored chat message history from memory.
     * Deletes the data associated with the key {@code KEY} in Eclipse preferences.
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
