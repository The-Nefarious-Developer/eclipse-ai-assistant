package com.developer.nefarious.zjoule.core.functions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import com.developer.nefarious.zjoule.login.LoginWizard;

public class LoginHandler extends Action {
	
	private Shell shell;
	
	private LoginHandler(final Shell shell) {
		this.shell = shell;
		setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
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