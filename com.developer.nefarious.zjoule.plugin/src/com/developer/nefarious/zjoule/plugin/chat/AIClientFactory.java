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

/**
 * A factory class responsible for creating instances of {@link IAIClient}.
 * The {@code AIClientFactory} determines the appropriate AI client to return
 * based on the deployment's model name. Currently, it supports creating clients
 * for OpenAI models.
 */
public abstract class AIClientFactory {

    /**
     * Creates and returns an appropriate implementation of {@link IAIClient} based on the
     * deployment's model name. If the model corresponds to OpenAI, an {@link OpenAIClient} is returned.
     * If no supported model is found, the method returns {@code null}.
     *
     * @return an instance of {@link IAIClient} for the corresponding model, or {@code null} if unsupported.
     */
    public static IAIClient getClient() {

        // Load memory components for access token, service key, resource group, deployment, and message history.
        MemoryAccessToken memoryAccessToken = MemoryAccessToken.getInstance();
        MemoryServiceKey memoryServiceKey = MemoryServiceKey.getInstance();
        MemoryResourceGroup memoryResourceGroup = MemoryResourceGroup.getInstance();
        MemoryDeployment memoryDeployment = MemoryDeployment.getInstance();
        MemoryMessageHistory memoryMessageHistory = MemoryMessageHistory.getInstance();

        // Initialize authentication helpers and the authentication client.
        AuthClientHelper authHelper = new AuthClientHelper();
        AuthClient authClient = new AuthClient(memoryAccessToken, memoryServiceKey, authHelper);

        // Load the deployment details.
        Deployment deployment = memoryDeployment.load();

        // Determine if the model corresponds to OpenAI and create the appropriate client.
        if (isOpenAI(deployment.getModelName())) {
            OpenAIClientHelper aiClientHelper = new OpenAIClientHelper();
            return new OpenAIClient(authClient, memoryMessageHistory, memoryResourceGroup, memoryDeployment, aiClientHelper);
        } else {
            return null;
        }
    }

    /**
     * Checks if the given model name corresponds to an OpenAI model.
     *
     * @param modelName the name of the model to check.
     * @return {@code true} if the model is an OpenAI model, {@code false} otherwise.
     */
    private static Boolean isOpenAI(final String modelName) {
        Set<String> openAIModels = Set.of("gpt-4-32k", "gpt-4", "gpt-35-turbo", "gpt-35-turbo-16k");
        return openAIModels.contains(modelName);
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private AIClientFactory() { }
}
