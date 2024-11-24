package com.developer.nefarious.zjoule.login.api;

import java.util.List;

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

    public void setCount(int count) {
        this.count = count;
    }

    public List<ResourceGroup> getResourceGroups() {
        return resourceGroups;
    }

    public void setResourceGroups(List<ResourceGroup> resourceGroups) {
        this.resourceGroups = resourceGroups;
    }
    
}
