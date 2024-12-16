package com.developer.nefarious.zjoule.plugin.login.api;

import java.net.URI;
import com.google.gson.Gson;

public class LoginClientHelper implements ILoginClientHelper {
	
	private Gson gson;
	
	public LoginClientHelper() {
		gson = new Gson();
	}

	@Override
	public URI createAuthUri(final String tokenEndpoint) {
		return URI.create(tokenEndpoint);
	}

	@Override
	public GetResourceGroupsResponse parseResourceGroupsResponseToObject(final String responseBody) {
		return gson.fromJson(responseBody, GetResourceGroupsResponse.class);
	}
	
	@Override
	public GetDeploymentsResponse parseDeploymentsResponseToObject(final String responseBody) {
		return gson.fromJson(responseBody, GetDeploymentsResponse.class);
	}

}
