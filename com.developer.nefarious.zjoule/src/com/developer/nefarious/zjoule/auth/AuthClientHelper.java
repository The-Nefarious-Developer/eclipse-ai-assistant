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
	public BodyPublisher createRequestBody(final String clientId, final String clientSecret) {
		// @formatter:off
		String requestBody = "grant_type=client_credentials" 
			+ "&client_id="	+ URLEncoder.encode(clientId, StandardCharsets.UTF_8) 
			+ "&client_secret="	+ URLEncoder.encode(clientSecret, StandardCharsets.UTF_8);
		// @formatter:on
		return BodyPublishers.ofString(requestBody);
	}

	@Override
	public URI createAuthUri(final String tokenEndpoint) {
		return URI.create(tokenEndpoint);
	}

	@Override
	public AccessToken convertResponseToObject(final String responseBody) {
		return gson.fromJson(responseBody, AccessToken.class);
	}

}
