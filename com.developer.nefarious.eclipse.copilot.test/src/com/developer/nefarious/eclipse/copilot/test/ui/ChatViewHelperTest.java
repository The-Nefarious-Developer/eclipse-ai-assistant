package com.developer.nefarious.eclipse.copilot.test.ui;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.developer.nefarious.eclipse.copilot.ui.ChatViewHelper;

public class ChatViewHelperTest {

	private ChatViewHelper cut;

	private String randomWord() {
		final String[] WORDS = { "apple", "banana", "grape" };
		int randomIndex = ThreadLocalRandom.current().nextInt(WORDS.length);
		return WORDS[randomIndex];
	}

	private String getViewContent(String mockJsContent, String mockCssContent) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("<!doctype html>");
		buffer.append("<html lang=\"en\">");
		buffer.append("<head>");
		buffer.append("<meta charset=\"utf-8\">");
		buffer.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		buffer.append("<title>Sample View</title>");
		buffer.append("<style>" + mockCssContent + "</style>");
		buffer.append("<script>" + mockJsContent + "</script>");
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

	@BeforeEach
	public void setUp() {
		cut = spy(new ChatViewHelper());
	}

	@Test
	public void testGetContent() {
		// Arrange		
		String mockJsFileContent = this.randomWord();
		doReturn(mockJsFileContent).when(cut).getResourceContent("ChatView.js");

		String mockCssFileContent = this.randomWord();
		doReturn(mockCssFileContent).when(cut).getResourceContent("ChatView.css");
		
		String expectedValue = this.getViewContent(mockJsFileContent, mockCssFileContent);

		// Act
		String returnValue = cut.getContent();

		// Assert
		assertEquals(returnValue, expectedValue);

	}

}
