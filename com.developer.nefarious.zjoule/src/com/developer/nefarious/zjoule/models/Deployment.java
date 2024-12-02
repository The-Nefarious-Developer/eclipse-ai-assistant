package com.developer.nefarious.zjoule.models;

import java.util.Map;

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

	public void setConfigurationId(final String configurationId) {
		this.configurationId = configurationId;
	}

	public String getConfigurationName() {
		return configurationName;
	}

	public void setConfigurationName(final String configurationName) {
		this.configurationName = configurationName;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(final String createdAt) {
		this.createdAt = createdAt;
	}

	public String getDeploymentUrl() {
		return deploymentUrl;
	}

	public void setDeploymentUrl(final String deploymentUrl) {
		this.deploymentUrl = deploymentUrl;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(final Details details) {
		this.details = details;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getLastOperation() {
		return lastOperation;
	}

	public void setLastOperation(final String lastOperation) {
		this.lastOperation = lastOperation;
	}

	public String getLatestRunningConfigurationId() {
		return latestRunningConfigurationId;
	}

	public void setLatestRunningConfigurationId(final String latestRunningConfigurationId) {
		this.latestRunningConfigurationId = latestRunningConfigurationId;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(final String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(final String scenarioId) {
		this.scenarioId = scenarioId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(final String startTime) {
		this.startTime = startTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public String getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(final String submissionTime) {
		this.submissionTime = submissionTime;
	}

	public String getTargetStatus() {
		return targetStatus;
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

	public void setResources(final Map<String, BackendDetails> resources) {
		this.resources = resources;
	}

	public Map<String, Object> getScaling() {
		return scaling;
	}

	public void setScaling(final Map<String, Object> scaling) {
		this.scaling = scaling;
	}

}

class BackendDetails {

	private Model model;

	public Model getModel() {
		return model;
	}

	public void setModel(final Model model) {
		this.model = model;
	}

}

class Model {

	private String name;

	private String version;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

}