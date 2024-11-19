package com.developer.nefarious.zjoule.auth;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

public class ServiceKeyModifyListener implements ModifyListener {
	
	private FirstLoginWizardPage firstLoginWizardPage;
	
	public ServiceKeyModifyListener(FirstLoginWizardPage firstLoginWizardPage) {
		this.firstLoginWizardPage = firstLoginWizardPage;
	}

	@Override
	public void modifyText(ModifyEvent event) {
		// Enable or disable the Next button based on textField content
        if (firstLoginWizardPage.getInputText().trim().isEmpty()) {
        	firstLoginWizardPage.setPageComplete(false); // Disable Next button
        } else {
        	firstLoginWizardPage.setPageComplete(true); // Enable Next button
        }
		
	}

}
