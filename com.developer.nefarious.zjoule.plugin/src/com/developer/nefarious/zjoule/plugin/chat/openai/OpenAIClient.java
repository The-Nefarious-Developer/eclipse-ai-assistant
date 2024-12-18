package com.developer.nefarious.zjoule.plugin.chat.openai;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.developer.nefarious.zjoule.plugin.auth.IAuthClient;
import com.developer.nefarious.zjoule.plugin.chat.IAIClient;
import com.developer.nefarious.zjoule.plugin.chat.IChatMessage;
import com.developer.nefarious.zjoule.plugin.chat.memory.IMemoryMessageHistory;
import com.developer.nefarious.zjoule.plugin.chat.models.Message;
import com.developer.nefarious.zjoule.plugin.chat.models.MessageHistory;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryDeployment;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryResourceGroup;
import com.developer.nefarious.zjoule.plugin.models.Deployment;
import com.developer.nefarious.zjoule.plugin.models.Role;

/**
 * Implements the {@link IAIClient} interface for interacting with OpenAI-based models.
 * The {@code OpenAIClient} handles chat completion requests, message creation, 
 * and management of chat message history.
 */
public class OpenAIClient implements IAIClient {

    /** The HTTP client used for sending requests to the OpenAI service. */
    private HttpClient httpClient;

    /** The authentication client used to retrieve access tokens and service URLs. */
    private IAuthClient auth;

    /** The memory component for persisting chat message history. */
    private IMemoryMessageHistory memoryMessageHistory;

    /** The memory component for managing resource groups. */
    private IMemoryResourceGroup memoryResourceGroup;

    /** The memory component for storing deployment details. */
    private IMemoryDeployment memoryDeployment;

    /** Helper class for constructing requests and processing responses for OpenAI. */
    private IOpenAIClientHelper helper;

    /**
     * Constructs a new {@code OpenAIClient} with the required dependencies.
     *
     * @param authClient the authentication client for retrieving tokens and service URLs.
     * @param memoryMessageHistory the memory component for managing chat history.
     * @param memoryResourceGroup the memory component for resource group information.
     * @param memoryDeployment the memory component for deployment details.
     * @param openAIClientHelper the helper for constructing requests and processing responses.
     */
    public OpenAIClient(
            final IAuthClient authClient,
            final IMemoryMessageHistory memoryMessageHistory,
            final IMemoryResourceGroup memoryResourceGroup,
            final IMemoryDeployment memoryDeployment,
            final IOpenAIClientHelper openAIClientHelper) {
        this.httpClient = HttpClient.newHttpClient();
        auth = authClient;
        this.memoryMessageHistory = memoryMessageHistory;
        this.memoryResourceGroup = memoryResourceGroup;
        this.memoryDeployment = memoryDeployment;
        helper = openAIClientHelper;
    }

    /**
     * Sends a chat completion request to the OpenAI service and retrieves the AI-generated response.
     *
     * @param messages the list of {@link IChatMessage} objects representing the chat context.
     * @return the AI-generated response as an {@link IChatMessage}.
     * @throws IOException if an I/O error occurs during the request.
     * @throws InterruptedException if the operation is interrupted.
     */
    @Override
    public IChatMessage chatCompletion(final List<IChatMessage> messages) throws IOException, InterruptedException {
        URI endpoint = createChatEndpoint();

        String token = auth.getAccessToken();
        String resourceGroup = memoryResourceGroup.load();

        BodyPublisher requestBody = helper.createRequestBody(messages);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(endpoint)
            .header("Authorization", "Bearer " + token)
            .header("AI-Resource-Group", resourceGroup)
            .POST(requestBody)
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return helper.convertResponseToObject(response.body());
    }

    /**
     * Creates a URI for the OpenAI chat completion endpoint based on the current deployment.
     *
     * @return the constructed {@link URI}.
     */
    private URI createChatEndpoint() {
        String serviceUrl = auth.getServiceUrl();
        Deployment deployment = memoryDeployment.load();
        String endpointInStringFormat = serviceUrl + "/inference/deployments/" + deployment.getId()
                + "/chat/completions?api-version=2023-05-15";
        return URI.create(endpointInStringFormat);
    }

    /**
     * Creates a chat message with the specified role and content.
     *
     * @param role the role of the message (e.g., {@link Role#USER}, {@link Role#SYSTEM}).
     * @param userPrompt the content of the message.
     * @return a new {@link OpenAIChatMessage} instance.
     */
    @Override
    public OpenAIChatMessage createMessage(final Role role, final String userPrompt) {
        return new OpenAIChatMessage(role, userPrompt);
    }

    /**
     * Retrieves the chat message history stored in memory.
     *
     * @return a list of {@link IChatMessage} objects representing the chat history.
     */
    @Override
    public List<IChatMessage> getMessageHistory() {
        MessageHistory messageHistory = memoryMessageHistory.load();
        if (messageHistory == null) {
            return Collections.emptyList();
        }

        List<Message> messages = messageHistory.getMessages();
        if (messages == null || messages.isEmpty()) {
            return Collections.emptyList();
        }

        return messages.stream().map(message ->
            new OpenAIChatMessage(message.getRole(), message.getContent()))
                .collect(Collectors.toList());
    }

    /**
     * Stores the chat message history in memory.
     *
     * @param chatMessages the list of {@link IChatMessage} objects to save as chat history.
     */
    @Override
    public void setMessageHistory(final List<IChatMessage> chatMessages) {
        MessageHistory newMessageHistory = new MessageHistory();
        newMessageHistory.setMessages(
                chatMessages.stream().map(chatMessage -> new Message(chatMessage.getRole(), chatMessage.getMessage()))
                        .collect(Collectors.toList()));
        memoryMessageHistory.save(newMessageHistory);
    }

}
