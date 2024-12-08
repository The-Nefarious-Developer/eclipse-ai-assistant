package com.developer.nefarious.zjoule.test.chat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.developer.nefarious.zjoule.chat.IChatOrchestrator;
import com.developer.nefarious.zjoule.chat.ChatOrchestrator;

public class ChatOrchestratorTest {
	
	private IChatOrchestrator cut;
	
	@BeforeEach
	public void setUp() {
		
		cut = new ChatOrchestrator();
	}
	
	@Test
	public void shouldPlumbAnswer() {
		// Arrange
		String expectedValue = "Existence is an abyss of purposeless chaos, devoid of inherent significance";
		String mockUserPrompt = "What's the meaning of life?";
		// Act
		String returnValue = cut.getAnswer(mockUserPrompt);
		// Assert
		assertTrue(true);
		assertEquals(expectedValue, returnValue);
	}

}
