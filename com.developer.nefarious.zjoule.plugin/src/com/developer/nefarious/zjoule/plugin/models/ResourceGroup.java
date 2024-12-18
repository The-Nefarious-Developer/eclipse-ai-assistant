package com.developer.nefarious.zjoule.plugin.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ResourceGroup {

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("labels")
	private List<String> labels;

	@SerializedName("resourceGroupId")
	private String resourceGroupId;

	@SerializedName("status")
	private String status;

	@SerializedName("statusMessage")
	private String statusMessage;

	@SerializedName("tenantId")
	private String tenantId;

	@SerializedName("zoneId")
	private String zoneId;

	// Getters and setters
	public String getCreatedAt() {
		return createdAt;
	}

	public List<String> getLabels() {
		return labels;
	}

	public String getResourceGroupId() {
		return resourceGroupId;
	}

	public String getStatus() {
		return status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public String getTenantId() {
		return tenantId;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setCreatedAt(final String createdAt) {
		this.createdAt = createdAt;
	}

	public void setLabels(final List<String> labels) {
		this.labels = labels;
	}

	public void setResourceGroupId(final String resourceGroupId) {
		this.resourceGroupId = resourceGroupId;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public void setStatusMessage(final String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public void setTenantId(final String tenantId) {
		this.tenantId = tenantId;
	}

	public void setZoneId(final String zoneId) {
		this.zoneId = zoneId;
	}

}
