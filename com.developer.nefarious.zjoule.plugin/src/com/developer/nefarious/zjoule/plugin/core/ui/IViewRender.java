package com.developer.nefarious.zjoule.plugin.core.ui;

/**
 * Interface for rendering HTML-based views in the application.
 * <p>
 * This interface defines methods for dynamically building HTML content and
 * reading resource files (e.g., JavaScript and CSS) from the plugin's bundle.
 * Implementations of this interface are responsible for creating and managing
 * the HTML views used in the application.
 */
public interface IViewRender {

	/**
     * Builds the HTML content for the view.
     * <p>
     * This method dynamically includes JavaScript and CSS resources by reading their contents
     * from the plugin's resources. The generated HTML contains a placeholder for chat interactions
     * and user instructions.
     *
     * @return the complete HTML content as a {@link String}.
     */
	String build();

	/**
     * Reads the content of a resource file located in the plugin's bundle.
     * <p>
     * This method resolves the file path using the plugin's bundle, reads the file content line by line,
     * and returns the content as a single string.
     *
     * @param filename the name of the file to read from the {@code resources/views} directory.
     * @return the content of the file as a {@link String}, or an empty string if an error occurs.
     */
	String getResourceContent(final String filename);

}
