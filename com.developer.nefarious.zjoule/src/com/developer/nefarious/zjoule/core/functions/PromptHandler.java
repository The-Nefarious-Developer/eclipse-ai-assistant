package com.developer.nefarious.zjoule.core.functions;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import com.developer.nefarious.zjoule.auth.AuthClient;
import com.developer.nefarious.zjoule.auth.AuthClientHelper;
import com.developer.nefarious.zjoule.chat.IChatMessage;
import com.developer.nefarious.zjoule.chat.memory.MemoryMessageHistory;
import com.developer.nefarious.zjoule.chat.openai.OpenAIClient;
import com.developer.nefarious.zjoule.chat.openai.OpenAIClientHelper;
import com.developer.nefarious.zjoule.chat.openai.OpenAIChatMessage;
import com.developer.nefarious.zjoule.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.memory.MemoryDeployment;
import com.developer.nefarious.zjoule.memory.MemoryResourceGroup;
import com.developer.nefarious.zjoule.memory.MemoryServiceKey;
import com.developer.nefarious.zjoule.models.Role;

public class PromptHandler extends BrowserFunction {

	public PromptHandler(final Browser browser, final String name) {
		super(browser, name);
	}
	
	public static PromptHandler create(final Browser browser, final String name) {
		return new PromptHandler(browser, name);
	}

	@Override
	public Object function(final Object[] arguments) {
		MemoryAccessToken memoryAccessToken = MemoryAccessToken.getInstance();
		MemoryServiceKey memoryServiceKey = MemoryServiceKey.getInstance();
		AuthClientHelper authHelper = new AuthClientHelper();
		AuthClient authClient = new AuthClient(memoryAccessToken, memoryServiceKey, authHelper);
		
		MemoryResourceGroup memoryResourceGroup = MemoryResourceGroup.getInstance();
		MemoryDeployment memoryDeployment = MemoryDeployment.getInstance();
		OpenAIClientHelper aiClientHelper = new OpenAIClientHelper();
		MemoryMessageHistory memoryMessageHistory = MemoryMessageHistory.getInstance();
		
		OpenAIClient aiClient = new OpenAIClient(authClient, memoryMessageHistory, memoryResourceGroup, memoryDeployment, aiClientHelper);
		
		String userPrompt = arguments[0].toString();
		OpenAIChatMessage message = new OpenAIChatMessage(Role.USER, userPrompt);
		
		List<OpenAIChatMessage> messages = new ArrayList<>();
		messages.add(message);
		
		IChatMessage answer;
		try {
			answer = aiClient.chatCompletion(messages);
		} catch (Exception e) {
			e.printStackTrace();
			return "Error during request execution";
		}
		
//		return getName() + " executed!";
		
//		return arguments[0].toString();
		
		return answer.getMessage();
	}

}
