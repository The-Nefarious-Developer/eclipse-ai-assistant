package com.developer.nefarious.zjoule.auth;

import org.eclipse.jface.wizard.Wizard;

public class LoginWizard extends Wizard {
	
	public LoginWizard() {
        setWindowTitle("Login to SAP AI Core");
    }
	
	@Override
	public void addPages() {
		addPage(new FirstLoginWizardPage());
		addPage(new SecondLoginWizardPage());
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return true;
	}

}
