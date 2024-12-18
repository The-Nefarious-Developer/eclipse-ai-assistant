package com.developer.nefarious.zjoule.plugin.memory;

import com.developer.nefarious.zjoule.plugin.models.ServiceKey;

/**
 * Interface for managing the storage and retrieval of service key information.
 * <p>
 * The {@code IMemoryServiceKey} defines methods for saving, loading, and
 * checking the validity of service key data stored in memory.
 */
public interface IMemoryServiceKey {

    /** Key used for storing and retrieving the service key information in memory. */
    public static final String KEY = "service-key";

    /**
     * Checks if the service key information is empty or invalid.
     *
     * @return {@code true} if the service key information is empty or invalid; {@code false} otherwise.
     */
    Boolean isEmpty();

    /**
     * Loads the service key information from memory.
     *
     * @return the {@link ServiceKey} object, or {@code null} if no data is found or loading fails.
     */
    ServiceKey load();

    /**
     * Saves the given service key information to memory.
     *
     * @param serviceKey the {@link ServiceKey} object to save.
     */
    void save(final ServiceKey serviceKey);
}
