package com.developer.nefarious.zjoule.login.events;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import com.developer.nefarious.zjoule.auth.ServiceKey;
import com.developer.nefarious.zjoule.login.api.GetResourceGroupsResponse;
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
			return;
		}

		if (!JsonValidator.isValidJson(inputText)) {
			showErrorMessage();
			disableNextButton();
			return;
		}

		ServiceKey serviceKey = parseInputToObject(inputText);
		if (!serviceKey.isValid()) {
			showErrorMessage();
			disableNextButton();
			return;
		}

		try {
			handleValidServiceKey(serviceKey);
		} catch (Exception e) {
			showErrorMessage();
			disableNextButton();
		}

	}

	private void handleValidServiceKey(final ServiceKey serviceKey) throws Exception {
		GetResourceGroupsResponse getResourceGroupsResponse = loginClient.getResourceGroups(serviceKey);
		firstLoginWizardPage.setResourceGroupsOnTheSeconPage(getResourceGroupsResponse);
		clearMessageLog();
		enableNextButton();
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
