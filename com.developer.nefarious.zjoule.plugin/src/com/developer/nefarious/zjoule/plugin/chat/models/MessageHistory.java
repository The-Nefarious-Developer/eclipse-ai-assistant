package com.developer.nefarious.zjoule.plugin.chat.models;

import java.util.List;

/**
 * Represents the history of chat messages in a conversation.
 * This class encapsulates a list of {@link Message} objects, which represent
 * the messages exchanged during a chat session.
 */
public class MessageHistory {

    /** The list of messages in the chat history. */
    private List<Message> messages;

    /**
     * Retrieves the list of messages in the chat history.
     *
     * @return a {@link List} of {@link Message} objects representing the chat history.
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Sets the list of messages in the chat history.
     *
     * @param messages a {@link List} of {@link Message} objects to set as the chat history.
     */
    public void setMessages(final List<Message> messages) {
        this.messages = messages;
    }
}
