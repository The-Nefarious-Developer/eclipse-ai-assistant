package com.developer.nefarious.zjoule.plugin.chat.openai;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OpenAIRequestResponse {

    private String id;

    private String object;

    private long created;

    private String model;

    private List<Choice> choices;

    private Usage usage;

    public static class Choice {
    	
        private int index;

        @SerializedName("finish_reason")
        private String finishReason;

        private OpenAIChatMessage message;

		public int getIndex() {
			return index;
		}

		public void setIndex(final int index) {
			this.index = index;
		}

		public String getFinishReason() {
			return finishReason;
		}

		public void setFinishReason(final String finishReason) {
			this.finishReason = finishReason;
		}

		public OpenAIChatMessage getMessage() {
			return message;
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

		public int getPromptTokens() {
			return promptTokens;
		}

		public void setPromptTokens(final int promptTokens) {
			this.promptTokens = promptTokens;
		}

		public int getCompletionTokens() {
			return completionTokens;
		}

		public void setCompletionTokens(final int completionTokens) {
			this.completionTokens = completionTokens;
		}

		public int getTotalTokens() {
			return totalTokens;
		}

		public void setTotalTokens(final int totalTokens) {
			this.totalTokens = totalTokens;
		}
        
    }

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getObject() {
		return object;
	}

	public void setObject(final String object) {
		this.object = object;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(final long created) {
		this.created = created;
	}

	public String getModel() {
		return model;
	}

	public void setModel(final String model) {
		this.model = model;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(final List<Choice> choices) {
		this.choices = choices;
	}

	public Usage getUsage() {
		return usage;
	}

	public void setUsage(final Usage usage) {
		this.usage = usage;
	}
    
}
