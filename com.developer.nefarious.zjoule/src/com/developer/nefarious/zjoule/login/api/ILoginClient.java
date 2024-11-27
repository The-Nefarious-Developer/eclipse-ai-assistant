package com.developer.nefarious.zjoule.login.api;

import java.io.IOException;
import com.developer.nefarious.zjoule.auth.ServiceKey;

public interface ILoginClient {
	
	GetResourceGroupsResponse getResourceGroups(final ServiceKey serviceKey) throws IOException, InterruptedException;
	
	GetDeploymentsResponse getDeployments(final ServiceKey serviceKey, final String resourceGroup) throws IOException, InterruptedException;
	
}
