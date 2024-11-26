package com.developer.nefarious.zjoule.login.events;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import com.developer.nefarious.zjoule.auth.ServiceKey;
import com.developer.nefarious.zjoule.login.api.ILoginClient;
import com.developer.nefarious.zjoule.login.pages.FirstLoginWizardPage;
import com.developer.nefarious.zjoule.login.utils.JsonValidator;
import com.google.gson.Gson;

public class ServiceKeyModifyListener implements ModifyListener {

	private FirstLoginWizardPage firstLoginWizardPage;

	private ILoginClient loginClient;

	public ServiceKeyModifyListener(final FirstLoginWizardPage firstLoginWizardPage, final ILoginClient loginClient) {
		this.firstLoginWizardPage = firstLoginWizardPage;
		this.loginClient = loginClient;
	}

	@Override
	public void modifyText(final ModifyEvent event) {
		String inputText = firstLoginWizardPage.getInputText().trim();
		
		if (inputText.isEmpty()) {
			clearMessageLog();
			disableNextButton();
		} else {
			if (JsonValidator.isValidJson(inputText)) {
				ServiceKey serviceKey = parseInputToObject(inputText);
				if (serviceKey.isValid()) {
//					try {
//					GetResourceGroupsResponse getResourceGroupsResponse = loginClient.getResourceGroups();
//					firstLoginWizardPage.setResourceGroupsOnTheSeconPage(getResourceGroupsResponse);
//					} catch(Exception error) {
//						firstLoginWizardPage.setValidationError("Invalid service key. Please provide valid credentials.");
//						disableNextButton();
//					}
					clearMessageLog();
					enableNextButton();
				} else {
					showErrorMessage();
					disableNextButton();
				}
			} else {
				showErrorMessage();
				disableNextButton();
			}
		}
	}

	private ServiceKey parseInputToObject(final String inputText) {
		Gson gson = new Gson();
		return gson.fromJson(inputText, ServiceKey.class);
	}
	
	private void showErrorMessage() {
		firstLoginWizardPage.setValidationError("Invalid service key. Please provide valid credentials.");
	}
	
	private void clearMessageLog() {
		firstLoginWizardPage.setValidationError(null);
	}

	private void enableNextButton() {
		firstLoginWizardPage.setPageComplete(true);
	}

	private void disableNextButton() {
		firstLoginWizardPage.setPageComplete(false);
	}

}
