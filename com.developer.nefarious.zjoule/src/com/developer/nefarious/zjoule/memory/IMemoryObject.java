package com.developer.nefarious.zjoule.memory;

public interface IMemoryObject {
	
	public static final String PLUGIN_ID = "com.developer.nefarious.zjoule";
	
	public void save(String value);
	
	public String load();

}
