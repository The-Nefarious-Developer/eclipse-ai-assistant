package com.developer.nefarious.zjoule.memory;

import com.developer.nefarious.zjoule.models.AccessToken;

public interface IMemoryAccessToken {
	
	public static final String KEY = "access-token";
	
	void save(AccessToken accessToken);
	
	AccessToken load();
	
}
