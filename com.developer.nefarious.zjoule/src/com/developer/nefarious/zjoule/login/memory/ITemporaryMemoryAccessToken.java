package com.developer.nefarious.zjoule.login.memory;

import com.developer.nefarious.zjoule.auth.AccessToken;

public interface ITemporaryMemoryAccessToken {
	
	public void persist(AccessToken temporaryAccessToken);

}
