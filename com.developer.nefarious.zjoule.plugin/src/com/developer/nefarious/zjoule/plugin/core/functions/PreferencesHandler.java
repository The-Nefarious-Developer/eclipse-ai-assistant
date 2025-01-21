package com.developer.nefarious.zjoule.plugin.core.functions;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;

public class PreferencesHandler extends Action {
	
	private static final String ICON = "platform:/plugin/org.eclipse.egit.ui/icons/obj16/settings.png";
	
	private static final String PREFERENCES_PAGE_ID = "com.developer.nefarious.zjoule.plugin.core.preferences.PluginPreferencesPage";
    
    public static PreferencesHandler create() {
        return new PreferencesHandler();
    }
    
    private PreferencesHandler() {
        setText("Preferences");
        setToolTipText("User settings.");
        setIcon();
    }
    
    @Override
    public void run() {
        PreferenceDialog dialog = PreferencesUtil.createPreferenceDialogOn(null, PREFERENCES_PAGE_ID, null, null);
        if (dialog != null) {
            dialog.open();
        }
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
