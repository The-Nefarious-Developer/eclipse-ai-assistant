package com.developer.nefarious.zjoule.test.login.events;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.ArrayList;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.login.api.GetDeploymentsResponse;
import com.developer.nefarious.zjoule.login.api.ILoginClient;
import com.developer.nefarious.zjoule.login.events.ResourceGroupSelectionAdapter;
import com.developer.nefarious.zjoule.login.pages.SecondLoginWizardPage;
import com.developer.nefarious.zjoule.login.utils.DeploymentConfigurationNameExtractor;
import com.developer.nefarious.zjoule.memory.IMemoryResourceGroup;
import com.developer.nefarious.zjoule.models.ServiceKey;

public class ResourceGroupSelectionAdapterTest {
	
	private ResourceGroupSelectionAdapter cut;
	
	@Mock
	private SecondLoginWizardPage mockSecondLoginWizardPage;
	
	@Mock
	private ILoginClient mockLoginClient;
	
	@Mock
	private IMemoryResourceGroup mockMemoryResourceGroup;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		cut = new ResourceGroupSelectionAdapter(mockSecondLoginWizardPage, mockLoginClient, mockMemoryResourceGroup);
	}
	
	@Test
	public void shouldEnableTheDeploymentSelection() throws IOException, InterruptedException {
		// Arrange
		SelectionEvent mockSelectionEvent = mock(SelectionEvent.class);
		
		Combo mockDeploymentDropdown = mock(Combo.class);
		when(mockSecondLoginWizardPage.getDeploymentDropdown()).thenReturn(mockDeploymentDropdown);
		
		Combo mockProjectDropdown = mock(Combo.class);
		when(mockSecondLoginWizardPage.getResourceGroupDropdown()).thenReturn(mockProjectDropdown);
		
		String mockText = "Some random text I don't care about.";
		when(mockProjectDropdown.getText()).thenReturn(mockText);
		
		ServiceKey mockServiceKey = mock(ServiceKey.class);
		when(mockSecondLoginWizardPage.getServiceKey()).thenReturn(mockServiceKey);
		
		GetDeploymentsResponse mockGetDeploymentsResponse = mock(GetDeploymentsResponse.class);
		when(mockLoginClient.getDeployments(mockServiceKey, mockText)).thenReturn(mockGetDeploymentsResponse);
		
		ArrayList<String> mockListOfConfigurationNames = new ArrayList<String>();
		try (MockedStatic<DeploymentConfigurationNameExtractor> configNameExtractorStatic = mockStatic(DeploymentConfigurationNameExtractor.class)) {
			configNameExtractorStatic.when(() -> DeploymentConfigurationNameExtractor.extractResourceGroupIds(mockGetDeploymentsResponse)).thenReturn(mockListOfConfigurationNames);
		}
		
		// Act
		cut.widgetSelected(mockSelectionEvent);
		
		// Assert
		verify(mockDeploymentDropdown).deselectAll();
		verify(mockDeploymentDropdown).setEnabled(false);
		verify(mockSecondLoginWizardPage).setPageComplete(false);
		verify(mockDeploymentDropdown).setEnabled(true);
		verify(mockSecondLoginWizardPage).setDeploymentsForSelection(mockListOfConfigurationNames);
		verify(mockMemoryResourceGroup).save(mockText);
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
