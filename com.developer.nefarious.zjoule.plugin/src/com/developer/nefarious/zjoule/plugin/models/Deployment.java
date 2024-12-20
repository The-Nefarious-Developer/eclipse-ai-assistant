package com.developer.nefarious.zjoule.plugin.models;

import java.util.Map;

/**
 * Represents detailed information about a deployment in a backend system.
 * <p>
 * This class includes metadata such as configuration details, deployment
 * status, and related resources. It provides utility methods to retrieve
 * information about the model, configurations, and operational status.
 */
public class Deployment {

	/** The unique identifier of the deployment configuration. */
	private String configurationId;

	/** The name of the deployment configuration. */
	private String configurationName;

	/** The creation timestamp of the deployment. */
	private String createdAt;

	/** The URL for accessing the deployment. */
	private String deploymentUrl;

	/** Detailed information about the deployment resources. */
	private Details details;

	/** The unique identifier of the deployment. */
	private String id;

	/** The last operation performed on the deployment. */
	private String lastOperation;

	/** The identifier of the latest running configuration. */
	private String latestRunningConfigurationId;

	/** The last modification timestamp of the deployment. */
	private String modifiedAt;

	/** The unique identifier of the associated scenario. */
	private String scenarioId;

	/** The start time of the deployment. */
	private String startTime;

	/** The current status of the deployment. */
	private String status;

	/** The submission timestamp of the deployment. */
	private String submissionTime;

	/** The target status of the deployment. */
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

	/**
	 * Retrieves the name of the model associated with this deployment.
	 *
	 * @return the name of the model.
	 */
	public String getModelName() {
		return getDetails().getResources().get("backendDetails").getModel().getName();
	}
}

/**
 * Represents detailed information about a backend resource.
 */
class BackendDetails {

	/** The model associated with the backend resource. */
	private Model model;

	public Model getModel() {
		return model;
	}

	public void setModel(final Model model) {
		this.model = model;
	}
}

/**
 * Represents additional details about a deployment, including resources and
 * scaling information.
 */
class Details {

	/** Resources associated with the deployment, keyed by resource type. */
	private Map<String, BackendDetails> resources;

	/** Scaling information for the deployment. */
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

/**
 * Represents a model with a name and version.
 */
class Model {

	/** The name of the model. */
	private String name;

	/** The version of the model. */
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
