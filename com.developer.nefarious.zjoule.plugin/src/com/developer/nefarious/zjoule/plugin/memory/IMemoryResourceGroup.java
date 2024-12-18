package com.developer.nefarious.zjoule.plugin.memory;

public interface IMemoryResourceGroup {

	public static final String KEY = "resource-group";

	Boolean isEmpty();

	String load();

	void save(final String resourceGroup);

}
