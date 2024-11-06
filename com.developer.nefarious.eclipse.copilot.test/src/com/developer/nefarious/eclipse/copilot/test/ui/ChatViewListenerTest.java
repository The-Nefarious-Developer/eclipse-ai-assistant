package com.developer.nefarious.eclipse.copilot.test.ui;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.eclipse.swt.SWT;

import com.developer.nefarious.eclipse.copilot.ui.BrowserFactory;
import com.developer.nefarious.eclipse.copilot.ui.ChatViewListener;
import com.developer.nefarious.eclipse.copilot.ui.ViewRender;

public class ChatViewListenerTest {

	private ChatViewListener cut;

	@Mock
	private BrowserFactory mockFactory;

	@Mock
	private Browser mockBrowser;

	@Mock
	private ViewRender viewRender;

	private String randomWord() {
		final String[] WORDS = { "apple", "banana", "grape" };
		int randomIndex = ThreadLocalRandom.current().nextInt(WORDS.length);
		return WORDS[randomIndex];
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		cut = spy(new ChatViewListener(mockFactory, viewRender));
	}

	@Test
	public void testCreatePartControl() {
		// Arrange
		Composite mockParent = mock(Composite.class);

		when(mockFactory.createBrowser(mockParent, SWT.WEBKIT)).thenReturn(mockBrowser);

		String mockViewContent = randomWord();
		when(viewRender.build()).thenReturn(mockViewContent);

		IWorkbenchPartSite mockSite = mock(IWorkbenchPartSite.class);
		when(cut.getWorkbenchPartSite()).thenReturn(mockSite);

		IWorkbenchPage mockPage = mock(IWorkbenchPage.class);
		when(mockSite.getPage()).thenReturn(mockPage);

		// Act
		cut.createPartControl(mockParent);

		// Assert
		verify(mockFactory).createBrowser(mockParent, SWT.WEBKIT);
		verify(mockBrowser).setText(mockViewContent);
		verify(mockPage).addSelectionListener(cut);
	}

	@Test
	public void testSetFocus() {
		// Arrange
		cut.setBrowser(mockBrowser);

		// Act
		cut.setFocus();

		// Assert
		verify(mockBrowser).setFocus();
	}

	@Test
	public void testDispose() {
		// Arrange
		IWorkbenchPartSite mockSite = mock(IWorkbenchPartSite.class);
		when(cut.getWorkbenchPartSite()).thenReturn(mockSite);

		IWorkbenchPage mockPage = mock(IWorkbenchPage.class);
		when(mockSite.getPage()).thenReturn(mockPage);

		// Act
		cut.dispose();

		// Assert
		verify(mockPage).removeSelectionListener(cut);
	}

}
