package com.developer.nefarious.zjoule.auth;

import com.developer.nefarious.zjoule.login.ServiceKey;

public class AuthClient implements IAuthClient {

	private ServiceKey serviceKey;
	
	public AuthClient() {
		serviceKey = new ServiceKey();
	}
	
	@Override
	public String getAccessToken() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ServiceKey getServiceKey() {
		return serviceKey;
	}
	
	public void setServiceKey(ServiceKey serviceKey) {
		this.serviceKey = serviceKey;
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
