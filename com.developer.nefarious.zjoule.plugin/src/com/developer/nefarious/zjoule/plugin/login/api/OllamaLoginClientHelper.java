package com.developer.nefarious.zjoule.plugin.login.api;

import java.net.URI;

import com.google.gson.Gson;

/**
 * A helper class for handling API requests and responses related to Ollama login.
 * <p>
 * The {@code OllamaLoginClientHelper} provides utility methods for creating URIs
 * and parsing JSON responses from the Ollama API.
 * </p>
 */
public class OllamaLoginClientHelper implements IOllamaLoginClientHelper {

    /** Gson instance for JSON serialization and deserialization. */
    private Gson gson;

    /**
     * Constructs an {@code OllamaLoginClientHelper} and initializes a {@link Gson} instance.
     */
    public OllamaLoginClientHelper() {
        gson = new Gson();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URI createUri(final String endpoint) {
        return URI.create(endpoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetOllamaModelsResponse parseOllamaModelsResponseToObject(final String responseBody) {
        return gson.fromJson(responseBody, GetOllamaModelsResponse.class);
    }
}