package com.developer.nefarious.zjoule.plugin.login.api;

import java.net.URI;

import com.google.gson.Gson;

public class OllamaLoginClientHelper implements IOllamaLoginClientHelper {
	
	private Gson gson;
	
	public OllamaLoginClientHelper() {
		gson = new Gson();
	}

	@Override
	public URI createUri(final String endpoint) {
		return URI.create(endpoint);
	}

	@Override
	public GetOllamaModelsResponse parseOllamaModelsResponseToObject(final String responseBody) {
		return gson.fromJson(responseBody, GetOllamaModelsResponse.class);
	}

}
