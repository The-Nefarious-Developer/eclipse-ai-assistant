package com.developer.nefarious.zjoule.memory;

public interface IEclipseMemory {
	
	public static final String PLUGIN_ID = "com.developer.nefarious.zjoule";
	
	public void saveOnEclipsePreferences(final String key, final String value);
	
	public String loadFromEclipsePreferences(final String key);

}
