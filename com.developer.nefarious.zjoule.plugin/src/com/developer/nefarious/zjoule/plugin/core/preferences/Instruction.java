package com.developer.nefarious.zjoule.plugin.core.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import com.developer.nefarious.zjoule.plugin.core.Activator;

/**
 * Provides system-level instructions for AI interactions.
 * The {@code Instruction} class contains the operation to retrieve instructions 
 * used to guide the AI's behavior and ensure responses adhere to best practices.
 * This is a utility class and cannot be instantiated.
 */
public abstract class Instruction {
	
	public static String KEY = "instructions";

    /**
     * Retrieves the preferred system message text.
     *
     * @return the system message text as a {@link String}.
     */
    public static String getMessage() {
    	IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        return store.getString(KEY);
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Instruction() { }

}
