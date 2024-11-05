package com.developer.nefarious.eclipse.copilot.test.ui;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.eclipse.swt.SWT;

import com.developer.nefarious.eclipse.copilot.ui.BrowserFactory;
import com.developer.nefarious.eclipse.copilot.ui.ChatView;

public class ChatViewTest {

	private ChatView cut;
	
	@Mock
	private BrowserFactory mockFactory;
	
	@Mock
	private Browser mockBrowser ;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		cut = new ChatView(mockFactory);
	}

	@Test
	public void testCreatePartControl() {
		// Arrange		
		Composite mockParent = mock(Composite.class);
		
		when(mockFactory.createBrowser(mockParent, SWT.WEBKIT)).thenReturn(mockBrowser);
		
		// Act
		cut.createPartControl(mockParent);
		
		// Assert
		verify(mockFactory).createBrowser(mockParent, SWT.WEBKIT);
        verify(mockBrowser).setText("test");
	}

}
