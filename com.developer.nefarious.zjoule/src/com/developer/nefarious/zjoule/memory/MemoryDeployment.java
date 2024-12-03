package com.developer.nefarious.zjoule.memory;

public class MemoryDeployment implements IMemoryDeployment {

	private static MemoryDeployment instance;

	private IEclipseMemory eclipseMemory;

	private MemoryDeployment(final IEclipseMemory eclipseMemory) {
		this.eclipseMemory = eclipseMemory;
	}

	public static void initialize(final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new MemoryDeployment(eclipseMemory);
		}
	}

	public static MemoryDeployment getInstance() {
		if (instance == null) {
			throw new IllegalStateException("MemoryAccessToken not initialized. Call initialize() first.");
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
	
}