package com.developer.nefarious.zjoule.plugin.core.functions;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import com.developer.nefarious.zjoule.plugin.chat.ChatOrchestrator;

public class PromptHandler extends BrowserFunction {

	public static PromptHandler create(final Browser browser, final String name) {
		return new PromptHandler(browser, name);
	}

	private ChatOrchestrator chatOrchestrator;

	public PromptHandler(final Browser browser, final String name) {
		super(browser, name);
		chatOrchestrator = new ChatOrchestrator();
	}

	@Override
	public Object function(final Object[] arguments) {
		String userPrompt = arguments[0].toString();
		return chatOrchestrator.getAnswer(userPrompt);
	}

}
