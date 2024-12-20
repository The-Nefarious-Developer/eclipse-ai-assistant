package com.developer.nefarious.zjoule.plugin.login.events;

import java.io.IOException;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import com.developer.nefarious.zjoule.plugin.login.api.GetResourceGroupsResponse;
import com.developer.nefarious.zjoule.plugin.login.api.ILoginClient;
import com.developer.nefarious.zjoule.plugin.login.pages.FirstLoginWizardPage;
import com.developer.nefarious.zjoule.plugin.login.utils.JsonValidator;
import com.developer.nefarious.zjoule.plugin.models.ServiceKey;
import com.google.gson.Gson;

/**
 * Handles modifications to the service key input field in the first page of the login wizard.
 * <p>
 * The {@code ServiceKeyModifyListener} validates the service key entered by the user, updates the UI state,
 * and retrieves resource groups if the service key is valid.
 */
public class ServiceKeyModifyListener implements ModifyListener {

    /** The first page of the login wizard associated with this listener. */
    private FirstLoginWizardPage firstLoginWizardPage;

    /** The login client used for validating the service key and retrieving resource groups. */
    private ILoginClient loginClient;

    /** A {@link Gson} instance for parsing the service key from JSON. */
    private Gson gson;

    /**
     * Constructs a new {@code ServiceKeyModifyListener}.
     *
     * @param firstLoginWizardPage the {@link FirstLoginWizardPage} containing the service key input field.
     * @param loginClient the {@link ILoginClient} for validating the service key and retrieving resource groups.
     * @param gson the {@link Gson} instance for parsing the service key JSON.
     */
    // @formatter:off
    public ServiceKeyModifyListener(
    		final FirstLoginWizardPage firstLoginWizardPage, 
    		final ILoginClient loginClient, 
    		final Gson gson) {
    	// @formatter:on
        this.firstLoginWizardPage = firstLoginWizardPage;
        this.loginClient = loginClient;
        this.gson = gson;
    }

    /**
     * Clears any error messages displayed in the UI.
     */
    private void clearMessageLog() {
        firstLoginWizardPage.setValidationError(null);
    }

    /**
     * Disables the "Next" button on the wizard page.
     */
    private void disableNextButton() {
        firstLoginWizardPage.setPageComplete(false);
    }

    /**
     * Enables the "Next" button on the wizard page.
     */
    private void enableNextButton() {
        firstLoginWizardPage.setPageComplete(true);
    }

    /**
     * Handles a valid service key by retrieving resource groups and updating the wizard state.
     *
     * @param serviceKey the validated {@link ServiceKey} object.
     * @throws IOException if an I/O error occurs during the resource group retrieval.
     * @throws InterruptedException if the resource group retrieval is interrupted.
     */
    private void handleValidServiceKey(final ServiceKey serviceKey) throws IOException, InterruptedException {
        GetResourceGroupsResponse getResourceGroupsResponse = loginClient.getResourceGroups(serviceKey);
        firstLoginWizardPage.setResourceGroupsOnTheSecondPage(getResourceGroupsResponse);
        firstLoginWizardPage.setServiceKey(serviceKey);
        clearMessageLog();
        enableNextButton();
    }

    /**
     * Displays an error message indicating an invalid service key.
     */
    private void showErrorMessage() {
        firstLoginWizardPage.setValidationError("Invalid service key. Please provide valid credentials.");
    }

    /**
     * Handles modifications to the service key input field.
     * <p>
     * This method performs the following steps:
     * <ol>
     *   <li>Clears the message log and disables the "Next" button if the input is empty.</li>
     *   <li>Validates the input JSON format using {@link JsonValidator}.</li>
     *   <li>Parses the input into a {@link ServiceKey} object and validates it.</li>
     *   <li>Retrieves resource groups and updates the wizard state if the service key is valid.</li>
     *   <li>Displays an error message and disables the "Next" button if validation fails or an error occurs.</li>
     * </ol>
     *
     * @param event the {@link ModifyEvent} triggered by the input field modification.
     */
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
}
