package com.developer.nefarious.zjoule.plugin.chat.openai;

import com.developer.nefarious.zjoule.plugin.chat.IChatMessage;
import com.developer.nefarious.zjoule.plugin.models.Role;

public class OpenAIChatMessage implements IChatMessage {

	private Role role;

    private String content;

    public OpenAIChatMessage() { }

    public OpenAIChatMessage(final Role role, final String content) {
    	this.role = role;
    	this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
	public String getMessage() {
		return content;
	}

    @Override
    public Role getRole() {
        return role;
    }

    public void setContent(final String content) {
        this.content = content;
    }

	public void setRole(final Role role) {
        this.role = role;
    }

}
