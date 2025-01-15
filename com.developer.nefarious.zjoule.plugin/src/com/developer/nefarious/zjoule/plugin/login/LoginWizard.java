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
import com.developer.nefarious.zjoule.plugin.login.pages.LoginOptionsPage;
import com.developer.nefarious.zjoule.plugin.login.pages.SecondLoginWizardPage;

/**
 * A wizard for logging into SAP AI Core.
 * <p>
 * The {@code LoginWizard} class manages a multi-page wizard that guides the user
 * through the login process, including entering credentials and selecting configurations.
 * <p>
 * It integrates with temporary memory components and client objects to handle authentication
 * and session management.
 */
public class LoginWizard extends Wizard {

    /** The browser instance used for login-related UI updates. */
    private Browser browser;

    /** The client responsible for managing the login process. */
    private ILoginClient loginClient;

    /**
     * Constructs a new {@code LoginWizard} instance.
     *
     * @param browser the {@link Browser} instance used for login-related UI updates.
     */
    public LoginWizard(final Browser browser) {
        this.browser = browser;

        setWindowTitle("AI Provider Setup");
        loginClient = createLoginClient();
    }

    /**
     * Adds the wizard pages to the login process.
     * <p>
     * This method initializes:
     * <ul>
     *   <li>The {@link FirstLoginWizardPage} for entering credentials.</li>
     *   <li>The {@link SecondLoginWizardPage} for selecting resources and configurations.</li>
     * </ul>
     */
    @Override
    public void addPages() {
        addPage(new FirstLoginWizardPage(loginClient));
        addPage(new SecondLoginWizardPage(loginClient, TemporaryMemoryResourceGroup.getInstance(), TemporaryMemoryDeployment.getInstance()));
    }

    /**
     * Creates and initializes the {@link ILoginClient} used for the login process.
     *
     * @return a new {@link ILoginClient} instance.
     */
    private ILoginClient createLoginClient() {
        TemporaryMemoryAccessToken tmpMemoryAccessToken = TemporaryMemoryAccessToken.getInstance();
        TemporaryMemoryServiceKey tmpMemoryServiceKey = TemporaryMemoryServiceKey.getInstance();

        IAuthClient tmpAuthClient = new AuthClient(tmpMemoryAccessToken, tmpMemoryServiceKey, new AuthClientHelper());
        return new LoginClient(new LoginClientHelper(), tmpAuthClient);
    }

    /**
     * Completes the login process and persists the temporary memory.
     * <p>
     * This method persists the following temporary memory components:
     * <ul>
     *   <li>{@link TemporaryMemoryAccessToken}</li>
     *   <li>{@link TemporaryMemoryServiceKey}</li>
     *   <li>{@link TemporaryMemoryResourceGroup}</li>
     *   <li>{@link TemporaryMemoryDeployment}</li>
     * </ul>
     * It also logs in the user by invoking {@link SessionManager#login(Browser)}.
     *
     * @return {@code true} if the wizard completes successfully.
     */
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