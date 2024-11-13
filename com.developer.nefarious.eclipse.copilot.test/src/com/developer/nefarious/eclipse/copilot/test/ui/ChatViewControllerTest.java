package com.developer.nefarious.eclipse.copilot.test.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.developer.nefarious.eclipse.copilot.ui.ChatViewController;
import com.developer.nefarious.eclipse.copilot.ui.IChatViewController;

public class ChatViewControllerTest {

	private IChatViewController cut;

	@BeforeEach
	public void setUp() {
		cut = new ChatViewController();
	}

	@Test
	public void testGetAnswerFromAI() {
		// Arrange
		String mockUserPrompt = "A very good prompt";
		String expectedValue = "Hello **bosta**";
		// Act
//		String returnValue = cut.getAnswerFromAI(mockUserPrompt);
		String returnValue = cut.getAnswerFromAI();
		// Assert
		assertEquals(returnValue, expectedValue);
	}

}
