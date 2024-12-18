package com.developer.nefarious.zjoule.plugin.login.api;

import java.io.IOException;

import com.developer.nefarious.zjoule.plugin.models.ServiceKey;

public interface ILoginClient {

	GetDeploymentsResponse getDeployments(final ServiceKey serviceKey, final String resourceGroup) throws IOException, InterruptedException;

	GetResourceGroupsResponse getResourceGroups(final ServiceKey serviceKey) throws IOException, InterruptedException;

}
