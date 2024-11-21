package com.developer.nefarious.zjoule.auth;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class AuthClient implements IAuthClient {

	@Override
	public String getAccessToken() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public String getAccessToken(ServiceKey serviceKey) {
//		
//		HttpClient httpClient = HttpClient.newHttpClient();
//		
//		String requestBody = "grant_type=client_credentials" +
//                "&client_id=" + URLEncoder.encode(serviceKey.getClientId(), StandardCharsets.UTF_8) +
//                "&client_secret=" + URLEncoder.encode(serviceKey.getClientSecret(), StandardCharsets.UTF_8);
//		
//		HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create(serviceKey.getTokenURL()))
//				.header("Content-Type", "application/x-www-form-urlencoded")
//				.POST(BodyPublishers.ofString(requestBody))
//				.build();
//		
//		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//	}
	
}
