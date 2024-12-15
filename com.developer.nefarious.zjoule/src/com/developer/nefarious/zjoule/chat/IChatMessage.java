package com.developer.nefarious.zjoule.chat;

import com.developer.nefarious.zjoule.models.Role;

public interface IChatMessage {
	
	Role getRole();
	
	String getMessage();

}
