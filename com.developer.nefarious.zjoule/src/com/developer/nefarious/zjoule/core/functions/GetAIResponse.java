package com.developer.nefarious.zjoule.core.functions;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

public class GetAIResponse extends BrowserFunction {

	public GetAIResponse(final Browser browser, final String name) {
		super(browser, name);
	}
	
	public static GetAIResponse create(final Browser browser, final String name) {
		return new GetAIResponse(browser, name);
	}

	@Override
	public Object function(final Object[] arguments) {
		return getName() + " executed!";
	}

}
