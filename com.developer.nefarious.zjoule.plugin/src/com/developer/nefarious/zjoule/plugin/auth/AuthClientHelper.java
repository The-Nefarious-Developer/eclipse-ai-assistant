package com.developer.nefarious.zjoule.plugin.auth;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;

import com.developer.nefarious.zjoule.plugin.models.AccessToken;
import com.google.gson.Gson;

/**
 * A helper class for the {@code AuthClient} that provides utility methods for
 * constructing HTTP requests, parsing responses, and managing URI conversions.
 */
public class AuthClientHelper implements IAuthClientHelper {

    /** A Gson instance for converting JSON responses to Java objects. */
    private Gson gson;

    /**
     * Constructs a new {@code AuthClientHelper} instance and initializes a {@link Gson} instance.
     */
    public AuthClientHelper() {
        gson = new Gson();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URI convertEndpointStringToURI(final String tokenEndpoint) {
        return URI.create(tokenEndpoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccessToken convertResponseToObject(final String responseBody) {
        return gson.fromJson(responseBody, AccessToken.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BodyPublisher createRequestBody(final String clientId, final String clientSecret) {
        // @formatter:off
        String requestBody = "grant_type=client_credentials"
            + "&client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8)
            + "&client_secret=" + URLEncoder.encode(clientSecret, StandardCharsets.UTF_8);
        // @formatter:on
        return BodyPublishers.ofString(requestBody);
    }
}
