package com.developer.nefarious.zjoule.plugin.chat.openai;

import java.util.List;

import com.developer.nefarious.zjoule.plugin.chat.IChatMessage;
import com.developer.nefarious.zjoule.plugin.models.Role;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class OpenAIRequestBody {

	private List<IChatMessage> messages;

	@SerializedName("max_tokens")
	private int maxTokens;

	private double temperature;

	@SerializedName("frequency_penalty")
	private double frequencyPenalty;

	@SerializedName("presence_penalty")
	private double presencePenalty;

	private String stop;

	public double getFrequencyPenalty() {
		return frequencyPenalty;
	}

	public int getMaxTokens() {
		return maxTokens;
	}

	public List<IChatMessage> getMessages() {
		return messages;
	}

	public double getPresencePenalty() {
		return presencePenalty;
	}

	public String getStop() {
		return stop;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setFrequencyPenalty(final double frequencyPenalty) {
		this.frequencyPenalty = frequencyPenalty;
	}

	public void setMaxTokens(final int maxTokens) {
		this.maxTokens = maxTokens;
	}

	public void setMessages(final List<IChatMessage> messages) {
		this.messages = messages;
	}

	public void setPresencePenalty(final double presencePenalty) {
		this.presencePenalty = presencePenalty;
	}

	public void setStop(final String stop) {
		this.stop = stop;
	}

	public void setTemperature(final double temperature) {
		this.temperature = temperature;
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
