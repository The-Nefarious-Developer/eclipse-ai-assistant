package com.developer.nefarious.zjoule.chat.openai;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublisher;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.developer.nefarious.zjoule.auth.IAuthClient;
import com.developer.nefarious.zjoule.chat.IAIClient;
import com.developer.nefarious.zjoule.chat.IChatMessage;
import com.developer.nefarious.zjoule.chat.memory.IMemoryMessageHistory;
import com.developer.nefarious.zjoule.chat.models.Message;
import com.developer.nefarious.zjoule.chat.models.MessageHistory;
import com.developer.nefarious.zjoule.memory.IMemoryDeployment;
import com.developer.nefarious.zjoule.memory.IMemoryResourceGroup;
import com.developer.nefarious.zjoule.models.Deployment;
import com.developer.nefarious.zjoule.models.Role;

public class OpenAIClient implements IAIClient {

	private HttpClient httpClient;

	private IAuthClient auth;

	private IMemoryMessageHistory memoryMessageHistory;

	private IMemoryResourceGroup memoryResourceGroup;

	private IMemoryDeployment memoryDeployment;

	private IOpenAIClientHelper helper;

	// @formatter:off
	public OpenAIClient(
			final IAuthClient authClient, 
			final IMemoryMessageHistory memoryMessageHistory,
			final IMemoryResourceGroup memoryResourceGroup,
			final IMemoryDeployment memoryDeployment,
			final IOpenAIClientHelper openAIClientHelper) {
		// @formatter:on
		this.httpClient = HttpClient.newHttpClient();
		auth = authClient;
		this.memoryMessageHistory = memoryMessageHistory;
		this.memoryResourceGroup = memoryResourceGroup;
		this.memoryDeployment = memoryDeployment;
		helper = openAIClientHelper;
	}

	@Override
	public IChatMessage chatCompletion(final List<IChatMessage> messages) throws IOException, InterruptedException {
		URI endpoint = createChatEndpoint();

		String token = auth.getAccessToken();
		String resourceGroup = memoryResourceGroup.load();

		BodyPublisher requestBody = helper.createRequestBody(messages);

		// @formatter:off
		HttpRequest request = HttpRequest.newBuilder()
			.uri(endpoint)
			.header("Authorization", "Bearer " + token)
			.header("AI-Resource-Group", resourceGroup)
			.POST(requestBody)
			.build();
		// @formatter:on

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		return helper.convertResponseToObject(response.body());
	}

	private URI createChatEndpoint() {
		String serviceUrl = auth.getServiceUrl();
		Deployment deployment = memoryDeployment.load();
		String endpointInStringFormat = serviceUrl + "/inference/deployments/" + deployment.getId()
				+ "/chat/completions?api-version=2023-05-15";
		return URI.create(endpointInStringFormat);
	}

	@Override
	public OpenAIChatMessage createMessage(final Role role, final String userPrompt) {
		return new OpenAIChatMessage(role, userPrompt);
	}

	@Override
	public void setMessageHistory(final List<IChatMessage> chatMessages) {
		MessageHistory newMessageHistory = new MessageHistory();
		newMessageHistory.setMessages(
				chatMessages.stream().map(chatMessage -> new Message(chatMessage.getRole(), chatMessage.getMessage()))
						.collect(Collectors.toList()));
		memoryMessageHistory.save(newMessageHistory);
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

		// Map the messages and collect them
		return messages.stream().map(message -> 
			new OpenAIChatMessage(message.getRole(), message.getContent()))
				.collect(Collectors.toList());
	}

}
