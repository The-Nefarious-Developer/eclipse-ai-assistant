package com.developer.nefarious.zjoule.login;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import com.google.gson.Gson;

public class ServiceKeyModifyListener implements ModifyListener {

	private FirstLoginWizardPage firstLoginWizardPage;

	public ServiceKeyModifyListener(FirstLoginWizardPage firstLoginWizardPage) {
		this.firstLoginWizardPage = firstLoginWizardPage;
	}

	@Override
	public void modifyText(ModifyEvent event) {
		String inputText = firstLoginWizardPage.getInputText().trim();
		// Enable or disable the Next button based on textField content
		if (inputText.isEmpty()) {
			firstLoginWizardPage.setValidationError(null);
			disableNextButton();
		} else {
			if (JsonValidator.isValidJson(inputText)) {
				ServiceKey serviceKey = parseInputToObject(inputText);
				if (serviceKey.isValid()) {
					firstLoginWizardPage.setValidationError(null);
					enableNextButton();
				} else {
					firstLoginWizardPage.setValidationError("Invalid service key. Please provide valid credentials.");
					disableNextButton();
				}
			} else {
				firstLoginWizardPage.setValidationError("Invalid service key. Please provide valid credentials.");
				disableNextButton();
			}
		}
	}

	private ServiceKey parseInputToObject(String inputText) {
		Gson gson = new Gson();
		return gson.fromJson(inputText, ServiceKey.class);
	}

	private void enableNextButton() {
		firstLoginWizardPage.setPageComplete(true);
	}

	private void disableNextButton() {
		firstLoginWizardPage.setPageComplete(false);
	}

}
