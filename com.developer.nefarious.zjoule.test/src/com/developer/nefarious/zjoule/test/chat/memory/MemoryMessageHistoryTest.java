package com.developer.nefarious.zjoule.test.chat.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.plugin.chat.memory.IMemoryMessageHistory;
import com.developer.nefarious.zjoule.plugin.chat.memory.MemoryMessageHistory;
import com.developer.nefarious.zjoule.plugin.chat.models.Message;
import com.developer.nefarious.zjoule.plugin.chat.models.MessageHistory;
import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.Role;

public class MemoryMessageHistoryTest {

	public static final String KEY = "message-history";
	
	private IMemoryMessageHistory cut;
	
	@Mock
	IObjectSerializer mockObjectSerializer;
	
	@Mock
	IEclipseMemory mockEclipseMemory;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		MemoryMessageHistory.resetInstance();
		MemoryMessageHistory.initialize(mockObjectSerializer, mockEclipseMemory);
		cut = spy(MemoryMessageHistory.getInstance());
	}
	
	@Test
	public void shouldSaveMessageHistory() {
		// Arrange
		MessageHistory mockMessageHistory = new MessageHistory();
		String mockSerializedObject = "It doesn't matter";
		when(mockObjectSerializer.serialize(mockMessageHistory)).thenReturn(mockSerializedObject);
		// Act
		cut.save(mockMessageHistory);
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(KEY, mockSerializedObject);
	}
	
	@Test
	public void shouldLoadMessageHistory() {
		// Arrange
		MessageHistory expectedValue = new MessageHistory();
		String mockSerializedObject = "It doesn't matter";
		when(mockEclipseMemory.loadFromEclipsePreferences(KEY)).thenReturn(mockSerializedObject);
		when(mockObjectSerializer.deserialize(mockSerializedObject, MessageHistory.class)).thenReturn(expectedValue);
		// Act
		MessageHistory returnValue = cut.load();
		// Assert
		assertEquals(returnValue, expectedValue);
	}
	
	@Test
	public void shouldClearMessageHistory() {
		// Arrange		
		// Act
		cut.clear();
		// Assert
		verify(mockEclipseMemory).deleteFromEclipsePreferences(KEY);
	}
	
	@Test
	public void shouldBeEmptyWhenNoMemory() {
		// Arrange
		doReturn(null).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}
	
	@Test
	public void shouldBeEmptyWhenMessagesAreNull() {
		// Arrange
		MessageHistory mockMessageHistoryWithEmptyMessages = new MessageHistory();
		doReturn(mockMessageHistoryWithEmptyMessages).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}
	
	@Test
	public void shouldBeEmptyWhenThereIsNoMessages() {
		// Arrange
		MessageHistory mockMessageHistoryWithEmptyMessages = new MessageHistory();
		mockMessageHistoryWithEmptyMessages.setMessages(new ArrayList<>());
		doReturn(mockMessageHistoryWithEmptyMessages).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}
	
	@Test
	public void shouldNotBeEmptyThereAreMessages() {
		// Arrange
		MessageHistory mockMessageHistoryWithEmptyMessages = new MessageHistory();
		List<Message> mockMessages = Arrays.asList(new Message(Role.USER, "If we’re all stardust, does that make vacuuming a cosmic event?"));
		mockMessageHistoryWithEmptyMessages.setMessages(mockMessages);
		doReturn(mockMessageHistoryWithEmptyMessages).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertFalse(returnValue);
	}
	
}
