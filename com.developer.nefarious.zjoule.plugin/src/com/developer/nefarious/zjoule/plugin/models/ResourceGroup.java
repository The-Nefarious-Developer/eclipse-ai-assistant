package com.developer.nefarious.zjoule.plugin.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a resource group in the system.
 * <p>
 * A resource group is an organizational entity that contains various metadata
 * such as creation time, labels, status, and identifiers for the tenant and zone.
 * This class provides accessors and mutators for managing resource group properties.
 * It is designed to be serialized and deserialized using the Google Gson library.
 * </p>
 */
public class ResourceGroup {

    /** The creation timestamp of the resource group. */
    @SerializedName("createdAt")
    private String createdAt;

    /** A list of labels associated with the resource group. */
    @SerializedName("labels")
    private List<Label> labels;

    /** The unique identifier of the resource group. */
    @SerializedName("resourceGroupId")
    private String resourceGroupId;

    /** The current status of the resource group. */
    @SerializedName("status")
    private String status;

    /** A message providing additional information about the status. */
    @SerializedName("statusMessage")
    private String statusMessage;

    /** The tenant ID associated with the resource group. */
    @SerializedName("tenantId")
    private String tenantId;

    /** The zone ID where the resource group resides. */
    @SerializedName("zoneId")
    private String zoneId;

    /**
     * Retrieves the creation timestamp of the resource group.
     *
     * @return the creation timestamp as a {@link String}.
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the resource group.
     *
     * @param createdAt the creation timestamp to set.
     */
    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Retrieves the labels associated with the resource group.
     *
     * @return a {@link List} of labels as {@link String}.
     */
    public List<Label> getLabels() {
        return labels;
    }

    /**
     * Sets the labels associated with the resource group.
     *
     * @param labels a {@link List} of labels to set.
     */
    public void setLabels(final List<Label> labels) {
        this.labels = labels;
    }

    /**
     * Retrieves the unique identifier of the resource group.
     *
     * @return the resource group ID as a {@link String}.
     */
    public String getResourceGroupId() {
        return resourceGroupId;
    }

    /**
     * Sets the unique identifier of the resource group.
     *
     * @param resourceGroupId the resource group ID to set.
     */
    public void setResourceGroupId(final String resourceGroupId) {
        this.resourceGroupId = resourceGroupId;
    }

    /**
     * Retrieves the current status of the resource group.
     *
     * @return the status as a {@link String}.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the current status of the resource group.
     *
     * @param status the status to set.
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Retrieves the status message providing additional information.
     *
     * @return the status message as a {@link String}.
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Sets the status message providing additional information.
     *
     * @param statusMessage the status message to set.
     */
    public void setStatusMessage(final String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * Retrieves the tenant ID associated with the resource group.
     *
     * @return the tenant ID as a {@link String}.
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * Sets the tenant ID associated with the resource group.
     *
     * @param tenantId the tenant ID to set.
     */
    public void setTenantId(final String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * Retrieves the zone ID where the resource group resides.
     *
     * @return the zone ID as a {@link String}.
     */
    public String getZoneId() {
        return zoneId;
    }

    /**
     * Sets the zone ID where the resource group resides.
     *
     * @param zoneId the zone ID to set.
     */
    public void setZoneId(final String zoneId) {
        this.zoneId = zoneId;
    }
}

/**
 * Represents a label associated with a resource group.
 * <p>
 * A label is a key-value pair used for categorizing or tagging resources.
 * Labels provide metadata that can be utilized for searching, filtering,
 * or organizing resource groups. Each label consists of a unique key and an
 * associated value.
 * </p>
 */
class Label { 
	
	/** The key of the label, representing the category or identifier. */
	private String key;
	
	/** The value of the label, providing additional information or context. */
	private String value;
	
	/**
     * Retrieves the key of the label.
     *
     * @return the key as a {@link String}.
     */
	public String getKey() {
        return key;
    }

	/**
     * Sets the key of the label.
     *
     * @param key the key to set.
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * Retrieves the value of the label.
     *
     * @return the value as a {@link String}.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the label.
     *
     * @param value the value to set.
     */
    public void setValue(final String value) {
        this.value = value;
    }
	
}