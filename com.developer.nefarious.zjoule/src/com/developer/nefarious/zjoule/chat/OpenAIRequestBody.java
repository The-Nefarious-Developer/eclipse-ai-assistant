package com.developer.nefarious.zjoule.chat;

import java.util.List;
import com.developer.nefarious.zjoule.models.Message;
import com.google.gson.annotations.SerializedName;

public class OpenAIRequestBody {
	
	private List<Message> messages;
	
	@SerializedName("max_tokens")
    private int maxTokens;
    
    private double temperature;
    
    @SerializedName("frequency_penalty")
    private double frequencyPenalty;
    
    @SerializedName("presence_penalty")
    private double presencePenalty;
    
    private String stop;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(final List<Message> messages) {
        this.messages = messages;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(final int maxTokens) {
        this.maxTokens = maxTokens;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(final double temperature) {
        this.temperature = temperature;
    }

    public double getFrequencyPenalty() {
        return frequencyPenalty;
    }

    public void setFrequencyPenalty(final double frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
    }

    public double getPresencePenalty() {
        return presencePenalty;
    }

    public void setPresencePenalty(final double presencePenalty) {
        this.presencePenalty = presencePenalty;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(final String stop) {
        this.stop = stop;
    }

}
