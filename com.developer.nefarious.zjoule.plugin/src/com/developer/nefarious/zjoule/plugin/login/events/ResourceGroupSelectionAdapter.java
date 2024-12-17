package com.developer.nefarious.zjoule.plugin.login.events;

import java.io.IOException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.developer.nefarious.zjoule.plugin.login.api.GetDeploymentsResponse;
import com.developer.nefarious.zjoule.plugin.login.api.ILoginClient;
import com.developer.nefarious.zjoule.plugin.login.pages.SecondLoginWizardPage;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryResourceGroup;
import com.developer.nefarious.zjoule.plugin.models.ServiceKey;

public class ResourceGroupSelectionAdapter extends SelectionAdapter {

	private SecondLoginWizardPage secondLoginWizardPage;

	private ILoginClient loginClient;
	
	private IMemoryResourceGroup memoryResourceGroup;

	// @formatter:off
	public ResourceGroupSelectionAdapter(
			final SecondLoginWizardPage secondLoginWizardPage,
			final ILoginClient loginClient,
			final IMemoryResourceGroup memoryResourceGroup) {
		// @formatter:on
		this.secondLoginWizardPage = secondLoginWizardPage;
		this.loginClient = loginClient;
		this.memoryResourceGroup = memoryResourceGroup;
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
		String selectedResourceGroup = secondLoginWizardPage.getResourceGroupDropdown().getText();
		ServiceKey serviceKey = secondLoginWizardPage.getServiceKey();
		GetDeploymentsResponse getDeploymentsResponse = loginClient.getDeployments(serviceKey, selectedResourceGroup);
		secondLoginWizardPage.setDeploymentsForSelection(getDeploymentsResponse.getDeployments());
		memoryResourceGroup.save(selectedResourceGroup);
		enableTheDeploymentSelection();
	}

	private void enableTheDeploymentSelection() {
		secondLoginWizardPage.getDeploymentDropdown().setEnabled(true);
	}

}
