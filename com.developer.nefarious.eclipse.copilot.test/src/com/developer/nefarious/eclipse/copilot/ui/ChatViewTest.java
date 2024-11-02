package com.developer.nefarious.eclipse.copilot.ui;

import org.eclipse.swt.widgets.Composite;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class ChatViewTest {

	private ChatView cut;

	@BeforeEach
	void setUp() {
		Composite mockedParent = Mockito.mock(Composite.class);
	}

	@Test
	public void testCreatePartControl() {
		Assertions.assertTrue(true);
	}

}
