package com.developer.nefarious.zjoule.core.functions;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import com.developer.nefarious.zjoule.chat.ChatOrchestrator;

public class PromptHandler extends BrowserFunction {
	
	private ChatOrchestrator chatOrchestrator;

	public PromptHandler(final Browser browser, final String name) {
		super(browser, name);
		chatOrchestrator = new ChatOrchestrator();
	}
	
	public static PromptHandler create(final Browser browser, final String name) {
		return new PromptHandler(browser, name);
	}

	@Override
	public Object function(final Object[] arguments) {
		String userPrompt = arguments[0].toString();
		String answer = chatOrchestrator.getAnswer(userPrompt);
		return answer;
	}

}
