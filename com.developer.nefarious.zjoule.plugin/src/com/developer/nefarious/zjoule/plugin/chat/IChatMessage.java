package com.developer.nefarious.zjoule.plugin.chat;

import com.developer.nefarious.zjoule.plugin.models.Role;

/**
 * Represents a message exchanged in a chat interaction with an AI model.
 * Implementations of this interface encapsulate the content of the message and
 * its associated role (e.g., user, system, assistant).
 */
public interface IChatMessage {

    /**
     * Retrieves the content of the chat message.
     *
     * @return the message content as a {@link String}.
     */
    String getMessage();

    /**
     * Retrieves the role associated with the message.
     * Roles define the context of the message within the chat, such as:
     * <ul>
     *   <li>{@link Role#USER}: The end user sending a message.</li>
     *   <li>{@link Role#SYSTEM}: System-provided instructions or context.</li>
     *   <li>{@link Role#ASSISTANT}: The AI assistant's response.</li>
     * </ul>
     *
     * @return the {@link Role} of the message.
     */
    Role getRole();
}
