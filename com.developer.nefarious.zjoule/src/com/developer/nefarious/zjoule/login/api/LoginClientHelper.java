package com.developer.nefarious.zjoule.login.api;

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
	public GetResourceGroupsResponse convertResponseToObject(final String responseBody) {
		return gson.fromJson(responseBody, GetResourceGroupsResponse.class);
	}

}
