package com.developer.nefarious.zjoule.plugin.login.pages;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class FirstOllamaLoginPage extends WizardPage {

	public static final String PAGE_ID = "Ollama Login First Page";

	private Text endpointText;
	
	private Text errorText;

	public FirstOllamaLoginPage() {
		super(PAGE_ID);

		setTitle("Ollama Setup");
		setDescription("Enter the host and port for the local Ollama instance.");
		setPageComplete(false); // Initially set the page as incomplete
	}

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
	
	@Override
    public boolean canFlipToNextPage() {
        return true;
    }

	@Override
	public IWizardPage getNextPage() {
		
		String input = endpointText.getText();
		
		if (input == null || input.isEmpty() || input.isBlank()) {
			displayErrorMessage("Please, enter a local Ollama endpoint to proceed.");
			return null;
		}
		
		errorText.setVisible(false);
		return super.getNextPage(); // Proceed to the next page
	}
	
	private void displayErrorMessage(final String message) {
		errorText.setText(message);
        errorText.setVisible(true);
	}

}
