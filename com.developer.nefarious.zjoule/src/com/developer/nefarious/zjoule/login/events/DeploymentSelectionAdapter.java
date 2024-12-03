package com.developer.nefarious.zjoule.login.events;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.developer.nefarious.zjoule.login.pages.SecondLoginWizardPage;
import com.developer.nefarious.zjoule.memory.IMemoryDeployment;

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
		String selectedDeployment = secondLoginWizardPage.getDeploymentDropdown().getText();
		if (selectedDeployment.isEmpty()) {
			secondLoginWizardPage.setPageComplete(false); // Disable Finish button if cleared
		} else {
			memoryDeployment.save(selectedDeployment);
			secondLoginWizardPage.setPageComplete(true); // Enable Finish button when second dropdown is filled
		}
	}

}
