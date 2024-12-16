package com.developer.nefarious.zjoule.core.events;

import org.eclipse.swt.browser.Browser;
import com.developer.nefarious.zjoule.auth.SessionManager;
import com.developer.nefarious.zjoule.core.functions.MessageHistoryLoader;
import com.developer.nefarious.zjoule.core.functions.TagHandler;
import com.developer.nefarious.zjoule.memory.EclipseMemory;

public class Initialization implements Runnable {
	
	private Browser browser;
	
	public Initialization(final Browser browser) {
		this.browser = browser;
	}

	@Override
	public void run() {
		if (SessionManager.isUserLoggedIn()) {
			SessionManager.login(browser);
			TagHandler.update(browser);
		} else {
			SessionManager.logout(browser, new EclipseMemory());
		}
		MessageHistoryLoader.loadFromMemory(browser);
		browser.execute("updatePlaceholder();");
	}

}
