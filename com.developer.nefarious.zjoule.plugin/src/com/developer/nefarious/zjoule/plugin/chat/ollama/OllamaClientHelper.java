package com.developer.nefarious.zjoule.plugin.chat.ollama;

import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.util.List;

import com.developer.nefarious.zjoule.plugin.chat.IChatMessage;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;
import com.developer.nefarious.zjoule.plugin.models.OllamaModel;
import com.google.gson.Gson;

public class OllamaClientHelper implements IOllamaClientHelper {
	
	private IMemoryObject<OllamaModel>  memoryOllamaModel;
	
	public OllamaClientHelper(final IMemoryObject<OllamaModel> memoryOllamaModel) {
		this.memoryOllamaModel = memoryOllamaModel;
	}

	@Override
	public IChatMessage convertResponseToObject(final String serializedResponseBody) {
        Gson gson = new Gson();
        OllamaRequestResponse deserializedResponseBody = gson.fromJson(serializedResponseBody, OllamaRequestResponse.class);
        return deserializedResponseBody.getMessage();
	}

	@Override
	public BodyPublisher createRequestBody(final List<IChatMessage> messages) {
        OllamaRequestBody requestBody = new OllamaRequestBody();
        
        requestBody.setModel(getSelectedModel());
        requestBody.setMessages(messages);
        requestBody.setStream(false);

        BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(requestBody.toString());
        return HttpRequest.BodyPublishers.fromPublisher(bodyPublisher);
	}
	
	private String getSelectedModel() {
		OllamaModel ollamaModel = memoryOllamaModel.load();
		return ollamaModel.getName();
	}

}