package com.developer.nefarious.zjoule.chat.openai;

import java.net.http.HttpRequest.BodyPublisher;
import java.util.List;

public interface IOpenAIClientHelper {
	
	BodyPublisher createRequestBody(final List<OpenAIMessage> messages);
	
	String convertResponseToObject(final String responseBody);

}
