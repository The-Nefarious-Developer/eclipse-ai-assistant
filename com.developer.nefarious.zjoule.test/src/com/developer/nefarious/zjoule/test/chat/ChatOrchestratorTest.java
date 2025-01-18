package com.developer.nefarious.zjoule.test.chat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.zjoule.plugin.chat.AIClientFactory;
import com.developer.nefarious.zjoule.plugin.chat.ChatOrchestrator;
import com.developer.nefarious.zjoule.plugin.chat.IAIClient;
import com.developer.nefarious.zjoule.plugin.chat.IChatMessage;
import com.developer.nefarious.zjoule.plugin.chat.IChatOrchestrator;
import com.developer.nefarious.zjoule.plugin.chat.utils.EditorContentReader;
import com.developer.nefarious.zjoule.plugin.core.preferences.Instruction;
import com.developer.nefarious.zjoule.plugin.models.Role;

public class ChatOrchestratorTest {

	private IChatOrchestrator cut;

	MockedStatic<AIClientFactory> mockedStaticAIClientFactory;

	MockedStatic<Instruction> mockedStaticInstruction;

	MockedStatic<EditorContentReader> mockedStaticEditorContentReader;

	@Mock
	private IAIClient mockAIClient;

	String mockBaseInstructions = "Answer like a pirate with diarrhea.";

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		mockedStaticAIClientFactory = mockStatic(AIClientFactory.class);
		mockedStaticInstruction = mockStatic(Instruction.class);
		mockedStaticEditorContentReader = mockStatic(EditorContentReader.class);

		mockedStaticInstruction.when(Instruction::getMessage).thenReturn(mockBaseInstructions);

		cut = new ChatOrchestrator();
	}

	@Test
	public void shouldNotStopIfThereIsNoMessageHistory() throws IOException, InterruptedException {
		// Arrange
		mockedStaticAIClientFactory.when(AIClientFactory::getClient).thenReturn(mockAIClient);

		String mockUserPrompt = "What's the meaning of life?";
		IChatMessage mockUserMessage = mock(IChatMessage.class);
		when(mockAIClient.createMessage(Role.USER, mockUserPrompt)).thenReturn(mockUserMessage);

		List<IChatMessage> mockMessageHistory = new ArrayList<>();
		when(mockAIClient.getMessageHistory()).thenReturn(mockMessageHistory);
		
		String mockEditorContent = null;
		mockedStaticEditorContentReader.when(EditorContentReader::readActiveEditorContent).thenReturn(mockEditorContent);

		IChatMessage mockSystemMessage = mock(IChatMessage.class);
		when(mockAIClient.createMessage(Role.SYSTEM, mockBaseInstructions)).thenReturn(mockSystemMessage);

		List<IChatMessage> mockInputMessages = Arrays.asList(mockSystemMessage, mockUserMessage);
		IChatMessage mockAnswer = mock(IChatMessage.class);
		when(mockAIClient.chatCompletion(mockInputMessages)).thenReturn(mockAnswer);

		List<IChatMessage> mockAllMessages = Arrays.asList(mockUserMessage,	mockAnswer);

		String expectedValue = "42";
		when(mockAnswer.getMessage()).thenReturn(expectedValue);

		// Act
		String returnValue = cut.getAnswer(mockUserPrompt, mockEditorContent);

		// Assert
		verify(mockAIClient).setMessageHistory(mockAllMessages);
		assertEquals(expectedValue, returnValue);
	}

	@Test
	public void shouldPlumbAnswer() throws IOException, InterruptedException {
		// Arrange
		mockedStaticAIClientFactory.when(AIClientFactory::getClient).thenReturn(mockAIClient);

		String mockUserPrompt = "What's the meaning of life?";
		IChatMessage mockUserMessage = mock(IChatMessage.class);
		when(mockAIClient.createMessage(Role.USER, mockUserPrompt)).thenReturn(mockUserMessage);

		IChatMessage mockOldMessage1 = mock(IChatMessage.class);
		IChatMessage mockOldMessage2 = mock(IChatMessage.class);
		List<IChatMessage> mockMessageHistory = Arrays.asList(mockOldMessage1, mockOldMessage2);
		when(mockAIClient.getMessageHistory()).thenReturn(mockMessageHistory);

		String mockEditorContent = "WRITE 'HELLO WORLD'.";
		mockedStaticEditorContentReader.when(EditorContentReader::readActiveEditorContent)
				.thenReturn(mockEditorContent);

		IChatMessage mockSystemMessage = mock(IChatMessage.class);
		String mockSystemInstructions = mockBaseInstructions + " Consider the following code as context: "
				+ mockEditorContent;
		when(mockAIClient.createMessage(Role.SYSTEM, mockSystemInstructions)).thenReturn(mockSystemMessage);

		List<IChatMessage> mockInputMessages = Arrays.asList(mockOldMessage1, mockOldMessage2, mockSystemMessage,
				mockUserMessage);
		IChatMessage mockAnswer = mock(IChatMessage.class);
		when(mockAIClient.chatCompletion(mockInputMessages)).thenReturn(mockAnswer);

		List<IChatMessage> mockAllMessages = Arrays.asList(mockOldMessage1, mockOldMessage2, mockUserMessage, mockAnswer);

		String expectedValue = "42";
		when(mockAnswer.getMessage()).thenReturn(expectedValue);

		// Act
		String returnValue = cut.getAnswer(mockUserPrompt, mockEditorContent);

		// Assert
		verify(mockAIClient).setMessageHistory(mockAllMessages);
		assertEquals(expectedValue, returnValue);
	}

	@Test
	public void shouldPlumbErrorMessageIfChatCompletionDoesntWork() throws IOException, InterruptedException {
		// Arrange
		mockedStaticAIClientFactory.when(AIClientFactory::getClient).thenReturn(mockAIClient);

		String mockUserPrompt = "What's the meaning of life?";
		IChatMessage mockUserMessage = mock(IChatMessage.class);
		when(mockAIClient.createMessage(Role.USER, mockUserPrompt)).thenReturn(mockUserMessage);

		IChatMessage mockOldMessage = mock(IChatMessage.class);
		List<IChatMessage> mockMessageHistory = Arrays.asList(mockOldMessage);
		when(mockAIClient.getMessageHistory()).thenReturn(mockMessageHistory);

		String mockEditorContent = null;
		mockedStaticEditorContentReader.when(EditorContentReader::readActiveEditorContent).thenReturn(mockEditorContent);

		IChatMessage mockSystemMessage = mock(IChatMessage.class);
		when(mockAIClient.createMessage(Role.SYSTEM, mockBaseInstructions)).thenReturn(mockSystemMessage);

		List<IChatMessage> mockInputMessages = Arrays.asList(mockOldMessage, mockSystemMessage, mockUserMessage);
		when(mockAIClient.chatCompletion(mockInputMessages)).thenThrow(new IOException("An error occurred"));

		String expectedValue = "Error during the AI request execution";

		// Act
		String returnValue = cut.getAnswer(mockUserPrompt, mockEditorContent);

		// Assert
		verify(mockAIClient, never()).setMessageHistory(any());
		assertEquals(expectedValue, returnValue);
	}

	@Test
	public void shouldReturnErrorIfModelIsIncompatible() {
		// Arrange
		String mockUserPrompt = "What's the meaning of life?";
		String expectedValue = "The model you have selected is incompatible with the current "
				+ "operation. Please verify the model's configuration or choose a "
				+ "compatible alternative.";
		
		String mockEditorContent = null;
		mockedStaticEditorContentReader.when(EditorContentReader::readActiveEditorContent).thenReturn(mockEditorContent);

		mockedStaticAIClientFactory.when(AIClientFactory::getClient).thenReturn(null);

		// Act
		String returnValue = cut.getAnswer(mockUserPrompt, mockEditorContent);

		// Assert
		assertEquals(expectedValue, returnValue);
	}

	@AfterEach
	public void tearDown() {
		if (mockedStaticAIClientFactory != null) {
			mockedStaticAIClientFactory.close();
		}
		if (mockedStaticInstruction != null) {
			mockedStaticInstruction.close();
		}
		if (mockedStaticEditorContentReader != null) {
			mockedStaticEditorContentReader.close();
		}
	}

}
