package com.developer.nefarious.zjoule.plugin.memory;

/**
 * A singleton class for managing the persistence of the Ollama endpoint in Eclipse preferences.
 * <p>
 * The {@code MemoryOllamaEndpoint} class stores, retrieves, and clears the Ollama endpoint 
 * using the Eclipse preferences system. It follows a singleton pattern and must be initialized 
 * before use.
 * </p>
 */
public class MemoryOllamaEndpoint implements IMemoryObject<String> {
	
	/** The key used for storing the Ollama endpoint in Eclipse preferences. */
    public static final String KEY = "ollama-endpoint";

    /** Singleton instance of {@code MemoryOllamaEndpoint}. */
    private static MemoryOllamaEndpoint instance;

    /** The memory handler for interacting with Eclipse preferences. */
    private IEclipseMemory eclipseMemory;

    /**
     * Retrieves the singleton instance of {@code MemoryOllamaEndpoint}.
     *
     * @return the singleton instance.
     * @throws IllegalStateException if the instance has not been initialized.
     */
    public static MemoryOllamaEndpoint getInstance() {
        if (instance == null) {
            throw new IllegalStateException("MemoryOllamaEndpoint not initialized. Call initialize() first.");
        }
        return instance;
    }

    /**
     * Initializes the singleton instance of {@code MemoryOllamaEndpoint}.
     * <p>
     * This method must be called before accessing {@link #getInstance()}.
     * </p>
     *
     * @param eclipseMemory the {@link IEclipseMemory} instance to be used for preference storage.
     */
    public static void initialize(final IEclipseMemory eclipseMemory) {
        if (instance == null) {
            instance = new MemoryOllamaEndpoint(eclipseMemory);
        }
    }

    /**
     * Resets the singleton instance.
     * <p>
     * This method clears the instance reference, allowing re-initialization.
     * </p>
     */
    public static void resetInstance() {
        instance = null;
    }

    /**
     * Private constructor to enforce the singleton pattern.
     *
     * @param eclipseMemory the {@link IEclipseMemory} instance used for storing preferences.
     */
    private MemoryOllamaEndpoint(final IEclipseMemory eclipseMemory) {
        this.eclipseMemory = eclipseMemory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isEmpty() {
        String ollamaEndpoint = load();
        if ((ollamaEndpoint == null) || ollamaEndpoint.isEmpty() || ollamaEndpoint.isBlank()) {
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
    public void save(final String ollamaEndpoint) {
        eclipseMemory.saveOnEclipsePreferences(KEY, ollamaEndpoint);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
    	eclipseMemory.deleteFromEclipsePreferences(KEY);
    }

}
