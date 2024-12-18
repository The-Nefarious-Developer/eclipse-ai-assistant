package com.developer.nefarious.zjoule.plugin.login.api;

import java.io.IOException;

import com.developer.nefarious.zjoule.plugin.models.ServiceKey;

/**
 * Interface for handling login-related API interactions with SAP AI Core.
 * <p>
 * The {@code ILoginClient} defines methods for retrieving deployments and resource groups
 * by interacting with the SAP AI Core API.
 */
public interface ILoginClient {

	/**
     * Retrieves a list of deployments from the SAP AI Core API.
     *
     * @param serviceKey the {@link ServiceKey} containing credentials and service information.
     * @param resourceGroup the resource group for which deployments are to be retrieved.
     * @return a {@link GetDeploymentsResponse} containing the deployments data.
     * @throws IOException if an I/O error occurs during the request.
     * @throws InterruptedException if the request is interrupted.
     */
	GetDeploymentsResponse getDeployments(final ServiceKey serviceKey, final String resourceGroup) throws IOException, InterruptedException;

	/**
     * Retrieves a list of resource groups from the SAP AI Core API.
     *
     * @param serviceKey the {@link ServiceKey} containing credentials and service information.
     * @return a {@link GetResourceGroupsResponse} containing the resource groups data.
     * @throws IOException if an I/O error occurs during the request.
     * @throws InterruptedException if the request is interrupted.
     */
	GetResourceGroupsResponse getResourceGroups(final ServiceKey serviceKey) throws IOException, InterruptedException;

}
