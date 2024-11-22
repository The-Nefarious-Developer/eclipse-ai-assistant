package com.developer.nefarious.zjoule.memory;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.osgi.service.prefs.BackingStoreException;

public abstract class MemoryObject implements IMemoryObject {

	private IEclipsePreferences preferences;

	public MemoryObject() {
		EclipseMemoryStorage eclipseMemory = EclipseMemoryStorage.getInstance();
		preferences = eclipseMemory.getEclipsePreferences();
	}

	@Override
	public void saveOnEclipsePreferences(String key, String value) {
		preferences.put(key, value);
		try {
			preferences.flush(); // Persist the data
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String loadFromEclipsePreferences(String key) {
		return preferences.get(key, null);
	}
}
