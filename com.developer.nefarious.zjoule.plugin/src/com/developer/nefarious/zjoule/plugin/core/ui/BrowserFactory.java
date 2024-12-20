package com.developer.nefarious.zjoule.plugin.core.ui;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;

/**
 * Factory class for creating {@link Browser} instances in an SWT application.
 * <p>
 * This utility class provides a method to instantiate {@link Browser} components
 * with a specified parent composite and style.
 */
public abstract class BrowserFactory {

    /**
     * Creates a new {@link Browser} instance with the specified parent and style.
     *
     * @param parent the parent {@link Composite} that will contain the browser.
     * @param style the style flags for the browser.
     * @return a newly created {@link Browser} instance.
     */
    public static Browser create(final Composite parent, final int style) {
        return new Browser(parent, style);
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private BrowserFactory() { }
}
