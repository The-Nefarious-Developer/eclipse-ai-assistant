package com.developer.nefarious.zjoule.chat;

import java.util.ArrayList;
import java.util.List;
import com.developer.nefarious.zjoule.models.Role;

public class ChatOrchestrator implements IChatOrchestrator  {
	
	@Override
	public String getAnswer(final String userPrompt) {
		
		List<IChatMessage> messages = new ArrayList<IChatMessage>();
		
		// 0. Define which AI Client should be used
		IAIClient aiClient = AIClientFactory.getClient();
		
		// 1. Get chat history
		List<IChatMessage> messageHistory = aiClient.getMessageHistory();
		messages.addAll(messageHistory);
		
		// 2. Get content of the active window
//		String editorContent = ActiveWindowContent.getActiveEditorContent();
//		IMessage message = aiClient.createMessage(Role.SYSTEM, editorContent);
		
		// 3. Create message object
		IChatMessage userMessage = aiClient.createMessage(Role.USER, userPrompt);
		messages.add(userMessage);
		
		// 4. Get answer from LLM
		IChatMessage answer;
		try {
			answer = aiClient.chatCompletion(messages);
		} catch (Exception e) {
			e.printStackTrace();
			return "Error during the AI request execution";
		}
		
		// 5. Save the response into the chat history
		messages.add(answer);
		aiClient.setMessageHistory(messages);
		
		// 7. Return response in string format
		return answer.getMessage();
	}

}
