package com.developer.nefarious.zjoule.plugin.login.api;

import java.net.URI;

import com.google.gson.Gson;

/**
 * A helper class for handling login-related API operations.
 * <p>
 * The {@code LoginClientHelper} provides utility methods for creating URIs
 * and parsing API responses into their corresponding Java objects.
 * Implements the {@link ILoginClientHelper} interface.
 */
public class LoginClientHelper implements ILoginClientHelper {

    /** The {@link Gson} instance for parsing JSON responses. */
    private Gson gson;

    /**
     * Constructs a new {@code LoginClientHelper} instance.
     * <p>
     * Initializes a {@link Gson} instance for JSON parsing.
     */
    public LoginClientHelper() {
        gson = new Gson();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URI createAuthUri(final String tokenEndpoint) {
        return URI.create(tokenEndpoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetDeploymentsResponse parseDeploymentsResponseToObject(final String responseBody) {
        return gson.fromJson(responseBody, GetDeploymentsResponse.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetResourceGroupsResponse parseResourceGroupsResponseToObject(final String responseBody) {
        return gson.fromJson(responseBody, GetResourceGroupsResponse.class);
    }
}
