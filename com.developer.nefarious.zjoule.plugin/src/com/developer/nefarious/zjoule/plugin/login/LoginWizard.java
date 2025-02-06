package com.developer.nefarious.zjoule.plugin.login;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Shell;

import com.developer.nefarious.zjoule.plugin.login.pages.LoginOptionsPage;

/**
 * A wizard for selecting and initiating the login process for an AI provider.
 * <p>
 * The {@code LoginWizard} presents a user interface for choosing between
 * different AI provider login options, such as SAP AI Core and Ollama.
 * It delegates the login process to the corresponding wizard based on the user's selection.
 * </p>
 */
public class LoginWizard extends Wizard {

    /** The parent shell for displaying the wizard dialog. */
    private Shell shell;

    /** The browser instance used for authentication during the login process. */
    private Browser browser;

    /**
     * Constructs a {@code LoginWizard} with the specified shell and browser.
     * <p>
     * This wizard sets up the login selection process and configures the UI title.
     * </p>
     *
     * @param shell   the parent {@link Shell} for displaying the wizard dialog.
     * @param browser the {@link Browser} instance used for login authentication.
     */
    public LoginWizard(final Shell shell, final Browser browser) {
        this.shell = shell;
        this.browser = browser;

        setWindowTitle("Setup AI Provider");
    }

    /**
     * Adds the login options page to the wizard.
     * <p>
     * This page presents the available AI provider login choices to the user.
     * </p>
     */
    @Override
    public void addPages() {
        addPage(new LoginOptionsPage());
    }

    /**
     * Completes the wizard by initiating the login process based on user selection.
     * <p>
     * If the user selects SAP AI Core, the {@code startSapAiCoreLogin()} method is triggered.
     * If the user selects Ollama, the {@code startOllamaLogin()} method is triggered.
     * The wizard completes successfully if a valid selection is made.
     * </p>
     *
     * @return {@code true} if the login process starts successfully, {@code false} otherwise.
     */
    @Override
    public boolean performFinish() {
        LoginOptionsPage loginOptionsPage = (LoginOptionsPage) getPage(LoginOptionsPage.PAGE_ID);

        if (loginOptionsPage.isOption1Selected()) {
            startSapAiCoreLogin();
            return true;
        }

        if (loginOptionsPage.isOption2Selected()) {
            startOllamaLogin();
            return true;
        }

        return false;
    }

    /**
     * Initiates the login process for SAP AI Core.
     * <p>
     * This method launches a new {@link SapLoginWizard} within a {@link WizardDialog}.
     * </p>
     */
    private void startSapAiCoreLogin() {
        SapLoginWizard wizard = new SapLoginWizard(browser);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();
    }

    /**
     * Initiates the login process for Ollama.
     * <p>
     * This method launches a new {@link OllamaLoginWizard} within a {@link WizardDialog}.
     * </p>
     */
    private void startOllamaLogin() {
        OllamaLoginWizard wizard = new OllamaLoginWizard(browser);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();
    }
}