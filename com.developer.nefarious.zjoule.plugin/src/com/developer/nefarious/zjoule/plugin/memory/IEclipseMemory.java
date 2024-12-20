package com.developer.nefarious.zjoule.plugin.memory;

import com.developer.nefarious.zjoule.plugin.core.Activator;

/**
 * Interface for managing interactions with Eclipse's preference storage system.
 * <p>
 * The {@code IEclipseMemory} interface defines methods for saving, loading, deleting, and clearing
 * key-value pairs in Eclipse's preferences.
 */
public interface IEclipseMemory {

	/** The plugin ID used to identify the preferences node in Eclipse's preference storage. */
	static final String PLUGIN_ID = Activator.PLUGIN_ID;

    /**
     * Clears all key-value pairs from Eclipse's preferences storage.
     * <p>
     * This method removes all data from the preferences node associated with the plugin and
     * flushes the changes to ensure they are persisted.
     */
	void clearAll();

    /**
     * Deletes the specified key-value pair from Eclipse's preferences storage.
     * <p>
     * This method removes the value associated with the given key and flushes the changes to
     * ensure they are persisted.
     *
     * @param key the key of the preference to remove.
     */
	void deleteFromEclipsePreferences(String key);

    /**
     * Loads the value associated with the specified key from Eclipse's preferences storage.
     *
     * @param key the key of the preference to load.
     * @return the value associated with the given key, or {@code null} if the key does not exist.
     */
	String loadFromEclipsePreferences(final String key);

    /**
     * Saves a key-value pair to Eclipse's preferences storage.
     * <p>
     * This method associates the given key with the specified value and flushes the changes
     * to ensure they are persisted.
     *
     * @param key the key of the preference to save.
     * @param value the value to associate with the given key.
     */
	void saveOnEclipsePreferences(final String key, final String value);

}
