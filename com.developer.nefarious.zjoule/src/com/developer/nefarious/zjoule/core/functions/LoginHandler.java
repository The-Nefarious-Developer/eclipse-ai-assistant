package com.developer.nefarious.zjoule.core.functions;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import com.developer.nefarious.zjoule.login.LoginWizard;

public class LoginHandler extends Action {
	
	private static final String ICON = "platform:/plugin/org.eclipse.xsd.edit/icons/full/obj16/XSDIdentityConstraintDefinitionKey.gif";
	
	private Shell shell;
	
	private LoginHandler(final Shell shell) {
		this.shell = shell;
		setToolTipText("Click to log in to your BTP subaccount.");
		setIcon();
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

	public static LoginHandler create(final Shell shell) {
		return new LoginHandler(shell);
	}

	@Override
	public void run() {
		LoginWizard wizard = new LoginWizard();
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();
	}

}