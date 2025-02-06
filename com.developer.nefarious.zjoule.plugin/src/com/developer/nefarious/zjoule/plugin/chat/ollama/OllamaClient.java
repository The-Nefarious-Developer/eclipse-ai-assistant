package com.developer.nefarious.zjoule.plugin.chat.ollama;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.developer.nefarious.zjoule.plugin.chat.IAIClient;
import com.developer.nefarious.zjoule.plugin.chat.IChatMessage;
import com.developer.nefarious.zjoule.plugin.chat.memory.IMemoryMessageHistory;
import com.developer.nefarious.zjoule.plugin.chat.models.Message;
import com.developer.nefarious.zjoule.plugin.chat.models.MessageHistory;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;
import com.developer.nefarious.zjoule.plugin.models.Role;

/**
 * A client implementation for interacting with the Ollama AI chat system.
 * <p>
 * The {@code OllamaClient} facilitates chat interactions by sending requests
 * to an Ollama chat API endpoint and processing responses. It also manages
 * message history using an in-memory storage system.
 * </p>
 */
public class OllamaClient implements IAIClient {
    
    /** The HTTP client used for sending chat requests. */
    private HttpClient httpClient;

    /** Memory storage for maintaining chat message history. */
    private IMemoryMessageHistory memoryMessageHistory;

    /** Memory storage for retrieving the Ollama chat API endpoint. */
    private IMemoryObject<String> memoryOllamaEndpoint;

    /** Helper class for constructing request payloads and processing responses. */
    private IOllamaClientHelper helper;

    /**
     * Constructs an {@code OllamaClient} with the specified dependencies.
     *
     * @param memoryMessageHistory the in-memory storage for chat message history.
     * @param memoryOllamaEndpoint the in-memory storage containing the Ollama chat API endpoint.
     * @param ollamaClientHelper   the helper class for handling request and response transformations.
     */
    public OllamaClient(
            final IMemoryMessageHistory memoryMessageHistory,
            final IMemoryObject<String> memoryOllamaEndpoint,
            final IOllamaClientHelper ollamaClientHelper) {
        this.httpClient = HttpClient.newHttpClient();
        this.memoryMessageHistory = memoryMessageHistory;
        this.memoryOllamaEndpoint = memoryOllamaEndpoint;
        helper = ollamaClientHelper;
    }

    /**
     * Sends a chat completion request to the Ollama API.
     * <p>
     * This method constructs a request using the provided chat messages and sends
     * it to the configured API endpoint. It then processes the response and returns
     * the generated AI message.
     * </p>
     *
     * @param messages the list of chat messages forming the conversation history.
     * @return the AI-generated response as an {@link IChatMessage}.
     * @throws IOException          if an I/O error occurs when sending the request.
     * @throws InterruptedException if the request is interrupted while waiting for a response.
     */
    @Override
    public IChatMessage chatCompletion(final List<IChatMessage> messages) throws IOException, InterruptedException {
        URI endpoint = createChatEndpoint();
        BodyPublisher requestBody = helper.createRequestBody(messages);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endpoint)
                .POST(requestBody)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return helper.convertResponseToObject(response.body());
    }

    /**
     * Creates a new chat message with the specified role and user input.
     *
     * @param role       the role of the message (e.g., user or assistant).
     * @param userPrompt the message content.
     * @return an instance of {@link IChatMessage} representing the user input.
     */
    @Override
    public IChatMessage createMessage(final Role role, final String userPrompt) {
        return new OllamaChatMessage(role, userPrompt);
    }

    /**
     * Retrieves the chat message history stored in memory.
     *
     * @return a list of {@link IChatMessage} representing the chat history,
     *         or an empty list if no history is available.
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
                new OllamaChatMessage(message.getRole(), message.getContent()))
                .collect(Collectors.toList());
    }

    /**
     * Stores the given chat message history in memory.
     *
     * @param chatMessages the list of chat messages to save.
     */
    @Override
    public void setMessageHistory(final List<IChatMessage> chatMessages) {
        MessageHistory newMessageHistory = new MessageHistory();
        newMessageHistory.setMessages(chatMessages.stream().map(
                chatMessage -> new Message(chatMessage.getRole(), chatMessage.getMessage()))
                .collect(Collectors.toList()));
        memoryMessageHistory.save(newMessageHistory);
    }

    /**
     * Creates a URI representing the chat endpoint for sending requests.
     *
     * @return the {@link URI} of the chat API endpoint.
     */
    private URI createChatEndpoint() {
        String endpoint = memoryOllamaEndpoint.load();
        String endpointInStringFormat = endpoint + "/api/chat";
        return URI.create(endpointInStringFormat);
    }
}