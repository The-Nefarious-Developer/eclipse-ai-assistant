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

/**
 * Handles opening the preferences dialog in the Eclipse plugin.
 * <p>
 * The {@code PreferencesHandler} extends {@link Action} to provide a UI action
 * that allows users to access the plugin's settings via the Eclipse preferences page.
 * It sets an icon for the action and executes the preferences dialog when triggered.
 * </p>
 */
public class PreferencesHandler extends Action {

    /** The icon path for the preferences action. */
    private static final String ICON = "platform:/plugin/org.eclipse.egit.ui/icons/obj16/settings.png";

    /** The preference page ID for the plugin's settings. */
    private static final String PREFERENCES_PAGE_ID = "com.developer.nefarious.zjoule.plugin.core.preferences.PluginPreferencesPage";

    /**
     * Creates and returns an instance of {@code PreferencesHandler}.
     *
     * @return a new instance of {@code PreferencesHandler}.
     */
    public static PreferencesHandler create() {
        return new PreferencesHandler();
    }

    /**
     * Private constructor to prevent direct instantiation.
     * <p>
     * This constructor initializes the action's text, tooltip, and icon.
     * </p>
     */
    private PreferencesHandler() {
        setText("Preferences");
        setToolTipText("User settings.");
        setIcon();
    }

    /**
     * Opens the preferences dialog for the plugin when the action is triggered.
     * <p>
     * This method invokes the Eclipse Preferences API to display the preference
     * dialog corresponding to {@link #PREFERENCES_PAGE_ID}.
     * </p>
     */
    @Override
    public void run() {
        PreferenceDialog dialog = PreferencesUtil.createPreferenceDialogOn(null, PREFERENCES_PAGE_ID, null, null);
        if (dialog != null) {
            dialog.open();
        }
    }

    /**
     * Sets the icon for the preferences action.
     * <p>
     * The method attempts to load the icon from the specified URL. If the URL
     * is invalid or cannot be loaded, it falls back to a default Eclipse info icon.
     * </p>
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