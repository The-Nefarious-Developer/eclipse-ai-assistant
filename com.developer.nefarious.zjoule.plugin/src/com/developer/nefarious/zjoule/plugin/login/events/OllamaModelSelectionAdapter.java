package com.developer.nefarious.zjoule.plugin.login.events;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.developer.nefarious.zjoule.plugin.login.pages.SecondOllamaLoginWizardPage;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;
import com.developer.nefarious.zjoule.plugin.models.OllamaModel;

public class OllamaModelSelectionAdapter extends SelectionAdapter {
	
	private SecondOllamaLoginWizardPage secondOllamaLoginWizardPage;
	
	private IMemoryObject<OllamaModel> memoryOllamaModel;
	
	public OllamaModelSelectionAdapter(
			final SecondOllamaLoginWizardPage secondOllamaLoginWizardPage,
			final IMemoryObject<OllamaModel> memoryOllamaModel) {
		this.secondOllamaLoginWizardPage = secondOllamaLoginWizardPage;
		this.memoryOllamaModel = memoryOllamaModel;
	}
	
	@Override
    public void widgetSelected(final SelectionEvent e) {
		
	}
}
