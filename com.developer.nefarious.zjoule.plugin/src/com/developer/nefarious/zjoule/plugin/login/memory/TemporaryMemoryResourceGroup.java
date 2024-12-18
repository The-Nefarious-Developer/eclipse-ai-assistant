package com.developer.nefarious.zjoule.plugin.login.memory;

import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;
import com.developer.nefarious.zjoule.plugin.memory.MemoryResourceGroup;

/**
 * Manages temporary storage and retrieval of resource group information during the login process.
 * <p>
 * The {@code TemporaryMemoryResourceGroup} class provides methods to save, load, and persist
 * temporary resource group data using Eclipse preferences. It implements {@link IMemoryObject<String>}
 * and {@link ITemporaryMemoryObject}.
 */
public class TemporaryMemoryResourceGroup implements IMemoryObject<String>, ITemporaryMemoryObject {

    /** Singleton instance of {@code TemporaryMemoryResourceGroup}. */
    private static TemporaryMemoryResourceGroup instance;

    /** Key used for storing and retrieving the temporary resource group in Eclipse preferences. */
    public static final String KEY = "tmp-" + MemoryResourceGroup.KEY;

    /** Manages interactions with Eclipse's preferences storage. */
    private IEclipseMemory eclipseMemory;

    /**
     * Retrieves the singleton instance of {@code TemporaryMemoryResourceGroup}.
     *
     * @return the singleton instance.
     * @throws IllegalStateException if the instance has not been initialized.
     */
    public static TemporaryMemoryResourceGroup getInstance() {
        if (instance == null) {
            throw new IllegalStateException("TemporaryMemoryResourceGroup not initialized. Call initialize() first.");
        }
        return instance;
    }

    /**
     * Initializes the {@code TemporaryMemoryResourceGroup} singleton with the specified dependencies.
     *
     * @param eclipseMemory the manager for Eclipse preferences storage.
     */
    public static void initialize(final IEclipseMemory eclipseMemory) {
        if (instance == null) {
            instance = new TemporaryMemoryResourceGroup(eclipseMemory);
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
     * @param eclipseMemory the manager for Eclipse preferences storage.
     */
    private TemporaryMemoryResourceGroup(final IEclipseMemory eclipseMemory) {
        this.eclipseMemory = eclipseMemory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isEmpty() {
        String resourceGroup = load();
        if ((resourceGroup == null) || resourceGroup.isEmpty() || resourceGroup.isBlank()) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String load() {
        return eclipseMemory.loadFromEclipsePreferences(KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void persist() {
        String resourceGroup = eclipseMemory.loadFromEclipsePreferences(KEY);
        eclipseMemory.saveOnEclipsePreferences(MemoryResourceGroup.KEY, resourceGroup);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final String resourceGroup) {
        eclipseMemory.saveOnEclipsePreferences(KEY, resourceGroup);
    }
}
