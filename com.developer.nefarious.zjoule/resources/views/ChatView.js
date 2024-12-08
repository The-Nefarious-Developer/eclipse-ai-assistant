function sendMessage() {
	const chatBox = document.getElementById('chatBox');
	const userInput = document.getElementById('userInput').value;
	if (userInput.trim() === "") return;
	try {
		// Display user message
		const userMessage = document.createElement('div');
		userMessage.className = 'message user-message';
		userMessage.textContent = userInput;
		chatBox.appendChild(userMessage);
		
		var result = getAIResponse(userInput); // Java callback
		// Display AI Answer
		const AIAnswer = document.createElement('div');
		AIAnswer.className = 'message bot-message';
		AIAnswer.textContent = result;
		chatBox.appendChild(AIAnswer);
	} catch (e) {

	}
	// Clear input
	document.getElementById('userInput').value = "";
	chatBox.scrollTop = chatBox.scrollHeight; // Auto-scroll to the bottom
}