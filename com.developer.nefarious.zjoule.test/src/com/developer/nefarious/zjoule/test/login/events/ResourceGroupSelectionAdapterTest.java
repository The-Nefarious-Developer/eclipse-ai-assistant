package com.developer.nefarious.zjoule.test.login.events;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.login.api.ILoginClient;
import com.developer.nefarious.zjoule.login.events.ResourceGroupSelectionAdapter;
import com.developer.nefarious.zjoule.login.pages.SecondLoginWizardPage;

public class ResourceGroupSelectionAdapterTest {
	
	private ResourceGroupSelectionAdapter cut;
	
	@Mock
	private SecondLoginWizardPage mockSecondLoginWizardPage;
	
	@Mock
	private ILoginClient mockLoginClient;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		cut = new ResourceGroupSelectionAdapter(mockSecondLoginWizardPage, mockLoginClient);
	}
	
	@Test
	public void shouldEnableTheDeploymentSelection() {
		// Arrange
		SelectionEvent mockSelectionEvent = mock(SelectionEvent.class);
		
		Combo mockDeploymentDropdown = mock(Combo.class);
		when(mockSecondLoginWizardPage.getDeploymentDropdown()).thenReturn(mockDeploymentDropdown);
		
		Combo mockProjectDropdown = mock(Combo.class);
		when(mockSecondLoginWizardPage.getResourceGroupDropdown()).thenReturn(mockProjectDropdown);
		
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
	public void shouldNotEnableTheDeploymentSelection() {
		// Arrange
		SelectionEvent mockSelectionEvent = mock(SelectionEvent.class);
		
		Combo mockDeploymentDropdown = mock(Combo.class);
		when(mockSecondLoginWizardPage.getDeploymentDropdown()).thenReturn(mockDeploymentDropdown);
		
		Combo mockProjectDropdown = mock(Combo.class);
		when(mockSecondLoginWizardPage.getResourceGroupDropdown()).thenReturn(mockProjectDropdown);
		
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
