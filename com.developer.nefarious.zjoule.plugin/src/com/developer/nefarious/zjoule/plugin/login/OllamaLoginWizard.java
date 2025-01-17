package com.developer.nefarious.zjoule.plugin.login;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.browser.Browser;

import com.developer.nefarious.zjoule.plugin.auth.SessionManager;
import com.developer.nefarious.zjoule.plugin.login.api.IOllamaLoginClient;
import com.developer.nefarious.zjoule.plugin.login.api.OllamaLoginClient;
import com.developer.nefarious.zjoule.plugin.login.api.OllamaLoginClientHelper;
import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryOllamaEndpoint;
import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryOllamaModel;
import com.developer.nefarious.zjoule.plugin.login.pages.FirstOllamaLoginWizardPage;
import com.developer.nefarious.zjoule.plugin.login.pages.SecondOllamaLoginWizardPage;

public class OllamaLoginWizard extends Wizard {
	
	private Browser browser;
	
	private IOllamaLoginClient ollamaLoginClient;
	
	public OllamaLoginWizard(final Browser browser) {
        this.browser = browser;

        setWindowTitle("Login to Ollama");
        ollamaLoginClient = new OllamaLoginClient(new OllamaLoginClientHelper());
    }
	
	@Override
    public void addPages() {
        addPage(new FirstOllamaLoginWizardPage(ollamaLoginClient, TemporaryMemoryOllamaEndpoint.getInstance()));
        addPage(new SecondOllamaLoginWizardPage(TemporaryMemoryOllamaModel.getInstance()));
    }

	@Override
	public boolean performFinish() {
		TemporaryMemoryOllamaEndpoint.getInstance().persist();
		TemporaryMemoryOllamaModel.getInstance().persist();
		
//		SessionManager.logout(browser, new EclipseMemory());
		SessionManager.login(browser);
		return true;
	}

}
