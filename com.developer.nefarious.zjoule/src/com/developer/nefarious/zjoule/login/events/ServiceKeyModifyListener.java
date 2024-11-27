package com.developer.nefarious.zjoule.login.events;

import java.io.IOException;
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
	
	private Gson gson;

	public ServiceKeyModifyListener(final FirstLoginWizardPage firstLoginWizardPage, final ILoginClient loginClient, final Gson gson) {
		this.firstLoginWizardPage = firstLoginWizardPage;
		this.loginClient = loginClient;
		this.gson = gson;
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

		ServiceKey serviceKey = gson.fromJson(inputText, ServiceKey.class);
		if (!serviceKey.isValid()) {
			showErrorMessage();
			disableNextButton();
			return;
		}

		try {
			handleValidServiceKey(serviceKey);
		} catch (IOException | InterruptedException e) {
			showErrorMessage();
			disableNextButton();
		}

	}

	private void handleValidServiceKey(final ServiceKey serviceKey) throws IOException, InterruptedException {
		GetResourceGroupsResponse getResourceGroupsResponse = loginClient.getResourceGroups(serviceKey);
		firstLoginWizardPage.setResourceGroupsOnTheSecondPage(getResourceGroupsResponse);
		firstLoginWizardPage.setServiceKey(serviceKey);
		clearMessageLog();
		enableNextButton();
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
