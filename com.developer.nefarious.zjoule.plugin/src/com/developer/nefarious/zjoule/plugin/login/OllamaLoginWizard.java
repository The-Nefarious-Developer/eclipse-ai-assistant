package com.developer.nefarious.zjoule.plugin.login;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.browser.Browser;

import com.developer.nefarious.zjoule.plugin.login.api.IOllamaLoginClient;
import com.developer.nefarious.zjoule.plugin.login.api.OllamaLoginClient;
import com.developer.nefarious.zjoule.plugin.login.api.OllamaLoginClientHelper;
import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryOllamaEndpoint;
import com.developer.nefarious.zjoule.plugin.login.pages.FirstOllamaLoginPage;
import com.developer.nefarious.zjoule.plugin.login.pages.SecondOllamaLoginPage;

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
        addPage(new FirstOllamaLoginPage(ollamaLoginClient));
        addPage(new SecondOllamaLoginPage());
    }

	@Override
	public boolean performFinish() {
		TemporaryMemoryOllamaEndpoint.getInstance().persist();
		
//		SessionManager.login(browser);
		return true;
	}

}
