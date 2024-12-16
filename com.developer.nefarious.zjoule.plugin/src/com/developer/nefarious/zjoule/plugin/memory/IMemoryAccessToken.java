package com.developer.nefarious.zjoule.plugin.memory;

import com.developer.nefarious.zjoule.plugin.models.AccessToken;

public interface IMemoryAccessToken {
	
	public static final String KEY = "access-token";
	
	void save(final AccessToken accessToken);
	
	AccessToken load();
	
	Boolean isEmpty();
	
}
