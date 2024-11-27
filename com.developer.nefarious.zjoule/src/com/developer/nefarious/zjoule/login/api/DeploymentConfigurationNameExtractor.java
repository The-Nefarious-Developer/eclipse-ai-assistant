package com.developer.nefarious.zjoule.login.api;

import java.util.ArrayList;
import java.util.List;

public class DeploymentConfigurationNameExtractor {
	
	public static ArrayList<String> extractResourceGroupIds(final GetDeploymentsResponse response) {
		// Step 0: Check if response is null
		if (response == null) {
			return null;
		}

		// Step 1: Get the list of ResourceGroup objects from the response
		List<Deployment> deployments = response.getDeployments();

		// Step 2: Create an ArrayList to store the resource group IDs
		ArrayList<String> deploymentConfigurationNames = new ArrayList<>();

		// Step 3 & 4: Loop through each ResourceGroup and extract the resourceGroupId
		for (Deployment deployment : deployments) {
			String configurationName = deployment.getConfigurationName();
			deploymentConfigurationNames.add(configurationName);
		}

		// Return the list of resource group IDs
		return deploymentConfigurationNames;
	}
	
}
