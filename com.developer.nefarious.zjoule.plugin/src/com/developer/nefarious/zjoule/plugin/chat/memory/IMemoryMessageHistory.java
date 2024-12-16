package com.developer.nefarious.zjoule.plugin.chat.memory;

import com.developer.nefarious.zjoule.plugin.chat.models.MessageHistory;

public interface IMemoryMessageHistory {
	
	public static final String KEY = "message-history";
	
	void save(final MessageHistory messages);
	
	MessageHistory load();
	
	void clear();
	
	Boolean isEmpty();

}
