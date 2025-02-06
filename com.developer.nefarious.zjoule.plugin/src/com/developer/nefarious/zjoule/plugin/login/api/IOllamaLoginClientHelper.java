package com.developer.nefarious.zjoule.plugin.login.api;

import java.net.URI;

/**
 * A helper interface for handling API requests and responses related to Ollama login.
 * <p>
 * The {@code OllamaLoginClientHelper} provides utility methods for creating URIs
 * and parsing JSON responses from the Ollama API.
 * </p>
 */
public interface IOllamaLoginClientHelper {
	
	/**
     * Creates a {@link URI} from the given endpoint string.
     *
     * @param endpoint the API endpoint as a {@link String}.
     * @return the corresponding {@link URI}.
     */
	URI createUri(final String endpoint);
	
	/**
     * Parses the JSON response body into a {@link GetOllamaModelsResponse} object.
     *
     * @param responseBody the JSON response from the API.
     * @return a {@link GetOllamaModelsResponse} containing the list of available Ollama models.
     */
	GetOllamaModelsResponse parseOllamaModelsResponseToObject(final String responseBody);

}
