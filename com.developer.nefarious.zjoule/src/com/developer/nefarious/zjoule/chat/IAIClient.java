package com.developer.nefarious.zjoule.chat;

import java.io.IOException;
import java.util.List;
import com.developer.nefarious.zjoule.models.Role;

public interface IAIClient<T> {

	IMessage chatCompletion(final List<T> messages) throws IOException, InterruptedException;
	
	IMessage createMessage(final Role role, final String userPrompt);
	
	List<IMessage> getMessageHistory();
	
	void setMessageHistory(final List<IMessage> messages);
	
}
