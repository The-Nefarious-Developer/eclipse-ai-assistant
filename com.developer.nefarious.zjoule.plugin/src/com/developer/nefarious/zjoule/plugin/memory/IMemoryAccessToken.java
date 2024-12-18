package com.developer.nefarious.zjoule.plugin.memory;

import com.developer.nefarious.zjoule.plugin.models.AccessToken;

/**
 * Interface for managing the storage and retrieval of access tokens.
 * <p>
 * The {@code IMemoryAccessToken} defines methods for saving, loading, and
 * checking the validity of access tokens stored in memory.
 */
public interface IMemoryAccessToken {

    /** Key used for storing and retrieving the access token in memory. */
    public static final String KEY = "access-token";

    /**
     * Checks if the access token is empty or invalid.
     *
     * @return {@code true} if the access token is empty or invalid; {@code false} otherwise.
     */
    Boolean isEmpty();

    /**
     * Loads the access token from memory.
     *
     * @return the {@link AccessToken} object, or {@code null} if no token is found or loading fails.
     */
    AccessToken load();

    /**
     * Saves the given access token to memory.
     *
     * @param accessToken the {@link AccessToken} object to save.
     */
    void save(final AccessToken accessToken);
}
