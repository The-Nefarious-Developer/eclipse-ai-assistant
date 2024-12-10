package com.developer.nefarious.zjoule.chat.models;

import java.util.ArrayList;
import java.util.List;

public class MessageHistory {
	
	private List<Message> messages = new ArrayList<>();

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(final List<Message> messages) {
		this.messages = messages;
	}

}
