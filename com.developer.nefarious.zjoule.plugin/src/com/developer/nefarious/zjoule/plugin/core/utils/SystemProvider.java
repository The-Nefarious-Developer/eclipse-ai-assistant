package com.developer.nefarious.zjoule.plugin.core.utils;

/**
 * Utility class for providing information about the current operating system.
 * <p>
 * This class is designed to be used as a utility and cannot be instantiated.
 * It provides a static method to retrieve the name of the operating system
 * as reported by the Java Virtual Machine (JVM).
 * </p>
 */
public abstract class SystemProvider {
	
	/**
     * Retrieves the name of the operating system on which the JVM is currently running.
     * <p>
     * This method wraps the {@code System.getProperty("os.name")} call, which 
     * provides the operating system name as a {@code String}.
     * </p>
     *
     * @return the name of the operating system, such as "Windows 10", "Linux", or "Mac OS X".
     *         The exact value is determined by the underlying JVM implementation.
     * @see System#getProperty(String)
     */
	public static String getCurrentSystem() {
		return System.getProperty("os.name");
	}
	
	/**
     * Private constructor to prevent instantiation of this utility class.
     */
	private SystemProvider() { }

}
