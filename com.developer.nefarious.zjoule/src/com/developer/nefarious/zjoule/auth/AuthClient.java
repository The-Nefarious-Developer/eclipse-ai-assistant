package com.developer.nefarious.zjoule.auth;

import com.developer.nefarious.zjoule.memory.MemoryAccessToken;

public class AuthClient implements IAuthClient {

	MemoryAccessToken memoryAccessToken;
	
	public AuthClient() {
		memoryAccessToken = new MemoryAccessToken();
	}
	
	public AuthClient(MemoryAccessToken memoryAccessToken) {
		this.memoryAccessToken = memoryAccessToken;
	}

	@Override
	public String getAccessToken() {
		AccessToken lastTokenResponse = memoryAccessToken.load();
		return lastTokenResponse.getAccessToken();
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
