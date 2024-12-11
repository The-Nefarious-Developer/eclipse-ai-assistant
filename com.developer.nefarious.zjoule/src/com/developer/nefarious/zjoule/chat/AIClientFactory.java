package com.developer.nefarious.zjoule.chat;

import java.util.Set;
import com.developer.nefarious.zjoule.auth.AuthClient;
import com.developer.nefarious.zjoule.auth.AuthClientHelper;
import com.developer.nefarious.zjoule.chat.memory.MemoryMessageHistory;
import com.developer.nefarious.zjoule.chat.openai.OpenAIClient;
import com.developer.nefarious.zjoule.chat.openai.OpenAIClientHelper;
import com.developer.nefarious.zjoule.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.memory.MemoryDeployment;
import com.developer.nefarious.zjoule.memory.MemoryResourceGroup;
import com.developer.nefarious.zjoule.memory.MemoryServiceKey;
import com.developer.nefarious.zjoule.models.Deployment;

public class AIClientFactory {

	public static IAIClient getClient() {

		MemoryAccessToken memoryAccessToken = MemoryAccessToken.getInstance();
		MemoryServiceKey memoryServiceKey = MemoryServiceKey.getInstance();
		MemoryResourceGroup memoryResourceGroup = MemoryResourceGroup.getInstance();
		MemoryDeployment memoryDeployment = MemoryDeployment.getInstance();
		MemoryMessageHistory memoryMessageHistory = MemoryMessageHistory.getInstance();
		
		AuthClientHelper authHelper = new AuthClientHelper();
		AuthClient authClient = new AuthClient(memoryAccessToken, memoryServiceKey, authHelper);

		Deployment deployment = memoryDeployment.load();

		if (isOpenAI(deployment.getModelName())) {
			OpenAIClientHelper aiClientHelper = new OpenAIClientHelper();
			return new OpenAIClient(authClient, memoryMessageHistory, memoryResourceGroup, memoryDeployment, aiClientHelper);
		} else {
			return null;
		}

	}

	private static Boolean isOpenAI(final String modelName) {
		Set<String> openAIModels = Set.of("gpt-4-32k", "gpt-4", "gpt-35-turbo", "gpt-35-turbo-16k");
		return openAIModels.contains(modelName);
	}

}
