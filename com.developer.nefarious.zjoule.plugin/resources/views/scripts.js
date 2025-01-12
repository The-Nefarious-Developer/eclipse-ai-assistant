function sendMessage() {
	const userInput = document.getElementById('userInput').value;
	if (userInput.trim() === "") return;
	try {
		addUserMessage(userInput);
		getAIResponse(userInput); // Java callback
	} catch (e) {
	}
	updatePlaceholder();
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

function receiveMessage(output) {
	addBotMessage(output);
	updatePlaceholder();
	clearMessageAndScrollDown();
} 

function login() {
	hideInstructions();
	activateChat();
	updatePlaceholder();
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
	hidePlaceholder();
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

function updatePlaceholder() {
	const chatBox = document.getElementById("chatBox");
	const hasMessages = !!chatBox.querySelector(".message");
	
	if (hasMessages) {
		hidePlaceholder();
	} else {
		showPlaceholder();
	}
}

function showPlaceholder() {
	const placeholder = document.getElementById("placeholder");
	placeholder.style.display = "block";
}

function hidePlaceholder() {
	const placeholder = document.getElementById("placeholder");
	placeholder.style.display = "none";
}