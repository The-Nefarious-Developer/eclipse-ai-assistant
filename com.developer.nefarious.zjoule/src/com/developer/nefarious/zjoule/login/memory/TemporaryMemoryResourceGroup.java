package com.developer.nefarious.zjoule.login.memory;

import com.developer.nefarious.zjoule.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.memory.IMemoryResourceGroup;

public class TemporaryMemoryResourceGroup implements IMemoryResourceGroup, ITemporaryMemoryObject {

	private static TemporaryMemoryResourceGroup instance;

	public static final String KEY = "tmp-" + IMemoryResourceGroup.KEY;

	IEclipseMemory eclipseMemory;

	private TemporaryMemoryResourceGroup(final IEclipseMemory eclipseMemory) {
		this.eclipseMemory = eclipseMemory;
	}

	public static void initialize(final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new TemporaryMemoryResourceGroup(eclipseMemory);
		}
	}

	public static TemporaryMemoryResourceGroup getInstance() {
		if (instance == null) {
			throw new IllegalStateException("TemporaryMemoryResourceGroup not initialized. Call initialize() first.");
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

	@Override
	public void persist() {
		String resourceGroup = eclipseMemory.loadFromEclipsePreferences(KEY);
		eclipseMemory.saveOnEclipsePreferences(IMemoryResourceGroup.KEY, resourceGroup);
	}
}
