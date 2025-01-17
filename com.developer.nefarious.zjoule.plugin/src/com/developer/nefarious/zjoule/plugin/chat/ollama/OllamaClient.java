package com.developer.nefarious.zjoule.plugin.chat.ollama;

import java.io.IOException;
import java.util.List;

import com.developer.nefarious.zjoule.plugin.chat.IAIClient;
import com.developer.nefarious.zjoule.plugin.chat.IChatMessage;
import com.developer.nefarious.zjoule.plugin.models.Role;

public class OllamaClient implements IAIClient {
	
	public OllamaClient(
			) {
		
	}

	@Override
	public IChatMessage chatCompletion(List<IChatMessage> messages) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IChatMessage createMessage(Role role, String userPrompt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IChatMessage> getMessageHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMessageHistory(List<IChatMessage> messages) {
		// TODO Auto-generated method stub
		
	}

}
