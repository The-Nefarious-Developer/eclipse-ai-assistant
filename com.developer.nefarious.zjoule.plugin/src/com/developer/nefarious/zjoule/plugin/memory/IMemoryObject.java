package com.developer.nefarious.zjoule.plugin.memory;

/**
 * Generic interface for managing the storage and retrieval of data in memory.
 * <p>
 * The {@code IMemoryObject} interface defines methods for saving, loading, and
 * checking the validity of data stored in memory. It is parameterized to support any data type.
 *
 * @param <T> the type of the object to be stored and retrieved.
 */
public interface IMemoryObject<T> {

    /**
     * Checks if the stored data is empty or invalid.
     *
     * @return {@code true} if the stored data is empty or invalid; {@code false} otherwise.
     */
    Boolean isEmpty();

    /**
     * Loads the data from memory.
     *
     * @return the stored object of type {@code T}, or {@code null} if no data is found or loading fails.
     */
    T load();

    /**
     * Saves the given data to memory.
     *
     * @param data the object of type {@code T} to save.
     */
    void save(final T data);
}
