package com.developer.nefarious.zjoule.memory;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;

public interface IEclipseMemoryStorage {
	
	public static final String PLUGIN_ID = "com.developer.nefarious.zjoule";
	
	IEclipsePreferences getEclipsePreferences();

}
