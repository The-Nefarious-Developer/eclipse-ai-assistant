package com.developer.nefarious.zjoule.plugin.login.api;

import java.util.List;

import com.developer.nefarious.zjoule.plugin.models.OllamaModel;

public class GetOllamaModelsResponse {
	
    private List<OllamaModel> models;

    // Getters and Setters
    public List<OllamaModel> getModels() {
        return models;
    }

    public void setModels(final List<OllamaModel> models) {
        this.models = models;
    }

}
