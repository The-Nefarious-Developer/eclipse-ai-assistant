function sendMessage() {
	const userInput = document.getElementById('userInput').value;
	if (userInput.trim() === "") return;
	try {
		addUserMessage(userInput);
		var result = getAIResponse(userInput); // Java callback
		addBotMessage(result);
	} catch (e) {
	}
	clearMessageAndScrollDown();
}

function addUserMessage(input) {
	const chatBox = document.getElementById('chatBox');
	const message = document.createElement('div');
	message.className = 'message user-message';
	message.textContent = input;
	chatBox.appendChild(message);
}

function addBotMessage(input) {
	const chatBox = document.getElementById('chatBox');
	const message = document.createElement('div');
	message.className = 'message bot-message';
	message.innerHTML = marked.parse(input);
	chatBox.appendChild(message);
}

function clearMessageAndScrollDown() {
	const chatBox = document.getElementById('chatBox');
	document.getElementById('userInput').value = "";
	chatBox.scrollTop = chatBox.scrollHeight;
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
}

function showInstructions() {
	const instructions = document.getElementById("instructions");
	instructions.style.display = "block";
}

function hideInstructions() {
	const instructions = document.getElementById("instructions");
	instructions.style.display = "none";
}

function updateTag(file) {
	const tagBox = document.querySelector(".tag-box");
	if (file != 'null') {
		tagBox.textContent = file;
		tagBox.style.display = "block";
	} else {
		tagBox.textContent = '';
		tagBox.style.display = "none";
	}

}