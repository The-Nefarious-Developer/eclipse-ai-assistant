package com.developer.nefarious.zjoule.login.events;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.developer.nefarious.zjoule.login.api.ILoginClient;
import com.developer.nefarious.zjoule.login.pages.SecondLoginWizardPage;

public class ResourceGroupSelectionAdapter extends SelectionAdapter {
	
	private SecondLoginWizardPage secondLoginWizardPage;
	
	private ILoginClient loginClient;
	
	public ResourceGroupSelectionAdapter(final SecondLoginWizardPage secondLoginWizardPage, final ILoginClient loginClient) {
		this.secondLoginWizardPage = secondLoginWizardPage;
	}
	
	@Override
    public void widgetSelected(final SelectionEvent e) {       
        resetSelectionStatus();
        
        if (isTheResourceGroupSelected()) {
        	enableTheDeploymentSelection();
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
	
	private void enableTheDeploymentSelection() {
		secondLoginWizardPage.getDeploymentDropdown().setEnabled(true);
	}

}
