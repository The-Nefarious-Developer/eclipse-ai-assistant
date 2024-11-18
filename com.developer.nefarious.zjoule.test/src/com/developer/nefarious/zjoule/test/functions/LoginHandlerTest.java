package com.developer.nefarious.zjoule.test.functions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Shell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.zjoule.functions.LoginHandler;
import com.developer.nefarious.zjoule.ui.ServiceKeyInputDialog;

public class LoginHandlerTest {

	private LoginHandler cut;

	@Mock
	private Browser mockBrowser;

	@Mock
	private Shell mockShell;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		cut = spy(new LoginHandler(mockBrowser, mockShell));
	}

	@Test
	public void testRun() {
		// Arrange
		try (MockedStatic<ServiceKeyInputDialog> mockedStaticInputDialog = mockStatic(ServiceKeyInputDialog.class)) {
			ServiceKeyInputDialog mockServiceKeyInputDialog = mock(ServiceKeyInputDialog.class);
			mockedStaticInputDialog.when(() -> ServiceKeyInputDialog.create(mockShell, null, "Please, insert the SAP AI Core Service Key: ", null, null)).thenReturn(mockServiceKeyInputDialog);
			// Act
			cut.run();
			// Assert
			mockedStaticInputDialog.verify(() -> ServiceKeyInputDialog.create(mockShell, null, "Please, insert the SAP AI Core Service Key: ", null, null), times(1));
			verify(mockServiceKeyInputDialog).open();
		}
	}

}
