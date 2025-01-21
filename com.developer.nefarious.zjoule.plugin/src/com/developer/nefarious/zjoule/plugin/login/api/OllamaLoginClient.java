package com.developer.nefarious.zjoule.plugin.login.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OllamaLoginClient implements IOllamaLoginClient {
	
	private HttpClient httpClient;
	
	private IOllamaLoginClientHelper ollamaLoginClientHelper;
	
	public OllamaLoginClient(final IOllamaLoginClientHelper ollamaLoginClientHelper) {
		httpClient = HttpClient.newHttpClient();
		this.ollamaLoginClientHelper = ollamaLoginClientHelper;
	}

	@Override
	public GetOllamaModelsResponse getModels(final String endpoint) 
			throws IOException, InterruptedException {
		URI endpointUri = ollamaLoginClientHelper.createUri(endpoint + "/api/tags");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endpointUri)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return ollamaLoginClientHelper.parseOllamaModelsResponseToObject(response.body());
	}

}
