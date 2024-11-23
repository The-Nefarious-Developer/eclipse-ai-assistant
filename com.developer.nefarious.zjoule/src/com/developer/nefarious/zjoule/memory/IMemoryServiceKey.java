package com.developer.nefarious.zjoule.memory;

import com.developer.nefarious.zjoule.login.ServiceKey;

public interface IMemoryServiceKey {
	
	public static final String KEY = "serviceKey";
	
	void save(ServiceKey serviceKey);
	
	ServiceKey load();

}
