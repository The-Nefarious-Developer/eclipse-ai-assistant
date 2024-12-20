package com.developer.nefarious.zjoule.plugin.core.functions;

import java.util.List;

import org.eclipse.swt.browser.Browser;

import com.developer.nefarious.zjoule.plugin.chat.memory.MemoryMessageHistory;
import com.developer.nefarious.zjoule.plugin.chat.models.Message;
import com.developer.nefarious.zjoule.plugin.chat.models.MessageHistory;
import com.developer.nefarious.zjoule.plugin.models.Role;

/**
 * Utility class for loading chat message history into a browser component.
 * <p>
 * The {@code MessageHistoryLoader} retrieves stored messages from memory and renders
 * them in a browser using JavaScript functions.
 */
public abstract class MessageHistoryLoader {

    /**
     * Escapes special characters in a string for safe inclusion in JavaScript code.
     *
     * @param content the string to escape.
     * @return the escaped string, or an empty string if {@code content} is {@code null}.
     */
    private static String escapeJavaScript(final String content) {
        if (content == null) {
            return "";
        }

        return content.replace("\\", "\\\\") // Escape backslashes
                      .replace("'", "\\'")  // Escape single quotes
                      .replace("\"", "\\\"") // Escape double quotes
                      .replace("\n", "\\n") // Escape newlines
                      .replace("\r", "\\r") // Escape carriage returns
                      .replace("\t", "\\t"); // Escape tabs
    }

    /**
     * Loads chat message history from memory and displays it in the specified browser.
     * <p>
     * This method retrieves the message history from {@link MemoryMessageHistory}, escapes
     * the content for safe rendering, and invokes JavaScript functions to add the messages
     * to the browser UI.
     * <ul>
     *   <li>User messages are added using {@code addUserMessage()}.</li>
     *   <li>Assistant messages are added using {@code addBotMessage()}.</li>
     * </ul>
     *
     * @param browser the {@link Browser} instance where the messages will be displayed.
     */
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

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private MessageHistoryLoader() { }
}
