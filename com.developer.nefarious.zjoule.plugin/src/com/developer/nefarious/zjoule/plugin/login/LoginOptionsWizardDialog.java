package com.developer.nefarious.zjoule.plugin.login;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * A custom wizard dialog for handling login options.
 * <p>
 * The {@code LoginOptionsWizardDialog} extends {@link WizardDialog} to present
 * a wizard-based interface for user login options. It customizes the finish
 * button text to display "Select" instead of the default label.
 * </p>
 */
public class LoginOptionsWizardDialog extends WizardDialog {

    /**
     * Constructs a {@code LoginOptionsWizardDialog} with the specified parent shell and wizard.
     *
     * @param parentShell the parent {@link Shell} for the wizard dialog.
     * @param wizard      the {@link LoginWizard} instance associated with this dialog.
     */
    public LoginOptionsWizardDialog(final Shell parentShell, final LoginWizard wizard) {
        super(parentShell, wizard);
    }

    /**
     * Updates the dialog buttons, modifying the finish button text to "Select".
     * <p>
     * This override ensures that the finish button is labeled appropriately
     * for selecting login options instead of completing a traditional wizard flow.
     * </p>
     */
    @Override
    public void updateButtons() {
        super.updateButtons();
        getButton(IDialogConstants.FINISH_ID).setText("Select");
    }
}