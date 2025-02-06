package com.developer.nefarious.zjoule.plugin.login.pages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * A wizard page for selecting an AI provider during the login process.
 * <p>
 * The {@code LoginOptionsPage} presents the user with two options:
 * <ul>
 *   <li>SAP AI Core - Selects a model from the SAP AI Core Generative AI Hub.</li>
 *   <li>Ollama (Local) - Selects a local Ollama model.</li>
 * </ul>
 * The page remains incomplete until the user selects an option.
 * </p>
 */
public class LoginOptionsPage extends WizardPage {

    /** The unique identifier for this wizard page. */
    public static final String PAGE_ID = "Login Options";

    /** The radio button for selecting SAP AI Core. */
    private Button option1;

    /** The radio button for selecting Ollama (Local). */
    private Button option2;

    /**
     * Constructs a {@code LoginOptionsPage} for selecting an AI provider.
     */
    public LoginOptionsPage() {
        super(PAGE_ID);
        setTitle("Login Options");
        setDescription("Choose the AI provider.");
        setPageComplete(false); // Initially set the page as incomplete
    }

    /**
     * Checks whether the SAP AI Core option is selected.
     *
     * @return {@code true} if the SAP AI Core option is selected, {@code false} otherwise.
     */
    public boolean isOption1Selected() {
        return option1.getSelection();
    }

    /**
     * Checks whether the Ollama (Local) option is selected.
     *
     * @return {@code true} if the Ollama (Local) option is selected, {@code false} otherwise.
     */
    public boolean isOption2Selected() {
        return option2.getSelection();
    }

    /**
     * Creates the UI components for the wizard page.
     * <p>
     * This method sets up two radio buttons for selecting the AI provider
     * and ensures that selecting an option marks the page as complete.
     * </p>
     *
     * @param parent the parent composite in which UI components are created.
     */
    @Override
    public void createControl(final Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(1, false));

        option1 = new Button(container, SWT.RADIO);
        option1.setText("SAP AI Core");
        option1.setToolTipText("Select model from the SAP AI Core Generative AI Hub.");
        option1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        option1.addListener(SWT.Selection, event -> setPageComplete(true));

        option2 = new Button(container, SWT.RADIO);
        option2.setText("Ollama (Local)");
        option2.setToolTipText("Select a local Ollama model.");
        option2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        option2.addListener(SWT.Selection, event -> setPageComplete(true));

        setControl(container);
    }
}