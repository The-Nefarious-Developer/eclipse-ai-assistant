package com.developer.nefarious.zjoule.auth;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import com.developer.nefarious.zjoule.memory.IMemoryAccessToken;
import com.developer.nefarious.zjoule.memory.IMemoryServiceKey;

public class AuthClient implements IAuthClient {

	private HttpClient httpClient;

	private IMemoryAccessToken memoryAccessToken;

	private IMemoryServiceKey memoryServiceKey;

	private IAuthClientHelper authClientHelper;

	// @formatter:off
	public AuthClient(
			final IMemoryAccessToken memoryAccessToken, 
			final IMemoryServiceKey memoryServiceKey,
			final IAuthClientHelper authClientHelper) {
		// @formatter:on
		this.memoryAccessToken = memoryAccessToken;
		this.memoryServiceKey = memoryServiceKey;
		this.authClientHelper = authClientHelper;
		httpClient = HttpClient.newHttpClient();
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

	public String getNewAccessToken() throws IOException, InterruptedException {
		ServiceKey serviceKey = memoryServiceKey.load();
		return getNewAccessToken(serviceKey);
	}

	public String getNewAccessToken(final ServiceKey serviceKey) throws IOException, InterruptedException {
		URI endpoint = authClientHelper.createAuthUri(serviceKey.getTokenURL());
		BodyPublisher requestBody = authClientHelper.createRequestBody(serviceKey.getClientId(), serviceKey.getClientSecret());

		// @formatter:off
		HttpRequest request = HttpRequest.newBuilder()
				.uri(endpoint)
				.header("Content-Type", "application/x-www-form-urlencoded")
				.POST(requestBody)
				.build();
		// @formatter:on
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		AccessToken newAccessToken = authClientHelper.convertResponseToObject(response.body());
		memoryAccessToken.save(newAccessToken);

		return newAccessToken.getAccessToken();
	}

}
