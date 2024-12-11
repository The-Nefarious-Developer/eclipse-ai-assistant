package com.developer.nefarious.zjoule.chat.memory;

import com.developer.nefarious.zjoule.chat.models.MessageHistory;
import com.developer.nefarious.zjoule.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.memory.utils.IObjectSerializer;

public class MemoryMessageHistory implements IMemoryMessageHistory {

	private static MemoryMessageHistory instance;

	private IObjectSerializer objectSerializer;

	private IEclipseMemory eclipseMemory;

	private MemoryMessageHistory(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		this.objectSerializer = objectSerializer;
		this.eclipseMemory = eclipseMemory;
	}

	public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new MemoryMessageHistory(objectSerializer, eclipseMemory);
		}
	}

	public static MemoryMessageHistory getInstance() {
		if (instance == null) {
			throw new IllegalStateException("MemoryMessageHistory not initialized. Call initialize() first.");
		}
		return instance;
	}

	public static void resetInstance() {
		instance = null;
	}

	@Override
	public void save(final MessageHistory messageHistory) {
		String serializedObject = objectSerializer.serialize(messageHistory);
		eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
	}

	@Override
	public MessageHistory load() {
		try {
			String serializedObject = eclipseMemory.loadFromEclipsePreferences(KEY);
			return objectSerializer.deserialize(serializedObject, MessageHistory.class);
		} catch (Exception e) {
			return null;
		}
	}

}
