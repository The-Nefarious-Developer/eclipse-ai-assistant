package com.developer.nefarious.zjoule.plugin.memory;

import com.developer.nefarious.zjoule.plugin.models.Deployment;

/**
 * Interface for managing the storage and retrieval of deployment information.
 * <p>
 * The {@code IMemoryDeployment} defines methods for saving, loading, and
 * checking the validity of deployment data stored in memory.
 */
public interface IMemoryDeployment {

    /** Key used for storing and retrieving deployment information in memory. */
    public static final String KEY = "deployment";

    /**
     * Checks if the deployment information is empty or invalid.
     *
     * @return {@code true} if the deployment information is empty or invalid; {@code false} otherwise.
     */
    Boolean isEmpty();

    /**
     * Loads the deployment information from memory.
     *
     * @return the {@link Deployment} object, or {@code null} if no deployment is found or loading fails.
     */
    Deployment load();

    /**
     * Saves the given deployment information to memory.
     *
     * @param deployment the {@link Deployment} object to save.
     */
    void save(final Deployment deployment);
}
