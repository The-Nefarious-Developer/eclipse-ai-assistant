package com.developer.nefarious.zjoule.auth;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;

import com.developer.nefarious.zjoule.login.ServiceKey;
import com.developer.nefarious.zjoule.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.memory.MemoryServiceKey;

public class AuthClient implements IAuthClient {

	private MemoryAccessToken memoryAccessToken;
	private MemoryServiceKey memoryServiceKey;

	public AuthClient() {
		memoryAccessToken = new MemoryAccessToken();
		memoryServiceKey = new MemoryServiceKey();
	}

	public AuthClient(MemoryAccessToken memoryAccessToken, MemoryServiceKey memoryServiceKey) {
		this.memoryAccessToken = memoryAccessToken;
		this.memoryServiceKey = memoryServiceKey;
	}

	@Override
	public String getAccessToken() {
		AccessToken lastTokenResponse = memoryAccessToken.load();
		if (lastTokenResponse.isValid()) {
			return lastTokenResponse.getAccessToken();
		} else {
			return getNewAccessToken();
		}
	}

	private String getNewAccessToken() {
		ServiceKey serviceKey = memoryServiceKey.load();

		HttpClient httpClient = HttpClient.newHttpClient();
		
		String requestBody = "grant_type=client_credentials" 
			+ "&client_id=" + URLEncoder.encode(serviceKey.getClientId(), StandardCharsets.UTF_8) 
			+ "&client_secret="	+ URLEncoder.encode(serviceKey.getClientSecret(), StandardCharsets.UTF_8);

		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create(serviceKey.getTokenURL()))
			.header("Content-Type", "application/x-www-form-urlencoded")
			.POST(BodyPublishers.ofString(requestBody))
			.build();

		return "";
	}

//	
//	private AccessToken accessToken;
//	
//	public AuthClient(ServiceKey serviceKey) {
//		this.serviceKey = serviceKey; 
//	}

//	@Override
//	public String getAccessToken() {
//		
//		HttpClient httpClient = HttpClient.newHttpClient();
//		
//		String requestBody = "grant_type=client_credentials" +
//            "&client_id=" + URLEncoder.encode(serviceKey.getClientId(), StandardCharsets.UTF_8) +
//            "&client_secret=" + URLEncoder.encode(serviceKey.getClientSecret(), StandardCharsets.UTF_8);
//		
//		HttpRequest request = HttpRequest.newBuilder()
//			.uri(URI.create(serviceKey.getTokenURL()))
//			.header("Content-Type", "application/x-www-form-urlencoded")
//			.POST(BodyPublishers.ofString(requestBody))
//			.build();
//		
//		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//		
//		Gson gson = new Gson();
//		accessToken = gson.fromJson(response.body(), AccessToken.class);
//		return accessToken.getAccessToken();
//	}

}
