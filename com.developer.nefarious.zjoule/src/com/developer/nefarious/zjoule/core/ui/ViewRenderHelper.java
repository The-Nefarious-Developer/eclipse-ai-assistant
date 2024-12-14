package com.developer.nefarious.zjoule.core.ui;

import java.util.List;
import com.developer.nefarious.zjoule.chat.memory.MemoryMessageHistory;
import com.developer.nefarious.zjoule.chat.models.Message;
import com.developer.nefarious.zjoule.chat.models.MessageHistory;
import com.developer.nefarious.zjoule.models.Role;

public class ViewRenderHelper implements IViewRenderHelper {
	
	private static ViewRenderHelper instance;
	
	private ViewRenderHelper() { }
	
	public static IViewRenderHelper getInstance() {
		if (instance == null) {
			instance = new ViewRenderHelper();
		}
		return instance;
	}

	@Override
	public StringBuilder getChatHistory() {
		StringBuilder result = new StringBuilder();
		
		MemoryMessageHistory memoryMessageHistory = MemoryMessageHistory.getInstance();
		MessageHistory messageHistory = memoryMessageHistory.load();
		
		if (messageHistory == null) {
			return result;
		}
		
		List<Message> messages = messageHistory.getMessages();
		
		messages.forEach(message -> result.append(createMessageTag(message)));
		return result;
	}
	
	private String createMessageTag(final Message message) {
		return (message.getRole() == Role.USER)
				? "<div class=\"message user-message\">" + message.getContent() + "</div>" 
				: "<div class=\"message bot-message\">" + message.getContent() + "</div>";
	}

}
