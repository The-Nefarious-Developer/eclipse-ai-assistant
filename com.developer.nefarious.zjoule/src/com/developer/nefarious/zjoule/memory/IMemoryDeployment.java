package com.developer.nefarious.zjoule.memory;

import com.developer.nefarious.zjoule.models.Deployment;

public interface IMemoryDeployment {
	
	public static final String KEY = "deployment";
	
	void save(final Deployment deployment);
	
	Deployment load();

}
