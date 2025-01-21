package com.developer.nefarious.zjoule.plugin.login.pages;

import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.developer.nefarious.zjoule.plugin.login.api.GetResourceGroupsResponse;
import com.developer.nefarious.zjoule.plugin.login.api.ISapLoginClient;
import com.developer.nefarious.zjoule.plugin.login.events.ServiceKeyModifyListener;
import com.developer.nefarious.zjoule.plugin.login.utils.ResourceGroupIdExtractor;
import com.developer.nefarious.zjoule.plugin.models.ServiceKey;
import com.google.gson.Gson;

/**
 * Represents the first page of the login wizard where the user provides SAP AI Core credentials.
 * <p>
 * This page includes:
 * <ul>
 *   <li>A text field for entering the content of the service key JSON.</li>
 *   <li>An error message widget for validation feedback.</li>
 *   <li>Logic to propagate resource group information to subsequent wizard pages.</li>
 * </ul>
 */
public class FirstSapLoginWizardPage extends WizardPage {

    /** The unique identifier for this wizard page. */
    public static final String PAGE_ID = "First Page";

    /** The height of the input text field. */
    private static final int INPUT_HEIGTH = 100;

    /** The width of the input text field. */
    private static final int INPUT_WIDTH = 300;

    /** The text field for user input. */
    private Text textField;

    /** The text widget for displaying validation error messages. */
    private Text errorText;

    /** The {@link ServiceKey} parsed from the user input. */
    private ServiceKey serviceKey;

    /** The login client for handling API interactions. */
    private ISapLoginClient sapLoginClient;

    /**
     * Constructs a new {@code FirstLoginWizardPage}.
     *
     * @param sapLoginClient the {@link ISapLoginClient} used for API interactions during the login process.
     */
    public FirstSapLoginWizardPage(final ISapLoginClient sapLoginClient) {
        super(PAGE_ID);
        setTitle("Provide credentials");
        setDescription("Attach the Service Key json file content for the SAP AI Core service.");
        setPageComplete(false); // Initially set the page as incomplete
        this.sapLoginClient = sapLoginClient;
    }

    /**
     * Creates the UI controls for this wizard page.
     *
     * @param parent the parent {@link Composite} for the wizard page controls.
     */
    @Override
    public void createControl(final Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(2, false));

        // Text field for user input
        textField = new Text(container, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        gridData.heightHint = INPUT_HEIGTH;
        gridData.widthHint = INPUT_WIDTH;
        textField.setLayoutData(gridData);

        // Add a ModifyListener to monitor textField changes
        textField.addModifyListener(new ServiceKeyModifyListener(this, sapLoginClient, new Gson()));

        // Hidden error text widget
        errorText = new Text(container, SWT.READ_ONLY | SWT.MULTI | SWT.WRAP);
        GridData errorTextGridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
        errorText.setLayoutData(errorTextGridData);
        errorText.setForeground(container.getDisplay().getSystemColor(SWT.COLOR_RED));
        errorText.setVisible(false); // Initially hidden

        setControl(container);
    }

    /**
     * Retrieves the text entered by the user in the input field.
     *
     * @return the user input as a {@link String}.
     */
    public String getInputText() {
        return textField.getText();
    }

    /**
     * Retrieves the {@link ServiceKey} parsed from the user input.
     *
     * @return the {@link ServiceKey} object.
     */
    public ServiceKey getServiceKey() {
        return serviceKey;
    }

    /**
     * Propagates resource groups from the service key response to the second wizard page.
     *
     * @param getResourceGroupsResponse the response containing available resource groups.
     */
    public void setResourceGroupsOnTheSecondPage(final GetResourceGroupsResponse getResourceGroupsResponse) {
        SecondSapLoginWizardPage secondPage = (SecondSapLoginWizardPage) getWizard().getPage(SecondSapLoginWizardPage.PAGE_ID);
        List<String> resourceGroupsAvailableForSelection = ResourceGroupIdExtractor.extractResourceGroupIds(getResourceGroupsResponse);
        secondPage.setResourceGroupsForSelection(resourceGroupsAvailableForSelection);
    }

    /**
     * Sets the {@link ServiceKey} for this page.
     *
     * @param serviceKey the {@link ServiceKey} object to set.
     */
    public void setServiceKey(final ServiceKey serviceKey) {
        this.serviceKey = serviceKey;
    }

    /**
     * Displays a validation error message in the UI or hides it if the message is {@code null} or empty.
     * <p>
     * If an error message is displayed, the page is marked as incomplete. Otherwise, it is marked as complete.
     *
     * @param message the error message to display, or {@code null} to hide the error widget.
     */
    public void setValidationError(final String message) {
        if (message != null && !message.isEmpty()) {
            errorText.setText(message);
            errorText.setVisible(true);
            setPageComplete(false);
        } else {
            errorText.setVisible(false);
            setPageComplete(true);
        }
        getShell().layout(true, true); // Update the layout to reflect visibility changes
    }
}
