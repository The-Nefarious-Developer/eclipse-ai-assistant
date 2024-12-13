package com.developer.nefarious.zjoule.auth;

import com.developer.nefarious.zjoule.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.memory.MemoryDeployment;
import com.developer.nefarious.zjoule.memory.MemoryResourceGroup;
import com.developer.nefarious.zjoule.memory.MemoryServiceKey;

public abstract class SessionManager {

	public static boolean isUserLoggedIn() {
		MemoryAccessToken memoryAccessToken = MemoryAccessToken.getInstance();
		MemoryServiceKey memoryServiceKey = MemoryServiceKey.getInstance();
		MemoryResourceGroup memoryResourceGroup = MemoryResourceGroup.getInstance();
		MemoryDeployment memoryDeployment = MemoryDeployment.getInstance();

		return (memoryAccessToken.isEmpty() || memoryServiceKey.isEmpty() || memoryResourceGroup.isEmpty()
				|| memoryDeployment.isEmpty()) ? false : true;
	}

}
