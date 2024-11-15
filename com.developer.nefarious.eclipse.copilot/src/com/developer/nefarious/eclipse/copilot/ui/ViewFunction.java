package com.developer.nefarious.eclipse.copilot.ui;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

public class ViewFunction extends BrowserFunction {

	public ViewFunction(Browser browser, String name) {
		super(browser, name);
	}

	@Override
	public Object function(Object[] arguments) {
		return getName() + " executed!";
	}

}
