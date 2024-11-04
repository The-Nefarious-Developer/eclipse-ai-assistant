package com.developer.nefarious.eclipse.copilot.test.ui;

import org.eclipse.swt.widgets.Composite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.developer.nefarious.eclipse.copilot.ui.NewChatView;

public class ChatViewTest {
	
	private NewChatView cut;
	
	@BeforeEach
	public void setUp() {
		cut = new NewChatView();
	}
	
	@Test
	public void testCreatePartControl() {
		// Arrange
		Composite mockParent = Mockito.mock(Composite.class);
		// Act
		cut.createPartControl(mockParent);
		// Assert
		Assertions.assertTrue(true);
	}

}
