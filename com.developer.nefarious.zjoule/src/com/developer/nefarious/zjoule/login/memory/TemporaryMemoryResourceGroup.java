package com.developer.nefarious.zjoule.login.memory;

import com.developer.nefarious.zjoule.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.memory.IMemoryResourceGroup;
import com.developer.nefarious.zjoule.models.ResourceGroup;
import com.developer.nefarious.zjoule.utils.IObjectSerializer;

public class TemporaryMemoryResourceGroup implements IMemoryResourceGroup, ITemporaryMemoryObject {

	private static TemporaryMemoryResourceGroup instance;

	public static final String KEY = "tmp-" + IMemoryResourceGroup.KEY;

	IObjectSerializer objectSerializer;

	IEclipseMemory eclipseMemory;

	private TemporaryMemoryResourceGroup(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		this.objectSerializer = objectSerializer;
		this.eclipseMemory = eclipseMemory;
	}

	public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new TemporaryMemoryResourceGroup(objectSerializer, eclipseMemory);
		}
	}

	public static TemporaryMemoryResourceGroup getInstance() {
		if (instance == null) {
			throw new IllegalStateException("TemporaryMemoryAccessToken not initialized. Call initialize() first.");
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

	@Override
	public void persist() {
		String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
		eclipseMemory.saveOnEclipsePreferences(IMemoryResourceGroup.KEY, serializedObject);
	}
}
