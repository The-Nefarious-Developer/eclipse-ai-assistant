package com.developer.nefarious.zjoule.plugin.login.api;

import java.util.List;
import com.developer.nefarious.zjoule.plugin.models.Deployment;
import com.google.gson.annotations.SerializedName;

public class GetDeploymentsResponse {
	
	private int count;
	
	@SerializedName("resources")
    private List<Deployment> deployments;

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public List<Deployment> getDeployments() {
        return deployments;
    }

    public void setResources(final List<Deployment> deployments) {
        this.deployments = deployments;
    }

}
