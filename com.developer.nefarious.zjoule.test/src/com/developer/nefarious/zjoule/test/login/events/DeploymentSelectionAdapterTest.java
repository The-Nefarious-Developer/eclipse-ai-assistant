package com.developer.nefarious.zjoule.test.login.events;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.zjoule.plugin.login.events.DeploymentSelectionAdapter;
import com.developer.nefarious.zjoule.plugin.login.pages.SecondLoginWizardPage;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryDeployment;
import com.developer.nefarious.zjoule.plugin.models.Deployment;

public class DeploymentSelectionAdapterTest {

	private DeploymentSelectionAdapter cut;

	@Mock
	private SecondLoginWizardPage mockSecondLoginWizardPage;

	@Mock
	private IMemoryDeployment mockMemoryDeployment;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		cut = spy(new DeploymentSelectionAdapter(mockSecondLoginWizardPage, mockMemoryDeployment));
	}

	@Test
	public void shouldSetAsCompleteWhenThereIsText() {
		// Arrange
		SelectionEvent mockSelectionEvent = mock(SelectionEvent.class);

		Combo mockDeploymentDropdown = mock(Combo.class);
		when(mockSecondLoginWizardPage.getDeploymentDropdown()).thenReturn(mockDeploymentDropdown);
		String mockSelectedDeploymentConfigurationName = "Selected Deployment";
		when(mockDeploymentDropdown.getText()).thenReturn(mockSelectedDeploymentConfigurationName);

		Deployment deployment1 = new Deployment();
		deployment1.setConfigurationName("Not this one");
		Deployment deployment2 = new Deployment();
		deployment2.setConfigurationName(mockSelectedDeploymentConfigurationName);
		Deployment deployment3 = new Deployment();
		deployment3.setConfigurationName("Note this one");
		List<Deployment> mockDeploymentsForselection = Arrays.asList(deployment1, deployment2, deployment3);
		when(mockSecondLoginWizardPage.getDeploymentsForSelection()).thenReturn(mockDeploymentsForselection);

		// Act
		cut.widgetSelected(mockSelectionEvent);

		// Assert
		verify(mockSecondLoginWizardPage).setPageComplete(true);
		verify(mockMemoryDeployment).save(deployment2);
	}

	@Test
	public void shouldSetAsIncompleteWhenThereIsNoText() {
		// Arrange
		SelectionEvent mockSelectionEvent = mock(SelectionEvent.class);

		Combo mockDeploymentDropdown = mock(Combo.class);
		when(mockSecondLoginWizardPage.getDeploymentDropdown()).thenReturn(mockDeploymentDropdown);
		String mockText = "";
		when(mockDeploymentDropdown.getText()).thenReturn(mockText);

		// Act
		cut.widgetSelected(mockSelectionEvent);

		// Assert
		verify(mockSecondLoginWizardPage).setPageComplete(false);
	}

}
