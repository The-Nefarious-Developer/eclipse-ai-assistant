package com.developer.nefarious.zjoule.test.core.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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
import com.developer.nefarious.zjoule.core.ui.ViewListener;
import com.developer.nefarious.zjoule.core.ui.IBrowserFactory;
import com.developer.nefarious.zjoule.core.ui.IViewRender;
import com.developer.nefarious.zjoule.core.events.Initialization;

public class ViewListenerTest {

	private ViewListener cut;

	@Mock
	private IBrowserFactory mockBrowserFactory;

	@Mock
	private Browser mockBrowser;

	@Mock
	private Shell mockShell;

	@Mock
	private IViewRender mockViewRender;
	
	@Mock
	ClearHandler mockClearHandler;
	
	@Mock
	LoginHandler mockLoginHandler;
	
	@Mock
	PromptHandler mockGetAIResponse;
	
	@Mock
	LogoutHandler mockLogoutHandler;
	
	@Mock
	IMenuManager mockMenu;
	
	@Mock
	IToolBarManager mockToolbar;
	
	@Mock
	IWorkbenchPage mockPage;
	
	@Mock
	Display mockDisplay;
	
	@Mock
	IWorkbenchPartSite mockSite;
	
	@Mock
	Composite mockParent;

	MockedStatic<ClearHandler> mockedStaticClearHandler;
	
	MockedStatic<LoginHandler> mockedStaticLoginHandler;

	MockedStatic<PromptHandler> mockedStaticPromptHandler;
	
	MockedStatic<LogoutHandler> mockedStaticLogoutHandler;
	
	MockedStatic<Display> mockedStaticDisplay;

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
		mockedStaticDisplay = mockStatic(Display.class);

		cut = spy(new ViewListener(mockBrowserFactory, mockViewRender));
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
		if (mockedStaticDisplay != null) {
			mockedStaticDisplay.close();
		}
	}

	// CHECKSTYLE:OFF: MethodLength
	@Test
	public void testCreatePartControl() {
		// Arrange
		when(mockBrowserFactory.createBrowser(mockParent, SWT.WEBKIT)).thenReturn(mockBrowser);

		String mockViewContent = randomWord();
		when(mockViewRender.build()).thenReturn(mockViewContent);
		
		when(cut.getSite()).thenReturn(mockSite);
		when(mockSite.getPage()).thenReturn(mockPage);
		
		doReturn(mockToolbar).when(cut).getToolbar();
		doReturn(mockMenu).when(cut).getMenu();
		
		mockedStaticClearHandler.when(() -> ClearHandler.create(mockBrowser)).thenReturn(mockClearHandler);
		mockedStaticLoginHandler.when(() -> LoginHandler.create(mockShell, mockBrowser)).thenReturn(mockLoginHandler);
		mockedStaticPromptHandler.when(() -> PromptHandler.create(mockBrowser, "getAIResponse")).thenReturn(mockGetAIResponse);
		mockedStaticLogoutHandler.when(() -> LogoutHandler.create(mockBrowser)).thenReturn(mockLogoutHandler);
		mockedStaticDisplay.when(Display::getDefault).thenReturn(mockDisplay);
		
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
		
		ArgumentCaptor<Runnable> captor = ArgumentCaptor.forClass(Runnable.class);
        verify(mockDisplay).asyncExec(captor.capture());
        Runnable actualRunnable = captor.getValue();
        assertTrue(actualRunnable instanceof Initialization);
	}
	// CHECKSTYLE:ON: MethodLength

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
