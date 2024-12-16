package com.developer.nefarious.zjoule.plugin.chat;

import com.developer.nefarious.zjoule.plugin.models.Role;

public interface IChatMessage {
	
	Role getRole();
	
	String getMessage();

}
