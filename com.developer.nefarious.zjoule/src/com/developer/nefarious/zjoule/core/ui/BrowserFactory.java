package com.developer.nefarious.zjoule.core.ui;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;

public class BrowserFactory implements IBrowserFactory {

	@Override
	public Browser createBrowser(final Composite parent, final int style) {
		return new Browser(parent, style);
	}

}
