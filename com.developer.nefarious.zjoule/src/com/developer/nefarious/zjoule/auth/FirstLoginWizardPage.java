package com.developer.nefarious.zjoule.auth;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class FirstLoginWizardPage extends WizardPage {

	private Text textField;
	private Text errorText;

	protected FirstLoginWizardPage() {
		super("First Page");
		setTitle("Provide credentials");
		setDescription("Attach the Service Key json file content for the SAP AI Core service.");
		setPageComplete(false); // Initially set the page as incomplete
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));

		// Text field for user input
		textField = new Text(container, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.heightHint = 100;
		gridData.widthHint = 300;
		textField.setLayoutData(gridData);

		// Add a ModifyListener to monitor textField changes
		textField.addModifyListener(new ServiceKeyModifyListener(this));

		// Hidden error text widget
		errorText = new Text(container, SWT.READ_ONLY | SWT.MULTI | SWT.WRAP);
		GridData errorTextGridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		errorText.setLayoutData(errorTextGridData);
		errorText.setForeground(container.getDisplay().getSystemColor(SWT.COLOR_RED));
		errorText.setVisible(false); // Initially hidden

		setControl(container);
	}

	public String getInputText() {
		return textField.getText();
	}

	// Method to set validation error message
	public void setValidationError(String message) {
		if (message != null && !message.isEmpty()) {
			errorText.setText(message);
			errorText.setVisible(true);
			setPageComplete(false);
		} else {
			errorText.setVisible(false);
			setPageComplete(true);
		}
		getShell().layout(true, true); // Update the layout to reflect visibility changes
	}

}
