package com.developer.nefarious.zjoule.plugin.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a machine learning model in the Ollama system.
 * <p>
 * This class provides metadata about the model, such as its name, size, digest,
 * modification date, and additional details contained within {@link OllamaModelDetails}.
 * It is designed for serialization and deserialization using the Google Gson library.
 * </p>
 */
public class OllamaModel {

    /** The name of the model. */
    private String name;

    /** The model identifier. */
    private String model;

    /** The timestamp indicating when the model was last modified. */
    @SerializedName("modified_at")
    private String modifiedAt;

    /** The size of the model in bytes. */
    private long size;

    /** The digest hash representing the model's unique fingerprint. */
    private String digest;

    /** Additional details about the model, including its format, family, and parameters. */
    private OllamaModelDetails details;

    /**
     * Retrieves the name of the model.
     *
     * @return the model name as a {@link String}.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the model.
     *
     * @param name the model name to set.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Retrieves the model identifier.
     *
     * @return the model identifier as a {@link String}.
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model identifier.
     *
     * @param model the model identifier to set.
     */
    public void setModel(final String model) {
        this.model = model;
    }

    /**
     * Retrieves the last modification timestamp of the model.
     *
     * @return the modification timestamp as a {@link String}.
     */
    public String getModifiedAt() {
        return modifiedAt;
    }

    /**
     * Sets the last modification timestamp of the model.
     *
     * @param modifiedAt the modification timestamp to set.
     */
    public void setModifiedAt(final String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    /**
     * Retrieves the size of the model in bytes.
     *
     * @return the model size as a {@code long}.
     */
    public long getSize() {
        return size;
    }

    /**
     * Sets the size of the model in bytes.
     *
     * @param size the model size to set.
     */
    public void setSize(final long size) {
        this.size = size;
    }

    /**
     * Retrieves the digest hash of the model.
     *
     * @return the digest hash as a {@link String}.
     */
    public String getDigest() {
        return digest;
    }

    /**
     * Sets the digest hash of the model.
     *
     * @param digest the digest hash to set.
     */
    public void setDigest(final String digest) {
        this.digest = digest;
    }

    /**
     * Retrieves additional details about the model.
     *
     * @return the {@link OllamaModelDetails} object containing additional model metadata.
     */
    public OllamaModelDetails getDetails() {
        return details;
    }

    /**
     * Sets additional details about the model.
     *
     * @param details the {@link OllamaModelDetails} object to set.
     */
    public void setDetails(final OllamaModelDetails details) {
        this.details = details;
    }

    /**
     * Retrieves the model's format from its details.
     *
     * @return the model format as a {@link String}.
     */
    public String getFormat() {
        return details.getFormat();
    }

    /**
     * Retrieves the model's family from its details.
     *
     * @return the model family as a {@link String}.
     */
    public String getFamily() {
        return details.getFamily();
    }

    /**
     * Retrieves the model's parameter size from its details.
     *
     * @return the parameter size as a {@link String}.
     */
    public String getParameterSize() {
        return details.getParameterSize();
    }

    /**
     * Retrieves the model's quantization level from its details.
     *
     * @return the quantization level as a {@link String}.
     */
    public String getQuantizationLevel() {
        return details.getQuantizationLevel();
    }
}

/**
 * Represents detailed metadata about an {@link OllamaModel}.
 * <p>
 * This class contains attributes such as the parent model, format, family,
 * and additional technical details like parameter size and quantization level.
 * It is designed for serialization and deserialization using the Google Gson library.
 * </p>
 */
class OllamaModelDetails {

    /** The parent model from which this model was derived, if applicable. */
    @SerializedName("parent_model")
    private String parentModel;

    /** The format of the model, indicating the model type. */
    private String format;

    /** The family of the model, grouping it within a larger category. */
    private String family;

    /** A list of families the model belongs to. */
    private List<String> families;

    /** The parameter size of the model, representing its complexity. */
    @SerializedName("parameter_size")
    private String parameterSize;

    /** The quantization level of the model, defining how the weights are stored. */
    @SerializedName("quantization_level")
    private String quantizationLevel;

    /**
     * Retrieves the parent model of this model.
     *
     * @return the parent model as a {@link String}.
     */
    public String getParentModel() {
        return parentModel;
    }

    /**
     * Sets the parent model of this model.
     *
     * @param parentModel the parent model to set.
     */
    public void setParentModel(final String parentModel) {
        this.parentModel = parentModel;
    }

    /**
     * Retrieves the format of the model.
     *
     * @return the model format as a {@link String}.
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the format of the model.
     *
     * @param format the format to set.
     */
    public void setFormat(final String format) {
        this.format = format;
    }

    /**
     * Retrieves the family of the model.
     *
     * @return the model family as a {@link String}.
     */
    public String getFamily() {
        return family;
    }

    /**
     * Sets the family of the model.
     *
     * @param family the family to set.
     */
    public void setFamily(final String family) {
        this.family = family;
    }

    /**
     * Retrieves the list of families the model belongs to.
     *
     * @return a {@link List} of families as {@link String}.
     */
    public List<String> getFamilies() {
        return families;
    }

    /**
     * Sets the list of families the model belongs to.
     *
     * @param families the list of families to set.
     */
    public void setFamilies(final List<String> families) {
        this.families = families;
    }

    /**
     * Retrieves the parameter size of the model.
     *
     * @return the parameter size as a {@link String}.
     */
    public String getParameterSize() {
        return parameterSize;
    }

    /**
     * Sets the parameter size of the model.
     *
     * @param parameterSize the parameter size to set.
     */
    public void setParameterSize(final String parameterSize) {
        this.parameterSize = parameterSize;
    }

    /**
     * Retrieves the quantization level of the model.
     *
     * @return the quantization level as a {@link String}.
     */
    public String getQuantizationLevel() {
        return quantizationLevel;
    }

    /**
     * Sets the quantization level of the model.
     *
     * @param quantizationLevel the quantization level to set.
     */
    public void setQuantizationLevel(final String quantizationLevel) {
        this.quantizationLevel = quantizationLevel;
    }
}