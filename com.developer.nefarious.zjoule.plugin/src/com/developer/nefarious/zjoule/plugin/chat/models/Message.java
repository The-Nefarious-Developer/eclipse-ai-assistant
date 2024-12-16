package com.developer.nefarious.zjoule.plugin.chat.models;

import com.developer.nefarious.zjoule.plugin.models.Role;

public class Message {

	private Role role;
	
    private String content;
    
    public Message() { }
    
    public Message(final Role role, final String content) { 
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
