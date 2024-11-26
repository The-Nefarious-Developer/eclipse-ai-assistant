package com.developer.nefarious.zjoule.login.api;

import java.net.URI;

public interface ILoginClientHelper {
	
	URI createAuthUri(String tokenEndpoint);
	
	GetResourceGroupsResponse convertResponseToObject(String responseBody);

}
