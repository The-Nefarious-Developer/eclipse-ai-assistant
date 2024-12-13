package com.developer.nefarious.zjoule.test.core.ui;

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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.SWT;
import com.developer.nefarious.zjoule.core.functions.PromptHandler;
import com.developer.nefarious.zjoule.core.functions.ClearHandler;
import com.developer.nefarious.zjoule.core.functions.LoginHandler;
import com.developer.nefarious.zjoule.core.functions.LogoutHandler;
import com.developer.nefarious.zjoule.core.ui.ChatViewListener;
import com.developer.nefarious.zjoule.core.ui.IBrowserFactory;
import com.developer.nefarious.zjoule.core.ui.IViewRender;

public class ChatViewListenerTest {

	private ChatViewListener cut;

	@Mock
	private IBrowserFactory mockBrowserFactory;

	@Mock
	private Browser mockBrowser;

	@Mock
	private Shell mockShell;

	@Mock
	private IViewRender mockViewRender;

	MockedStatic<ClearHandler> mockedStaticClearHandler;
	
	MockedStatic<LoginHandler> mockedStaticLoginHandler;

	MockedStatic<PromptHandler> mockedStaticPromptHandler;
	
	MockedStatic<LogoutHandler> mockedStaticLogoutHandler;

	private String randomWord() {
		final String[] WORDS = { "apple", "banana", "grape" };
		int randomIndex = ThreadLocalRandom.current().nextInt(WORDS.length);
		return WORDS[randomIndex];
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		mockedStaticClearHandler = mockStatic(ClearHandler.class);
		mockedStaticLoginHandler = mockStatic(LoginHandler.class);
		mockedStaticPromptHandler = mockStatic(PromptHandler.class);
		mockedStaticLogoutHandler = mockStatic(LogoutHandler.class);

		cut = spy(new ChatViewListener(mockBrowserFactory, mockViewRender));
		cut.setShell(mockShell);
	}

	@AfterEach
	public void tearDown() {
		// Reset the mock after each test
		reset(mockBrowser);
		
		if (mockedStaticClearHandler != null) {
			mockedStaticClearHandler.close();
		}
		if (mockedStaticLoginHandler != null) {
			mockedStaticLoginHandler.close();
		}
		if (mockedStaticPromptHandler != null) {
			mockedStaticPromptHandler.close();
		}
		if (mockedStaticLogoutHandler != null) {
			mockedStaticLogoutHandler.close();
		}
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
		doReturn(mockToolbar).when(cut).getToolbar();

		IMenuManager mockMenu = mock(IMenuManager.class);
		doReturn(mockMenu).when(cut).getMenu();
		
		ClearHandler mockClearHandler = mock(ClearHandler.class);
		mockedStaticClearHandler.when(() -> ClearHandler.create(mockBrowser)).thenReturn(mockClearHandler);

		LoginHandler mockLoginHandler = mock(LoginHandler.class);
		mockedStaticLoginHandler.when(() -> LoginHandler.create(mockShell, mockBrowser)).thenReturn(mockLoginHandler);

		PromptHandler mockGetAIResponse = mock(PromptHandler.class);
		mockedStaticPromptHandler.when(() -> PromptHandler.create(mockBrowser, "getAIResponse")).thenReturn(mockGetAIResponse);
		
		LogoutHandler mockLogoutHandler = mock(LogoutHandler.class);
		mockedStaticLogoutHandler.when(() -> LogoutHandler.create(mockBrowser)).thenReturn(mockLogoutHandler);

		// Act
		cut.createPartControl(mockParent);

		// Assert
		verify(mockBrowserFactory).createBrowser(mockParent, SWT.WEBKIT);
		verify(mockBrowser).setText(mockViewContent);
		verify(mockPage).addSelectionListener(cut);
		mockedStaticPromptHandler.verify(() -> PromptHandler.create(mockBrowser, "getAIResponse"), times(1));
		verify(mockBrowser).addDisposeListener(any(DisposeListener.class));
		verify(mockToolbar).add(mockLoginHandler);
		verify(mockMenu).add(mockLogoutHandler);
		verify(mockMenu).add(any(Separator.class));
		verify(mockMenu).add(mockClearHandler);
//		verify(mockBrowser).execute("logout();");
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
