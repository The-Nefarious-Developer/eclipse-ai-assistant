package com.developer.nefarious.zjoule.login.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.developer.nefarious.zjoule.auth.IAuthClient;
import com.developer.nefarious.zjoule.auth.ServiceKey;

public class LoginClient implements ILoginClient {
	
	private HttpClient httpClient;
	
	private ILoginClientHelper loginClientHelper;
	
	private IAuthClient authClient;
	
	public LoginClient(final ILoginClientHelper loginClientHelper, final IAuthClient authClient) {
		httpClient = HttpClient.newHttpClient();
		this.loginClientHelper = loginClientHelper;
		this.authClient = authClient;
	}

	@Override
	public GetResourceGroupsResponse getResourceGroups(final ServiceKey serviceKey) throws IOException, InterruptedException {
		URI endpoint = loginClientHelper.createAuthUri(serviceKey.getTokenURL());
		
		// @formatter:off
		HttpRequest request = HttpRequest.newBuilder()
				.uri(endpoint)
				.header("Authorization", "Bearer " + authClient.getNewAccessToken(serviceKey))
				.GET()
				.build();
		// @formatter:on
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		GetResourceGroupsResponse getResourceGroupsResponse = loginClientHelper.convertResponseToObject(response.body());
		return getResourceGroupsResponse;
	}

}
