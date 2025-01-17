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

public class FirstOllamaLoginWizardPage extends WizardPage {

	public static final String PAGE_ID = "Ollama Login First Page";

	private IOllamaLoginClient ollamaLoginClient;
	
	private IMemoryObject<String> memoryOllamaEndpoint;

	private Text endpointText;

	private Text errorText;

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

		String ollamaEndpoint = endpointText.getText();

		if (ollamaEndpoint == null || ollamaEndpoint.isEmpty() || ollamaEndpoint.isBlank()) {
			displayErrorMessage("Please, enter a local Ollama endpoint to proceed.");
			return null;
		}

		try {
			setAvailableModelsFor(ollamaEndpoint);
		} catch (IllegalArgumentException | IOException | InterruptedException e) {
			displayErrorMessage("Local instance of Ollama invalid or doesn't exist in the informed address.");
			return null;
		}
		
		errorText.setVisible(false);
		memoryOllamaEndpoint.save(ollamaEndpoint);
		return super.getNextPage(); // Proceed to the next wizard page
	}

	private void setAvailableModelsFor(final String ollamaEndpoint) throws IOException, InterruptedException {
		GetOllamaModelsResponse getOllamaModelsResponse = ollamaLoginClient.getModels(ollamaEndpoint);
		SecondOllamaLoginWizardPage secondPage = (SecondOllamaLoginWizardPage) getWizard().getPage(SecondOllamaLoginWizardPage.PAGE_ID);
		secondPage.setOllamaModelsForSelection(getOllamaModelsResponse);
	}

	private void displayErrorMessage(final String message) {
		errorText.setText(message);
		errorText.setVisible(true);
	}

}
