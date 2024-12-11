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
import com.developer.nefarious.zjoule.chat.AIClientFactory;
import com.developer.nefarious.zjoule.chat.ChatOrchestrator;
import com.developer.nefarious.zjoule.chat.IAIClient;
import com.developer.nefarious.zjoule.chat.IChatMessage;
import com.developer.nefarious.zjoule.chat.IChatOrchestrator;
import com.developer.nefarious.zjoule.models.Role;

public class ChatOrchestratorTest {

	private IChatOrchestrator cut;

	MockedStatic<AIClientFactory> mockAIClientFactory;

	@Mock
	private IAIClient mockAIClient;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockAIClientFactory = mockStatic(AIClientFactory.class);
		cut = new ChatOrchestrator();
	}

	@AfterEach
	public void tearDown() {
		if (mockAIClientFactory != null) {
			mockAIClientFactory.close();
		}
	}

	@Test
	public void shouldPlumbAnswer() throws IOException, InterruptedException {
		// Arrange
		mockAIClientFactory.when(AIClientFactory::getClient).thenReturn(mockAIClient);
		
		String mockUserPrompt = "What's the meaning of life?";
		IChatMessage mockUserMessage = mock(IChatMessage.class);
		when(mockAIClient.createMessage(Role.USER, mockUserPrompt)).thenReturn(mockUserMessage);

		IChatMessage mockOldMessage1 = mock(IChatMessage.class);
		IChatMessage mockOldMessage2 = mock(IChatMessage.class);
		List<IChatMessage> mockMessageHistory = Arrays.asList(mockOldMessage1, mockOldMessage2);
		when(mockAIClient.getMessageHistory()).thenReturn(mockMessageHistory);

		List<IChatMessage> mockInputMessages = Arrays.asList(mockOldMessage1, mockOldMessage2, mockUserMessage);
		IChatMessage mockAnswer = mock(IChatMessage.class);
		when(mockAIClient.chatCompletion(mockInputMessages)).thenReturn(mockAnswer);

		List<IChatMessage> mockAllMessages = Arrays.asList(mockOldMessage1, mockOldMessage2, mockUserMessage,
				mockAnswer);

		String expectedValue = "42";
		when(mockAnswer.getMessage()).thenReturn(expectedValue);

		// Act
		String returnValue = cut.getAnswer(mockUserPrompt);

		// Assert
		verify(mockAIClient).setMessageHistory(mockAllMessages);
		assertEquals(expectedValue, returnValue);
	}

	@Test
	public void shouldPlumbErrorMessageIfChatCompletionDoesntWork() throws IOException, InterruptedException {
		// Arrange
		mockAIClientFactory.when(AIClientFactory::getClient).thenReturn(mockAIClient);
		
		String mockUserPrompt = "What's the meaning of life?";
		IChatMessage mockUserMessage = mock(IChatMessage.class);
		when(mockAIClient.createMessage(Role.USER, mockUserPrompt)).thenReturn(mockUserMessage);

		IChatMessage mockOldMessage = mock(IChatMessage.class);
		List<IChatMessage> mockMessageHistory = Arrays.asList(mockOldMessage);
		when(mockAIClient.getMessageHistory()).thenReturn(mockMessageHistory);

		List<IChatMessage> mockInputMessages = Arrays.asList(mockOldMessage, mockUserMessage);
		when(mockAIClient.chatCompletion(mockInputMessages)).thenThrow(new IOException("An error occurred"));

		String expectedValue = "Error during the AI request execution";

		// Act
		String returnValue = cut.getAnswer(mockUserPrompt);

		// Assert
		verify(mockAIClient, never()).setMessageHistory(any());
		assertEquals(expectedValue, returnValue);
	}

	@Test
	public void shouldNotStopIfThereIsNoMessageHistory() throws IOException, InterruptedException {
		// Arrange
		mockAIClientFactory.when(AIClientFactory::getClient).thenReturn(mockAIClient);
		
		String mockUserPrompt = "What's the meaning of life?";
		IChatMessage mockUserMessage = mock(IChatMessage.class);
		when(mockAIClient.createMessage(Role.USER, mockUserPrompt)).thenReturn(mockUserMessage);

		List<IChatMessage> mockMessageHistory = new ArrayList<IChatMessage>();
		when(mockAIClient.getMessageHistory()).thenReturn(mockMessageHistory);

		List<IChatMessage> mockInputMessages = Arrays.asList(mockUserMessage);

		IChatMessage mockAnswer = mock(IChatMessage.class);
		when(mockAIClient.chatCompletion(mockInputMessages)).thenReturn(mockAnswer);

		List<IChatMessage> mockAllMessages = Arrays.asList(mockUserMessage,	mockAnswer);

		String expectedValue = "42";
		when(mockAnswer.getMessage()).thenReturn(expectedValue);

		// Act
		String returnValue = cut.getAnswer(mockUserPrompt);

		// Assert
		verify(mockAIClient).setMessageHistory(mockAllMessages);
		assertEquals(expectedValue, returnValue);
	}

}
