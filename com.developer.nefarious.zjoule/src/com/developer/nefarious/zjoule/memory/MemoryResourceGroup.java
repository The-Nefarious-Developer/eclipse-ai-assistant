package com.developer.nefarious.zjoule.memory;

import com.developer.nefarious.zjoule.models.ResourceGroup;
import com.developer.nefarious.zjoule.utils.IObjectSerializer;

public class MemoryResourceGroup implements IMemoryResourceGroup {

	private static MemoryResourceGroup instance;

	private IObjectSerializer objectSerializer;

	private IEclipseMemory eclipseMemory;

	private MemoryResourceGroup(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		this.objectSerializer = objectSerializer;
		this.eclipseMemory = eclipseMemory;
	}

	public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new MemoryResourceGroup(objectSerializer, eclipseMemory);
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
	public void save(final ResourceGroup resourceGroup) {
		String serializedObject = objectSerializer.serialize(resourceGroup);
		eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
	}

	@Override
	public ResourceGroup load() {
		String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
		return objectSerializer.deserialize(serializedObject, ResourceGroup.class);
	}

}
