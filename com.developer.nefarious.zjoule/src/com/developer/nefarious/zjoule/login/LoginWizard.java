package com.developer.nefarious.zjoule.login;

import org.eclipse.jface.wizard.Wizard;
import com.developer.nefarious.zjoule.auth.AuthClient;
import com.developer.nefarious.zjoule.auth.AuthClientHelper;
import com.developer.nefarious.zjoule.auth.IAuthClient;
import com.developer.nefarious.zjoule.login.api.ILoginClient;
import com.developer.nefarious.zjoule.login.api.LoginClient;
import com.developer.nefarious.zjoule.login.api.LoginClientHelper;
import com.developer.nefarious.zjoule.login.memory.TemporaryMemoryAccessToken;
import com.developer.nefarious.zjoule.login.memory.TemporaryMemoryDeployment;
import com.developer.nefarious.zjoule.login.memory.TemporaryMemoryResourceGroup;
import com.developer.nefarious.zjoule.login.memory.TemporaryMemoryServiceKey;
import com.developer.nefarious.zjoule.login.pages.FirstLoginWizardPage;
import com.developer.nefarious.zjoule.login.pages.SecondLoginWizardPage;
import com.developer.nefarious.zjoule.memory.EclipseMemory;
import com.developer.nefarious.zjoule.memory.utils.ObjectSerializer;

public class LoginWizard extends Wizard {
	
	private ILoginClient loginClient;
	
	public LoginWizard() {
        setWindowTitle("Login to SAP AI Core");
        loginClient = createLoginClient();
    }
	
	@Override
	public void addPages() {
		addPage(new FirstLoginWizardPage(loginClient));
		addPage(new SecondLoginWizardPage(loginClient, TemporaryMemoryResourceGroup.getInstance(), TemporaryMemoryDeployment.getInstance()));
	}

	@Override
	public boolean performFinish() {
		TemporaryMemoryAccessToken.getInstance().persist();
		TemporaryMemoryServiceKey.getInstance().persist();
		TemporaryMemoryResourceGroup.getInstance().persist();
		TemporaryMemoryDeployment.getInstance().persist();
		return true;
	}
	
	private ILoginClient createLoginClient() {
        TemporaryMemoryAccessToken tmpMemoryAccessToken = TemporaryMemoryAccessToken.getInstance();       
		TemporaryMemoryServiceKey tmpMemoryServiceKey = TemporaryMemoryServiceKey.getInstance();
		
		IAuthClient tmpAuthClient = new AuthClient(tmpMemoryAccessToken, tmpMemoryServiceKey, new AuthClientHelper());
		return new LoginClient(new LoginClientHelper(), tmpAuthClient);
	}

}
