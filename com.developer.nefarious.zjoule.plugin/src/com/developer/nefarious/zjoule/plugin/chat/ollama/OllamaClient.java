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

public class OllamaClient implements IAIClient {
	
	private HttpClient httpClient;
	
	private IMemoryMessageHistory memoryMessageHistory;
	
	private IMemoryObject<String> memoryOllamaEndpoint;
	
	private IOllamaClientHelper helper;
	
	public OllamaClient(
			final IMemoryMessageHistory memoryMessageHistory,
			final IMemoryObject<String> memoryOllamaEndpoint,
			final IOllamaClientHelper ollamaClientHelper) {
		this.httpClient = HttpClient.newHttpClient();
		this.memoryMessageHistory = memoryMessageHistory;
		this.memoryOllamaEndpoint = memoryOllamaEndpoint;
		helper = ollamaClientHelper;
	}

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

	@Override
	public IChatMessage createMessage(final Role role, final String userPrompt) {
		return new OllamaChatMessage(role, userPrompt);
	}

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

	@Override
	public void setMessageHistory(final List<IChatMessage> chatMessages) {
		MessageHistory newMessageHistory = new MessageHistory();
        newMessageHistory.setMessages(chatMessages.stream().map(
        		chatMessage -> new Message(chatMessage.getRole(), chatMessage.getMessage()))
                        .collect(Collectors.toList()));
        memoryMessageHistory.save(newMessageHistory);
	}
	
	private URI createChatEndpoint() {
        String endpoint = memoryOllamaEndpoint.load();
        String endpointInStringFormat = endpoint + "/api/chat";
        return URI.create(endpointInStringFormat);
    }

}
