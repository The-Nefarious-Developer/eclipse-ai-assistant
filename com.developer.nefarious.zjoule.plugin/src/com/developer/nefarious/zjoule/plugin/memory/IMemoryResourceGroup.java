package com.developer.nefarious.zjoule.plugin.memory;

public interface IMemoryResourceGroup {
	
	public static final String KEY = "resource-group";
	
	void save(final String resourceGroup);
	
	String load();
	
	Boolean isEmpty();

}
