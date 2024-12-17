package com.developer.nefarious.zjoule.plugin.login.utils;

import java.util.ArrayList;
import java.util.List;
import com.developer.nefarious.zjoule.plugin.login.api.GetResourceGroupsResponse;
import com.developer.nefarious.zjoule.plugin.models.ResourceGroup;

public class ResourceGroupIdExtractor {
	
	public static ArrayList<String> extractResourceGroupIds(final GetResourceGroupsResponse response) {
		// Step 0: Check if response is null
        if (response == null) {
            return null;
        }
        
        // Step 1: Get the list of ResourceGroup objects from the response
        List<ResourceGroup> resourceGroups = response.getResourceGroups();

        // Step 2: Create an ArrayList to store the resource group IDs
        ArrayList<String> resourceGroupIds = new ArrayList<>();

        // Step 3 & 4: Loop through each ResourceGroup and extract the resourceGroupId
        for (ResourceGroup resourceGroup : resourceGroups) {
            String resourceGroupId = resourceGroup.getResourceGroupId();
            resourceGroupIds.add(resourceGroupId);
        }

        // Return the list of resource group IDs
        return resourceGroupIds;
    }

}
