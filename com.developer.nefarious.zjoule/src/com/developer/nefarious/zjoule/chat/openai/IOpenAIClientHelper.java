package com.developer.nefarious.zjoule.chat.openai;

import java.net.http.HttpRequest.BodyPublisher;
import java.util.List;
import com.developer.nefarious.zjoule.chat.IChatMessage;

public interface IOpenAIClientHelper {
	
	static final int MAX_TOKENS = 4096;
	
	static final double TEMPERATURE = 0;
	
	static final double FREQUENCY_PENALTY = 0;
	
	static final double PRESENCE_PENALTY = 0;
	
	static final String STOP = "null";
	
	BodyPublisher createRequestBody(final List<IChatMessage> messages);
	
	IChatMessage convertResponseToObject(final String responseBody);

}
