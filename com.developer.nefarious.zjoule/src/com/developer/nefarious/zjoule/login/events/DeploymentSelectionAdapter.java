package com.developer.nefarious.zjoule.login.events;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.developer.nefarious.zjoule.login.pages.SecondLoginWizardPage;

public class DeploymentSelectionAdapter extends SelectionAdapter {
	
	private SecondLoginWizardPage secondLoginWizardPage;
	
	public DeploymentSelectionAdapter(final SecondLoginWizardPage secondLoginWizardPage) {
		this.secondLoginWizardPage = secondLoginWizardPage;
	}
	
	@Override
	public void widgetSelected(final SelectionEvent e) {
		if (!secondLoginWizardPage.getDeploymentDropdown().getText().isEmpty()) {
			secondLoginWizardPage.setPageComplete(true); // Enable Finish button when second dropdown is filled
		} else {
			secondLoginWizardPage.setPageComplete(false); // Disable Finish button if cleared
		}
	}

}
