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
import com.developer.nefarious.zjoule.memory.EclipseMemory;
import com.developer.nefarious.zjoule.utils.ObjectSerializer;

public class LoginWizard extends Wizard {
	
	private ILoginClient loginClient;
	
	public LoginWizard() {
        setWindowTitle("Login to SAP AI Core");
        loginClient = createLoginClient();
        TemporaryMemoryAccessToken.initialize(new ObjectSerializer(), new EclipseMemory());
        TemporaryMemoryServiceKey.initialize(new ObjectSerializer(), new EclipseMemory());
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
		TemporaryMemoryAccessToken tmpMemoryAccessToken = TemporaryMemoryAccessToken.getInstance();
		TemporaryMemoryServiceKey tmpMemoryServiceKey = TemporaryMemoryServiceKey.getInstance();
		IAuthClient tmpAuthClient = new AuthClient(tmpMemoryAccessToken, tmpMemoryServiceKey, new AuthClientHelper());
		return new LoginClient(new LoginClientHelper(), tmpAuthClient);
	}

}
