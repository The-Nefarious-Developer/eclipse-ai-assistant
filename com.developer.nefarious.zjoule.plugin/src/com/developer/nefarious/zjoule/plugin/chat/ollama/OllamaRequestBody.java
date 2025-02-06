package com.developer.nefarious.zjoule.plugin.chat.ollama;

import java.util.List;

import com.developer.nefarious.zjoule.plugin.chat.IChatMessage;
import com.developer.nefarious.zjoule.plugin.models.Role;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Represents the request body for sending chat interactions to the Ollama API.
 * <p>
 * This class contains the model name, streaming flag, and a list of chat messages.
 * It also provides serialization to JSON using the {@link Gson} library.
 * </p>
 */
public class OllamaRequestBody {

    /** The name of the AI model to use for chat completion. */
    private String model;

    /** Indicates whether the chat response should be streamed. */
    private boolean stream;

    /** The list of chat messages forming the conversation history. */
    private List<IChatMessage> messages;

    /**
     * Retrieves the name of the AI model.
     *
     * @return the model name as a {@link String}.
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the name of the AI model.
     *
     * @param model the model name to set.
     */
    public void setModel(final String model) {
        this.model = model;
    }

    /**
     * Checks if streaming is enabled for the chat response.
     *
     * @return {@code true} if streaming is enabled, {@code false} otherwise.
     */
    public boolean isStream() {
        return stream;
    }

    /**
     * Enables or disables streaming for the chat response.
     *
     * @param stream {@code true} to enable streaming, {@code false} to disable.
     */
    public void setStream(final boolean stream) {
        this.stream = stream;
    }

    /**
     * Retrieves the list of chat messages included in the request.
     *
     * @return a list of {@link IChatMessage} objects.
     */
    public List<IChatMessage> getMessages() {
        return messages;
    }

    /**
     * Sets the list of chat messages to be included in the request.
     *
     * @param messages a list of {@link IChatMessage} objects.
     */
    public void setMessages(final List<IChatMessage> messages) {
        this.messages = messages;
    }

    /**
     * Serializes this object to its JSON representation using {@link Gson}.
     * <p>
     * The {@link Role.RoleSerializer} is registered to handle role serialization.
     * </p>
     *
     * @return the JSON representation of this request body as a {@link String}.
     */
    @Override
    public String toString() {
        // @formatter:off
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Role.class, new Role.RoleSerializer())
            .create();
        // @formatter:on
        return gson.toJson(this);
    }
}