package com.developer.nefarious.zjoule.test.chat.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.chat.memory.IMemoryMessageHistory;
import com.developer.nefarious.zjoule.chat.memory.MemoryMessageHistory;
import com.developer.nefarious.zjoule.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.chat.models.MessageHistory;

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
		cut = MemoryMessageHistory.getInstance();
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
	
}
