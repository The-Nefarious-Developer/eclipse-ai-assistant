package com.developer.nefarious.zjoule.test.ui;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;

import com.developer.nefarious.zjoule.functions.GetAIResponse;
import com.developer.nefarious.zjoule.functions.LoginHandler;
import com.developer.nefarious.zjoule.ui.ChatViewListener;
import com.developer.nefarious.zjoule.ui.IBrowserFactory;
import com.developer.nefarious.zjoule.ui.IViewRender;

public class ChatViewListenerTest {

	private ChatViewListener cut;

	@Mock
	private IBrowserFactory mockBrowserFactory;

	@Mock
	private Browser mockBrowser;

	@Mock
	private IViewRender mockViewRender;

	private String randomWord() {
		final String[] WORDS = { "apple", "banana", "grape" };
		int randomIndex = ThreadLocalRandom.current().nextInt(WORDS.length);
		return WORDS[randomIndex];
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		cut = spy(new ChatViewListener(mockBrowserFactory, mockViewRender));
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

		IWorkbenchPartSite mockSite = mock(IWorkbenchPartSite.class);
		when(cut.getSite()).thenReturn(mockSite);
		IWorkbenchPage mockPage = mock(IWorkbenchPage.class);
		when(mockSite.getPage()).thenReturn(mockPage);
		
		IToolBarManager mockToolbar = mock(IToolBarManager.class);
//		when(cut.getToolbar()).thenReturn(mockToolbar); Does't work with spies
		doReturn(mockToolbar).when(cut).getToolbar(); // Work with spies
		
		// Mock the setup of the getAIResponse
		try (MockedStatic<GetAIResponse> mockedStatic = mockStatic(GetAIResponse.class)) {
			GetAIResponse mockGetAIResponse = mock(GetAIResponse.class);
			mockedStatic.when(() -> GetAIResponse.create(mockBrowser, "getAIResponse")).thenReturn(mockGetAIResponse);
			
			// Act
			cut.createPartControl(mockParent);

			// Assert
			verify(mockBrowserFactory).createBrowser(mockParent, SWT.WEBKIT);
			verify(mockBrowser).setText(mockViewContent);
			verify(mockPage).addSelectionListener(cut);
			mockedStatic.verify(() -> GetAIResponse.create(mockBrowser, "getAIResponse"), times(1));
			verify(mockBrowser).addDisposeListener(any(DisposeListener.class));
			verify(mockToolbar).add(any(LoginHandler.class));
		}

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
		when(cut.getSite()).thenReturn(mockSite);

		IWorkbenchPage mockPage = mock(IWorkbenchPage.class);
		when(mockSite.getPage()).thenReturn(mockPage);

		// Act
		cut.dispose();

		// Assert
		verify(mockPage).removeSelectionListener(cut);
	}

}
