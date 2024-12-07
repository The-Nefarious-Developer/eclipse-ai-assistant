package com.developer.nefarious.zjoule.chat.openai;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublisher;
import java.util.List;
import com.developer.nefarious.zjoule.auth.IAuthClient;
import com.developer.nefarious.zjoule.memory.IMemoryDeployment;
import com.developer.nefarious.zjoule.memory.IMemoryResourceGroup;

public class OpenAIClient implements IOpenIAIClient {

	private IAuthClient auth;

	private HttpClient httpClient;
	
	private IMemoryResourceGroup memoryResourceGroup;
	
	private IMemoryDeployment memoryDeployment;
	
	private IOpenAIClientHelper helper;

	// @formatter:off
	public OpenAIClient(
			final IAuthClient authClient, 
			final HttpClient httpClient,
			final IMemoryResourceGroup memoryResourceGroup,
			final IMemoryDeployment memoryDeployment,
			final IOpenAIClientHelper openAIClientHelper) {
		// @formatter:on
		auth = authClient;
		this.httpClient = httpClient;
		this.memoryResourceGroup = memoryResourceGroup;
		this.memoryDeployment = memoryDeployment;
		helper = openAIClientHelper;
	}

	@Override
	public String chatCompletion(final List<OpenAIMessage> messages) throws IOException, InterruptedException {
		String serviceUrl = auth.getServiceUrl();
		String deployment = memoryDeployment.load();
		URI endpoint = URI.create(serviceUrl + "/inference/deployments/" + deployment);
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

}
