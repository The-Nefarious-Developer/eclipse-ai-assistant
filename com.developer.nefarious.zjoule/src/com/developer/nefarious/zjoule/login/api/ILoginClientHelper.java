package com.developer.nefarious.zjoule.login.api;

import java.net.URI;

public interface ILoginClientHelper {
	
	URI createAuthUri(final String tokenEndpoint);
	
	GetResourceGroupsResponse parseResourceGroupsResponseToObject(final String responseBody);
	
	GetDeploymentsResponse parseDeploymentsResponseToObject(final String responseBody);

}
