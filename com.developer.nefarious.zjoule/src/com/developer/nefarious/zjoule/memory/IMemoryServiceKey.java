package com.developer.nefarious.zjoule.memory;

import com.developer.nefarious.zjoule.models.ServiceKey;

public interface IMemoryServiceKey {
	
	public static final String KEY = "service-key";
	
	void save(final ServiceKey serviceKey);
	
	ServiceKey load();

}
