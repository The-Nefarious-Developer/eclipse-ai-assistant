package com.developer.nefarious.zjoule.plugin.login.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.developer.nefarious.zjoule.plugin.login.api.GetOllamaModelsResponse;
import com.developer.nefarious.zjoule.plugin.login.events.OllamaModelSelectionAdapter;
import com.developer.nefarious.zjoule.plugin.login.utils.OllamaModelNamesExtractor;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;
import com.developer.nefarious.zjoule.plugin.models.OllamaModel;

/**
 * The second wizard page for selecting an Ollama model.
 * <p>
 * The {@code SecondOllamaLoginWizardPage} allows users to select a model from
 * a dropdown list populated with available Ollama models retrieved from the API.
 * The selection is stored in memory and used during the login process.
 * </p>
 */
public class SecondOllamaLoginWizardPage extends WizardPage {

    /** The unique identifier for this wizard page. */
    public static final String PAGE_ID = "Ollama Login Second Page";

    /** The dropdown menu for selecting an Ollama model. */
    private Combo ollamaModelDropdown;

    /** A list of available Ollama models for selection. */
    private List<OllamaModel> ollamaModelsForSelection = new ArrayList<>();

    /** Memory storage for persisting the selected Ollama model. */
    private IMemoryObject<OllamaModel> memoryOllamaModel;

    /**
     * Constructs a {@code SecondOllamaLoginWizardPage} for selecting an Ollama model.
     *
     * @param memoryOllamaModel the memory object for storing the selected Ollama model.
     */
    public SecondOllamaLoginWizardPage(final IMemoryObject<OllamaModel> memoryOllamaModel) {
        super(PAGE_ID);
        setTitle("Ollama Setup");
        setDescription("Select the Ollama model.");
        setPageComplete(false); // Initially set the page as incomplete
        this.memoryOllamaModel = memoryOllamaModel;
    }

    /**
     * Creates the UI components for the wizard page.
     * <p>
     * This method sets up a dropdown menu for selecting an Ollama model
     * and attaches an event listener to handle model selection.
     * </p>
     *
     * @param parent the parent composite in which UI components are created.
     */
    @Override
    public void createControl(final Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(2, false));

        Label modelLabel = new Label(container, SWT.NONE);
        modelLabel.setText("Select the Model:");

        ollamaModelDropdown = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        ollamaModelDropdown.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        ollamaModelDropdown.addSelectionListener(new OllamaModelSelectionAdapter(this, memoryOllamaModel));

        setControl(container);
    }

    /**
     * Retrieves the list of Ollama models available for selection.
     *
     * @return a list of {@link OllamaModel} objects.
     */
    public List<OllamaModel> getOllamaModelsForSelection() {
        return ollamaModelsForSelection;
    }

    /**
     * Populates the dropdown menu with available Ollama models.
     * <p>
     * This method extracts model names from the API response and updates
     * the selection list.
     * </p>
     *
     * @param getOllamaModelsResponse the API response containing the available models.
     */
    public void setOllamaModelsForSelection(final GetOllamaModelsResponse getOllamaModelsResponse) {
        this.ollamaModelsForSelection = getOllamaModelsResponse.getModels();
        List<String> ollamaModelNames = OllamaModelNamesExtractor.extractModelNames(getOllamaModelsResponse);
        ollamaModelDropdown.setItems(ollamaModelNames.toArray(new String[0]));
    }

    /**
     * Retrieves the dropdown component used for selecting an Ollama model.
     *
     * @return the {@link Combo} component for model selection.
     */
    public Combo getOllamaModelDropdown() {
        return ollamaModelDropdown;
    }

    /**
     * Controls the visibility of the wizard page.
     * <p>
     * When the page is hidden, it deselects all options and marks the page as incomplete.
     * </p>
     *
     * @param visible {@code true} if the page is being shown, {@code false} otherwise.
     */
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (!visible) {
            ollamaModelDropdown.deselectAll();
            setPageComplete(false);
        }
    }
}