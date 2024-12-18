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

public class LoginHandler extends Action {

	private static final String ICON = "platform:/plugin/org.eclipse.jdt.debug.ui/icons/full/elcl16/deadlock_view.png";

	public static LoginHandler create(final Shell shell, final Browser browser) {
		return new LoginHandler(shell, browser);
	}

	private Shell shell;

	private Browser browser;

	private LoginHandler(final Shell shell, final Browser browser) {
		this.shell = shell;
		this.browser = browser;

		setToolTipText("Click to log in to your BTP subaccount.");
		setIcon();
	}

	@Override
	public void run() {
		LoginWizard wizard = new LoginWizard(browser);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();
	}

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