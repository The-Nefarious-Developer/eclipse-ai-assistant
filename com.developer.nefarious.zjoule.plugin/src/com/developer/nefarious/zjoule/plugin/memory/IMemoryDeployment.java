package com.developer.nefarious.zjoule.plugin.memory;

import com.developer.nefarious.zjoule.plugin.models.Deployment;

public interface IMemoryDeployment {
	
	public static final String KEY = "deployment";
	
	void save(final Deployment deployment);
	
	Deployment load();
	
	Boolean isEmpty();

}
