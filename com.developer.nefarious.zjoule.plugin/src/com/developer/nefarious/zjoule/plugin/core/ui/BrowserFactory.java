package com.developer.nefarious.zjoule.plugin.core.ui;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;

public abstract class BrowserFactory {
	
	private BrowserFactory() { }

	public static Browser create(final Composite parent, final int style) {
		return new Browser(parent, style);
	}

}
