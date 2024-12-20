package com.developer.nefarious.zjoule.plugin.memory;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;

/**
 * Manages interactions with Eclipse's preference storage system.
 * <p>
 * The {@code EclipseMemory} class provides methods to save, load, and delete key-value pairs
 * from Eclipse's preferences and to clear all stored preferences. It implements {@link IEclipseMemory}.
 */
public class EclipseMemory implements IEclipseMemory {

    /** The Eclipse preferences instance used for storing key-value data. */
    private IEclipsePreferences preferences;

    /**
     * Retrieves the global Eclipse preferences for the plugin.
     *
     * @return the {@link IEclipsePreferences} instance associated with the plugin.
     */
    public static IEclipsePreferences getEclipsePreferences() {
        return InstanceScope.INSTANCE.getNode(PLUGIN_ID);
    }

    /**
     * Constructs a new {@code EclipseMemory} instance.
     * <p>
     * Initializes the preferences field with the global Eclipse preferences for the plugin.
     */
    public EclipseMemory() {
        preferences = EclipseMemory.getEclipsePreferences();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearAll() {
        try {
            preferences.clear();
            preferences.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteFromEclipsePreferences(final String key) {
        try {
            preferences.remove(key);
            preferences.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String loadFromEclipsePreferences(final String key) {
        return preferences.get(key, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOnEclipsePreferences(final String key, final String value) {
        preferences.put(key, value);
        try {
            preferences.flush(); // Persist the data
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }
}
