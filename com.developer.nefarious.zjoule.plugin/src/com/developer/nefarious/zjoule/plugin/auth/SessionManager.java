package com.developer.nefarious.zjoule.plugin.auth;

import org.eclipse.swt.browser.Browser;

import com.developer.nefarious.zjoule.plugin.core.functions.TagHandler;
import com.developer.nefarious.zjoule.plugin.memory.EclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.plugin.memory.MemoryDeployment;
import com.developer.nefarious.zjoule.plugin.memory.MemoryOllamaEndpoint;
import com.developer.nefarious.zjoule.plugin.memory.MemoryOllamaModel;
import com.developer.nefarious.zjoule.plugin.memory.MemoryResourceGroup;
import com.developer.nefarious.zjoule.plugin.memory.MemoryServiceKey;

/**
 * Manages the user's session within the application.
 * The {@code SessionManager} class provides static methods for login, logout,
 * and session state verification. It interacts with various memory components
 * and the browser to manage user sessions.
 */
public abstract class SessionManager {


    public static boolean isUserLoggedIn() {
    	return (isSapSessionOn() || isOllamaSessionOn()) ? true : false;
    }
    
    private static boolean isSapSessionOn() {
        MemoryAccessToken memoryAccessToken = MemoryAccessToken.getInstance();
        MemoryServiceKey memoryServiceKey = MemoryServiceKey.getInstance();
        MemoryResourceGroup memoryResourceGroup = MemoryResourceGroup.getInstance();
        MemoryDeployment memoryDeployment = MemoryDeployment.getInstance();

        return (memoryAccessToken.isEmpty() || memoryServiceKey.isEmpty() || memoryResourceGroup.isEmpty()
                || memoryDeployment.isEmpty()) ? false : true;
    }
    
    private static boolean isOllamaSessionOn() {
        MemoryOllamaEndpoint memoryOllamaEndpoint = MemoryOllamaEndpoint.getInstance();
        MemoryOllamaModel memoryOllamaModel = MemoryOllamaModel.getInstance();

        return (memoryOllamaEndpoint.isEmpty() || memoryOllamaModel.isEmpty()) ? false : true;
    }

    /**
     * Executes the login process using the specified browser.
     * This method triggers the `login()` JavaScript function in the browser
     * and updates the associated tags using the {@link TagHandler}.
     *
     * @param browser the {@link Browser} instance to execute the login process.
     */
    public static void login(final Browser browser) {
        browser.execute("login();");
        TagHandler.update(browser);
    }

    /**
     * Executes the logout process using the specified browser and clears all memory.
     * The method triggers the `logout()` JavaScript function in the browser
     * and clears all user-related data from the {@link EclipseMemory}.
     *
     * @param browser the {@link Browser} instance to execute the logout process.
     *                If the browser is null or disposed, no JavaScript execution occurs.
     * @param eclipseMemory the {@link EclipseMemory} instance used to clear all memory.
     */
    public static void logout(final Browser browser, final EclipseMemory eclipseMemory) {
        eclipseMemory.clearAll();
        if (browser != null && !browser.isDisposed()) {
            browser.execute("logout();");
        }
    }
    
    public static void clearAllSessions() {
    	clearSapSession();
    	clearOllamaSession();
    }
    
    private static void clearSapSession() {
    	MemoryAccessToken.getInstance().clear();
    	MemoryServiceKey.getInstance().clear();
    	MemoryResourceGroup.getInstance().clear();
    	MemoryDeployment.getInstance().clear();
    }
    
    private static void clearOllamaSession() {
    	MemoryOllamaEndpoint.getInstance().clear();
    	MemoryOllamaModel.getInstance().clear();    	
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private SessionManager() { }

}
