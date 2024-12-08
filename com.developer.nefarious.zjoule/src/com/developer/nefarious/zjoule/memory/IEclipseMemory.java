package com.developer.nefarious.zjoule.memory;

import com.developer.nefarious.zjoule.core.Activator;

public interface IEclipseMemory {
	
	public static final String PLUGIN_ID = Activator.PLUGIN_ID;
	
	public void saveOnEclipsePreferences(final String key, final String value);
	
	public String loadFromEclipsePreferences(final String key);

}
