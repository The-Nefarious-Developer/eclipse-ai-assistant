package com.developer.nefarious.zjoule.plugin.login.api;

import java.net.URI;

/**
 * Interface for helper methods used in login-related API operations.
 * <p>
 * The {@code ILoginClientHelper} defines methods for creating API request URIs
 * and parsing JSON response bodies into their corresponding Java objects.
 */
public interface ILoginClientHelper {

	/**
     * Creates a URI for the given API endpoint.
     *
     * @param tokenEndpoint the API endpoint as a {@link String}.
     * @return the corresponding {@link URI}.
     */
	URI createAuthUri(final String tokenEndpoint);

	/**
     * Parses a JSON response body into a {@link GetDeploymentsResponse} object.
     *
     * @param responseBody the JSON response body as a {@link String}.
     * @return the parsed {@link GetDeploymentsResponse}.
     */
	GetDeploymentsResponse parseDeploymentsResponseToObject(final String responseBody);

	/**
     * Parses a JSON response body into a {@link GetResourceGroupsResponse} object.
     *
     * @param responseBody the JSON response body as a {@link String}.
     * @return the parsed {@link GetResourceGroupsResponse}.
     */
	GetResourceGroupsResponse parseResourceGroupsResponseToObject(final String responseBody);

}
