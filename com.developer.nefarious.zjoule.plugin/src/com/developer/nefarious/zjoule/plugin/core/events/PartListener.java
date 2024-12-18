package com.developer.nefarious.zjoule.plugin.core.events;

import org.eclipse.swt.browser.Browser;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;

import com.developer.nefarious.zjoule.plugin.core.functions.TagHandler;

/**
 * Implements a part listener to monitor and handle editor-related events in the Eclipse workspace.
 * <p>
 * This class listens for specific part lifecycle events, such as editor closures, and updates
 * the associated browser state using the {@link TagHandler}.
 */
public class PartListener implements IPartListener2 {

    /** The {@link Browser} instance to be updated during part events. */
    private Browser browser;

    /**
     * Factory method to create a {@code PartListener} instance.
     *
     * @param browser the {@link Browser} instance associated with the listener.
     * @return a new {@code PartListener} instance.
     */
    public static PartListener create(final Browser browser) {
        return new PartListener(browser);
    }

    /**
     * Constructs a new {@code PartListener} instance.
     *
     * @param browser the {@link Browser} instance to be updated during part events.
     */
    private PartListener(final Browser browser) {
        this.browser = browser;
    }

    /**
     * Handles the {@code partClosed} event triggered when a part is closed.
     * <p>
     * If the closed part is an {@link IEditorReference}, this method updates the browser
     * state by invoking {@link TagHandler#update(Browser)}.
     *
     * @param partRef the {@link IWorkbenchPartReference} representing the closed part.
     */
    @Override
    public void partClosed(final IWorkbenchPartReference partRef) {
        if (partRef instanceof IEditorReference) {
            TagHandler.update(browser);
        }
    }

}
