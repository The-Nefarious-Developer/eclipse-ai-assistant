package com.developer.nefarious.zjoule.core.functions;

import java.util.List;
import org.eclipse.swt.browser.Browser;
import com.developer.nefarious.zjoule.chat.memory.MemoryMessageHistory;
import com.developer.nefarious.zjoule.chat.models.Message;
import com.developer.nefarious.zjoule.chat.models.MessageHistory;
import com.developer.nefarious.zjoule.models.Role;

public abstract class MessageHistoryLoader {

	public static void loadFromMemory(final Browser browser) {
		MemoryMessageHistory memoryMessageHistory = MemoryMessageHistory.getInstance();
		MessageHistory messageHistory = memoryMessageHistory.load();

		if (messageHistory != null) {
			List<Message> messages = messageHistory.getMessages();

			if (messages != null) {
				messages.forEach(message -> {
					String escapedContent = escapeJavaScript(message.getContent());
					if (message.getRole() == Role.USER) {
						browser.execute("addUserMessage('" + escapedContent + "');");
					}
					if (message.getRole() == Role.ASSISTANT) {
						browser.execute("addBotMessage('" + escapedContent + "');");
					}
				});
			}

		}

	}

	private static String escapeJavaScript(String content) {
		if (content == null) {
			return "";
		}

		return content.replace("\\", "\\\\") // Escape backslashes
				.replace("'", "\\'") // Escape single quotes
				.replace("\"", "\\\"") // Escape double quotes
				.replace("\n", "\\n") // Escape newlines
				.replace("\r", "\\r") // Escape carriage returns
				.replace("\t", "\\t"); // Escape tabs
	}

}
