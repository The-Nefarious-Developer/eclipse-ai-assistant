package com.developer.nefarious.zjoule.auth;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class FirstLoginWizardPage extends WizardPage {
	
	private Text textField;

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

        textField = new Text(container, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);

        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.heightHint = 100;
        gridData.widthHint = 300;
        textField.setLayoutData(gridData);

        // Add a ModifyListener to monitor textField changes
        textField.addModifyListener(new ServiceKeyModifyListener(this));

        setControl(container);
	}
	
	public String getInputText() {
        return textField.getText();
    }

}
