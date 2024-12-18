package com.developer.nefarious.zjoule.plugin.chat.memory;

import com.developer.nefarious.zjoule.plugin.chat.models.MessageHistory;
import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;

public class MemoryMessageHistory implements IMemoryMessageHistory {

	private static MemoryMessageHistory instance;

	public static MemoryMessageHistory getInstance() {
		if (instance == null) {
			throw new IllegalStateException("MemoryMessageHistory not initialized. Call initialize() first.");
		}
		return instance;
	}

	public static void initialize(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		if (instance == null) {
			instance = new MemoryMessageHistory(objectSerializer, eclipseMemory);
		}
	}

	public static void resetInstance() {
		instance = null;
	}

	private IObjectSerializer objectSerializer;

	private IEclipseMemory eclipseMemory;

	private MemoryMessageHistory(final IObjectSerializer objectSerializer, final IEclipseMemory eclipseMemory) {
		this.objectSerializer = objectSerializer;
		this.eclipseMemory = eclipseMemory;
	}

	@Override
	public void clear() {
		eclipseMemory.deleteFromEclipsePreferences(KEY);
	}

	@Override
	public Boolean isEmpty() {
		MessageHistory messageHistory = load();
		if ((messageHistory == null) || (messageHistory.getMessages() == null) || messageHistory.getMessages().isEmpty()) {
			return true;
		}
		return false;
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

	@Override
	public void save(final MessageHistory messageHistory) {
		String serializedObject = objectSerializer.serialize(messageHistory);
		eclipseMemory.saveOnEclipsePreferences(KEY, serializedObject);
	}

}
