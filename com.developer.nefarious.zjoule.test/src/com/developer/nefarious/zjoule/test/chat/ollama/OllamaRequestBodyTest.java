package com.developer.nefarious.zjoule.test.chat.ollama;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.developer.nefarious.zjoule.plugin.chat.ollama.OllamaChatMessage;
import com.developer.nefarious.zjoule.plugin.chat.ollama.OllamaRequestBody;
import com.developer.nefarious.zjoule.plugin.models.Role;

public class OllamaRequestBodyTest {

	// CHECKSTYLE:OFF - Deactivate checkstyle to avoid magic number issues on this test
	@Test
	public void shouldContainNullValuesWhenDoubleQuoted() {
		// Arrange
		OllamaRequestBody cut = new OllamaRequestBody();

		String expectedValue = "{\"model\":\"llama3.2:latest\","
		        + "\"stream\":false,"
		        + "\"messages\":["
		        + "{\"role\":\"user\","
		        + "\"content\":\"null\"},"
		        + "{\"role\":\"assistant\","
		        + "\"content\":\"Hi there!\"}]}";

		OllamaChatMessage message1 = new OllamaChatMessage();
		message1.setRole(Role.USER);
		message1.setContent("null");

		OllamaChatMessage message2 = new OllamaChatMessage();
		message2.setRole(Role.ASSISTANT);
		message2.setContent("Hi there!");

		cut.setMessages(List.of(message1, message2));
		cut.setStream(false);
		cut.setModel("llama3.2:latest");

		// Act
		String returnValue = cut.toString();

		// Assert
		assertEquals(expectedValue, returnValue);
	}

	@Test
	public void shouldConvertObjectToString() {
		// Arrange
		OllamaRequestBody cut = new OllamaRequestBody();

		String expectedValue = "{\"model\":\"llama3.2:latest\","
		        + "\"stream\":false,"
		        + "\"messages\":["
		        + "{\"role\":\"user\","
		        + "\"content\":\"Hello!\"},"
		        + "{\"role\":\"assistant\","
		        + "\"content\":\"Hi there!\"}]}";

		OllamaChatMessage message1 = new OllamaChatMessage();
		message1.setRole(Role.USER);
		message1.setContent("Hello!");

		OllamaChatMessage message2 = new OllamaChatMessage();
		message2.setRole(Role.ASSISTANT);
		message2.setContent("Hi there!");

		cut.setMessages(List.of(message1, message2));
		cut.setStream(false);
		cut.setModel("llama3.2:latest");
		
		// Act
		String returnValue = cut.toString();

		// Assert
		assertEquals(expectedValue, returnValue);
	}
	// CHECKSTYLE:ON
	
}
