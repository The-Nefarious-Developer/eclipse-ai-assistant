package com.developer.nefarious.zjoule.test.auth;

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

import com.developer.nefarious.zjoule.auth.ProjectSelectionAdapter;
import com.developer.nefarious.zjoule.auth.SecondLoginWizardPage;

public class ProjectSelectionAdapterTest {
	
	private ProjectSelectionAdapter cut;
	
	@Mock
	SecondLoginWizardPage mockSecondLoginWizardPage;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		cut = new ProjectSelectionAdapter(mockSecondLoginWizardPage);
	}
	
	@Test
	public void testWidgetSelectedWhenThereIsText() {
		// Arrange
		SelectionEvent mockSelectionEvent = mock(SelectionEvent.class);
		
		Combo mockDeploymentDropdown = mock(Combo.class);
		when(mockSecondLoginWizardPage.getDeploymentDropdown()).thenReturn(mockDeploymentDropdown);
		
		Combo mockProjectDropdown = mock(Combo.class);
		when(mockSecondLoginWizardPage.getProjectDropdown()).thenReturn(mockProjectDropdown);
		
		String mockText = "Some random text I don't care about.";
		when(mockProjectDropdown.getText()).thenReturn(mockText);
		
		// Act
		cut.widgetSelected(mockSelectionEvent);
		
		// Assert
		verify(mockDeploymentDropdown).deselectAll();
		verify(mockDeploymentDropdown).setEnabled(false);
		verify(mockSecondLoginWizardPage).setPageComplete(false);
		verify(mockDeploymentDropdown).setEnabled(true);
	}
	
	@Test
	public void testWidgetSelectedWhenThereIsNoText() {
		// Arrange
		SelectionEvent mockSelectionEvent = mock(SelectionEvent.class);
		
		Combo mockDeploymentDropdown = mock(Combo.class);
		when(mockSecondLoginWizardPage.getDeploymentDropdown()).thenReturn(mockDeploymentDropdown);
		
		Combo mockProjectDropdown = mock(Combo.class);
		when(mockSecondLoginWizardPage.getProjectDropdown()).thenReturn(mockProjectDropdown);
		
		String mockText = "";
		when(mockProjectDropdown.getText()).thenReturn(mockText);
		
		// Act
		cut.widgetSelected(mockSelectionEvent);
		
		// Assert
		verify(mockDeploymentDropdown).deselectAll();
		verify(mockDeploymentDropdown).setEnabled(false);
		verify(mockSecondLoginWizardPage).setPageComplete(false);
	}

}
