package com.developer.nefarious.zjoule.memory;

public interface IMemoryResourceGroup {
	
	public static final String KEY = "resource-group";
	
	void save(final String resourceGroup);
	
	String load();

}
