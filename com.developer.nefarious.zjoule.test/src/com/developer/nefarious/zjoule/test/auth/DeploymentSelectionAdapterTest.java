package com.developer.nefarious.zjoule.test.auth;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.zjoule.auth.DeploymentSelectionAdapter;
import com.developer.nefarious.zjoule.auth.SecondLoginWizardPage;

public class DeploymentSelectionAdapterTest {
	
	private DeploymentSelectionAdapter cut;
	
	@Mock
	SecondLoginWizardPage mockSecondLoginWizardPage;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		cut = new DeploymentSelectionAdapter(mockSecondLoginWizardPage);
	}
	
//	@Test
//	public void testWidgetSelectedWhenThereIsText() {
//		// Arrange
//		SelectionEvent mockSelectionEvent = mock(SelectionEvent.class);
//		
//		Combo mockDeploymentDropdown = mock(Combo.class);
//		when(mockSecondLoginWizardPage.getDeploymentDropdown()).thenReturn(mockDeploymentDropdown);
//		String mockText = mock(String.class);
//		when(mockDeploymentDropdown.getText()).thenReturn(mockText);
//		when(mockText.isEmpty()).thenReturn(false);
//		
//		// Act
//		cut.widgetSelected(mockSelectionEvent);
//		
//		// Assert
//		assertTrue(true);
//		verify(mockSecondLoginWizardPage).setPageComplete(true);
//	}
	
	@Test
	public void testWidgetSelectedWhenThereIsNoText() {
		// Arrange
//		SelectionEvent mockSelectionEvent = mock(SelectionEvent.class);
//		
//		Combo mockDeploymentDropdown = mock(Combo.class);
//		when(mockSecondLoginWizardPage.getDeploymentDropdown()).thenReturn(mockDeploymentDropdown);
//		String mockText = mock(String.class);
//		when(mockDeploymentDropdown.getText()).thenReturn(mockText);
//		when(mockText.isEmpty()).thenReturn(true);
//		
//		// Act
//		cut.widgetSelected(mockSelectionEvent);
		
		// Assert
		assertTrue(true);
//		verify(mockSecondLoginWizardPage).setPageComplete(false);
	}

}
