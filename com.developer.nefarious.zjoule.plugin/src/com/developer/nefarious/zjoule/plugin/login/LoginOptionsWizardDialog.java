package com.developer.nefarious.zjoule.plugin.login;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

public class LoginOptionsWizardDialog extends WizardDialog {
	
	public LoginOptionsWizardDialog(final Shell parentShell, final LoginWizard wizard) {
        super(parentShell, wizard);
    }

    @Override
	public void updateButtons() {
        super.updateButtons();
        getButton(IDialogConstants.FINISH_ID).setText("Select");
    }
}
