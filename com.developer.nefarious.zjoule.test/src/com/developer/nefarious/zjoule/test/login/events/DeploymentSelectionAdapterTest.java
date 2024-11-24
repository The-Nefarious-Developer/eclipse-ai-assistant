package com.developer.nefarious.zjoule.test.login.events;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.login.events.DeploymentSelectionAdapter;
import com.developer.nefarious.zjoule.login.pages.SecondLoginWizardPage;

public class DeploymentSelectionAdapterTest {
	
	private DeploymentSelectionAdapter cut;
	
	@Mock
	SecondLoginWizardPage mockSecondLoginWizardPage;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		cut = new DeploymentSelectionAdapter(mockSecondLoginWizardPage);
	}
	
	@Test
	public void testWidgetSelectedWhenThereIsText() {
		// Arrange
		SelectionEvent mockSelectionEvent = mock(SelectionEvent.class);
		
		Combo mockDeploymentDropdown = mock(Combo.class);
		when(mockSecondLoginWizardPage.getDeploymentDropdown()).thenReturn(mockDeploymentDropdown);
		String mockText = "Some random text I don't care about.";
		when(mockDeploymentDropdown.getText()).thenReturn(mockText);
		
		// Act
		cut.widgetSelected(mockSelectionEvent);
		
		// Assert
		assertTrue(true);
		verify(mockSecondLoginWizardPage).setPageComplete(true);
	}
	
	@Test
	public void testWidgetSelectedWhenThereIsNoText() {
		// Arrange
		SelectionEvent mockSelectionEvent = mock(SelectionEvent.class);
		
		Combo mockDeploymentDropdown = mock(Combo.class);
		when(mockSecondLoginWizardPage.getDeploymentDropdown()).thenReturn(mockDeploymentDropdown);
		String mockText = "";
		when(mockDeploymentDropdown.getText()).thenReturn(mockText);
		
		// Act
		cut.widgetSelected(mockSelectionEvent);
		
		// Assert
//		assertTrue(true);
		verify(mockSecondLoginWizardPage).setPageComplete(false);
	}

}
