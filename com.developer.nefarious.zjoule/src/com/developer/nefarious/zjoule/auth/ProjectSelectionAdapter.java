package com.developer.nefarious.zjoule.auth;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ProjectSelectionAdapter extends SelectionAdapter {
	
	private SecondLoginWizardPage secondLoginWizardPage;
	
	public ProjectSelectionAdapter(SecondLoginWizardPage secondLoginWizardPage) {
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
