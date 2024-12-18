package com.developer.nefarious.zjoule.plugin.login.utils;

import java.util.ArrayList;
import java.util.List;

import com.developer.nefarious.zjoule.plugin.login.api.GetResourceGroupsResponse;
import com.developer.nefarious.zjoule.plugin.models.ResourceGroup;

/**
 * Utility class for extracting resource group IDs from a {@link GetResourceGroupsResponse}.
 * <p>
 * This class provides a static method to process a response and retrieve a list
 * of resource group IDs. It is abstract to prevent instantiation.
 */
public abstract class ResourceGroupIdExtractor {

    /**
     * Extracts a list of resource group IDs from the given {@link GetResourceGroupsResponse}.
     * <p>
     * If the response is {@code null}, the method returns {@code null}.
     * Otherwise, it processes the list of {@link ResourceGroup} objects contained
     * in the response and extracts their IDs into a new list.
     *
     * @param response the {@link GetResourceGroupsResponse} containing resource group data.
     * @return a {@link List} of resource group IDs as {@link String}, or {@code null} if the response is {@code null}.
     */
    public static List<String> extractResourceGroupIds(final GetResourceGroupsResponse response) {
        // Step 0: Check if response is null
        if (response == null) {
            return null;
        }

        // Step 1: Get the list of ResourceGroup objects from the response
        List<ResourceGroup> resourceGroups = response.getResourceGroups();

        // Step 2: Create an ArrayList to store the resource group IDs
        List<String> resourceGroupIds = new ArrayList<>();

        // Step 3 & 4: Loop through each ResourceGroup and extract the resourceGroupId
        for (ResourceGroup resourceGroup : resourceGroups) {
            String resourceGroupId = resourceGroup.getResourceGroupId();
            resourceGroupIds.add(resourceGroupId);
        }

        // Return the list of resource group IDs
        return resourceGroupIds;
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ResourceGroupIdExtractor() { }
    
}
