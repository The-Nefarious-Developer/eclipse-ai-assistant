package com.developer.nefarious.eclipse.copilot.test.ui;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.eclipse.copilot.ui.ChatView;

public class ChatViewTest {

	private ChatView cut;

	@Mock
	private Browser mockBrowser;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		cut = spy(new ChatView());
		cut.setBrowser(mockBrowser);
	}

	@Test
	public void testCreatePartControl() {
		// Arrange
		Composite mockParent = mock(Composite.class);

		// Act
		cut.createPartControl(mockParent);

		// Assert
		verify(mockBrowser).setText("test");
	}

}
