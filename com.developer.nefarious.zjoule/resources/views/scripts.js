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
		AIAnswer.innerHTML = marked.parse(result);
		chatBox.appendChild(AIAnswer);
	} catch (e) {

	}
	// Clear input
	document.getElementById('userInput').value = "";
	chatBox.scrollTop = chatBox.scrollHeight; // Auto-scroll to the bottom
}

function login() {
	hideInstructions();
	activateChat();
}

function logout() {
	clearMessages();
	deactivateChat();
	showInstructions();
}

function clearMessages() {
	const chatBox = document.getElementById('chatBox');
	chatBox.innerHTML = ''; // Remove all child elements
}

function deactivateChat() {
	const chatContainer = document.querySelector(".chat-container");
	chatContainer.style.display = "none";
}

function activateChat() {
	const chatContainer = document.querySelector(".chat-container");
	chatContainer.style.display = 'flex'; // Show the input
	//chatContainer.style.display = 'block';
}

function showInstructions() {
	const instructions = document.getElementById("instructions");
	instructions.style.display = "block";
}

function hideInstructions() {
	const instructions = document.getElementById("instructions");
	instructions.style.display = "none";
}