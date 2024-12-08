package com.developer.nefarious.zjoule.chat.openai;

import com.developer.nefarious.zjoule.models.Role;

public class OpenAIMessage {

	private Role role;
	
    private String content;
    
    public OpenAIMessage() { }
    
    public OpenAIMessage(final Role role, final String content) { 
    	this.role = role;
    	this.content = content;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }
    
}
