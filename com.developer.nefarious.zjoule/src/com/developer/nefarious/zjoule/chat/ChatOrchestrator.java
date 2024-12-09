package com.developer.nefarious.zjoule.chat;

public class ChatOrchestrator implements IChatOrchestrator  {
	
	@Override
	public String getAnswer(final String userPrompt) {
		
//		List<AIMessage> messages = new ArrayList<>();
		
		// 0. Define which AI Client should be used
//		IAIClient<?> aiClient = AIClientFactory.getClient(memoryDeployment);
		
		// 1. Create message object
//		IMessage message = aiClient.createMessage(Role.USER, userPrompt);
		
		// 2. Get chat history
//		IMemoryMessageHistory memoryMessageHistory = MemoryMessageHistory.getInstance();
//		List<IMessage> messageHsitory = aiClient.getMessageHistory()
		
		// 3. Get content of the active window
//		String editorContent = ActiveWindowContent.getActiveEditorContent();
//		IMessage message = aiClient.createMessage(Role.SYSTEM, editorContent);
		
		// 4. Get answer from LLM
//		try {
//			IMessage answer = aiClient.chatCompletion(messages);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "Error during the AI request execution";
//		}
		
		// 5. Save the response into the chat history
//		messages.add(answer);
//		aiClient.updateMessageHistory(messages);
		
		// 7. Return response in string format
//		return answer.getMessage();
		return "Existence is an abyss of purposeless chaos, devoid of inherent significance";
	}

}
