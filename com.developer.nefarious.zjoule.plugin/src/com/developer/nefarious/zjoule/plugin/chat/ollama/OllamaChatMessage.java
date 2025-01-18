package com.developer.nefarious.zjoule.plugin.chat.ollama;

import com.developer.nefarious.zjoule.plugin.chat.IChatMessage;
import com.developer.nefarious.zjoule.plugin.models.Role;

/**
 * Represents a chat message exchanged with the Ollama model.
 * The {@code OllamaChatMessage} class implements {@link IChatMessage} and 
 * encapsulates the role and content of a message in the chat context.
 */
public class OllamaChatMessage implements IChatMessage {
	

    /** The role of the message (e.g., user, system, assistant). */
    private Role role;

    /** The content of the message. */
    private String content;

    /**
     * Default constructor for creating an empty {@code OllamaChatMessage}.
     */
    public OllamaChatMessage() { }

    /**
     * Constructs a new {@code OllamaChatMessage} with the specified role and content.
     *
     * @param role the role of the message (e.g., {@link Role#USER}, {@link Role#SYSTEM}).
     * @param content the content of the message.
     */
    public OllamaChatMessage(final Role role, final String content) {
        this.role = role;
        this.content = content;
    }

    /**
     * Retrieves the content of the message.
     *
     * @return the content of the message as a {@link String}.
     */
    public String getContent() {
        return content;
    }

    /**
     * Retrieves the message content.
     * This is the implementation of the {@link IChatMessage#getMessage()} method.
     *
     * @return the message content as a {@link String}.
     */
    @Override
    public String getMessage() {
        return content;
    }

    /**
     * Retrieves the role associated with the message.
     * This is the implementation of the {@link IChatMessage#getRole()} method.
     *
     * @return the role of the message as a {@link Role}.
     */
    @Override
    public Role getRole() {
        return role;
    }

    /**
     * Sets the content of the message.
     *
     * @param content the new content of the message.
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * Sets the role of the message.
     *
     * @param role the new role of the message (e.g., {@link Role#USER}, {@link Role#SYSTEM}).
     */
    public void setRole(final Role role) {
        this.role = role;
    }

}
