package com.developer.nefarious.zjoule.auth;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
        textField.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                // Enable or disable the Next button based on textField content
                if (textField.getText().trim().isEmpty()) {
                    setPageComplete(false); // Disable Next button
                } else {
                    setPageComplete(true); // Enable Next button
                }
            }
        });

        setControl(container);
	}
	
	public String getInputText() {
        return textField.getText();
    }

}
