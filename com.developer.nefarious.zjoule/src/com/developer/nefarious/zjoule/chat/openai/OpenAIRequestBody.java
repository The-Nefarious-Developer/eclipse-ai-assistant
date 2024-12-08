package com.developer.nefarious.zjoule.chat.openai;

import java.util.List;
import com.developer.nefarious.zjoule.models.Role;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class OpenAIRequestBody {

	private List<OpenAIMessage> messages;

	@SerializedName("max_tokens")
	private int maxTokens;

	private double temperature;

	@SerializedName("frequency_penalty")
	private double frequencyPenalty;

	@SerializedName("presence_penalty")
	private double presencePenalty;

	private String stop;

	public List<OpenAIMessage> getMessages() {
		return messages;
	}

	public void setMessages(final List<OpenAIMessage> messages) {
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
