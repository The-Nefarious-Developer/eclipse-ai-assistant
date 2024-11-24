package com.developer.nefarious.zjoule.login.api;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GetResourceGroupsResponse {
	
	@SerializedName("count")
    private int count;

    @SerializedName("resources")
    private List<ResourceGroup> resources;

    // Getters and setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ResourceGroup> getResources() {
        return resources;
    }

    public void setResources(List<ResourceGroup> resources) {
        this.resources = resources;
    }
    
}
