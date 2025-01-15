package com.developer.nefarious.zjoule.plugin.login.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.developer.nefarious.zjoule.plugin.login.api.ISapLoginClient;
import com.developer.nefarious.zjoule.plugin.login.events.DeploymentSelectionAdapter;
import com.developer.nefarious.zjoule.plugin.login.events.ResourceGroupSelectionAdapter;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;
import com.developer.nefarious.zjoule.plugin.models.Deployment;
import com.developer.nefarious.zjoule.plugin.models.ServiceKey;

/**
 * Represents the second page of the login wizard, where the user selects a 
 * resource group and deployment ID based on the credentials provided on the 
 * first page.
 * <p>
 * This page includes:
 * <ul>
 *   <li>A dropdown for selecting a resource group.</li>
 *   <li>A dropdown for selecting a deployment ID (enabled after selecting a resource group).</li>
 * </ul>
 * It also communicates with {@link FirstLoginWizardPage} to retrieve service key data.
 */
public class SecondLoginWizardPage extends WizardPage {

    /** The unique identifier for this wizard page. */
    public static final String PAGE_ID = "Second Page";

    /** Dropdown for selecting a resource group. */
    private Combo resourceGroupDropdown;

    /** Dropdown for selecting a deployment ID. */
    private Combo deploymentDropdown;

    /** List of resource groups available for selection. */
    private List<String> resourceGroupsForSelection = new ArrayList<>();

    /** List of deployments available for selection. */
    private List<Deployment> deploymentsForSelection = new ArrayList<>();

    /** Client for handling API interactions. */
    private ISapLoginClient sapLoginClient;

    /** Memory interface for managing resource group data. */
    private IMemoryObject<String> memoryResourceGroup;

    /** Memory interface for managing deployment data. */
    private IMemoryObject<Deployment> memoryDeployment;

    /**
     * Constructs a new {@code SecondLoginWizardPage}.
     * 
     * @param sapLoginClient the {@link ISapLoginClient} used for API interactions during the login process.
     * @param memoryResourceGroup the {@link IMemoryObject<String>} for resource group memory management.
     * @param memoryDeployment the {@link IMemoryObject<Deployment>} for deployment memory management.
     */
    // @formatter:off
    public SecondLoginWizardPage(
            final ISapLoginClient sapLoginClient,
            final IMemoryObject<String> memoryResourceGroup,
            final IMemoryObject<Deployment> memoryDeployment) {
        // @formatter:on
        super(PAGE_ID);
        setTitle("Select the model");
        setDescription("Choose the Resource Group and the Deployment ID.");
        setPageComplete(false); // Initially set the page as incomplete
        this.sapLoginClient = sapLoginClient;
        this.memoryResourceGroup = memoryResourceGroup;
        this.memoryDeployment = memoryDeployment;
    }

    /**
     * Creates the UI controls for this wizard page.
     * 
     * @param parent the parent {@link Composite} for the wizard page controls.
     */
    @Override
    public void createControl(final Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(2, false)); // Two columns for labels and dropdowns

        // Create label and dropdown for resource group selection
        Label projectLabel = new Label(container, SWT.NONE);
        projectLabel.setText("Select the Resource Group:");

        resourceGroupDropdown = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        resourceGroupDropdown.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        // Add a SelectionListener to enable the deployment dropdown when a valid resource group is selected
        resourceGroupDropdown
                .addSelectionListener(new ResourceGroupSelectionAdapter(this, sapLoginClient, memoryResourceGroup));

        // Create label and dropdown for deployment ID selection
        Label deploymentLabel = new Label(container, SWT.NONE);
        deploymentLabel.setText("Select Deployment ID:");

        deploymentDropdown = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        deploymentDropdown.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        deploymentDropdown.setEnabled(false); // Initially disabled

        // Add a SelectionListener to track changes in the deployment dropdown
        deploymentDropdown.addSelectionListener(new DeploymentSelectionAdapter(this, memoryDeployment));

        setControl(container);
    }

    /**
     * Retrieves the deployment dropdown widget.
     * 
     * @return the {@link Combo} representing the deployment dropdown.
     */
    public Combo getDeploymentDropdown() {
        return deploymentDropdown;
    }

    /**
     * Retrieves the list of deployments available for selection.
     * 
     * @return a {@link List} of {@link Deployment} objects.
     */
    public List<Deployment> getDeploymentsForSelection() {
        return deploymentsForSelection;
    }

    /**
     * Retrieves the resource group dropdown widget.
     * 
     * @return the {@link Combo} representing the resource group dropdown.
     */
    public Combo getResourceGroupDropdown() {
        return resourceGroupDropdown;
    }

    /**
     * Retrieves the {@link ServiceKey} from the first wizard page.
     * 
     * @return the {@link ServiceKey} provided on the first page.
     */
    public ServiceKey getServiceKey() {
        FirstLoginWizardPage firstPage = (FirstLoginWizardPage) getWizard().getPage(FirstLoginWizardPage.PAGE_ID);
        return firstPage.getServiceKey();
    }

    /**
     * Sets the list of deployments available for selection and updates the dropdown.
     * 
     * @param deploymentsForSelection a {@link List} of {@link Deployment} objects.
     */
    public void setDeploymentsForSelection(final List<Deployment> deploymentsForSelection) {
        this.deploymentsForSelection = deploymentsForSelection;
        deploymentDropdown.setItems(
                deploymentsForSelection.stream().map(Deployment::getConfigurationName).toArray(String[]::new));
    }

    /**
     * Sets the list of resource groups available for selection.
     * 
     * @param resourceGroupsForSelection a {@link List} of resource group IDs as {@link String}.
     */
    public void setResourceGroupsForSelection(final List<String> resourceGroupsForSelection) {
        this.resourceGroupsForSelection = resourceGroupsForSelection;
    }

    /**
     * Sets the visibility of this page. When visible, it populates the resource group
     * dropdown using data from the first page. When hidden, it clears dropdown selections.
     * 
     * @param visible {@code true} if the page should be visible; {@code false} otherwise.
     */
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            // Retrieve data from the first page
            FirstLoginWizardPage firstPage = (FirstLoginWizardPage) getWizard().getPage(FirstLoginWizardPage.PAGE_ID);
            String data = firstPage.getInputText();

            // Dynamically populate resource group dropdown based on first page's data
            if (data != null && !data.isEmpty()) {
                resourceGroupDropdown.setItems(resourceGroupsForSelection.toArray(new String[0]));
            }
        } else {
            // Clear the inputs when the page is no longer visible
            resourceGroupDropdown.deselectAll();
            deploymentDropdown.deselectAll();
            deploymentDropdown.setEnabled(false);
            setPageComplete(false);
        }
    }
}
