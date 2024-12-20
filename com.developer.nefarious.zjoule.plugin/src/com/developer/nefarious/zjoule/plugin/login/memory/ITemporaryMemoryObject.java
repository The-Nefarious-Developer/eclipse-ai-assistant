package com.developer.nefarious.zjoule.plugin.login.memory;

/**
 * Represents an object that can be persisted from temporary memory to permanent storage.
 * <p>
 * The {@code ITemporaryMemoryObject} interface defines a method for persisting
 * temporary data to a more durable storage mechanism.
 */
public interface ITemporaryMemoryObject {

    /**
     * Persists the temporary memory object to permanent storage.
     */
    void persist();
}
