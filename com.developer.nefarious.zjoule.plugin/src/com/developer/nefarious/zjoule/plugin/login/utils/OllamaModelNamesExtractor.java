package com.developer.nefarious.zjoule.plugin.login.utils;

import java.util.ArrayList;
import java.util.List;

import com.developer.nefarious.zjoule.plugin.login.api.GetOllamaModelsResponse;
import com.developer.nefarious.zjoule.plugin.models.OllamaModel;

public abstract class OllamaModelNamesExtractor {
	
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
