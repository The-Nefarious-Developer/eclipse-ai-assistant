package com.developer.nefarious.zjoule.plugin.auth;

import org.eclipse.swt.browser.Browser;
import com.developer.nefarious.zjoule.plugin.core.functions.TagHandler;
import com.developer.nefarious.zjoule.plugin.memory.EclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.plugin.memory.MemoryDeployment;
import com.developer.nefarious.zjoule.plugin.memory.MemoryResourceGroup;
import com.developer.nefarious.zjoule.plugin.memory.MemoryServiceKey;

public abstract class SessionManager {
	
	private SessionManager() { }

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
