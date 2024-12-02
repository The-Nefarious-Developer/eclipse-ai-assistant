package com.developer.nefarious.zjoule.login;

import org.eclipse.jface.wizard.Wizard;
import com.developer.nefarious.zjoule.auth.AuthClient;
import com.developer.nefarious.zjoule.auth.AuthClientHelper;
import com.developer.nefarious.zjoule.auth.IAuthClient;
import com.developer.nefarious.zjoule.login.api.ILoginClient;
import com.developer.nefarious.zjoule.login.api.LoginClient;
import com.developer.nefarious.zjoule.login.api.LoginClientHelper;
import com.developer.nefarious.zjoule.login.memory.TemporaryMemoryAccessToken;
import com.developer.nefarious.zjoule.login.memory.TemporaryMemoryServiceKey;
import com.developer.nefarious.zjoule.login.pages.FirstLoginWizardPage;
import com.developer.nefarious.zjoule.login.pages.SecondLoginWizardPage;

public class LoginWizard extends Wizard {
	
	private ILoginClient loginClient;
	
	public LoginWizard() {
        setWindowTitle("Login to SAP AI Core");
        loginClient = createLoginClient();
    }
	
	@Override
	public void addPages() {
		addPage(new FirstLoginWizardPage(loginClient));
		addPage(new SecondLoginWizardPage(loginClient));
	}

	@Override
	public boolean performFinish() {
		System.out.println("done!");
		// TODO Auto-generated method stub
		return true;
	}
	
	private ILoginClient createLoginClient() {
		IAuthClient tmpAuthClient = new AuthClient(new TemporaryMemoryAccessToken(), new TemporaryMemoryServiceKey(), new AuthClientHelper());
		return new LoginClient(new LoginClientHelper(), tmpAuthClient);
	}

}
