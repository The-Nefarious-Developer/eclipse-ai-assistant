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
import com.developer.nefarious.zjoule.core.ui.ChatViewRender;
import com.developer.nefarious.zjoule.memory.EclipseMemory;

public class LogoutHandler extends Action {
	
	private static final String ICON = "platform:/plugin/org.eclipse.wst.wsdl.ui/org/eclipse/wst/wsdl/ui/internal/icons/output_obj.gif";
	
	private Browser browser;
	
	private LogoutHandler(final Browser browser) {
		this.browser = browser;
		
		setText("Logout");
		setToolTipText("End Session.");
		setIcon();
	}
	
	public static LogoutHandler create(final Browser browser) {
		return new LogoutHandler(browser);
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
		EclipseMemory eclipseMemory = new EclipseMemory();
		eclipseMemory.clearAll();
		
		ChatViewRender viewRender = new ChatViewRender();
		browser.setText(viewRender.build());
	}

}
