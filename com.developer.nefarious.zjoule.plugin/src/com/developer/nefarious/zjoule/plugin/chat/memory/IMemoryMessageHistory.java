package com.developer.nefarious.zjoule.plugin.chat.memory;

import com.developer.nefarious.zjoule.plugin.chat.models.MessageHistory;

/**
 * Interface for managing chat message history in memory.
 * Provides methods for clearing, checking, loading, and saving chat message history.
 * Implementations of this interface interact with a persistence mechanism to store
 * and retrieve {@link MessageHistory} objects.
 */
public interface IMemoryMessageHistory {

    /** Key used for storing and retrieving the message history in the persistence layer. */
    public static final String KEY = "message-history";

    /**
     * Clears the stored chat message history from memory.
     * Deletes the data associated with the key {@code KEY} in the persistence mechanism.
     */
    void clear();

    /**
     * Checks if the stored chat message history is empty.
     *
     * @return {@code true} if the stored message history is empty or null; {@code false} otherwise.
     */
    Boolean isEmpty();

    /**
     * Loads the chat message history from memory.
     *
     * @return the {@link MessageHistory} object, or {@code null} if deserialization fails or no data is found.
     */
    MessageHistory load();

    /**
     * Saves the given {@link MessageHistory} object to memory.
     *
     * @param messageHistory the {@link MessageHistory} object to save.
     */
    void save(final MessageHistory messageHistory);

}
