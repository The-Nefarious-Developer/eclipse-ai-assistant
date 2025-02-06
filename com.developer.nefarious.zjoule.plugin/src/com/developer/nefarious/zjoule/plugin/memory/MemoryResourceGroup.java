package com.developer.nefarious.zjoule.plugin.memory;

/**
 * Manages the storage and retrieval of resource group information in memory.
 * <p>
 * The {@code MemoryResourceGroup} class provides methods to save, load, and check
 * the validity of resource group data stored in Eclipse preferences. It implements {@link IMemoryObject<String>}.
 */
public class MemoryResourceGroup implements IMemoryObject<String> {
	
    /** Key used for storing and retrieving the resource group information in memory. */
    public static final String KEY = "resource-group";

    /** Singleton instance of {@code MemoryResourceGroup}. */
    private static MemoryResourceGroup instance;

    /** Manages interactions with Eclipse's preferences storage. */
    private IEclipseMemory eclipseMemory;

    /**
     * Retrieves the singleton instance of {@code MemoryResourceGroup}.
     *
     * @return the singleton instance.
     * @throws IllegalStateException if the instance has not been initialized.
     */
    public static MemoryResourceGroup getInstance() {
        if (instance == null) {
            throw new IllegalStateException("MemoryResourceGroup not initialized. Call initialize() first.");
        }
        return instance;
    }

    /**
     * Initializes the {@code MemoryResourceGroup} singleton with the specified dependencies.
     *
     * @param eclipseMemory the manager for Eclipse preferences storage.
     */
    public static void initialize(final IEclipseMemory eclipseMemory) {
        if (instance == null) {
            instance = new MemoryResourceGroup(eclipseMemory);
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
    private MemoryResourceGroup(final IEclipseMemory eclipseMemory) {
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
    public void save(final String resourceGroup) {
        eclipseMemory.saveOnEclipsePreferences(KEY, resourceGroup);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
    	eclipseMemory.deleteFromEclipsePreferences(KEY);
    }
    
}