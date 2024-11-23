package com.developer.nefarious.zjoule.auth;

import java.io.IOException;

public interface IAuthClient {
	
	public String getAccessToken() throws IOException, InterruptedException;

}
