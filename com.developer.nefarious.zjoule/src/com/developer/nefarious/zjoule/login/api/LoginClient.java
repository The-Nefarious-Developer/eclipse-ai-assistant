package com.developer.nefarious.zjoule.login.api;

import java.net.http.HttpClient;
import java.util.ArrayList;

public class LoginClient implements ILoginClient{
	
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
