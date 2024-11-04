package com.developer.nefarious.eclipse.copilot.test.ui;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.eclipse.copilot.ui.NewChatView;

public class ChatViewTest {

	private NewChatView cut;

	@Mock
	private Browser mockBrowser;

	@Mock
	private Composite mockParent;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		cut = spy(new NewChatView());
		cut.setBrowser(mockBrowser);
	}

	@Test
	public void testCreatePartControl() {
		// Arrange

		// Act
		cut.createPartControl(mockParent);

		// Assert
		Assertions.assertTrue(true);
		verify(mockBrowser).setText("test");
	}

}
