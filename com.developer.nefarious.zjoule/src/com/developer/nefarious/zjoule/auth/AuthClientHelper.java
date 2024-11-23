package com.developer.nefarious.zjoule.auth;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

public class AuthClientHelper implements IAuthClientHelper {
	
	private Gson gson;
	
	public AuthClientHelper() {
		gson = new Gson();
	}

	@Override
	public BodyPublisher createRequestBody(String clientId, String clientSecret) {
		String requestBody = "grant_type=client_credentials" 
			+ "&client_id="	+ URLEncoder.encode(clientId, StandardCharsets.UTF_8) 
			+ "&client_secret="	+ URLEncoder.encode(clientSecret, StandardCharsets.UTF_8);
		return BodyPublishers.ofString(requestBody);
	}

	@Override
	public URI createAuthUri(String tokenEndpoint) {
		return URI.create(tokenEndpoint);
	}

	@Override
	public AccessToken convertResponseToObject(String responseBody) {
		return gson.fromJson(responseBody, AccessToken.class);
	}

}
