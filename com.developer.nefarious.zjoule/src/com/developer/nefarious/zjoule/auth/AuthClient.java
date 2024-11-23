package com.developer.nefarious.zjoule.auth;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;

import com.developer.nefarious.zjoule.login.ServiceKey;
import com.developer.nefarious.zjoule.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.memory.MemoryServiceKey;

public class AuthClient implements IAuthClient {

	private MemoryAccessToken memoryAccessToken;
	private MemoryServiceKey memoryServiceKey;
	private AuthClientHelper authClientHelper;

	public AuthClient() {
		memoryAccessToken = new MemoryAccessToken();
		memoryServiceKey = new MemoryServiceKey();
		authClientHelper = new AuthClientHelper();
	}

	public AuthClient(MemoryAccessToken memoryAccessToken, MemoryServiceKey memoryServiceKey, AuthClientHelper authClientHelper) {
		this.memoryAccessToken = memoryAccessToken;
		this.memoryServiceKey = memoryServiceKey;
		this.authClientHelper = authClientHelper;
	}

	@Override
	public String getAccessToken() throws IOException, InterruptedException {
		AccessToken lastTokenResponse = memoryAccessToken.load();
		if (lastTokenResponse.isValid()) {
			return lastTokenResponse.getAccessToken();
		} else {
			return getNewAccessToken();
		}
	}
	
	private String getNewAccessToken() throws IOException, InterruptedException {
		ServiceKey serviceKey = memoryServiceKey.load();

		HttpClient httpClient = HttpClient.newHttpClient();
		
		URI endpoint = authClientHelper.createAuthUri(serviceKey.getTokenURL());
		BodyPublisher requestBody = authClientHelper.createRequestBody(serviceKey.getClientId(), serviceKey.getClientSecret());

		HttpRequest request = HttpRequest.newBuilder()
			.uri(endpoint)
			.header("Content-Type", "application/x-www-form-urlencoded")
			.POST(requestBody)
			.build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		AccessToken newAccessToken = authClientHelper.convertResponseToObject(response.body());
		memoryAccessToken.save(newAccessToken);
		
		return newAccessToken.getAccessToken();
	}



}
