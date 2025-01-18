package com.developer.nefarious.zjoule.plugin.chat;

/**
 * Interface for orchestrating AI-based chat interactions.
 * Implementations of this interface are responsible for managing the process
 * of generating AI responses based on user prompts and contextual information.
 */
public interface IChatOrchestrator {

    /**
     * Generates an AI response for the given user prompt by performing the following steps:
     * <ol>
     *   <li>Determining which AI client to use.</li>
     *   <li>Fetching the chat message history from the client.</li>
     *   <li>Retrieving the content of the active editor window as context.</li>
     *   <li>Creating system and user messages for the AI.</li>
     *   <li>Obtaining the response from the AI client via a chat completion request.</li>
     *   <li>Saving the updated chat history back to the AI client.</li>
     *   <li>Returning the response message as a string.</li>
     * </ol>
     *
     * @param userPrompt the prompt or query provided by the user
     * @param editorContent the editor content to be provided as context 
     * @return the AI-generated response as a {@link String}.
     */
	String getAnswer(final String userPrompt, final String editorContent);

}
