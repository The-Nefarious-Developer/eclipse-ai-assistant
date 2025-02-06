package com.developer.nefarious.zjoule.plugin.login.utils;

import java.util.ArrayList;
import java.util.List;

import com.developer.nefarious.zjoule.plugin.login.api.GetOllamaModelsResponse;
import com.developer.nefarious.zjoule.plugin.models.OllamaModel;

/**
 * A utility class for extracting Ollama model names from API responses.
 * <p>
 * The {@code OllamaModelNamesExtractor} provides a static method to convert a
 * {@link GetOllamaModelsResponse} into a list of model names. This helps in
 * populating dropdown menus or other UI components with available models.
 * </p>
 */
public abstract class OllamaModelNamesExtractor {

    /**
     * Extracts model names from a {@link GetOllamaModelsResponse}.
     * <p>
     * This method retrieves the list of {@link OllamaModel} objects from the response
     * and extracts their names into a list of strings.
     * </p>
     *
     * @param response the API response containing Ollama models.
     * @return a list of model names, or {@code null} if the response is {@code null}.
     */
    public static List<String> extractModelNames(final GetOllamaModelsResponse response) {
        // Step 0: Check if response is null
        if (response == null) {
            return null;
        }

        // Step 1: Get the list of Model objects from the response
        List<OllamaModel> ollamaModels = response.getModels();

        // Step 2: Create an ArrayList to store the model names
        List<String> ollamaModelNames = new ArrayList<>();

        // Step 3 & 4: Loop through each Model and extract its name
        for (OllamaModel ollamaModel : ollamaModels) {
            String ollamaModelName = ollamaModel.getName();
            ollamaModelNames.add(ollamaModelName);
        }

        // Return the list of model names
        return ollamaModelNames;
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private OllamaModelNamesExtractor() { }
}