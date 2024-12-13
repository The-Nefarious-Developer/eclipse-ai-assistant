package com.developer.nefarious.zjoule.memory;

import com.developer.nefarious.zjoule.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.models.Deployment;

public class MemoryDeployment implements IMemoryDeployment {

	private static MemoryDeployment instance;

	private IObjectSerializer objectSerializer;

	private IEclipseMemory eclipseMemory;

	private MemoryDeployment(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		this.objectSerializer = objectSerializer;
		this.eclipseMemory = eclipseMemory;
	}

	public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new MemoryDeployment(objectSerializer, eclipseMemory);
		}
	}

	public static MemoryDeployment getInstance() {
		if (instance == null) {
			throw new IllegalStateException("MemoryDeployment not initialized. Call initialize() first.");
		}
		return instance;
	}

	public static void resetInstance() {
		instance = null;
	}

	@Override
	public void save(final Deployment deployment) {
		String serializedObject = objectSerializer.serialize(deployment);
		eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
	}

	@Override
	public Deployment load() {
		try {
			String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
			return objectSerializer.deserialize(serializedObject, Deployment.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Boolean isEmpty() {
		Deployment deployment = load();
		if (deployment == null) {
			return true;
		}
		if (deployment.getConfigurationName() == null) {
			return true;
		}
		if (deployment.getConfigurationName().isEmpty() || 
				deployment.getConfigurationName().isBlank()) {
			return true;
		}
		return false;
	}

}