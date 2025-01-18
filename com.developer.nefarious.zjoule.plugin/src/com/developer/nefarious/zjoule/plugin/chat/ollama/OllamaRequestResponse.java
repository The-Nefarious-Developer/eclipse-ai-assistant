package com.developer.nefarious.zjoule.plugin.chat.ollama;

import com.google.gson.annotations.SerializedName;

public class OllamaRequestResponse {

    private String model;

    @SerializedName("created_at")
    private String createdAt;

    private OllamaChatMessage message;

    @SerializedName("done_reason")
    private String doneReason;

    private boolean done;

    @SerializedName("total_duration")
    private long totalDuration;

    @SerializedName("load_duration")
    private long loadDuration;

    @SerializedName("prompt_eval_count")
    private int promptEvalCount;

    @SerializedName("prompt_eval_duration")
    private long promptEvalDuration;

    @SerializedName("eval_count")
    private int evalCount;

    @SerializedName("eval_duration")
    private long evalDuration;

    public String getModel() {
        return model;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final String createdAt) {
        this.createdAt = createdAt;
    }

    public OllamaChatMessage getMessage() {
        return message;
    }

    public void setMessage(final OllamaChatMessage message) {
        this.message = message;
    }

    public String getDoneReason() {
        return doneReason;
    }

    public void setDoneReason(final String doneReason) {
        this.doneReason = doneReason;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(final boolean done) {
        this.done = done;
    }

    public long getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(final long totalDuration) {
        this.totalDuration = totalDuration;
    }

    public long getLoadDuration() {
        return loadDuration;
    }

    public void setLoadDuration(final long loadDuration) {
        this.loadDuration = loadDuration;
    }

    public int getPromptEvalCount() {
        return promptEvalCount;
    }

    public void setPromptEvalCount(final int promptEvalCount) {
        this.promptEvalCount = promptEvalCount;
    }

    public long getPromptEvalDuration() {
        return promptEvalDuration;
    }

    public void setPromptEvalDuration(final long promptEvalDuration) {
        this.promptEvalDuration = promptEvalDuration;
    }

    public int getEvalCount() {
        return evalCount;
    }

    public void setEvalCount(final int evalCount) {
        this.evalCount = evalCount;
    }

    public long getEvalDuration() {
        return evalDuration;
    }

    public void setEvalDuration(final long evalDuration) {
        this.evalDuration = evalDuration;
    }
}
