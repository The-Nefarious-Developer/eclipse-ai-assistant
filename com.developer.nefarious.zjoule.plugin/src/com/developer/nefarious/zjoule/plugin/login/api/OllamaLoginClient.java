package com.developer.nefarious.zjoule.plugin.login.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * A client for handling Ollama login-related API interactions.
 * <p>
 * The {@code OllamaLoginClient} communicates with the Ollama API to retrieve
 * available models. It uses an {@link HttpClient} for HTTP requests and
 * an {@link IOllamaLoginClientHelper} for handling request construction and response parsing.
 * </p>
 */
public class OllamaLoginClient implements IOllamaLoginClient {

    /** The HTTP client used to send API requests. */
    private HttpClient httpClient;

    /** The helper class responsible for constructing requests and parsing responses. */
    private IOllamaLoginClientHelper ollamaLoginClientHelper;

    /**
     * Constructs an {@code OllamaLoginClient} with the specified helper.
     * <p>
     * Initializes an {@link HttpClient} instance for sending HTTP requests.
     * </p>
     *
     * @param ollamaLoginClientHelper an instance of {@link IOllamaLoginClientHelper}
     *                                for request and response handling.
     */
    public OllamaLoginClient(final IOllamaLoginClientHelper ollamaLoginClientHelper) {
        httpClient = HttpClient.newHttpClient();
        this.ollamaLoginClientHelper = ollamaLoginClientHelper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetOllamaModelsResponse getModels(final String endpoint)
            throws IOException, InterruptedException {
        URI endpointUri = ollamaLoginClientHelper.createUri(endpoint + "/api/tags");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(endpointUri)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return ollamaLoginClientHelper.parseOllamaModelsResponseToObject(response.body());
    }
}