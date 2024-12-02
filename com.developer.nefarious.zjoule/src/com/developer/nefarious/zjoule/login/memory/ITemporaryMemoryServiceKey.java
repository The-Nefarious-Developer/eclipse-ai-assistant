package com.developer.nefarious.zjoule.login.memory;

import com.developer.nefarious.zjoule.auth.ServiceKey;

public interface ITemporaryMemoryServiceKey {
	
	public void persist(ServiceKey temporaryServiceKey);

}
