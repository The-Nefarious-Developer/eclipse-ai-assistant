package com.developer.nefarious.zjoule.plugin.login.events;

import java.io.IOException;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.developer.nefarious.zjoule.plugin.login.api.GetDeploymentsResponse;
import com.developer.nefarious.zjoule.plugin.login.api.ILoginClient;
import com.developer.nefarious.zjoule.plugin.login.pages.SecondLoginWizardPage;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryResourceGroup;
import com.developer.nefarious.zjoule.plugin.models.ServiceKey;

/**
 * Handles selection events for the resource group dropdown in the second page of the login wizard.
 * <p>
 * The {@code ResourceGroupSelectionAdapter} listens for changes to the selected resource group,
 * updates the deployment dropdown with available deployments for the selected group,
 * and saves the selected resource group to memory.
 */
public class ResourceGroupSelectionAdapter extends SelectionAdapter {

    /** The second login wizard page associated with this adapter. */
    private SecondLoginWizardPage secondLoginWizardPage;

    /** The login client for retrieving deployments. */
    private ILoginClient loginClient;

    /** The memory manager for storing the selected resource group. */
    private IMemoryResourceGroup memoryResourceGroup;

    /**
     * Constructs a new {@code ResourceGroupSelectionAdapter}.
     *
     * @param secondLoginWizardPage the {@link SecondLoginWizardPage} containing the resource group dropdown.
     * @param loginClient the {@link ILoginClient} for retrieving deployments.
     * @param memoryResourceGroup the {@link IMemoryResourceGroup} used to store the selected resource group.
     */
    // @formatter:off
    public ResourceGroupSelectionAdapter(
            final SecondLoginWizardPage secondLoginWizardPage,
            final ILoginClient loginClient,
            final IMemoryResourceGroup memoryResourceGroup) {
    	// @formatter:on
        this.secondLoginWizardPage = secondLoginWizardPage;
        this.loginClient = loginClient;
        this.memoryResourceGroup = memoryResourceGroup;
    }

    /**
     * Enables the deployment dropdown for user interaction.
     */
    private void enableTheDeploymentSelection() {
        secondLoginWizardPage.getDeploymentDropdown().setEnabled(true);
    }

    /**
     * Retrieves the deployments for the selected resource group and updates the deployment dropdown.
     * <p>
     * This method:
     * <ul>
     *   <li>Fetches deployments from the API using the selected resource group and service key.</li>
     *   <li>Populates the deployment dropdown with the retrieved deployments.</li>
     *   <li>Saves the selected resource group to memory.</li>
     *   <li>Enables the deployment dropdown for further selection.</li>
     * </ul>
     *
     * @throws IOException if an I/O error occurs during the API request.
     * @throws InterruptedException if the API request is interrupted.
     */
    private void handleAvailableDeployments() throws IOException, InterruptedException {
        String selectedResourceGroup = secondLoginWizardPage.getResourceGroupDropdown().getText();
        ServiceKey serviceKey = secondLoginWizardPage.getServiceKey();
        GetDeploymentsResponse getDeploymentsResponse = loginClient.getDeployments(serviceKey, selectedResourceGroup);
        secondLoginWizardPage.setDeploymentsForSelection(getDeploymentsResponse.getDeployments());
        memoryResourceGroup.save(selectedResourceGroup);
        enableTheDeploymentSelection();
    }

    /**
     * Checks if a resource group is selected.
     *
     * @return {@code true} if a resource group is selected; {@code false} otherwise.
     */
    private Boolean isTheResourceGroupSelected() {
        return !secondLoginWizardPage.getResourceGroupDropdown().getText().isEmpty();
    }

    /**
     * Resets the deployment dropdown and disables the Next button.
     * <p>
     * Clears and disables the deployment dropdown and marks the page as incomplete
     * to ensure a new deployment selection is required.
     */
    private void resetSelectionStatus() {
        secondLoginWizardPage.getDeploymentDropdown().deselectAll();
        secondLoginWizardPage.getDeploymentDropdown().setEnabled(false);
        secondLoginWizardPage.setPageComplete(false);
    }

    /**
     * Handles selection events triggered by the resource group dropdown.
     * <p>
     * When a new resource group is selected:
     * <ul>
     *   <li>Resets the deployment dropdown and page status.</li>
     *   <li>Fetches and populates available deployments for the selected group.</li>
     * </ul>
     * If no resource group is selected, the deployment dropdown remains disabled.
     *
     * @param event the {@link SelectionEvent} triggered by the dropdown.
     */
    @Override
    public void widgetSelected(final SelectionEvent event) {
        resetSelectionStatus();

        if (isTheResourceGroupSelected()) {
            try {
                handleAvailableDeployments();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
