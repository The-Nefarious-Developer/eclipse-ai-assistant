package com.developer.nefarious.zjoule.plugin.login.events;

import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.developer.nefarious.zjoule.plugin.login.pages.SecondOllamaLoginWizardPage;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;
import com.developer.nefarious.zjoule.plugin.models.OllamaModel;

public class OllamaModelSelectionAdapter extends SelectionAdapter {
	
	private SecondOllamaLoginWizardPage secondOllamaLoginWizardPage;
	
	private IMemoryObject<OllamaModel> memoryOllamaModel;
	
	// @formatter:off
	public OllamaModelSelectionAdapter(
			final SecondOllamaLoginWizardPage secondOllamaLoginWizardPage,
			final IMemoryObject<OllamaModel> memoryOllamaModel) {
		// @formatter:on
		this.secondOllamaLoginWizardPage = secondOllamaLoginWizardPage;
		this.memoryOllamaModel = memoryOllamaModel;
	}
	
	private OllamaModel getSelectedOllamaModelObject(final String selectedOllamaModelName) {
        List<OllamaModel> ollamaModelForSelection = secondOllamaLoginWizardPage.getOllamaModelsForSelection();

        return ollamaModelForSelection.stream()
                .filter(ollamaModel -> selectedOllamaModelName.equals(ollamaModel.getName()))
                .findFirst()
                .orElse(null);
    }
	
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
