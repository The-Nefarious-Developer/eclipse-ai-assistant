package com.developer.nefarious.zjoule.login;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class SecondLoginWizardPage extends WizardPage {

	private Combo projectDropdown;
	private Combo deploymentDropdown;

	protected SecondLoginWizardPage() {
		super("Second Page");
		setTitle("Select the model");
		setDescription("Choose the Project and the Deployment ID.");
		setPageComplete(false); // Initially set the page as incomplete
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(2, false)); // Two columns for labels and dropdowns

        // Create label and dropdown for project selection
        Label projectLabel = new Label(container, SWT.NONE);
        projectLabel.setText("Select Project:");

        projectDropdown = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        projectDropdown.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        projectDropdown.setItems(new String[] { "Project A", "Project B", "Project C" }); // Example items

        // Add a SelectionListener to enable the deployment dropdown when a valid project is selected
        projectDropdown.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (!projectDropdown.getText().isEmpty()) {
                    deploymentDropdown.setEnabled(true); // Enable deployment dropdown
                } else {
                    deploymentDropdown.setEnabled(false); // Disable deployment dropdown
                    setPageComplete(false); // Ensure Finish is disabled if deploymentDropdown is cleared
                }
            }
        });

        // Create label and dropdown for deployment ID selection
        Label deploymentLabel = new Label(container, SWT.NONE);
        deploymentLabel.setText("Select Deployment ID:");

        deploymentDropdown = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        deploymentDropdown.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        deploymentDropdown.setItems(new String[] { "Deployment 1", "Deployment 2", "Deployment 3" }); // Example items
        deploymentDropdown.setEnabled(false); // Initially disabled

        // Add a SelectionListener to track changes in the deployment dropdown
        deploymentDropdown.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (!deploymentDropdown.getText().isEmpty()) {
                    setPageComplete(true); // Enable Finish button when second dropdown is filled
                } else {
                    setPageComplete(false); // Disable Finish button if cleared
                }
            }
        });

        setControl(container);
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	    if (visible) {
	        // Retrieve data from the first page
	        FirstLoginWizardPage firstPage = (FirstLoginWizardPage) getWizard().getPage("First Page");
	        String data = firstPage.getInputText();

	        // Example: Dynamically populate project dropdown based on first page's data
	        if (data != null && !data.isEmpty()) {
	            projectDropdown.setItems(new String[] { data + " - Project X", data + " - Project Y" });
	            deploymentDropdown.setItems(new String[] { data + " - Deployment A", data + " - Deployment B" });
	        }
	    } else {
	        // Clear the inputs when the page is no longer visible (e.g., user clicked Back)
	        projectDropdown.deselectAll();
	        deploymentDropdown.deselectAll();
	        deploymentDropdown.setEnabled(false);
	        setPageComplete(false);
	    }
	}

}
