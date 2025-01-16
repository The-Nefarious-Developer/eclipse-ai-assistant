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

public class SecondOllamaLoginPage extends WizardPage {
	
	public static final String PAGE_ID = "Ollama Login Second Page";
	
	private Combo modelDropdown;
	
	private List<String> modelsForSelection = new ArrayList<>();
	
	public SecondOllamaLoginPage() {
		super(PAGE_ID);
		
		setTitle("Ollama Setup");
        setDescription("Select the Ollama model.");
        setPageComplete(false); // Initially set the page as incomplete
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
	    
        Label modelLabel = new Label(container, SWT.NONE);
        modelLabel.setText("Select the Model:");

        modelDropdown = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        modelDropdown.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        modelDropdown.addListener(SWT.Selection, event -> selectModel());
              
        setControl(container);
	}
	
	private void selectModel() {
		setPageComplete(true);
	}
	
	public void setModelsForSelection(List<String> modelsForSelection) {
		modelDropdown.setItems(modelsForSelection.toArray(new String[0]));
	}

}
