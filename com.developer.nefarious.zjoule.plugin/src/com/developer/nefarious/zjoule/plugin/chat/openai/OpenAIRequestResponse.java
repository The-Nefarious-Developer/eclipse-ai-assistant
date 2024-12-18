package com.developer.nefarious.zjoule.plugin.chat.openai;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class OpenAIRequestResponse {

    public static class Choice {

        private int index;

        @SerializedName("finish_reason")
        private String finishReason;

        private OpenAIChatMessage message;

		public String getFinishReason() {
			return finishReason;
		}

		public int getIndex() {
			return index;
		}

		public OpenAIChatMessage getMessage() {
			return message;
		}

		public void setFinishReason(final String finishReason) {
			this.finishReason = finishReason;
		}

		public void setIndex(final int index) {
			this.index = index;
		}

		public void setMessage(final OpenAIChatMessage message) {
			this.message = message;
		}

    }

    public static class Usage {

        @SerializedName("prompt_tokens")
        private int promptTokens;

        @SerializedName("completion_tokens")
        private int completionTokens;

        @SerializedName("total_tokens")
        private int totalTokens;

		public int getCompletionTokens() {
			return completionTokens;
		}

		public int getPromptTokens() {
			return promptTokens;
		}

		public int getTotalTokens() {
			return totalTokens;
		}

		public void setCompletionTokens(final int completionTokens) {
			this.completionTokens = completionTokens;
		}

		public void setPromptTokens(final int promptTokens) {
			this.promptTokens = promptTokens;
		}

		public void setTotalTokens(final int totalTokens) {
			this.totalTokens = totalTokens;
		}

    }

    private String id;

    private String object;

    private long created;

    private String model;

    private List<Choice> choices;

    private Usage usage;

	public List<Choice> getChoices() {
		return choices;
	}

	public long getCreated() {
		return created;
	}

	public String getId() {
		return id;
	}

	public String getModel() {
		return model;
	}

	public String getObject() {
		return object;
	}

	public Usage getUsage() {
		return usage;
	}

	public void setChoices(final List<Choice> choices) {
		this.choices = choices;
	}

	public void setCreated(final long created) {
		this.created = created;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public void setModel(final String model) {
		this.model = model;
	}

	public void setObject(final String object) {
		this.object = object;
	}

	public void setUsage(final Usage usage) {
		this.usage = usage;
	}

}
