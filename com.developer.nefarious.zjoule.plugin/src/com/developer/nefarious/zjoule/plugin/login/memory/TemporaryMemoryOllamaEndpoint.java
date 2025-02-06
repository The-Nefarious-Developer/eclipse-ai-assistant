package com.developer.nefarious.zjoule.plugin.login.memory;

import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;
import com.developer.nefarious.zjoule.plugin.memory.MemoryOllamaEndpoint;

/**
 * A temporary memory storage for the Ollama endpoint used during the login process.
 * <p>
 * The {@code TemporaryMemoryOllamaEndpoint} stores the Ollama endpoint in Eclipse preferences
 * temporarily before persisting it permanently. It provides methods to load, save, clear,
 * and persist data. This class follows a singleton pattern and requires explicit initialization.
 * </p>
 */
public class TemporaryMemoryOllamaEndpoint implements IMemoryObject<String>, ITemporaryMemoryObject {

	/** Singleton instance of {@code TemporaryMemoryOllamaEndpoint}. */
    private static TemporaryMemoryOllamaEndpoint instance;

    /** The preference key used for temporarily storing the Ollama endpoint. */
    public static final String KEY = "tmp-" + MemoryOllamaEndpoint.KEY;

    /** The memory handler for interacting with Eclipse preferences. */
    private IEclipseMemory eclipseMemory;

    /**
     * Retrieves the singleton instance of {@code TemporaryMemoryOllamaEndpoint}.
     *
     * @return the singleton instance.
     * @throws IllegalStateException if the instance has not been initialized.
     */
    public static TemporaryMemoryOllamaEndpoint getInstance() {
        if (instance == null) {
            throw new IllegalStateException("TemporaryMemoryOllamaEndpoint not initialized. Call initialize() first.");
        }
        return instance;
    }

    /**
     * Initializes the singleton instance of {@code TemporaryMemoryOllamaEndpoint}.
     * <p>
     * This method must be called before accessing {@link #getInstance()}.
     * </p>
     *
     * @param eclipseMemory the {@link IEclipseMemory} instance to be used for preference storage.
     */
    public static void initialize(final IEclipseMemory eclipseMemory) {
        if (instance == null) {
            instance = new TemporaryMemoryOllamaEndpoint(eclipseMemory);
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
    private TemporaryMemoryOllamaEndpoint(final IEclipseMemory eclipseMemory) {
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
    public void persist() {
        String ollamaEndpoint = eclipseMemory.loadFromEclipsePreferences(KEY);
        eclipseMemory.saveOnEclipsePreferences(MemoryOllamaEndpoint.KEY, ollamaEndpoint);
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
