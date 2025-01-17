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

public class SecondOllamaLoginWizardPage extends WizardPage {
	
	public static final String PAGE_ID = "Ollama Login Second Page";
	
	private Combo ollamaModelDropdown;
	
	private List<OllamaModel> ollamaModelsForSelection = new ArrayList<>();
	
	private IMemoryObject<OllamaModel> memoryOllamaModel;
	
	public SecondOllamaLoginWizardPage(final IMemoryObject<OllamaModel> memoryOllamaModel) {
		super(PAGE_ID);
		setTitle("Ollama Setup");
        setDescription("Select the Ollama model.");
        setPageComplete(false); // Initially set the page as incomplete
        this.memoryOllamaModel = memoryOllamaModel;
	}

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
	
	public List<OllamaModel> getOllamaModelsForSelection() {
        return ollamaModelsForSelection;
    }
	
	public void setOllamaModelsForSelection(final GetOllamaModelsResponse getOllamaModelsResponse) {
		this.ollamaModelsForSelection = getOllamaModelsResponse.getModels();
		List<String> ollamaModelNames = OllamaModelNamesExtractor.extractModelNames(getOllamaModelsResponse);
		ollamaModelDropdown.setItems(ollamaModelNames.toArray(new String[0]));
	}
	
	public Combo getOllamaModelDropdown() {
        return ollamaModelDropdown;
    }

}
