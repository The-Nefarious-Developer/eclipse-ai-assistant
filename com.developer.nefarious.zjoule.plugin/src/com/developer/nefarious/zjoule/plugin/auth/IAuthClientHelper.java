package com.developer.nefarious.zjoule.plugin.auth;

import java.net.URI;
import java.net.http.HttpRequest.BodyPublisher;

import com.developer.nefarious.zjoule.plugin.models.AccessToken;

/**
 * Defines utility methods for handling authentication-related tasks.
 * Implementations of this interface provide functionalities such as
 * constructing HTTP request bodies, converting endpoint strings to URIs,
 * and parsing authentication responses.
 */
public interface IAuthClientHelper {

	/**
     * Converts a token endpoint URL string into a {@link URI} object.
     *
     * @param tokenEndpoint the token endpoint URL as a {@link String}.
     * @return the corresponding {@link URI} object.
     */
	URI convertEndpointStringToURI(final String tokenEndpoint);

	/**
     * Converts a JSON response body into an {@link AccessToken} object.
     *
     * @param responseBody the JSON response body as a {@link String}.
     * @return an {@link AccessToken} object created from the response body.
     */
	AccessToken convertResponseToObject(final String responseBody);

	/**
     * Creates a request body for a client credentials grant type.
     * The request body includes the client ID and client secret encoded as URL parameters.
     *
     * @param clientId the client ID as a {@link String}.
     * @param clientSecret the client secret as a {@link String}.
     * @return a {@link BodyPublisher} containing the request body.
     */
	BodyPublisher createRequestBody(final String clientId, final String clientSecret);

}
