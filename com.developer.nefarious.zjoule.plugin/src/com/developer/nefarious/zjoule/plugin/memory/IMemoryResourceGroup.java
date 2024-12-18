package com.developer.nefarious.zjoule.plugin.memory;

/**
 * Interface for managing the storage and retrieval of resource group information.
 * <p>
 * The {@code IMemoryResourceGroup} defines methods for saving, loading, and
 * checking the validity of resource group data stored in memory.
 */
public interface IMemoryResourceGroup {

    /** Key used for storing and retrieving the resource group information in memory. */
    public static final String KEY = "resource-group";

    /**
     * Checks if the resource group information is empty or invalid.
     *
     * @return {@code true} if the resource group information is empty or invalid; {@code false} otherwise.
     */
    Boolean isEmpty();

    /**
     * Loads the resource group information from memory.
     *
     * @return the resource group name as a {@link String}, or {@code null} if no data is found.
     */
    String load();

    /**
     * Saves the given resource group information to memory.
     *
     * @param resourceGroup the name of the resource group to save as a {@link String}.
     */
    void save(final String resourceGroup);
}
