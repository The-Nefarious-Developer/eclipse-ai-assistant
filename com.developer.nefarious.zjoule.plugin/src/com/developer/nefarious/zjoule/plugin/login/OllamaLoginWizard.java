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

/**
 * A wizard for handling the Ollama login process.
 * <p>
 * The {@code OllamaLoginWizard} guides the user through multiple steps
 * to authenticate with Ollama and configure its endpoint and model settings.
 * The wizard clears existing sessions before finalizing the login process.
 * </p>
 */
public class OllamaLoginWizard extends Wizard {

    /** The browser instance used for authentication during the login process. */
    private Browser browser;

    /** The client responsible for managing the Ollama login process. */
    private IOllamaLoginClient ollamaLoginClient;

    /**
     * Constructs an {@code OllamaLoginWizard} with the specified browser.
     * <p>
     * Initializes the wizard with a title and configures an instance of
     * {@link OllamaLoginClient} for handling API interactions.
     * </p>
     *
     * @param browser the {@link Browser} instance used for authentication.
     */
    public OllamaLoginWizard(final Browser browser) {
        this.browser = browser;

        setWindowTitle("Login to Ollama");
        ollamaLoginClient = new OllamaLoginClient(new OllamaLoginClientHelper());
    }

    /**
     * Adds the necessary pages to the wizard for configuring the Ollama login.
     * <p>
     * The wizard includes:
     * <ul>
     *   <li>{@link FirstOllamaLoginWizardPage} - Handles endpoint configuration.</li>
     *   <li>{@link SecondOllamaLoginWizardPage} - Handles model selection.</li>
     * </ul>
     * </p>
     */
    @Override
    public void addPages() {
        addPage(new FirstOllamaLoginWizardPage(ollamaLoginClient, TemporaryMemoryOllamaEndpoint.getInstance()));
        addPage(new SecondOllamaLoginWizardPage(TemporaryMemoryOllamaModel.getInstance()));
    }

    /**
     * Completes the wizard by persisting the login configuration and logging in.
     * <p>
     * This method:
     * <ul>
     *   <li>Clears all existing sessions using {@link SessionManager#clearAllSessions()}.</li>
     *   <li>Persists the Ollama endpoint and model information.</li>
     *   <li>Triggers the login process using {@link SessionManager#login(Browser)}.</li>
     * </ul>
     * </p>
     *
     * @return {@code true} to indicate that the login process was successfully completed.
     */
    @Override
    public boolean performFinish() {
        SessionManager.clearAllSessions();

        TemporaryMemoryOllamaEndpoint.getInstance().persist();
        TemporaryMemoryOllamaModel.getInstance().persist();

        SessionManager.login(browser);
        return true;
    }
}