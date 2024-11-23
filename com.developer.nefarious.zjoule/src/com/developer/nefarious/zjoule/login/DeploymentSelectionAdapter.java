package com.developer.nefarious.zjoule.login;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class DeploymentSelectionAdapter extends SelectionAdapter {
	
	private SecondLoginWizardPage secondLoginWizardPage;
	
	public DeploymentSelectionAdapter(SecondLoginWizardPage secondLoginWizardPage) {
		this.secondLoginWizardPage = secondLoginWizardPage;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		if (!secondLoginWizardPage.getDeploymentDropdown().getText().isEmpty()) {
			secondLoginWizardPage.setPageComplete(true); // Enable Finish button when second dropdown is filled
		} else {
			secondLoginWizardPage.setPageComplete(false); // Disable Finish button if cleared
		}
	}

}
