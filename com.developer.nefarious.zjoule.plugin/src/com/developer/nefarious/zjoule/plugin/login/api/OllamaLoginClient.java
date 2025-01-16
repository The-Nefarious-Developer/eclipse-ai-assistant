package com.developer.nefarious.zjoule.plugin.login.api;

import java.net.http.HttpClient;

public class OllamaLoginClient implements IOllamaLoginClient {
	
	private HttpClient httpClient;
	
	private IOllamaLoginClientHelper ollamaLoginClientHelper;
	
	public OllamaLoginClient(final IOllamaLoginClientHelper ollamaLoginClientHelper) {
		httpClient = HttpClient.newHttpClient();
		this.ollamaLoginClientHelper = ollamaLoginClientHelper;
	}

	@Override
	public GetOllamaModelsResponse getModels() {
		return null;
	}

}
