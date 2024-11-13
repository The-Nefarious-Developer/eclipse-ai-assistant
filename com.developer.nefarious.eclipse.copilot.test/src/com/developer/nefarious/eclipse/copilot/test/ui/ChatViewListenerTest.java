package com.developer.nefarious.eclipse.copilot.test.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.eclipse.swt.SWT;

import com.developer.nefarious.eclipse.copilot.ui.ChatViewController;
import com.developer.nefarious.eclipse.copilot.ui.ChatViewListener;
import com.developer.nefarious.eclipse.copilot.ui.IBrowserFactory;
import com.developer.nefarious.eclipse.copilot.ui.IChatViewController;
import com.developer.nefarious.eclipse.copilot.ui.IFunctionFactory;
import com.developer.nefarious.eclipse.copilot.ui.IViewRender;

public class ChatViewListenerTest {

	private ChatViewListener cut;

	@Mock
	private IBrowserFactory mockBrowserFactory;

	@Mock
	private Browser mockBrowser;

	@Mock
	private IViewRender mockViewRender;

	@Mock
	private IFunctionFactory mockFunctionFactory;

	@Mock
	private IChatViewController mockChatViewController;

	private String randomWord() {
		final String[] WORDS = { "apple", "banana", "grape" };
		int randomIndex = ThreadLocalRandom.current().nextInt(WORDS.length);
		return WORDS[randomIndex];
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		cut = spy(
				new ChatViewListener(mockBrowserFactory, mockViewRender, mockFunctionFactory, mockChatViewController));
	}

	@AfterEach
	public void tearDown() {
		// Reset the mock after each test
		reset(mockBrowser);
	}

	@Test
	public void testCreatePartControl() {
		// Arrange
		Composite mockParent = mock(Composite.class);

		when(mockBrowserFactory.createBrowser(mockParent, SWT.WEBKIT)).thenReturn(mockBrowser);

		String mockViewContent = randomWord();
		when(mockViewRender.build()).thenReturn(mockViewContent);

		// Mock the getSite().getPage().addSelectionListener(this) call
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
	public void shouldEnableGetAIResponse() {
		// Arrange
		Composite mockParent = mock(Composite.class);

		when(mockBrowserFactory.createBrowser(mockParent, SWT.WEBKIT)).thenReturn(mockBrowser);

		IWorkbenchPartSite mockSite = mock(IWorkbenchPartSite.class);
		when(cut.getWorkbenchPartSite()).thenReturn(mockSite);
		IWorkbenchPage mockPage = mock(IWorkbenchPage.class);
		when(mockSite.getPage()).thenReturn(mockPage);
		
		BrowserFunction mockBrowserFunction = mock(BrowserFunction.class);
		when(mockFunctionFactory.createFunction(eq(mockBrowser), eq("getAIResponse"), any(Runnable.class))).thenReturn(mockBrowserFunction);
		
		// Act
		cut.createPartControl(mockParent);
		
		// Assert
		verify(mockFunctionFactory).createFunction(eq(mockBrowser), eq("getAIResponse"), any(Runnable.class));
		verify(mockBrowser).addDisposeListener(any(DisposeListener.class));
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
