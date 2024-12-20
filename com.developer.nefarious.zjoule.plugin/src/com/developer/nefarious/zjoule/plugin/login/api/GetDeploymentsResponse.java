package com.developer.nefarious.zjoule.plugin.login.api;

import java.util.List;

import com.developer.nefarious.zjoule.plugin.models.Deployment;
import com.google.gson.annotations.SerializedName;

/**
 * Represents the response from an API call that retrieves a list of deployments.
 * <p>
 * The {@code GetDeploymentsResponse} class contains the total count of deployments
 * and a list of {@link Deployment} objects returned by the API.
 */
public class GetDeploymentsResponse {

    /** The total number of deployments in the response. */
    private int count;

    /** A list of {@link Deployment} objects returned by the API. */
    @SerializedName("resources")
    private List<Deployment> deployments;

    /**
     * Retrieves the total count of deployments.
     *
     * @return the total count of deployments as an {@code int}.
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the total count of deployments.
     *
     * @param count the total count of deployments as an {@code int}.
     */
    public void setCount(final int count) {
        this.count = count;
    }

    /**
     * Retrieves the list of deployments.
     *
     * @return a {@link List} of {@link Deployment} objects.
     */
    public List<Deployment> getDeployments() {
        return deployments;
    }

    /**
     * Sets the list of deployments.
     *
     * @param deployments a {@link List} of {@link Deployment} objects.
     */
    public void setResources(final List<Deployment> deployments) {
        this.deployments = deployments;
    }
    
}
