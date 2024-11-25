package com.developer.nefarious.zjoule.login.pages;

import java.util.ArrayList;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import com.developer.nefarious.zjoule.login.events.DeploymentSelectionAdapter;
import com.developer.nefarious.zjoule.login.events.ResourceGroupSelectionAdapter;

public class SecondLoginWizardPage extends WizardPage {
	
	public static final String PAGE_ID = "Second Page";

	private Combo resourceGroupDropdown;
	
	private Combo deploymentDropdown;
	
	private ArrayList<String> resourceGroupsForSelection = new ArrayList<String>();
	
	private ArrayList<String> deploymentsForSelection = new ArrayList<String>();

	public SecondLoginWizardPage() {
		super(PAGE_ID);
		setTitle("Select the model");
		setDescription("Choose the Resource Group and the Deployment ID.");
		setPageComplete(false); // Initially set the page as incomplete
	}

	@Override
	public void createControl(final Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false)); // Two columns for labels and dropdowns

		// Create label and dropdown for project selection
		Label projectLabel = new Label(container, SWT.NONE);
		projectLabel.setText("Select the Resource Group:");

		resourceGroupDropdown = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		resourceGroupDropdown.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		// Add a SelectionListener to enable the deployment dropdown when a valid project is selected
		resourceGroupDropdown.addSelectionListener(new ResourceGroupSelectionAdapter(this));

		// Create label and dropdown for deployment ID selection
		Label deploymentLabel = new Label(container, SWT.NONE);
		deploymentLabel.setText("Select Deployment ID:");

		deploymentDropdown = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		deploymentDropdown.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		deploymentDropdown.setEnabled(false); // Initially disabled

		// Add a SelectionListener to track changes in the deployment dropdown
		deploymentDropdown.addSelectionListener(new DeploymentSelectionAdapter(this));

		setControl(container);
	}

	@Override
	public void setVisible(final boolean visible) {
		super.setVisible(visible);
		if (visible) {
			// Retrieve data from the first page
			FirstLoginWizardPage firstPage = (FirstLoginWizardPage) getWizard().getPage(FirstLoginWizardPage.PAGE_ID);
			String data = firstPage.getInputText();

			// Example: Dynamically populate project dropdown based on first page's data
			if (data != null && !data.isEmpty()) {
				resourceGroupDropdown.setItems(resourceGroupsForSelection.toArray(new String[0]));
				deploymentDropdown.setItems(deploymentsForSelection.toArray(new String[0]));
			}
		} else {
			// Clear the inputs when the page is no longer visible (e.g., user clicked Back)
			resourceGroupDropdown.deselectAll();
			deploymentDropdown.deselectAll();
			deploymentDropdown.setEnabled(false);
			setPageComplete(false);
		}
	}

	public Combo getProjectDropdown() {
		return resourceGroupDropdown;
	}

	public Combo getDeploymentDropdown() {
		return deploymentDropdown;
	}
	
	public void setResourceGroupsForSelection(final ArrayList<String> resourceGroupsForSelection) {
		this.resourceGroupsForSelection = resourceGroupsForSelection;
	}
	
	public void setDeployments(final ArrayList<String> deploymentsForSelection) {
		this.deploymentsForSelection = deploymentsForSelection;
	}

}
