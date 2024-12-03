package com.developer.nefarious.zjoule.memory;

public class MemoryResourceGroup implements IMemoryResourceGroup {

	private static MemoryResourceGroup instance;

	private IEclipseMemory eclipseMemory;

	private MemoryResourceGroup(final IEclipseMemory eclipseMemory) {
		this.eclipseMemory = eclipseMemory;
	}

	public static void initialize(final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new MemoryResourceGroup(eclipseMemory);
		}
	}

	public static MemoryResourceGroup getInstance() {
		if (instance == null) {
			throw new IllegalStateException("MemoryResourceGroup not initialized. Call initialize() first.");
		}
		return instance;
	}

	public static void resetInstance() {
		instance = null;
	}

	@Override
	public void save(final String resourceGroup) {
		eclipseMemory.saveOnEclipsePreferences(KEY, resourceGroup);
	}

	@Override
	public String load() {
		return eclipseMemory.loadFromEclipsePreferences(KEY);
	}

}
