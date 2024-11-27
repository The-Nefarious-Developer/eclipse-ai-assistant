package com.developer.nefarious.zjoule.login.events;

import java.io.IOException;
import java.util.ArrayList;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.developer.nefarious.zjoule.auth.ServiceKey;
import com.developer.nefarious.zjoule.login.api.DeploymentConfigurationNameExtractor;
import com.developer.nefarious.zjoule.login.api.GetDeploymentsResponse;
import com.developer.nefarious.zjoule.login.api.ILoginClient;
import com.developer.nefarious.zjoule.login.pages.SecondLoginWizardPage;

public class ResourceGroupSelectionAdapter extends SelectionAdapter {

	private SecondLoginWizardPage secondLoginWizardPage;

	private ILoginClient loginClient;

	// @formatter:off
	public ResourceGroupSelectionAdapter(
			final SecondLoginWizardPage secondLoginWizardPage,
			final ILoginClient loginClient) {
		// @formatter:off
		this.secondLoginWizardPage = secondLoginWizardPage;
		this.loginClient = loginClient;
	}

	@Override
	public void widgetSelected(final SelectionEvent event) {
		resetSelectionStatus();

		if (isTheResourceGroupSelected()) {
			try {
				handleAvailableDeployments();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void resetSelectionStatus() {
		// Clear and disable the deployment dropdown when the resource group is changed
		secondLoginWizardPage.getDeploymentDropdown().deselectAll();
		secondLoginWizardPage.getDeploymentDropdown().setEnabled(false);
		// Disable the Next button since a new deployment needs to be selected
		secondLoginWizardPage.setPageComplete(false);
	}

	private Boolean isTheResourceGroupSelected() {
		return !secondLoginWizardPage.getResourceGroupDropdown().getText().isEmpty();
	}

	private void handleAvailableDeployments() throws IOException, InterruptedException {
		ServiceKey serviceKey = secondLoginWizardPage.getServiceKey();
		String selectedResourceGroup = secondLoginWizardPage.getResourceGroupDropdown().getText(); 
		GetDeploymentsResponse getDeploymentsResponse = loginClient.getDeployments(serviceKey, selectedResourceGroup);
		ArrayList<String> availableDeployments = DeploymentConfigurationNameExtractor.extractResourceGroupIds(getDeploymentsResponse);
		secondLoginWizardPage.setDeploymentsForSelection(availableDeployments);
		enableTheDeploymentSelection();	
	}
	
	private void enableTheDeploymentSelection() {
		secondLoginWizardPage.getDeploymentDropdown().setEnabled(true);
	}
	
}
