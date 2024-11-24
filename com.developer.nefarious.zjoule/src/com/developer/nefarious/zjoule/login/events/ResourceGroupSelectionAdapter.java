package com.developer.nefarious.zjoule.login.events;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.developer.nefarious.zjoule.login.pages.SecondLoginWizardPage;

public class ResourceGroupSelectionAdapter extends SelectionAdapter {
	
	private SecondLoginWizardPage secondLoginWizardPage;
	
	public ResourceGroupSelectionAdapter(SecondLoginWizardPage secondLoginWizardPage) {
		this.secondLoginWizardPage = secondLoginWizardPage;
	}
	
	@Override
    public void widgetSelected(SelectionEvent e) {       
        // Clear and disable the deployment dropdown when the project is changed
        secondLoginWizardPage.getDeploymentDropdown().deselectAll();
        secondLoginWizardPage.getDeploymentDropdown().setEnabled(false);

        // Disable the Next button since a new deployment needs to be selected
        secondLoginWizardPage.setPageComplete(false);

        // Enable deployment dropdown if a valid project is selected
        if (!secondLoginWizardPage.getProjectDropdown().getText().isEmpty()) {
        	secondLoginWizardPage.getDeploymentDropdown().setEnabled(true);
        }
    }

}
