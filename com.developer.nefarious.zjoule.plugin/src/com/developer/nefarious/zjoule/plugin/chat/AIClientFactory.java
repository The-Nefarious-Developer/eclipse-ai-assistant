package com.developer.nefarious.zjoule.plugin.chat;

import java.util.Set;

import com.developer.nefarious.zjoule.plugin.auth.AuthClient;
import com.developer.nefarious.zjoule.plugin.auth.AuthClientHelper;
import com.developer.nefarious.zjoule.plugin.chat.memory.MemoryMessageHistory;
import com.developer.nefarious.zjoule.plugin.chat.openai.OpenAIClient;
import com.developer.nefarious.zjoule.plugin.chat.openai.OpenAIClientHelper;
import com.developer.nefarious.zjoule.plugin.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.plugin.memory.MemoryDeployment;
import com.developer.nefarious.zjoule.plugin.memory.MemoryResourceGroup;
import com.developer.nefarious.zjoule.plugin.memory.MemoryServiceKey;
import com.developer.nefarious.zjoule.plugin.models.Deployment;

public abstract class AIClientFactory {

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

	private AIClientFactory() { }

}
