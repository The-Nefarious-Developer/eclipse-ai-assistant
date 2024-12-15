package com.developer.nefarious.zjoule.login.events;

import java.util.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.developer.nefarious.zjoule.login.pages.SecondLoginWizardPage;
import com.developer.nefarious.zjoule.memory.IMemoryDeployment;
import com.developer.nefarious.zjoule.models.Deployment;

public class DeploymentSelectionAdapter extends SelectionAdapter {

	private SecondLoginWizardPage secondLoginWizardPage;

	private IMemoryDeployment memoryDeployment;

	// @formatter:off
	public DeploymentSelectionAdapter(
			final SecondLoginWizardPage secondLoginWizardPage, 
			final IMemoryDeployment memoryDeployment) {
		// @formatter:on
		this.secondLoginWizardPage = secondLoginWizardPage;
		this.memoryDeployment = memoryDeployment;
	}

	@Override
	public void widgetSelected(final SelectionEvent e) {
		String selectedDeploymentConfigurationName = secondLoginWizardPage.getDeploymentDropdown().getText();
		if (selectedDeploymentConfigurationName.isEmpty()) {
			secondLoginWizardPage.setPageComplete(false); // Disable Finish button if cleared
		} else {
			Deployment selectedDeploymentObject = getSelectedDeploymentObject(selectedDeploymentConfigurationName);
			memoryDeployment.save(selectedDeploymentObject);
			secondLoginWizardPage.setPageComplete(true); // Enable Finish button when second dropdown is filled
		}
	}
	
	private Deployment getSelectedDeploymentObject(final String selectedDeploymentConfigurationName) {
		List<Deployment> deploymentsForSelection = secondLoginWizardPage.getDeploymentsForSelection();
		
		return deploymentsForSelection.stream()
		        .filter(deployment -> selectedDeploymentConfigurationName.equals(deployment.getConfigurationName()))
		        .findFirst()
		        .orElse(null);
	}

}
