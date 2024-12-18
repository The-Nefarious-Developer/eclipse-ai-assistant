package com.developer.nefarious.zjoule.plugin.core.events;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.browser.Browser;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

import com.developer.nefarious.zjoule.plugin.core.functions.TagHandler;

/**
 * Listens for selection changes in the Eclipse workspace and updates the browser state accordingly.
 * <p>
 * Implements {@link ISelectionListener} to monitor selection events and invoke updates
 * using the {@link TagHandler}.
 */
public class SelectionListener implements ISelectionListener {

    /** The {@link Browser} instance to be updated during selection events. */
    private Browser browser;

    /**
     * Factory method to create a {@code SelectionListener} instance.
     *
     * @param browser the {@link Browser} instance associated with the listener.
     * @return a new {@code SelectionListener} instance.
     */
    public static ISelectionListener create(final Browser browser) {
        return new SelectionListener(browser);
    }

    /**
     * Constructs a new {@code SelectionListener} instance.
     *
     * @param browser the {@link Browser} instance to be updated during selection events.
     */
    private SelectionListener(final Browser browser) {
        this.browser = browser;
    }

    /**
     * Invoked when the selection in the Eclipse workspace changes.
     * <p>
     * This method updates the browser state using {@link TagHandler#update(Browser)}.
     *
     * @param part the {@link IWorkbenchPart} in which the selection changed.
     * @param selection the new {@link ISelection}.
     */
    @Override
    public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
        TagHandler.update(browser);
    }
}
