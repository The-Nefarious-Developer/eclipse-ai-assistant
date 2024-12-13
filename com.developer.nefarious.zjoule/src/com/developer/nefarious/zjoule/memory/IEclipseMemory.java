package com.developer.nefarious.zjoule.memory;

import com.developer.nefarious.zjoule.core.Activator;

public interface IEclipseMemory {
	
	static final String PLUGIN_ID = Activator.PLUGIN_ID;
	
	void saveOnEclipsePreferences(final String key, final String value);
	
	String loadFromEclipsePreferences(final String key);
	
	void deleteFromEclipsePreferences(String key);
	
	void clearAll();

}
