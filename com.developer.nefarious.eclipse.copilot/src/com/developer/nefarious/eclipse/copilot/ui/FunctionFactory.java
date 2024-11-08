package com.developer.nefarious.eclipse.copilot.ui;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

public class FunctionFactory implements IFunctionFactory {

	@Override
	public BrowserFunction createFunction(Browser browser, String name, Runnable function) {
		return new ViewFunction(browser, name, function);
	}

}
