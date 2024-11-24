package com.developer.nefarious.zjoule.test.login.events;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.swt.events.ModifyEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.zjoule.login.events.ServiceKeyModifyListener;
import com.developer.nefarious.zjoule.login.pages.FirstLoginWizardPage;
import com.developer.nefarious.zjoule.login.utils.JsonValidator;

public class ServiceKeyModifyListenerTest {

	public static final String INVALID_INPUT_MESSAGE = "Invalid service key. Please provide valid credentials.";

	private ServiceKeyModifyListener cut;

	@Mock
	private FirstLoginWizardPage mockFirstLoginWizardPage;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		cut = spy(new ServiceKeyModifyListener(mockFirstLoginWizardPage));
	}

	@Test
	public void shouldEnableTheNextButtonIfInputIsValid() {
		// Arrange
		ModifyEvent mockModifyEvent = mock(ModifyEvent.class);
		String mockValidInputText = "{" + "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
				+ "\"clientid\": \"this matters\", " + "\"clientsecret\": \"this matters\", "
				+ "\"url\": \"this matters\"" + "}";
		when(mockFirstLoginWizardPage.getInputText()).thenReturn(mockValidInputText);
		try (MockedStatic<JsonValidator> mockedJsonValidatorStatic = mockStatic(JsonValidator.class)) {
			mockedJsonValidatorStatic.when(() -> JsonValidator.isValidJson(mockValidInputText)).thenReturn(true);
			// Act
			cut.modifyText(mockModifyEvent);
			// Assert
			verify(mockFirstLoginWizardPage).setValidationError(null);
			verify(mockFirstLoginWizardPage).setPageComplete(true);
		}
	}

	@Test
	public void shouldDisableTheNextButtonIfInputIsEmpty() {
		// Arrange
		ModifyEvent mockModifyEvent = mock(ModifyEvent.class);
		String mockInputText = "";
		when(mockFirstLoginWizardPage.getInputText()).thenReturn(mockInputText);
		// Act
		cut.modifyText(mockModifyEvent);
		// Assert
		verify(mockFirstLoginWizardPage).setValidationError(null);
		verify(mockFirstLoginWizardPage).setPageComplete(false);
	}

	@Test
	public void shouldDisableTheNextButtonIfInputIsInvalid() {
		// Arrange
		ModifyEvent mockModifyEvent = mock(ModifyEvent.class);
		String mockValidInputText = "{" + "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
				+ "\"clientid\": \"this matters\", " + "\"clientsecret\": \"this matters\", "
				+ "\"url\": \"this matters\"" + "}";
		when(mockFirstLoginWizardPage.getInputText()).thenReturn(mockValidInputText);
		try (MockedStatic<JsonValidator> mockedJsonValidatorStatic = mockStatic(JsonValidator.class)) {
			mockedJsonValidatorStatic.when(() -> JsonValidator.isValidJson(mockValidInputText)).thenReturn(false);
			// Act
			cut.modifyText(mockModifyEvent);
			// Assert
			verify(mockFirstLoginWizardPage).setValidationError(INVALID_INPUT_MESSAGE);
			verify(mockFirstLoginWizardPage).setPageComplete(false);
		}
	}

	/*
	 * Be aware that in the following test, the input conversion method is not being
	 * mocked. More tests are being covered through the ServiceKeyTest.java. The
	 * purpose here is only make sure the error message is going to be properly
	 * displayed.
	 */
	@Test
	public void shouldDisableTheNextButtonIfInputIsNotComplete() {
		// Arrange
		ModifyEvent mockModifyEvent = mock(ModifyEvent.class);
		String mockValidInputText = "{" + "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
				+ "\"clientid\": \"this matters\", " + "\"clientsecret\": \"\", " + "\"url\": \"this matters\"" + "}";
		when(mockFirstLoginWizardPage.getInputText()).thenReturn(mockValidInputText);
		try (MockedStatic<JsonValidator> mockedJsonValidatorStatic = mockStatic(JsonValidator.class)) {
			mockedJsonValidatorStatic.when(() -> JsonValidator.isValidJson(mockValidInputText)).thenReturn(true);
			// Act
			cut.modifyText(mockModifyEvent);
			// Assert
			verify(mockFirstLoginWizardPage).setValidationError(INVALID_INPUT_MESSAGE);
			verify(mockFirstLoginWizardPage).setPageComplete(false);
		}
	}

}
