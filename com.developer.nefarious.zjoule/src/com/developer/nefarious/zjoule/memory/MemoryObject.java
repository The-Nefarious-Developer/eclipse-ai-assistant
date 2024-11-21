package com.developer.nefarious.zjoule.memory;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;

public abstract class MemoryObject implements IMemoryObject {
	
	protected String key;
	
	@Override
	public void save(String value) {
		IEclipsePreferences preferences = InstanceScope.INSTANCE.getNode(PLUGIN_ID);
        preferences.put(key, value);
        try {
            preferences.flush(); // Persist the data
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
	}

	@Override
	public String load() {
		IEclipsePreferences preferences = InstanceScope.INSTANCE.getNode(PLUGIN_ID);
		return preferences.get(key, null);
	}
}
