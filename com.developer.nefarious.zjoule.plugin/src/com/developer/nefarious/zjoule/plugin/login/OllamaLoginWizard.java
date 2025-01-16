package com.developer.nefarious.zjoule.plugin.login;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.browser.Browser;

import com.developer.nefarious.zjoule.plugin.login.pages.FirstOllamaLoginPage;
import com.developer.nefarious.zjoule.plugin.login.pages.SecondOllamaLoginPage;

public class OllamaLoginWizard extends Wizard {
	
	private Browser browser;
	
	public OllamaLoginWizard(final Browser browser) {
        this.browser = browser;

        setWindowTitle("Login to Ollama");
    }
	
	@Override
    public void addPages() {
        addPage(new FirstOllamaLoginPage());
        addPage(new SecondOllamaLoginPage());
    }

	@Override
	public boolean performFinish() {
//		TemporaryMemoryOllamaConfig.getInstance().persist();
		
//		SessionManager.login(browser);
		return true;
	}

}
