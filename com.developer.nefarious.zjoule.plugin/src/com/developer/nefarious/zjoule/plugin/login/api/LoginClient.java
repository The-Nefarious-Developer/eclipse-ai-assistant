package com.developer.nefarious.zjoule.plugin.login.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.developer.nefarious.zjoule.plugin.auth.IAuthClient;
import com.developer.nefarious.zjoule.plugin.models.ServiceKey;

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
		URI endpoint = loginClientHelper.createAuthUri(serviceKey.getServiceURL() + "/admin/resourceGroups");
		
		// @formatter:off
		HttpRequest request = HttpRequest.newBuilder()
				.uri(endpoint)
				.header("Authorization", "Bearer " + authClient.getNewAccessToken(serviceKey))
				.GET()
				.build();
		// @formatter:off
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		return loginClientHelper.parseResourceGroupsResponseToObject(response.body());
	}

	@Override
	public GetDeploymentsResponse getDeployments(final ServiceKey serviceKey, final String resourceGroup) throws IOException, InterruptedException {
		URI endpoint = loginClientHelper.createAuthUri(serviceKey.getServiceURL() + "/lm/deployments");
		
		// @formatter:off
		HttpRequest request = HttpRequest.newBuilder()
				.uri(endpoint)
				.header("Authorization", "Bearer " + authClient.getNewAccessToken(serviceKey))
				.header("AI-Resource-Group", resourceGroup)
				.GET()
				.build();
		// @formatter:on
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		return loginClientHelper.parseDeploymentsResponseToObject(response.body());
	}

}
