package com.developer.nefarious.zjoule.plugin.login.api;

import java.io.IOException;
import java.net.http.HttpClient;

/**
 * A client interface for handling Ollama login-related API interactions.
 * <p>
 * The {@code OllamaLoginClient} communicates with the Ollama API to retrieve
 * available models. It uses an {@link HttpClient} for HTTP requests and
 * an {@link IOllamaLoginClientHelper} for handling request construction and response parsing.
 * </p>
 */
public interface IOllamaLoginClient {
	
	/**
     * Retrieves the available Ollama models from the specified endpoint.
     * <p>
     * This method sends a GET request to the Ollama API's `/api/tags` endpoint
     * and parses the response into a {@link GetOllamaModelsResponse} object.
     * </p>
     *
     * @param endpoint the base URL of the Ollama API.
     * @return a {@link GetOllamaModelsResponse} containing the list of available models.
     * @throws IOException          if an I/O error occurs while sending the request.
     * @throws InterruptedException if the request is interrupted while waiting for a response.
     */
	GetOllamaModelsResponse getModels(final String endpoint) throws IOException, InterruptedException;
	
}
