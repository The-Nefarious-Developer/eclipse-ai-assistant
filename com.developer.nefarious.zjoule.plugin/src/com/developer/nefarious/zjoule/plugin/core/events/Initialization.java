package com.developer.nefarious.zjoule.plugin.core.events;

import org.eclipse.swt.browser.Browser;

import com.developer.nefarious.zjoule.plugin.auth.SessionManager;
import com.developer.nefarious.zjoule.plugin.core.functions.MessageHistoryLoader;
import com.developer.nefarious.zjoule.plugin.core.functions.TagHandler;
import com.developer.nefarious.zjoule.plugin.memory.EclipseMemory;

/**
 * Handles the initialization process for the application by managing user sessions,
 * loading message history, and updating the browser state.
 * <p>
 * This class implements {@link Runnable} and can be used in thread-based operations
 * for initializing components in the Eclipse environment.
 */
public class Initialization implements Runnable {

    /** The {@link Browser} instance to interact with during initialization. */
    private Browser browser;

    /**
     * Constructs an {@code Initialization} instance with the specified {@link Browser}.
     *
     * @param browser the {@link Browser} instance used during initialization.
     */
    public Initialization(final Browser browser) {
        this.browser = browser;
    }

    /**
     * Executes the initialization process.
     * <p>
     * The method performs the following steps:
     * <ol>
     *   <li>Checks if the user is logged in using {@link SessionManager#isUserLoggedIn()}.</li>
     *   <li>If logged in, logs the user in via {@link SessionManager#login(Browser)}
     *       and updates tags using {@link TagHandler#update(Browser)}.</li>
     *   <li>If not logged in, logs the user out using {@link SessionManager#logout(Browser, EclipseMemory)}.</li>
     *   <li>Loads the chat message history from memory using {@link MessageHistoryLoader#loadFromMemory(Browser)}.</li>
     *   <li>Executes a JavaScript function, {@code updatePlaceholder()}, in the browser to update the UI.</li>
     * </ol>
     */
    @Override
    public void run() {
        if (SessionManager.isUserLoggedIn()) {
            SessionManager.login(browser);
            TagHandler.update(browser);
        } else {
            SessionManager.logout(browser, new EclipseMemory());
        }
        MessageHistoryLoader.loadFromMemory(browser);
        browser.execute("updatePlaceholder();");
    }
}
