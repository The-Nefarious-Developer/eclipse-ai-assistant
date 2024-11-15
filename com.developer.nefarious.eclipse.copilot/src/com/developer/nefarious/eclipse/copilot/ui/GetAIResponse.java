package com.developer.nefarious.eclipse.copilot.ui;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

public class GetAIResponse extends BrowserFunction {

	public GetAIResponse(Browser browser, String name) {
		super(browser, name);
	}
	
	public static GetAIResponse create(Browser browser, String name) {
		return new GetAIResponse(browser, name);
	}

	@Override
	public Object function(Object[] arguments) {
		return getName() + " executed!";
	}

}
