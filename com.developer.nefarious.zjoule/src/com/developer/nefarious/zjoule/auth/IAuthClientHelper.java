package com.developer.nefarious.zjoule.auth;

import java.net.URI;
import java.net.http.HttpRequest.BodyPublisher;
import com.developer.nefarious.zjoule.models.AccessToken;

public interface IAuthClientHelper {
	
	BodyPublisher createRequestBody(String clientId, String clientSecret);
	
	URI createAuthUri(String tokenEndpoint);
	
	AccessToken convertResponseToObject(String responseBody);

}
