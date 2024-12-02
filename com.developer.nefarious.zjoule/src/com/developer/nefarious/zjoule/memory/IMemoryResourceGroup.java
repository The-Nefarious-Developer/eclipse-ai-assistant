package com.developer.nefarious.zjoule.memory;

import com.developer.nefarious.zjoule.models.ResourceGroup;

public interface IMemoryResourceGroup {
	
	public static final String KEY = "resource-group";
	
	void save(ResourceGroup resourceGroup);
	
	ResourceGroup load();

}
