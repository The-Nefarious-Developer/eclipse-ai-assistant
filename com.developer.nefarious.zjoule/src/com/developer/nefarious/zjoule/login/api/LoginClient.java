package com.developer.nefarious.zjoule.login.api;

import java.net.http.HttpClient;

public class LoginClient implements ILoginClient {
	
	private HttpClient httpClient;
	
	public LoginClient() {
		httpClient = HttpClient.newHttpClient();
	}

	@Override
	public GetResourceGroupsResponse getResourceGroups() {
		// TODO Auto-generated method stub
		return null;
	}

}
