package com.developer.nefarious.zjoule.plugin.auth;

import java.net.URI;
import java.net.http.HttpRequest.BodyPublisher;
import com.developer.nefarious.zjoule.plugin.models.AccessToken;

public interface IAuthClientHelper {
	
	BodyPublisher createRequestBody(final String clientId, final String clientSecret);
	
	URI convertEndpointStringToURI(final String tokenEndpoint);
	
	AccessToken convertResponseToObject(final String responseBody);

}
