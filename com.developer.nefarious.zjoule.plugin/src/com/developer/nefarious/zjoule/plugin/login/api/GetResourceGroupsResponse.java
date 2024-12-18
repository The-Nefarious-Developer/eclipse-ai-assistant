package com.developer.nefarious.zjoule.plugin.login.api;

import java.util.List;

import com.developer.nefarious.zjoule.plugin.models.ResourceGroup;
import com.google.gson.annotations.SerializedName;

public class GetResourceGroupsResponse {

	@SerializedName("count")
    private int count;

    @SerializedName("resources")
    private List<ResourceGroup> resourceGroups;

    // Getters and setters
    public int getCount() {
        return count;
    }

    public List<ResourceGroup> getResourceGroups() {
        return resourceGroups;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public void setResourceGroups(final List<ResourceGroup> resourceGroups) {
        this.resourceGroups = resourceGroups;
    }

}
