package com.developer.nefarious.zjoule.login.pages;

import java.util.ArrayList;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import com.developer.nefarious.zjoule.login.api.GetResourceGroupsResponse;
import com.developer.nefarious.zjoule.login.api.ILoginClient;
import com.developer.nefarious.zjoule.login.api.ResourceGroupIdExtractor;
import com.developer.nefarious.zjoule.login.events.ServiceKeyModifyListener;
import com.developer.nefarious.zjoule.models.ServiceKey;
import com.google.gson.Gson;

public class FirstLoginWizardPage extends WizardPage {
	
	public static final String PAGE_ID = "First Page";

	private Text textField;
	
	private Text errorText;
	
	private final int inputHeigth = 100;
	
	private final int inputWidth = 300;
	
	private ServiceKey serviceKey;
	
	private ILoginClient loginClient;

	public FirstLoginWizardPage(final ILoginClient loginClient) {
		super(PAGE_ID);
		setTitle("Provide credentials");
		setDescription("Attach the Service Key json file content for the SAP AI Core service.");
		setPageComplete(false); // Initially set the page as incomplete
		this.loginClient = loginClient;
	}

	@Override
	public void createControl(final Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));

		// Text field for user input
		textField = new Text(container, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.heightHint = inputHeigth;
		gridData.widthHint = inputWidth;
		textField.setLayoutData(gridData);

		// Add a ModifyListener to monitor textField changes
		textField.addModifyListener(new ServiceKeyModifyListener(this, loginClient, new Gson()));

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
	public void setValidationError(final String message) {
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
	
	public void setResourceGroupsOnTheSecondPage(final GetResourceGroupsResponse getResourceGroupsResponse) {
		SecondLoginWizardPage secondPage = (SecondLoginWizardPage) getWizard().getPage(SecondLoginWizardPage.PAGE_ID);
		ArrayList<String> resourceGroupsAvailableForSelection = ResourceGroupIdExtractor.extractResourceGroupIds(getResourceGroupsResponse);
		secondPage.setResourceGroupsForSelection(resourceGroupsAvailableForSelection);
	}
	
	public void setServiceKey(final ServiceKey serviceKey) {
		this.serviceKey = serviceKey;
	}
	
	public ServiceKey getServiceKey() {
		return serviceKey;
	}

}
