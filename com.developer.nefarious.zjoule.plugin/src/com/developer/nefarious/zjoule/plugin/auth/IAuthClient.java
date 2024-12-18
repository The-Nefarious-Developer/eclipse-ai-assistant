package com.developer.nefarious.zjoule.plugin.auth;

import java.io.IOException;

import com.developer.nefarious.zjoule.plugin.models.ServiceKey;

public interface IAuthClient {

	public String getAccessToken() throws IOException, InterruptedException;

	public String getNewAccessToken() throws IOException, InterruptedException;

	public String getNewAccessToken(final ServiceKey serviceKey) throws IOException, InterruptedException;

	public String getServiceUrl();

}
