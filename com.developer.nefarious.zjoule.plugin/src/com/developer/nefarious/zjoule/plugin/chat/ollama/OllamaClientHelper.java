package com.developer.nefarious.zjoule.plugin.chat.ollama;

import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.util.List;

import com.developer.nefarious.zjoule.plugin.chat.IChatMessage;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;
import com.developer.nefarious.zjoule.plugin.models.OllamaModel;
import com.google.gson.Gson;

/**
 * A helper class for constructing requests and processing responses for the Ollama AI chat system.
 * <p>
 * The {@code OllamaClientHelper} is responsible for serializing chat request bodies,
 * deserializing chat responses, and retrieving the currently selected Ollama model.
 * </p>
 */
public class OllamaClientHelper implements IOllamaClientHelper {
    
    /** In-memory storage for the selected Ollama model. */
    private IMemoryObject<OllamaModel> memoryOllamaModel;

    /**
     * Constructs an {@code OllamaClientHelper} with the specified memory storage.
     *
     * @param memoryOllamaModel the in-memory storage containing the selected Ollama model.
     */
    public OllamaClientHelper(final IMemoryObject<OllamaModel> memoryOllamaModel) {
        this.memoryOllamaModel = memoryOllamaModel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IChatMessage convertResponseToObject(final String serializedResponseBody) {
        Gson gson = new Gson();
        OllamaRequestResponse deserializedResponseBody = gson.fromJson(serializedResponseBody, OllamaRequestResponse.class);
        return deserializedResponseBody.getMessage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BodyPublisher createRequestBody(final List<IChatMessage> messages) {
        OllamaRequestBody requestBody = new OllamaRequestBody();

        requestBody.setModel(getSelectedModel());
        requestBody.setMessages(messages);
        requestBody.setStream(false);

        BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(requestBody.toString());
        return HttpRequest.BodyPublishers.fromPublisher(bodyPublisher);
    }

    /**
     * Retrieves the name of the currently selected Ollama model from memory.
     *
     * @return the name of the selected Ollama model.
     */
    private String getSelectedModel() {
        OllamaModel ollamaModel = memoryOllamaModel.load();
        return ollamaModel.getName();
    }
}