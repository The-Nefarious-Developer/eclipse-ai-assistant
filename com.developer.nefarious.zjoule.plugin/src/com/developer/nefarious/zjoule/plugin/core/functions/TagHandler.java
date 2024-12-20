package com.developer.nefarious.zjoule.plugin.core.functions;

import org.eclipse.swt.browser.Browser;

import com.developer.nefarious.zjoule.plugin.chat.utils.EditorContentReader;

/**
 * Handles updating tags in the browser based on the currently active editor file.
 * <p>
 * This utility class retrieves the name of the currently active file in the Eclipse editor
 * and updates the tag in the browser using a JavaScript function.
 */
public abstract class TagHandler {

    /**
     * Updates the tag in the browser with the name of the currently active editor file.
     * <p>
     * This method retrieves the active editor's file name using {@link EditorContentReader#getActiveEditorFileName()}
     * and invokes the {@code updateTag()} JavaScript function in the browser to reflect this information.
     *
     * @param browser the {@link Browser} instance where the tag will be updated.
     */
    public static void update(final Browser browser) {
        String nameOfTheCurrentActiveFile = EditorContentReader.getActiveEditorFileName();
        browser.execute("updateTag('" + nameOfTheCurrentActiveFile + "');");
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private TagHandler() { }
}
