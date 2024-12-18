package com.developer.nefarious.zjoule.plugin.models;

import java.util.Map;

class BackendDetails {

	private Model model;

	public Model getModel() {
		return model;
	}

	public void setModel(final Model model) {
		this.model = model;
	}

}

public class Deployment {

	private String configurationId;

	private String configurationName;

	private String createdAt;

	private String deploymentUrl;

	private Details details;

	private String id;

	private String lastOperation;

	private String latestRunningConfigurationId;

	private String modifiedAt;

	private String scenarioId;

	private String startTime;

	private String status;

	private String submissionTime;

	private String targetStatus;

	public String getConfigurationId() {
		return configurationId;
	}

	public String getConfigurationName() {
		return configurationName;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getDeploymentUrl() {
		return deploymentUrl;
	}

	public Details getDetails() {
		return details;
	}

	public String getId() {
		return id;
	}

	public String getLastOperation() {
		return lastOperation;
	}

	public String getLatestRunningConfigurationId() {
		return latestRunningConfigurationId;
	}

	public String getModelName() {
		return getDetails().getResources().get("backendDetails").getModel().getName();
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public String getScenarioId() {
		return scenarioId;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getStatus() {
		return status;
	}

	public String getSubmissionTime() {
		return submissionTime;
	}

	public String getTargetStatus() {
		return targetStatus;
	}

	public void setConfigurationId(final String configurationId) {
		this.configurationId = configurationId;
	}

	public void setConfigurationName(final String configurationName) {
		this.configurationName = configurationName;
	}

	public void setCreatedAt(final String createdAt) {
		this.createdAt = createdAt;
	}

	public void setDeploymentUrl(final String deploymentUrl) {
		this.deploymentUrl = deploymentUrl;
	}

	public void setDetails(final Details details) {
		this.details = details;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public void setLastOperation(final String lastOperation) {
		this.lastOperation = lastOperation;
	}

	public void setLatestRunningConfigurationId(final String latestRunningConfigurationId) {
		this.latestRunningConfigurationId = latestRunningConfigurationId;
	}

	public void setModifiedAt(final String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public void setScenarioId(final String scenarioId) {
		this.scenarioId = scenarioId;
	}

	public void setStartTime(final String startTime) {
		this.startTime = startTime;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public void setSubmissionTime(final String submissionTime) {
		this.submissionTime = submissionTime;
	}

	public void setTargetStatus(final String targetStatus) {
		this.targetStatus = targetStatus;
	}

}

class Details {

	private Map<String, BackendDetails> resources;

	private Map<String, Object> scaling;

	public Map<String, BackendDetails> getResources() {
		return resources;
	}

	public Map<String, Object> getScaling() {
		return scaling;
	}

	public void setResources(final Map<String, BackendDetails> resources) {
		this.resources = resources;
	}

	public void setScaling(final Map<String, Object> scaling) {
		this.scaling = scaling;
	}

}

class Model {

	private String name;

	private String version;

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

}