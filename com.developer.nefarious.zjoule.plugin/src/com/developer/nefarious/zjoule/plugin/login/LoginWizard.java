package com.developer.nefarious.zjoule.plugin.login;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.browser.Browser;

import com.developer.nefarious.zjoule.plugin.auth.AuthClient;
import com.developer.nefarious.zjoule.plugin.auth.AuthClientHelper;
import com.developer.nefarious.zjoule.plugin.auth.IAuthClient;
import com.developer.nefarious.zjoule.plugin.auth.SessionManager;
import com.developer.nefarious.zjoule.plugin.login.api.ILoginClient;
import com.developer.nefarious.zjoule.plugin.login.api.LoginClient;
import com.developer.nefarious.zjoule.plugin.login.api.LoginClientHelper;
import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryAccessToken;
import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryDeployment;
import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryResourceGroup;
import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryServiceKey;
import com.developer.nefarious.zjoule.plugin.login.pages.FirstLoginWizardPage;
import com.developer.nefarious.zjoule.plugin.login.pages.SecondLoginWizardPage;

public class LoginWizard extends Wizard {

	private Browser browser;

	private ILoginClient loginClient;

	public LoginWizard(final Browser browser) {
		this.browser = browser;

        setWindowTitle("Login to SAP AI Core");
        loginClient = createLoginClient();
    }

	@Override
	public void addPages() {
		addPage(new FirstLoginWizardPage(loginClient));
		addPage(new SecondLoginWizardPage(loginClient, TemporaryMemoryResourceGroup.getInstance(), TemporaryMemoryDeployment.getInstance()));
	}

	private ILoginClient createLoginClient() {
        TemporaryMemoryAccessToken tmpMemoryAccessToken = TemporaryMemoryAccessToken.getInstance();
		TemporaryMemoryServiceKey tmpMemoryServiceKey = TemporaryMemoryServiceKey.getInstance();

		IAuthClient tmpAuthClient = new AuthClient(tmpMemoryAccessToken, tmpMemoryServiceKey, new AuthClientHelper());
		return new LoginClient(new LoginClientHelper(), tmpAuthClient);
	}

	@Override
	public boolean performFinish() {
		TemporaryMemoryAccessToken.getInstance().persist();
		TemporaryMemoryServiceKey.getInstance().persist();
		TemporaryMemoryResourceGroup.getInstance().persist();
		TemporaryMemoryDeployment.getInstance().persist();

		SessionManager.login(browser);
		return true;
	}

}
