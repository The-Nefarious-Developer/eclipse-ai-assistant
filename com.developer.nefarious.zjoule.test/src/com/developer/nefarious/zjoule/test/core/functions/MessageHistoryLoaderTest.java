package com.developer.nefarious.zjoule.test.core.functions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.browser.Browser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.zjoule.plugin.chat.memory.MemoryMessageHistory;
import com.developer.nefarious.zjoule.plugin.chat.models.Message;
import com.developer.nefarious.zjoule.plugin.chat.models.MessageHistory;
import com.developer.nefarious.zjoule.plugin.core.functions.MessageHistoryLoader;
import com.developer.nefarious.zjoule.plugin.models.Role;

public class MessageHistoryLoaderTest {

	private MockedStatic<MemoryMessageHistory> mockedStaticMemoryMessageHistory;

	@Mock
	private MemoryMessageHistory mockMemoryMessageHistory;

	@Mock
	private MessageHistory mockMessageHistory;

	@Mock
	private Browser mockBrowser;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		mockedStaticMemoryMessageHistory = mockStatic(MemoryMessageHistory.class);
		mockedStaticMemoryMessageHistory.when(MemoryMessageHistory::getInstance).thenReturn(mockMemoryMessageHistory);
	}

	@Test
	public void shouldAddAssistantMessage() {
		// Arrange
		when(mockMemoryMessageHistory.load()).thenReturn(mockMessageHistory);

		String mockAssistantMessage = "assistant-message";
		List<Message> mockMessages = Arrays.asList(new Message(Role.ASSISTANT, mockAssistantMessage));
		when(mockMessageHistory.getMessages()).thenReturn(mockMessages);

		// Act
		MessageHistoryLoader.loadFromMemory(mockBrowser);

		// Assert
		verify(mockBrowser).execute("addBotMessage('" + mockAssistantMessage + "');");
	}

	@Test
	public void shouldAddUserMessage() {
		// Arrange
		when(mockMemoryMessageHistory.load()).thenReturn(mockMessageHistory);

		String mockUserMessage = "user-message";
		List<Message> mockMessages = Arrays.asList(new Message(Role.USER, mockUserMessage));
		when(mockMessageHistory.getMessages()).thenReturn(mockMessages);

		// Act
		MessageHistoryLoader.loadFromMemory(mockBrowser);

		// Assert
		verify(mockBrowser).execute("addUserMessage('" + mockUserMessage + "');");
	}

	@Test
	public void shouldAvoidErrorWhenMessageHistoryIsNull() {
		// Arrange
		when(mockMemoryMessageHistory.load()).thenReturn(null);

		// Act
		MessageHistoryLoader.loadFromMemory(mockBrowser);

		// Assert
		verify(mockBrowser, never()).execute(any());
	}

	@Test
	public void shouldAvoidErrorWhenMessagesAreEmpty() {
		// Arrange
		when(mockMemoryMessageHistory.load()).thenReturn(mockMessageHistory);

		List<Message> mockEmptyListOfMessages = new ArrayList<>();
		when(mockMessageHistory.getMessages()).thenReturn(mockEmptyListOfMessages);

		// Act
		MessageHistoryLoader.loadFromMemory(mockBrowser);

		// Assert
		verify(mockBrowser, never()).execute(any());
	}

	@Test
	public void shouldAvoidErrorWhenMessagesAreNull() {
		// Arrange
		when(mockMemoryMessageHistory.load()).thenReturn(mockMessageHistory);
		when(mockMessageHistory.getMessages()).thenReturn(null);

		// Act
		MessageHistoryLoader.loadFromMemory(mockBrowser);

		// Assert
		verify(mockBrowser, never()).execute(any());
	}

	@Test
	public void shouldIgnoreSystemMessage() {
		// Arrange
		when(mockMemoryMessageHistory.load()).thenReturn(mockMessageHistory);

		String mockSystemMessage = "system-message";
		List<Message> mockMessages = Arrays.asList(new Message(Role.SYSTEM, mockSystemMessage));
		when(mockMessageHistory.getMessages()).thenReturn(mockMessages);

		// Act
		MessageHistoryLoader.loadFromMemory(mockBrowser);

		// Assert
		verify(mockBrowser, never()).execute(any());
	}

	@AfterEach
	public void tearDown() {
		if (mockedStaticMemoryMessageHistory != null) {
			mockedStaticMemoryMessageHistory.close();
		}
	}

}
