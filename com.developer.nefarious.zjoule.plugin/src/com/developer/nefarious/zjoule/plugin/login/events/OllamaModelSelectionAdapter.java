package com.developer.nefarious.zjoule.plugin.login.events;

import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.developer.nefarious.zjoule.plugin.login.pages.SecondOllamaLoginWizardPage;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;
import com.developer.nefarious.zjoule.plugin.models.OllamaModel;

/**
 * A selection adapter for handling Ollama model selection events.
 * <p>
 * The {@code OllamaModelSelectionAdapter} listens for selection events on
 * the Ollama model dropdown in the {@link SecondOllamaLoginWizardPage}. It retrieves
 * the selected model and stores it in memory, while also updating the wizard page's
 * completion state.
 * </p>
 */
public class OllamaModelSelectionAdapter extends SelectionAdapter {

    /** The wizard page where the Ollama model selection occurs. */
    private SecondOllamaLoginWizardPage secondOllamaLoginWizardPage;

    /** The memory object used to store the selected Ollama model. */
    private IMemoryObject<OllamaModel> memoryOllamaModel;

    /**
     * Constructs an {@code OllamaModelSelectionAdapter} for handling model selection events.
     *
     * @param secondOllamaLoginWizardPage the wizard page containing the model selection dropdown.
     * @param memoryOllamaModel           the memory object used to persist the selected model.
     */
    public OllamaModelSelectionAdapter(
            final SecondOllamaLoginWizardPage secondOllamaLoginWizardPage,
            final IMemoryObject<OllamaModel> memoryOllamaModel) {
        this.secondOllamaLoginWizardPage = secondOllamaLoginWizardPage;
        this.memoryOllamaModel = memoryOllamaModel;
    }

    /**
     * Retrieves the {@link OllamaModel} object that matches the given model name.
     *
     * @param selectedOllamaModelName the name of the selected Ollama model.
     * @return the corresponding {@link OllamaModel} object, or {@code null} if no match is found.
     */
    private OllamaModel getSelectedOllamaModelObject(final String selectedOllamaModelName) {
        List<OllamaModel> ollamaModelsForSelection = secondOllamaLoginWizardPage.getOllamaModelsForSelection();

        return ollamaModelsForSelection.stream()
                .filter(ollamaModel -> selectedOllamaModelName.equals(ollamaModel.getName()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Handles the selection event for the Ollama model dropdown.
     * <p>
     * If a model is selected, it is stored in memory, and the wizard page
     * is marked as complete. If no model is selected, the wizard page remains incomplete.
     * </p>
     *
     * @param e the {@link SelectionEvent} triggered by user selection.
     */
    @Override
    public void widgetSelected(final SelectionEvent e) {
        String selectedOllamaModelName = secondOllamaLoginWizardPage.getOllamaModelDropdown().getText();
        if (selectedOllamaModelName.isEmpty()) {
            secondOllamaLoginWizardPage.setPageComplete(false);
        } else {
            OllamaModel selectedOllamaModelObject = getSelectedOllamaModelObject(selectedOllamaModelName);
            memoryOllamaModel.save(selectedOllamaModelObject);
            secondOllamaLoginWizardPage.setPageComplete(true);
        }
    }
}