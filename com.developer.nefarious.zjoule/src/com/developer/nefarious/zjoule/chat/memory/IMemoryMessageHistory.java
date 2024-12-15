package com.developer.nefarious.zjoule.chat.memory;

import com.developer.nefarious.zjoule.chat.models.MessageHistory;

public interface IMemoryMessageHistory {
	
	public static final String KEY = "message-history";
	
	void save(final MessageHistory messages);
	
	MessageHistory load();
	
	void clear();
	
	Boolean isEmpty();

}
