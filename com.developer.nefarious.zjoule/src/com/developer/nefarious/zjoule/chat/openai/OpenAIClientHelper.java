package com.developer.nefarious.zjoule.chat.openai;

import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.util.List;
import com.developer.nefarious.zjoule.chat.IMessage;
import com.google.gson.Gson;

public class OpenAIClientHelper implements IOpenAIClientHelper {

	@Override
	public BodyPublisher createRequestBody(final List<OpenAIMessage> messages) {
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

	@Override
	public IMessage convertResponseToObject(final String serializedResponseBody) {
		Gson gson = new Gson();
		OpenAIRequestResponse deserializedResponseBody = gson.fromJson(serializedResponseBody, OpenAIRequestResponse.class);
		return getFirstAnswer(deserializedResponseBody);
	}
	
	private IMessage getFirstAnswer(final OpenAIRequestResponse responseBody) {
		return responseBody.getChoices().get(0).getMessage();
	}

}
