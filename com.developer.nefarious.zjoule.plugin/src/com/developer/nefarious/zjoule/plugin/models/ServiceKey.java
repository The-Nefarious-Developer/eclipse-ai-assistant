package com.developer.nefarious.zjoule.plugin.models;

import com.google.gson.annotations.SerializedName;

public class ServiceKey {

	@SerializedName("serviceurls")
	private ServiceUrls serviceUrl;

	@SerializedName("clientid")
	private String clientId;

	@SerializedName("clientsecret")
	private String clientSecret;

	@SerializedName("url")
	private String tokenUrl;

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public String getServiceURL() {
		return serviceUrl.getApiUrl() + "/v2";
	}

	public String getTokenURL() {
		return tokenUrl + "/oauth/token";
	}

	public Boolean isValid() {
		return serviceUrl != null && serviceUrl.getApiUrl() != null && !serviceUrl.getApiUrl().isEmpty()
				&& clientId != null && !clientId.isEmpty() && clientSecret != null && !clientSecret.isEmpty()
				&& tokenUrl != null && !tokenUrl.isEmpty();
	}

	public void setClientId(final String clientId) {
		this.clientId = clientId;
	}

	public void setClientSecret(final String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setTokenUrl(final String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}

}

class ServiceUrls {

	@SerializedName("AI_API_URL")
	private String apiUrl;

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(final String AI_API_URL) {
		this.apiUrl = AI_API_URL;
	}

}