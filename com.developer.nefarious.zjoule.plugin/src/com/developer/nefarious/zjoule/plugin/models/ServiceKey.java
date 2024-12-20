package com.developer.nefarious.zjoule.plugin.models;

import com.google.gson.annotations.SerializedName;

/**
 * Represents the service key configuration required for accessing API endpoints and authentication.
 * <p>
 * The {@code ServiceKey} class contains information about the API URLs, client credentials, and token URL.
 * It provides methods to validate the service key's integrity and to retrieve the formatted URLs.
 * </p>
 */
public class ServiceKey {

    /** The URLs associated with the service. */
    @SerializedName("serviceurls")
    private ServiceUrls serviceUrl;

    /** The client ID for authentication. */
    @SerializedName("clientid")
    private String clientId;

    /** The client secret for authentication. */
    @SerializedName("clientsecret")
    private String clientSecret;

    /** The token URL for obtaining OAuth tokens. */
    @SerializedName("url")
    private String tokenUrl;

    /**
     * Retrieves the client ID for authentication.
     *
     * @return the client ID.
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Sets the client ID for authentication.
     *
     * @param clientId the client ID to set.
     */
    public void setClientId(final String clientId) {
        this.clientId = clientId;
    }

    /**
     * Retrieves the client secret for authentication.
     *
     * @return the client secret.
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * Sets the client secret for authentication.
     *
     * @param clientSecret the client secret to set.
     */
    public void setClientSecret(final String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * Retrieves the API service URL, formatted with a version suffix.
     *
     * @return the API service URL with the `/v2` suffix.
     */
    public String getServiceURL() {
        return serviceUrl.getApiUrl() + "/v2";
    }

    /**
     * Retrieves the token URL for OAuth authentication.
     *
     * @return the token URL appended with the `/oauth/token` suffix.
     */
    public String getTokenURL() {
        return tokenUrl + "/oauth/token";
    }

    /**
     * Validates the service key's integrity by ensuring all required fields are non-null and non-empty.
     *
     * @return {@code true} if the service key is valid; {@code false} otherwise.
     */
    public Boolean isValid() {
        return serviceUrl != null && serviceUrl.getApiUrl() != null && !serviceUrl.getApiUrl().isEmpty()
                && clientId != null && !clientId.isEmpty() && clientSecret != null && !clientSecret.isEmpty()
                && tokenUrl != null && !tokenUrl.isEmpty();
    }

    /**
     * Sets the token URL for OAuth authentication.
     *
     * @param tokenUrl the token URL to set.
     */
    public void setTokenUrl(final String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }
}

/**
 * Represents the service URLs associated with the service key.
 * <p>
 * The {@code ServiceUrls} class contains information about the API base URL and provides accessors to manage it.
 * </p>
 */
class ServiceUrls {

    /** The base URL of the API. */
    @SerializedName("AI_API_URL")
    private String apiUrl;

    /**
     * Retrieves the base URL of the API.
     *
     * @return the API base URL.
     */
    public String getApiUrl() {
        return apiUrl;
    }

    /**
     * Sets the base URL of the API.
     *
     * @param AI_API_URL the API base URL to set.
     */
    public void setApiUrl(final String AI_API_URL) {
        this.apiUrl = AI_API_URL;
    }
}
