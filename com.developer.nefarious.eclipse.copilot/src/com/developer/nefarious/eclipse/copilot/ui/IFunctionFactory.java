package com.developer.nefarious.eclipse.copilot.ui;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

public interface IFunctionFactory {
	
	BrowserFunction createFunction(Browser browser, String name);

}
