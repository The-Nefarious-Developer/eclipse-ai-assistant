package com.developer.nefarious.zjoule.login.memory;

import com.developer.nefarious.zjoule.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.memory.IMemoryDeployment;

public class TemporaryMemoryDeployment implements IMemoryDeployment, ITemporaryMemoryObject {

	private static TemporaryMemoryDeployment instance;

	public static final String KEY = "tmp-" + IMemoryDeployment.KEY;

	IEclipseMemory eclipseMemory;

	private TemporaryMemoryDeployment(final IEclipseMemory eclipseMemory) {
		this.eclipseMemory = eclipseMemory;
	}

	public static void initialize(final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new TemporaryMemoryDeployment(eclipseMemory);
		}
	}

	public static TemporaryMemoryDeployment getInstance() {
		if (instance == null) {
			throw new IllegalStateException("TemporaryMemoryDeployment not initialized. Call initialize() first.");
		}
		return instance;
	}

	public static void resetInstance() {
		instance = null;
	}

	@Override
	public void save(final String deployment) {
		eclipseMemory.saveOnEclipsePreferences(KEY, deployment);
	}

	@Override
	public String load() {
		return eclipseMemory.loadFromEclipsePreferences(KEY);
	}

	@Override
	public void persist() {
		String deployment = eclipseMemory.loadFromEclipsePreferences(KEY);
		eclipseMemory.saveOnEclipsePreferences(IMemoryDeployment.KEY, deployment);
	}
}
