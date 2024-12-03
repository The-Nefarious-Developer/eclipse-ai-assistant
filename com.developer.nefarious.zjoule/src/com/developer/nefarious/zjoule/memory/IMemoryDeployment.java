package com.developer.nefarious.zjoule.memory;

public interface IMemoryDeployment {
	
	public static final String KEY = "deployment";
	
	void save(String deployment);
	
	String load();

}
