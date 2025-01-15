package com.developer.nefarious.zjoule.plugin.login;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.browser.Browser;

import com.developer.nefarious.zjoule.plugin.login.pages.LoginOptionsPage;

public class LoginWizard extends Wizard {
	
	/** The browser instance used for login-related UI updates. */
    private Browser browser;
    
    public LoginWizard(final Browser browser) {
        this.browser = browser;

        setWindowTitle("Setup AI Provider");
    }
    
    @Override
    public void addPages() {
        addPage(new LoginOptionsPage());
    }

	@Override
	public boolean performFinish() {
		return false;
	}

}
