package com.developer.nefarious.zjoule.login.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.developer.nefarious.zjoule.auth.ServiceKey;

public class LoginClient implements ILoginClient {
	
	private HttpClient httpClient;
	
	private LoginClientHelper loginClientHelper;
	
	public LoginClient() {
		httpClient = HttpClient.newHttpClient();
		loginClientHelper = new LoginClientHelper();
	}

	@Override
	public GetResourceGroupsResponse getResourceGroups(final ServiceKey serviceKey) throws IOException, InterruptedException {
		URI endpoint = loginClientHelper.createAuthUri(serviceKey.getTokenURL());
		
		// @formatter:off
		HttpRequest request = HttpRequest.newBuilder()
				.uri(endpoint)
				.header("Content-Type", "application/x-www-form-urlencoded")
				.GET()
				.build();
		// @formatter:on
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		GetResourceGroupsResponse getResourceGroupsResponse = loginClientHelper.convertResponseToObject(response.body());
		return getResourceGroupsResponse;
	}

}
