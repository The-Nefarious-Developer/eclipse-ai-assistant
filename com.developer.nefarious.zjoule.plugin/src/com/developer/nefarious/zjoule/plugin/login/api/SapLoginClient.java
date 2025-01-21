package com.developer.nefarious.zjoule.plugin.login.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.developer.nefarious.zjoule.plugin.auth.IAuthClient;
import com.developer.nefarious.zjoule.plugin.models.ServiceKey;

/**
 * Implements the {@link ISapLoginClient} interface for managing API interactions related to login operations.
 * <p>
 * The {@code LoginClient} class communicates with SAP AI Core APIs to retrieve deployments
 * and resource groups, leveraging an {@link IAuthClient} for authentication and an {@link ISapLoginClientHelper}
 * for building requests and parsing responses.
 */
public class SapLoginClient implements ISapLoginClient {

    /** The HTTP client used for making API requests. */
    private HttpClient httpClient;

    /** Helper class for constructing requests and parsing responses. */
    private ISapLoginClientHelper sapLoginClientHelper;

    /** The authentication client used for retrieving access tokens. */
    private IAuthClient authClient;

    /**
     * Constructs a new {@code LoginClient} instance.
     *
     * @param sapLoginClientHelper the helper for constructing requests and parsing responses.
     * @param authClient the authentication client for retrieving access tokens.
     */
    public SapLoginClient(final ISapLoginClientHelper sapLoginClientHelper, final IAuthClient authClient) {
        httpClient = HttpClient.newHttpClient();
        this.sapLoginClientHelper = sapLoginClientHelper;
        this.authClient = authClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetDeploymentsResponse getDeployments(final ServiceKey serviceKey, final String resourceGroup) 
            throws IOException, InterruptedException {
        URI endpoint = sapLoginClientHelper.createAuthUri(serviceKey.getServiceURL() + "/lm/deployments");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endpoint)
                .header("Authorization", "Bearer " + authClient.getNewAccessToken(serviceKey))
                .header("AI-Resource-Group", resourceGroup)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return sapLoginClientHelper.parseDeploymentsResponseToObject(response.body());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetResourceGroupsResponse getResourceGroups(final ServiceKey serviceKey) 
            throws IOException, InterruptedException {
        URI endpoint = sapLoginClientHelper.createAuthUri(serviceKey.getServiceURL() + "/admin/resourceGroups");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endpoint)
                .header("Authorization", "Bearer " + authClient.getNewAccessToken(serviceKey))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return sapLoginClientHelper.parseResourceGroupsResponseToObject(response.body());
    }
}
