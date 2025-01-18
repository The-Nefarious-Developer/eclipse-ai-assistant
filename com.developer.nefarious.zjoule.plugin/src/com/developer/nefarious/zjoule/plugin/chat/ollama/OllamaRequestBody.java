package com.developer.nefarious.zjoule.plugin.chat.ollama;

import java.util.List;

import com.developer.nefarious.zjoule.plugin.chat.IChatMessage;
import com.developer.nefarious.zjoule.plugin.models.Role;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OllamaRequestBody {

    private String model;

    private boolean stream;

    private List<IChatMessage> messages;

    public String getModel() {
        return model;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public boolean isStream() {
        return stream;
    }

    public void setStream(final boolean stream) {
        this.stream = stream;
    }

    public List<IChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(final List<IChatMessage> messages) {
        this.messages = messages;
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
