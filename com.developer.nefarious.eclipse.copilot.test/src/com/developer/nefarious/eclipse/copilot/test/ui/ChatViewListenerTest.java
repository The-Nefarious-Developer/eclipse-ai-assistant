package com.developer.nefarious.eclipse.copilot.test.ui;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.eclipse.swt.SWT;

import com.developer.nefarious.eclipse.copilot.ui.ChatViewListener;
import com.developer.nefarious.eclipse.copilot.ui.IBrowserFactory;
import com.developer.nefarious.eclipse.copilot.ui.IFunctionFactory;
import com.developer.nefarious.eclipse.copilot.ui.IViewRender;

public class ChatViewListenerTest {

	private ChatViewListener cut;

	@Mock
	private IBrowserFactory mockBrowserFactory;
	
	@Mock
	private Browser mockBrowser;
	
	@Mock
	private IFunctionFactory mockFunctionFactory;

	@Mock
	private BrowserFunction mockBrowserFunction;
	
	@Mock
	private IViewRender viewRender;

	private String randomWord() {
		final String[] WORDS = { "apple", "banana", "grape" };
		int randomIndex = ThreadLocalRandom.current().nextInt(WORDS.length);
		return WORDS[randomIndex];
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		cut = spy(new ChatViewListener(mockBrowserFactory, viewRender));
//		cut = spy(new ChatViewListener(mockBrowserFactory, viewRender, mockFunctionFactory));
	}

	@Test
	public void testCreatePartControl() {
		// Arrange
		Composite mockParent = mock(Composite.class);

		when(mockBrowserFactory.createBrowser(mockParent, SWT.WEBKIT)).thenReturn(mockBrowser);
//		when(mockFunctionFactory.createFunction(mockBrowser, "test", )).thenReturn(mockBrowserFunction);

		String mockViewContent = randomWord();
		when(viewRender.build()).thenReturn(mockViewContent);

		IWorkbenchPartSite mockSite = mock(IWorkbenchPartSite.class);
		when(cut.getWorkbenchPartSite()).thenReturn(mockSite);

		IWorkbenchPage mockPage = mock(IWorkbenchPage.class);
		when(mockSite.getPage()).thenReturn(mockPage);

		// Act
		cut.createPartControl(mockParent);

		// Assert
		verify(mockBrowserFactory).createBrowser(mockParent, SWT.WEBKIT);
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
