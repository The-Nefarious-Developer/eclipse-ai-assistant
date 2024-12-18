package com.developer.nefarious.zjoule.plugin.chat.models;

import com.developer.nefarious.zjoule.plugin.models.Role;

/**
 * Represents a single chat message with a role and content.
 * This class is used to encapsulate a message's metadata, including its role
 * (e.g., user, assistant, system) and its textual content.
 */
public class Message {

    /** The role of the message (e.g., user, assistant, system). */
    private Role role;

    /** The textual content of the message. */
    private String content;

    /**
     * Default constructor for creating an empty {@code Message}.
     */
    public Message() { }

    /**
     * Constructs a {@code Message} with the specified role and content.
     *
     * @param role the role of the message (e.g., {@link Role#USER}, {@link Role#SYSTEM}).
     * @param content the textual content of the message.
     */
    public Message(final Role role, final String content) {
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
     * Sets the content of the message.
     *
     * @param content the new content of the message.
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * Retrieves the role of the message.
     *
     * @return the role of the message as a {@link Role}.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of the message.
     *
     * @param role the new role of the message (e.g., {@link Role#USER}, {@link Role#ASSISTANT}).
     */
    public void setRole(final Role role) {
        this.role = role;
    }
}
