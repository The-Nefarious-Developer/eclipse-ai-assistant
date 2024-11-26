package com.developer.nefarious.zjoule.auth;

import java.io.IOException;

public interface IAuthClient {
	
	public String getAccessToken() throws IOException, InterruptedException;
	
	public String getNewAccessToken() throws IOException, InterruptedException;
	
	public String getNewAccessToken(ServiceKey serviceKey) throws IOException, InterruptedException;

}
