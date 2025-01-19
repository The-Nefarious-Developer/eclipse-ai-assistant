package com.developer.nefarious.zjoule.plugin.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class OllamaModel {
	
    private String name;

    private String model;

    @SerializedName("modified_at")
    private String modifiedAt;

    private long size;

    private String digest;

    private OllamaModelDetails details;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(final String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public long getSize() {
        return size;
    }

    public void setSize(final long size) {
        this.size = size;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(final String digest) {
        this.digest = digest;
    }

    public OllamaModelDetails getDetails() {
        return details;
    }

    public void setDetails(final OllamaModelDetails details) {
        this.details = details;
    }
    
    public String getFormat() {
        return details.getFormat();
    }
    
    public String getFamily() {
        return details.getFamily();
    }
    
    public String getParameterSize() {
        return details.getParameterSize();
    }
    
    public String getQuantizationLevel() {
        return details.getQuantizationLevel();
    }

}

class OllamaModelDetails {
	
    @SerializedName("parent_model")
    private String parentModel;

    private String format;

    private String family;

    private List<String> families;

    @SerializedName("parameter_size")
    private String parameterSize;

    @SerializedName("quantization_level")
    private String quantizationLevel;

    // Getters and Setters
    public String getParentModel() {
        return parentModel;
    }

    public void setParentModel(final String parentModel) {
        this.parentModel = parentModel;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(final String format) {
        this.format = format;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(final String family) {
        this.family = family;
    }

    public List<String> getFamilies() {
        return families;
    }

    public void setFamilies(final List<String> families) {
        this.families = families;
    }

    public String getParameterSize() {
        return parameterSize;
    }

    public void setParameterSize(final String parameterSize) {
        this.parameterSize = parameterSize;
    }

    public String getQuantizationLevel() {
        return quantizationLevel;
    }

    public void setQuantizationLevel(final String quantizationLevel) {
        this.quantizationLevel = quantizationLevel;
    }
}
