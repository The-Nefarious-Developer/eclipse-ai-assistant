package com.developer.nefarious.zjoule.plugin.chat.openai;

import java.net.http.HttpRequest.BodyPublisher;
import java.util.List;

import com.developer.nefarious.zjoule.plugin.chat.IChatMessage;

/**
 * Defines helper methods for interacting with the OpenAI service.
 * This interface provides functionality for processing chat completion requests
 * and parsing responses returned by the OpenAI API.
 */
public interface IOpenAIClientHelper {

    /** The maximum number of tokens allowed in a single request. */
    static final int MAX_TOKENS = 4096;

    /** The temperature parameter for controlling the randomness of AI responses. */
    static final double TEMPERATURE = 0;

    /** The frequency penalty parameter for discouraging repeated phrases. */
    static final double FREQUENCY_PENALTY = 0;

    /** The presence penalty parameter for encouraging diverse topic generation. */
    static final double PRESENCE_PENALTY = 0;

    /** The stop sequence used to terminate AI responses. */
    static final String STOP = "null";

    /**
     * Converts a serialized JSON response body from the OpenAI service into an {@link IChatMessage}.
     * The method deserializes the response and extracts the first message returned by the AI.
     *
     * @param serializedResponseBody the JSON response body as a {@link String}.
     * @return the first {@link IChatMessage} extracted from the response.
     */
    IChatMessage convertResponseToObject(final String serializedResponseBody);

    /**
     * Creates an HTTP request body for a chat completion request to the OpenAI service.
     * The request body includes parameters such as:
     * <ul>
     *   <li>Chat messages providing the context for the conversation.</li>
     *   <li>Maximum number of tokens allowed for the response.</li>
     *   <li>Temperature, frequency penalty, and presence penalty settings.</li>
     *   <li>Stop sequences for terminating the response.</li>
     * </ul>
     *
     * @param messages a list of {@link IChatMessage} objects representing the chat context.
     * @return a {@link BodyPublisher} object containing the serialized request body.
     */
    BodyPublisher createRequestBody(final List<IChatMessage> messages);
}
