package com.developer.nefarious.zjoule.ui;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ServiceKeyInputDialog extends InputDialog {

	private ServiceKeyInputDialog(
			Shell parentShell, 
			String dialogTitle, 
			String dialogMessage, 
			String initialValue,
			IInputValidator validator) {
		super(parentShell, dialogTitle, dialogMessage, initialValue, validator);
	}
	
	public static ServiceKeyInputDialog create(
			Shell parentShell, 
			String dialogTitle, 
			String dialogMessage, 
			String initialValue,
			IInputValidator validator) {
		return new ServiceKeyInputDialog(parentShell, dialogTitle, dialogMessage, initialValue, validator);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Control control = super.createDialogArea(parent);
		
		// Adjust the size of the dialog
        getShell().setSize(400, 300); // Set width and height
        
     // Find the input box (Text control) and adjust its layout
        Text inputText = getText();
        GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gridData.heightHint = 50; // Set desired height for the input box
        inputText.setLayoutData(gridData);
        
        return control;
	}

}
