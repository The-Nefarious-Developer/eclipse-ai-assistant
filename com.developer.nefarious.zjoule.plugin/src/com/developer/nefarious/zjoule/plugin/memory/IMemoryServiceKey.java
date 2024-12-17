package com.developer.nefarious.zjoule.plugin.memory;

import com.developer.nefarious.zjoule.plugin.models.ServiceKey;

public interface IMemoryServiceKey {
	
	public static final String KEY = "service-key";
	
	void save(final ServiceKey serviceKey);
	
	ServiceKey load();
	
	Boolean isEmpty();

}
