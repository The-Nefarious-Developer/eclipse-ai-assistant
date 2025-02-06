package com.developer.nefarious.zjoule.plugin.login.api;

import java.util.List;

import com.developer.nefarious.zjoule.plugin.models.OllamaModel;

/**
 * Represents the response containing a list of available Ollama models.
 * <p>
 * This class is used to store and retrieve a list of {@link OllamaModel}
 * objects, typically received from an API call.
 * </p>
 */
public class GetOllamaModelsResponse {

    /** The list of available Ollama models. */
    private List<OllamaModel> models;

    /**
     * Retrieves the list of Ollama models.
     *
     * @return a list of {@link OllamaModel} objects.
     */
    public List<OllamaModel> getModels() {
        return models;
    }

    /**
     * Sets the list of Ollama models.
     *
     * @param models a list of {@link OllamaModel} objects to set.
     */
    public void setModels(final List<OllamaModel> models) {
        this.models = models;
    }
}