package com.developer.nefarious.zjoule.test.core.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.chat.memory.MemoryMessageHistory;
import com.developer.nefarious.zjoule.chat.models.Message;
import com.developer.nefarious.zjoule.chat.models.MessageHistory;
import com.developer.nefarious.zjoule.core.ui.IViewRenderHelper;
import com.developer.nefarious.zjoule.core.ui.ViewRenderHelper;
import com.developer.nefarious.zjoule.models.Role;

public class ViewRenderHelperTest {
	
	private IViewRenderHelper cut;
	
	private MockedStatic<MemoryMessageHistory> mockedStaticMemoryMessageHistory;
	
	@Mock
	private MemoryMessageHistory mockMemoryMessageHistory;
	
	@Mock
	private MessageHistory mockMessageHistory;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		mockedStaticMemoryMessageHistory = mockStatic(MemoryMessageHistory.class);
		mockedStaticMemoryMessageHistory.when(MemoryMessageHistory::getInstance).thenReturn(mockMemoryMessageHistory);
		
		cut = ViewRenderHelper.getInstance();
	}
	
	@AfterEach
	public void tearDown() {
		if (mockedStaticMemoryMessageHistory != null) {
			mockedStaticMemoryMessageHistory.close();
		}
	}
	
	@Test
	public void shouldPlumbChatHistory() {
		// Arrange
		String mockUserQuestion = "user-question";
		String mockAIAnswer = "ai-answer";
		
		StringBuilder expectedValue = new StringBuilder();
		expectedValue.append("<div class=\"message user-message\">" + mockUserQuestion + "</div>");
		expectedValue.append("<div class=\"message bot-message\">" + mockAIAnswer + "</div>");
		
		List<Message> mockMessages = Arrays.asList(
				new Message(Role.USER, mockUserQuestion), 
				new Message(Role.ASSISTANT, mockAIAnswer));
		
		when(mockMemoryMessageHistory.load()).thenReturn(mockMessageHistory);
		when(mockMessageHistory.getMessages()).thenReturn(mockMessages);
		// Act
		StringBuilder returnValue = cut.getChatHistory();
		// Assert
		assertEquals(expectedValue.toString(), returnValue.toString());
	}
	
	@Test
	public void shouldReturnAnEmptyBufferIfMessagesAreEmpty() {
		// Arrange	
		List<Message> mockMessages = new ArrayList<>();
		when(mockMemoryMessageHistory.load()).thenReturn(mockMessageHistory);
		when(mockMessageHistory.getMessages()).thenReturn(mockMessages);
		// Act
		StringBuilder returnValue = cut.getChatHistory();
		// Assert
		assertEquals(0, returnValue.length());
	}
	
	@Test
	public void shouldReturnAnEmptyBufferInCaseTheMessageHistoryIsNull() {
		// Arrange
		when(mockMemoryMessageHistory.load()).thenReturn(null);
		// Act
		StringBuilder returnValue = cut.getChatHistory();
		// Assert
		assertEquals(0, returnValue.length());
	}

}
