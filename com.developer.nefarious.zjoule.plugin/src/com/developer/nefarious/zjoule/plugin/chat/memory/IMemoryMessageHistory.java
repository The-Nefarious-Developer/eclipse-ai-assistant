package com.developer.nefarious.zjoule.plugin.chat.memory;

import com.developer.nefarious.zjoule.plugin.chat.models.MessageHistory;

public interface IMemoryMessageHistory {

	public static final String KEY = "message-history";

	void clear();

	Boolean isEmpty();

	MessageHistory load();

	void save(final MessageHistory messages);

}
