package com.developer.nefarious.zjoule.plugin.login;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Shell;

import com.developer.nefarious.zjoule.plugin.login.pages.LoginOptionsPage;

public class LoginWizard extends Wizard {
	
	private Shell shell;
	
    private Browser browser;
    
    public LoginWizard(final Shell shell, final Browser browser) {
    	this.shell = shell;
        this.browser = browser;

        setWindowTitle("Setup AI Provider");
    }
    
    @Override
    public void addPages() {
        addPage(new LoginOptionsPage());
    }

	@Override
	public boolean performFinish() {
		LoginOptionsPage loginOptionsPage = (LoginOptionsPage) getPage(LoginOptionsPage.PAGE_ID);
		
		if (loginOptionsPage.isOption1Selected()) {
			startSapAiCoreLogin();
			return true;
		}
		
		if (loginOptionsPage.isOption2Selected()) {
			startOllamaLogin();
			return true;
		}
		
		return false;
	}
	
	private void startSapAiCoreLogin() {
		SapLoginWizard wizard = new SapLoginWizard(browser);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();
	}
	
	private void startOllamaLogin() {
		OllamaLoginWizard wizard = new OllamaLoginWizard(browser);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();
	}

}
