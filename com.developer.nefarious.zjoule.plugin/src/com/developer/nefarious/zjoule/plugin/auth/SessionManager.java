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


	/**
     * Checks if the user is logged in by verifying both SAP and Ollama sessions.
     *
     * @return {@code true} if the user is logged into either SAP or Ollama, {@code false} otherwise.
     */
    public static boolean isUserLoggedIn() {
    	return (isSapSession() || isOllamaSession()) ? true : false;
    }
    
    /**
     * Checks if an SAP session is active by verifying required memory components.
     *
     * @return {@code true} if all required SAP session components are present, {@code false} otherwise.
     */
    public static boolean isSapSession() {
        MemoryAccessToken memoryAccessToken = MemoryAccessToken.getInstance();
        MemoryServiceKey memoryServiceKey = MemoryServiceKey.getInstance();
        MemoryResourceGroup memoryResourceGroup = MemoryResourceGroup.getInstance();
        MemoryDeployment memoryDeployment = MemoryDeployment.getInstance();

        return (memoryAccessToken.isEmpty() || memoryServiceKey.isEmpty() || memoryResourceGroup.isEmpty()
                || memoryDeployment.isEmpty()) ? false : true;
    }
    
    /**
     * Checks if an Ollama session is active by verifying required memory components.
     *
     * @return {@code true} if all required Ollama session components are present, {@code false} otherwise.
     */
    public static boolean isOllamaSession() {
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
    
    /**
     * Clears all active sessions, including both SAP and Ollama sessions.
     */
    public static void clearAllSessions() {
    	clearSapSession();
    	clearOllamaSession();
    }
    
    /**
     * Clears all SAP session-related memory components.
     * <p>
     * This method resets stored access tokens, service keys, resource groups,
     * and deployment information associated with the SAP session.
     * </p>
     */
    private static void clearSapSession() {
    	MemoryAccessToken.getInstance().clear();
    	MemoryServiceKey.getInstance().clear();
    	MemoryResourceGroup.getInstance().clear();
    	MemoryDeployment.getInstance().clear();
    }
    
    /**
     * Clears all Ollama session-related memory components.
     * <p>
     * This method resets stored Ollama endpoint and model information.
     * </p>
     */
    private static void clearOllamaSession() {
    	MemoryOllamaEndpoint.getInstance().clear();
    	MemoryOllamaModel.getInstance().clear();    	
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private SessionManager() { }

}
