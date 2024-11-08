package com.developer.nefarious.eclipse.copilot.ui;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;

public class BrowserFactory implements IBrowserFactory {

	@Override
	public Browser createBrowser(Composite parent, int style) {
		return new Browser(parent, style);
	}

}
