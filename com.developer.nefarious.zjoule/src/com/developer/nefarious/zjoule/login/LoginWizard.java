package com.developer.nefarious.zjoule.login;

import org.eclipse.jface.wizard.Wizard;

import com.developer.nefarious.zjoule.login.pages.FirstLoginWizardPage;
import com.developer.nefarious.zjoule.login.pages.SecondLoginWizardPage;

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
