package com.developer.nefarious.zjoule.plugin.login.pages;

import java.io.IOException;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.developer.nefarious.zjoule.plugin.login.api.GetOllamaModelsResponse;
import com.developer.nefarious.zjoule.plugin.login.api.IOllamaLoginClient;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;

/**
 * The first wizard page for setting up an Ollama instance.
 * <p>
 * The {@code FirstOllamaLoginWizardPage} allows the user to enter the local Ollama endpoint
 * and validates its existence by retrieving the available models. If the endpoint is invalid,
 * an error message is displayed.
 * </p>
 */
public class FirstOllamaLoginWizardPage extends WizardPage {

    /** The unique identifier for this wizard page. */
    public static final String PAGE_ID = "Ollama Login First Page";

    /** The client responsible for retrieving Ollama models from the provided endpoint. */
    private IOllamaLoginClient ollamaLoginClient;

    /** Memory storage for persisting the Ollama endpoint. */
    private IMemoryObject<String> memoryOllamaEndpoint;

    /** The text field for entering the Ollama endpoint. */
    private Text endpointText;

    /** The text field for displaying error messages (initially hidden). */
    private Text errorText;

    /**
     * Constructs a {@code FirstOllamaLoginWizardPage} for entering the Ollama endpoint.
     *
     * @param ollamaLoginClient   the client for querying available Ollama models.
     * @param memoryOllamaEndpoint the memory object for storing the selected endpoint.
     */
    public FirstOllamaLoginWizardPage(
            final IOllamaLoginClient ollamaLoginClient,
            final IMemoryObject<String> memoryOllamaEndpoint) {
        super(PAGE_ID);
        setTitle("Ollama Setup");
        setDescription("Enter the host and port for the local Ollama instance.");
        setPageComplete(true);
        this.ollamaLoginClient = ollamaLoginClient;
        this.memoryOllamaEndpoint = memoryOllamaEndpoint;
    }

    /**
     * Creates the UI components for the wizard page.
     *
     * @param parent the parent composite in which UI components are created.
     */
    @Override
    public void createControl(final Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(2, false));

        Label inputLabel = new Label(container, SWT.NONE);
        inputLabel.setText("Ollama Endpoint:");

        endpointText = new Text(container, SWT.BORDER);
        endpointText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        endpointText.setText("http://localhost:11434");

        // Hidden error text widget
        errorText = new Text(container, SWT.READ_ONLY | SWT.MULTI | SWT.WRAP);
        GridData errorTextGridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
        errorText.setLayoutData(errorTextGridData);
        errorText.setForeground(container.getDisplay().getSystemColor(SWT.COLOR_RED));
        errorText.setVisible(false); // Initially hidden

        setControl(container);
    }

    /**
     * Determines whether the next wizard page can be displayed.
     *
     * @return {@code true} to allow navigation to the next page.
     */
    @Override
    public boolean canFlipToNextPage() {
        return true;
    }

    /**
     * Retrieves the next wizard page after validating the entered Ollama endpoint.
     * <p>
     * If the endpoint is empty or invalid, an error message is displayed and navigation
     * to the next page is blocked.
     * </p>
     *
     * @return the next {@link IWizardPage}, or {@code null} if validation fails.
     */
    @Override
    public IWizardPage getNextPage() {

        String ollamaEndpoint = endpointText.getText();

        if (ollamaEndpoint == null || ollamaEndpoint.isEmpty() || ollamaEndpoint.isBlank()) {
            displayErrorMessage("Please, enter a local Ollama endpoint to proceed.");
            return null;
        }

        try {
            setAvailableModelsFor(ollamaEndpoint);
        } catch (IllegalArgumentException | IOException | InterruptedException e) {
            displayErrorMessage("Local instance of Ollama invalid or doesn't exist at the provided address.");
            return null;
        }

        errorText.setVisible(false);
        memoryOllamaEndpoint.save(ollamaEndpoint);
        return super.getNextPage(); // Proceed to the next wizard page
    }

    /**
     * Queries the Ollama API for available models at the specified endpoint.
     * <p>
     * This method retrieves available models and updates the second wizard page
     * with the options.
     * </p>
     *
     * @param ollamaEndpoint the URL of the Ollama instance.
     * @throws IOException          if an I/O error occurs while querying the API.
     * @throws InterruptedException if the request is interrupted.
     */
    private void setAvailableModelsFor(final String ollamaEndpoint) throws IOException, InterruptedException {
        GetOllamaModelsResponse getOllamaModelsResponse = ollamaLoginClient.getModels(ollamaEndpoint);
        SecondOllamaLoginWizardPage secondPage = 
                (SecondOllamaLoginWizardPage) getWizard().getPage(SecondOllamaLoginWizardPage.PAGE_ID);
        secondPage.setOllamaModelsForSelection(getOllamaModelsResponse);
    }

    /**
     * Displays an error message in the error text field.
     *
     * @param message the error message to display.
     */
    private void displayErrorMessage(final String message) {
        errorText.setText(message);
        errorText.setVisible(true);
    }
}