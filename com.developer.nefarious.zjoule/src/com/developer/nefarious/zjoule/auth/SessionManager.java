package com.developer.nefarious.zjoule.auth;

import org.eclipse.swt.browser.Browser;
import com.developer.nefarious.zjoule.core.functions.TagHandler;
import com.developer.nefarious.zjoule.memory.EclipseMemory;
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
	
	public static void logout(final Browser browser, final EclipseMemory eclipseMemory) {
		eclipseMemory.clearAll();
		if (browser != null && !browser.isDisposed()) {
			browser.execute("logout();");
		}
	}
	
	public static void login(final Browser browser) {
		browser.execute("login();");
		TagHandler.update(browser);
	}

}
