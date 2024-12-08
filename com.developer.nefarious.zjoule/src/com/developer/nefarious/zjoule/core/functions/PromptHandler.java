package com.developer.nefarious.zjoule.core.functions;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import com.developer.nefarious.zjoule.auth.AuthClient;
import com.developer.nefarious.zjoule.auth.AuthClientHelper;
import com.developer.nefarious.zjoule.chat.openai.OpenAIClient;
import com.developer.nefarious.zjoule.chat.openai.OpenAIClientHelper;
import com.developer.nefarious.zjoule.chat.openai.OpenAIMessage;
import com.developer.nefarious.zjoule.memory.EclipseMemory;
import com.developer.nefarious.zjoule.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.memory.MemoryDeployment;
import com.developer.nefarious.zjoule.memory.MemoryResourceGroup;
import com.developer.nefarious.zjoule.memory.MemoryServiceKey;
import com.developer.nefarious.zjoule.memory.utils.ObjectSerializer;
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
		MemoryAccessToken.initialize(new ObjectSerializer(), new EclipseMemory());
		MemoryServiceKey.initialize(new ObjectSerializer(), new EclipseMemory());
		MemoryResourceGroup.initialize(new EclipseMemory());
		MemoryDeployment.initialize(new ObjectSerializer(), new EclipseMemory());
		
		MemoryAccessToken memoryAccessToken = MemoryAccessToken.getInstance();
		MemoryServiceKey memoryServiceKey = MemoryServiceKey.getInstance();
		AuthClientHelper authHelper = new AuthClientHelper();
		AuthClient authClient = new AuthClient(memoryAccessToken, memoryServiceKey, authHelper);
		
		HttpClient httpClient = HttpClient.newHttpClient();
		MemoryResourceGroup memoryResourceGroup = MemoryResourceGroup.getInstance();
		MemoryDeployment memoryDeployment = MemoryDeployment.getInstance();
		OpenAIClientHelper aiClientHelper = new OpenAIClientHelper();
		
		OpenAIClient aiClient = new OpenAIClient(authClient, httpClient, memoryResourceGroup, memoryDeployment, aiClientHelper);
		
		String userPrompt = arguments[0].toString();
		OpenAIMessage message = new OpenAIMessage(Role.USER, userPrompt);
		
		List<OpenAIMessage> messages = new ArrayList<>();
		messages.add(message);
		
		String answer;
		try {
			answer = aiClient.chatCompletion(messages);
		} catch (Exception e) {
			e.printStackTrace();
			return "Error during request execution";
		}
		
//		return getName() + " executed!";
		
//		return arguments[0].toString();
		
		return answer;
	}

}
