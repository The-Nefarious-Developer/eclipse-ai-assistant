package com.developer.nefarious.zjoule.login.api;

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

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public String getResourceGroupId() {
		return resourceGroupId;
	}

	public void setResourceGroupId(String resourceGroupId) {
		this.resourceGroupId = resourceGroupId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	
}
