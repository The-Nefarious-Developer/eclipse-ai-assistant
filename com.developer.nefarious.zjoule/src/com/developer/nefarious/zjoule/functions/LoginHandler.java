package com.developer.nefarious.zjoule.functions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Shell;

import com.developer.nefarious.zjoule.auth.LoginWizard;

public class LoginHandler extends Action {
	
	private Browser browser;
	private Shell shell;
	
	public LoginHandler(Browser browser, Shell shell) {
		this.browser = browser;
		this.shell = shell;
	}

	@Override
	public void run() {
		LoginWizard wizard = new LoginWizard();
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();
	}

}