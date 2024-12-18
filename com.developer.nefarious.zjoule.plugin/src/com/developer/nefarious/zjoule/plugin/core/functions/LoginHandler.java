package com.developer.nefarious.zjoule.plugin.core.functions;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.developer.nefarious.zjoule.plugin.login.LoginWizard;

/**
 * Handles the "Login" action for connecting to a BTP subaccount.
 * <p>
 * This class extends {@link Action} and launches a {@link LoginWizard} in a 
 * {@link WizardDialog} when the action is triggered. It also manages the icon for the action.
 */
public class LoginHandler extends Action {

    /** Path to the icon used for the "Login" action. */
    private static final String ICON = "platform:/plugin/org.eclipse.jdt.debug.ui/icons/full/elcl16/deadlock_view.png";

    /** The {@link Shell} instance used to display the login wizard dialog. */
    private Shell shell;

    /** The {@link Browser} instance used by the login wizard. */
    private Browser browser;

    /**
     * Factory method to create a new {@code LoginHandler} instance.
     *
     * @param shell the {@link Shell} instance used to display the login wizard dialog.
     * @param browser the {@link Browser} instance used by the login wizard.
     * @return a new {@code LoginHandler} instance.
     */
    public static LoginHandler create(final Shell shell, final Browser browser) {
        return new LoginHandler(shell, browser);
    }

    /**
     * Constructs a new {@code LoginHandler} instance.
     *
     * @param shell the {@link Shell} instance used to display the login wizard dialog.
     * @param browser the {@link Browser} instance used by the login wizard.
     */
    private LoginHandler(final Shell shell, final Browser browser) {
        this.shell = shell;
        this.browser = browser;

        setToolTipText("Click to log in to your BTP subaccount.");
        setIcon();
    }

    /**
     * Executes the "Login" action.
     * <p>
     * This method launches the {@link LoginWizard} in a {@link WizardDialog},
     * allowing the user to log in to their BTP subaccount.
     */
    @Override
    public void run() {
        LoginWizard wizard = new LoginWizard(browser);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();
    }

    /**
     * Sets the icon for the action.
     * <p>
     * Attempts to load the icon from the specified path. If the icon cannot be loaded,
     * a default icon from Eclipse's shared images is used.
     */
    private void setIcon() {
        try {
            URI iconURI = new URI(ICON);
            URL iconURL = iconURI.toURL();
            setImageDescriptor(ImageDescriptor.createFromURL(iconURL));
        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
            setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
        }
    }
}
