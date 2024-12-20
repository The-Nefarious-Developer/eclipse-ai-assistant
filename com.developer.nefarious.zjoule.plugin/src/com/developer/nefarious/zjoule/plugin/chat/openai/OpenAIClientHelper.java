package com.developer.nefarious.zjoule.plugin.chat.openai;

import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.util.List;

import com.developer.nefarious.zjoule.plugin.chat.IChatMessage;
import com.google.gson.Gson;

/**
 * A helper class for the {@code OpenAIClient} that provides utility methods for
 * creating HTTP request bodies and converting responses from the OpenAI service.
 * Implements the {@link IOpenAIClientHelper} interface.
 */
public class OpenAIClientHelper implements IOpenAIClientHelper {

	/**
     * {@inheritDoc}
     */
    @Override
    public IChatMessage convertResponseToObject(final String serializedResponseBody) {
        Gson gson = new Gson();
        OpenAIRequestResponse deserializedResponseBody = gson.fromJson(serializedResponseBody, OpenAIRequestResponse.class);
        return getFirstAnswer(deserializedResponseBody);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BodyPublisher createRequestBody(final List<IChatMessage> messages) {
        OpenAIRequestBody requestBody = new OpenAIRequestBody();

        requestBody.setMessages(messages);
        requestBody.setMaxTokens(MAX_TOKENS);
        requestBody.setTemperature(TEMPERATURE);
        requestBody.setFrequencyPenalty(FREQUENCY_PENALTY);
        requestBody.setPresencePenalty(PRESENCE_PENALTY);
        requestBody.setStop(STOP);

        BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(requestBody.toString());
        return HttpRequest.BodyPublishers.fromPublisher(bodyPublisher);
    }

    /**
     * Extracts the first answer from the OpenAI response.
     * 
     * @param responseBody the {@link OpenAIRequestResponse} object containing the response data.
     * @return the first {@link IChatMessage} from the response.
     */
    private IChatMessage getFirstAnswer(final OpenAIRequestResponse responseBody) {
        return responseBody.getChoices().get(0).getMessage();
    }
}
