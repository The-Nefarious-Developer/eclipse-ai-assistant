package com.developer.nefarious.eclipse.copilot.ui;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

public class IViewFunction extends BrowserFunction {

	private Runnable function;

	public IViewFunction(Browser browser, String name, Runnable function) {
		super(browser, name);
		this.function = function;
	}

	@Override
	public Object function(Object[] arguments) {
		function.run();
		return getName() + " executed!";
	}

}
