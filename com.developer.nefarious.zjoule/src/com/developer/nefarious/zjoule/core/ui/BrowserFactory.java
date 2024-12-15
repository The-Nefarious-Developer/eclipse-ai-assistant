package com.developer.nefarious.zjoule.core.ui;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;

public abstract class BrowserFactory {

	public static Browser create(final Composite parent, final int style) {
		return new Browser(parent, style);
	}

}
