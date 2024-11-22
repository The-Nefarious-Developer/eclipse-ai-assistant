package com.developer.nefarious.zjoule.memory;

public interface IMemoryObject {
	
	public void saveOnEclipsePreferences(String key, String value);
	
	public String loadFromEclipsePreferences(String key);

}
