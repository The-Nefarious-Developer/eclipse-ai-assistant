package com.developer.nefarious.zjoule.plugin.chat.openai;

import java.util.List;

import com.developer.nefarious.zjoule.plugin.chat.IChatMessage;
import com.developer.nefarious.zjoule.plugin.models.Role;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

/**
 * Represents the request body for an OpenAI chat completion API call.
 * This class encapsulates the parameters required by the OpenAI API,
 * including chat messages, maximum tokens, temperature, penalties, and stop sequences.
 */
public class OpenAIRequestBody {

    /** A list of chat messages that provide context for the chat completion request. */
    private List<IChatMessage> messages;

    /** The maximum number of tokens allowed in the response. */
    @SerializedName("max_tokens")
    private int maxTokens;

    /** The temperature parameter for controlling randomness in the AI's response. */
    private double temperature;

    /** The penalty applied for repeated phrases in the AI's response. */
    @SerializedName("frequency_penalty")
    private double frequencyPenalty;

    /** The penalty applied for encouraging diverse topic generation in the AI's response. */
    @SerializedName("presence_penalty")
    private double presencePenalty;

    /** The sequence used to terminate the AI's response. */
    private String stop;

    // Getters

    /**
     * Retrieves the frequency penalty applied to the response.
     *
     * @return the frequency penalty as a {@code double}.
     */
    public double getFrequencyPenalty() {
        return frequencyPenalty;
    }

    /**
     * Retrieves the maximum number of tokens allowed in the response.
     *
     * @return the maximum token limit as an {@code int}.
     */
    public int getMaxTokens() {
        return maxTokens;
    }

    /**
     * Retrieves the list of chat messages in the request.
     *
     * @return a {@link List} of {@link IChatMessage} objects.
     */
    public List<IChatMessage> getMessages() {
        return messages;
    }

    /**
     * Retrieves the presence penalty applied to the response.
     *
     * @return the presence penalty as a {@code double}.
     */
    public double getPresencePenalty() {
        return presencePenalty;
    }

    /**
     * Retrieves the stop sequence used to terminate the AI's response.
     *
     * @return the stop sequence as a {@link String}.
     */
    public String getStop() {
        return stop;
    }

    /**
     * Retrieves the temperature parameter for the AI's response.
     *
     * @return the temperature as a {@code double}.
     */
    public double getTemperature() {
        return temperature;
    }

    // Setters

    /**
     * Sets the frequency penalty applied to the response.
     *
     * @param frequencyPenalty the frequency penalty as a {@code double}.
     */
    public void setFrequencyPenalty(final double frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
    }

    /**
     * Sets the maximum number of tokens allowed in the response.
     *
     * @param maxTokens the maximum token limit as an {@code int}.
     */
    public void setMaxTokens(final int maxTokens) {
        this.maxTokens = maxTokens;
    }

    /**
     * Sets the list of chat messages in the request.
     *
     * @param messages a {@link List} of {@link IChatMessage} objects.
     */
    public void setMessages(final List<IChatMessage> messages) {
        this.messages = messages;
    }

    /**
     * Sets the presence penalty applied to the response.
     *
     * @param presencePenalty the presence penalty as a {@code double}.
     */
    public void setPresencePenalty(final double presencePenalty) {
        this.presencePenalty = presencePenalty;
    }

    /**
     * Sets the stop sequence used to terminate the AI's response.
     *
     * @param stop the stop sequence as a {@link String}.
     */
    public void setStop(final String stop) {
        this.stop = stop;
    }

    /**
     * Sets the temperature parameter for the AI's response.
     *
     * @param temperature the temperature as a {@code double}.
     */
    public void setTemperature(final double temperature) {
        this.temperature = temperature;
    }

    /**
     * Serializes this object to its JSON representation using {@link Gson}.
     * The {@link Role.RoleSerializer} is registered to handle role serialization.
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
