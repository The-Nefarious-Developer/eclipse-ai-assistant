package com.developer.nefarious.eclipse.copilot.ui;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ChatViewRender implements ViewRender {

	@Override
	public String build() {
		String js = this.getResourceContent("ChatView.js");
		String css = this.getResourceContent("ChatView.css");

		StringBuilder buffer = new StringBuilder();
		buffer.append("<!doctype html>");
		buffer.append("<html lang=\"en\">");
		buffer.append("<head>");
		buffer.append("<meta charset=\"utf-8\">");
		buffer.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		buffer.append("<title>Sample View</title>");
		buffer.append("<style>" + css + "</style>");
		buffer.append("<script>" + js + "</script>");
		buffer.append("</script>");
		buffer.append("</head>");
		buffer.append("<body>");
		buffer.append("<div class=\"chat-container\">");
		buffer.append("<div class=\"chat-header\">Chatbot Conversation</div>");
		buffer.append("<div class=\"chat-box\" id=\"chatBox\">");
		buffer.append("<div class=\"message bot-message\">Hello! How can I help you today?</div>");
		buffer.append("<div class=\"message user-message\">Hi! Could you tell me the weather?</div>");
		buffer.append("<div class=\"message bot-message\">Sure! The weather today is sunny with a high of 75°F.</div>");
		buffer.append("</div>");
		buffer.append("<div class=\"chat-input\">");
		buffer.append("<input type=\"text\" id=\"userInput\" placeholder=\"Type your message here...\" />");
		buffer.append("<button onclick=\"sendMessage()\">Send</button>");
		buffer.append("</div>");
		buffer.append("</div>");
		buffer.append("</body>");
		buffer.append("</html>");

		return buffer.toString();

	}

	@Override
	public String getResourceContent(String filename) {
		String content = null;
		try (InputStream inputStream = getClass().getResourceAsStream(filename)) {
			content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
		} catch (IOException e) {
		}
		return content;
	}

}
