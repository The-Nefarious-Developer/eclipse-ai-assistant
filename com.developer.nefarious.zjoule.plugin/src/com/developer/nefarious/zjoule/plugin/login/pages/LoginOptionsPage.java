package com.developer.nefarious.zjoule.plugin.login.pages;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class LoginOptionsPage extends WizardPage {
	
	public static final String PAGE_ID = "Login Options";

	public LoginOptionsPage() {
		super(PAGE_ID);
		setTitle("Login Options");
        setDescription("Choose the AI provider.");
        setPageComplete(false); // Initially set the page as incomplete
        
	}

	@Override
	public void createControl(final Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		
		Button option1 = new Button(container, SWT.RADIO);
		option1.setText("SAP AI Core");
	    option1.setToolTipText("Select model from the SAP AI Core Generative AI Hub.");
	    option1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	    option1.addListener(SWT.Selection, event -> setOption1());
	    
	    Button option2 = new Button(container, SWT.RADIO);
	    option2.setText("Ollama (Local)");
	    option2.setToolTipText("Select a local Ollama model.");
	    option2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	    option2.addListener(SWT.Selection, event -> setOption2());
	        
	    setControl(container);
	}
	
	private void setOption1() {
		setPageComplete(true);
	}
	
	private void setOption2() {
		setPageComplete(true);
	}

}
