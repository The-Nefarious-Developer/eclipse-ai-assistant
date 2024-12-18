package com.developer.nefarious.zjoule.plugin.login.events;

import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.developer.nefarious.zjoule.plugin.login.pages.SecondLoginWizardPage;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryDeployment;
import com.developer.nefarious.zjoule.plugin.models.Deployment;

/**
 * Handles selection events for the deployment dropdown in the second page of the login wizard.
 * <p>
 * The {@code DeploymentSelectionAdapter} listens for changes to the selected deployment configuration,
 * updates the page's state, and saves the selected deployment to memory.
 */
public class DeploymentSelectionAdapter extends SelectionAdapter {

    /** The second login wizard page associated with this adapter. */
    private SecondLoginWizardPage secondLoginWizardPage;

    /** The memory manager for storing the selected deployment. */
    private IMemoryDeployment memoryDeployment;

    /**
     * Constructs a new {@code DeploymentSelectionAdapter}.
     *
     * @param secondLoginWizardPage the {@link SecondLoginWizardPage} containing the deployment dropdown.
     * @param memoryDeployment the {@link IMemoryDeployment} used to store the selected deployment.
     */
    // @formatter:off
    public DeploymentSelectionAdapter(
            final SecondLoginWizardPage secondLoginWizardPage,
            final IMemoryDeployment memoryDeployment) {
    	// @formatter:on
        this.secondLoginWizardPage = secondLoginWizardPage;
        this.memoryDeployment = memoryDeployment;
    }

    /**
     * Retrieves the {@link Deployment} object matching the selected configuration name.
     *
     * @param selectedDeploymentConfigurationName the name of the selected deployment configuration.
     * @return the matching {@link Deployment} object, or {@code null} if no match is found.
     */
    private Deployment getSelectedDeploymentObject(final String selectedDeploymentConfigurationName) {
        List<Deployment> deploymentsForSelection = secondLoginWizardPage.getDeploymentsForSelection();

        return deploymentsForSelection.stream()
                .filter(deployment -> selectedDeploymentConfigurationName.equals(deployment.getConfigurationName()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Handles selection events triggered by the deployment dropdown.
     * <p>
     * If a deployment is selected, it is saved to memory and the page is marked as complete.
     * If no selection is made, the page is marked as incomplete.
     *
     * @param e the {@link SelectionEvent} triggered by the dropdown.
     */
    @Override
    public void widgetSelected(final SelectionEvent e) {
        String selectedDeploymentConfigurationName = secondLoginWizardPage.getDeploymentDropdown().getText();
        if (selectedDeploymentConfigurationName.isEmpty()) {
            secondLoginWizardPage.setPageComplete(false); // Disable Finish button if cleared
        } else {
            Deployment selectedDeploymentObject = getSelectedDeploymentObject(selectedDeploymentConfigurationName);
            memoryDeployment.save(selectedDeploymentObject);
            secondLoginWizardPage.setPageComplete(true); // Enable Finish button when second dropdown is filled
        }
    }
}
