package com.developer.nefarious.zjoule.core.functions;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.browser.Browser;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import com.developer.nefarious.zjoule.auth.SessionManager;
import com.developer.nefarious.zjoule.chat.memory.MemoryMessageHistory;

public class ClearHandler extends Action {

	private static final String ICON = "platform:/plugin/org.eclipse.ui/icons/full/etool16/clear.png";

	private Browser browser;

	private ClearHandler(final Browser browser) {
		this.browser = browser;

		setText("Clear Chat");
		setToolTipText("Clear message history.");
		setIcon();
	}

	public static ClearHandler create(final Browser browser) {
		return new ClearHandler(browser);
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

	@Override
	public void run() {
		if (SessionManager.isUserLoggedIn()) {
			MemoryMessageHistory memoryMessageHistory = MemoryMessageHistory.getInstance();
			memoryMessageHistory.clear();
			browser.execute("clearMessages();");
		}
	}

}
