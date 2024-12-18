package com.developer.nefarious.zjoule.plugin.login.api;

import java.util.List;

import com.developer.nefarious.zjoule.plugin.models.ResourceGroup;
import com.google.gson.annotations.SerializedName;

/**
 * Represents the response from an API call that retrieves a list of resource groups.
 * <p>
 * The {@code GetResourceGroupsResponse} class contains the total count of resource groups
 * and a list of {@link ResourceGroup} objects returned by the API.
 */
public class GetResourceGroupsResponse {

    /** The total number of resource groups in the response. */
    @SerializedName("count")
    private int count;

    /** A list of {@link ResourceGroup} objects returned by the API. */
    @SerializedName("resources")
    private List<ResourceGroup> resourceGroups;

    /**
     * Retrieves the total count of resource groups.
     *
     * @return the total count of resource groups as an {@code int}.
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the total count of resource groups.
     *
     * @param count the total count of resource groups as an {@code int}.
     */
    public void setCount(final int count) {
        this.count = count;
    }

    /**
     * Retrieves the list of resource groups.
     *
     * @return a {@link List} of {@link ResourceGroup} objects.
     */
    public List<ResourceGroup> getResourceGroups() {
        return resourceGroups;
    }

    /**
     * Sets the list of resource groups.
     *
     * @param resourceGroups a {@link List} of {@link ResourceGroup} objects.
     */
    public void setResourceGroups(final List<ResourceGroup> resourceGroups) {
        this.resourceGroups = resourceGroups;
    }
}
