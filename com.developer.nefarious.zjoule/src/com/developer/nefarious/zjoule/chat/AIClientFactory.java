package com.developer.nefarious.zjoule.chat;

import java.net.http.HttpClient;

import com.developer.nefarious.zjoule.auth.AuthClient;
import com.developer.nefarious.zjoule.auth.AuthClientHelper;
import com.developer.nefarious.zjoule.chat.openai.OpenAIClient;
import com.developer.nefarious.zjoule.chat.openai.OpenAIClientHelper;
import com.developer.nefarious.zjoule.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.memory.MemoryDeployment;
import com.developer.nefarious.zjoule.memory.MemoryResourceGroup;
import com.developer.nefarious.zjoule.memory.MemoryServiceKey;

public class AIClientFactory {
	
	public static IAIClient<?> getClient() {
		
//		Deployment deployment = memoryDeployment.load();
		
//		switch (deployment.getModelName()) {
//		case "gpt-4-32k", "gpt-4", "gpt-35-turbo", "gpt-35-turbo-16k" -> new OpenAIClient();
//		default -> null;
//		}
		
		MemoryAccessToken memoryAccessToken = MemoryAccessToken.getInstance();
		MemoryServiceKey memoryServiceKey = MemoryServiceKey.getInstance();
		AuthClientHelper authHelper = new AuthClientHelper();
		AuthClient authClient = new AuthClient(memoryAccessToken, memoryServiceKey, authHelper);
		
		HttpClient httpClient = HttpClient.newHttpClient();
		MemoryResourceGroup memoryResourceGroup = MemoryResourceGroup.getInstance();
		MemoryDeployment memoryDeployment = MemoryDeployment.getInstance();
		OpenAIClientHelper aiClientHelper = new OpenAIClientHelper();
		
		return new OpenAIClient(authClient, httpClient, memoryResourceGroup, memoryDeployment, aiClientHelper);
		
	}

}
