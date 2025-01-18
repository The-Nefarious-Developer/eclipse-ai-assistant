package com.developer.nefarious.zjoule.plugin.chat;

import java.io.IOException;
import java.util.List;

import com.developer.nefarious.zjoule.plugin.models.Role;

/**
 * Interface for AI client implementations.
 * Defines methods for interacting with AI models, managing chat messages,
 * and facilitating chat completion requests.
 * 
 * Implementations, such as {@code OpenAIClient}, handle communication with specific AI platforms
 * by utilizing authentication, message history, and model-specific configurations.
 */
public interface IAIClient {

    /**
     * Sends a list of chat messages to the AI model and retrieves the AI-generated response.
     *
     * @param messages the list of {@link IChatMessage} objects representing the chat history 
     *                 and the user's current prompt.
     * @return the AI-generated response as an {@link IChatMessage}.
     * @throws IOException if an I/O error occurs during the request.
     * @throws InterruptedException if the operation is interrupted.
     */
    IChatMessage chatCompletion(final List<IChatMessage> messages) throws IOException, InterruptedException;

    /**
     * Creates a new chat message with the specified role and content.
     * 
     * Roles, such as {@link Role#USER} or {@link Role#SYSTEM}, determine the context of the message
     * within the chat.
     *
     * @param role the role of the message (e.g., user, system, assistant).
     * @param userPrompt the content of the message as a {@link String}.
     * @return a newly created {@link IChatMessage}.
     */
    IChatMessage createMessage(final Role role, final String userPrompt);

    /**
     * Retrieves the chat message history.
     * This method returns a list of previously exchanged messages that are stored in memory.
     *
     * @return a list of {@link IChatMessage} objects representing the chat history.
     */
    List<IChatMessage> getMessageHistory();

    /**
     * Updates the chat message history with the provided list of messages.
     * 
     * @param chatMessages the list of {@link IChatMessage} objects to save as the updated chat history.
     */
    void setMessageHistory(final List<IChatMessage> chatMessages);

}
