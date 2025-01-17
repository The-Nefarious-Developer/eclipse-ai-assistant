package com.developer.nefarious.zjoule.plugin.chat.ollama;

import java.net.http.HttpRequest.BodyPublisher;
import java.util.List;

import com.developer.nefarious.zjoule.plugin.chat.IChatMessage;

public class OllamaClientHelper implements IOllamaClientHelper {

	@Override
	public IChatMessage convertResponseToObject(String serializedResponseBody) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BodyPublisher createRequestBody(List<IChatMessage> messages) {
		// TODO Auto-generated method stub
		return null;
	}

}
