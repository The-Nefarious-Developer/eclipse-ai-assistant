package com.developer.nefarious.zjoule.test.auth;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.swt.events.ModifyEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.zjoule.auth.FirstLoginWizardPage;
import com.developer.nefarious.zjoule.auth.ServiceKeyModifyListener;

public class ServiceKeyModifyListenerTest {

	private ServiceKeyModifyListener cut;
	
	@Mock
	FirstLoginWizardPage mockFirstLoginWizardPage;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		cut = new ServiceKeyModifyListener(mockFirstLoginWizardPage);
	}
	
	@Test
	public void testModifyTextWhenThereIsText() {
		// Arrange
		ModifyEvent mockModifyEvent = mock(ModifyEvent.class);
		
		String mockInputText = "Text I don't care about";
		when(mockFirstLoginWizardPage.getInputText()).thenReturn(mockInputText);
		
		// Act
		cut.modifyText(mockModifyEvent);
		
		// Assert
		assertTrue(true);
		verify(mockFirstLoginWizardPage).setPageComplete(true);
	}
	
	@Test
	public void testModifyTextWhenThereIsNoText() {
		// Arrange
		ModifyEvent mockModifyEvent = mock(ModifyEvent.class);
		
		String mockInputText = "";
		when(mockFirstLoginWizardPage.getInputText()).thenReturn(mockInputText);
		
		// Act
		cut.modifyText(mockModifyEvent);
		
		// Assert
		assertTrue(true);
		verify(mockFirstLoginWizardPage).setPageComplete(false);
	}
	
}
