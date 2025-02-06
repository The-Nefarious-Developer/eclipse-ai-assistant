package com.developer.nefarious.zjoule.plugin.core.preferences;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

/**
 * Represents a customizable input field for user preferences.
 * <p>
 * The {@code InputField} extends {@link StringFieldEditor} and provides an
 * input text field with a fixed width and height. It is designed for use in
 * Eclipse preference pages to capture user input with validation on focus loss.
 * </p>
 */
public class InputField extends StringFieldEditor {
    
    /** Default width of the input field in characters. */
    private static final int WIDTH_IN_CHARS = 10;

    /** Default height of the input field in characters. */
    private static final int HEIGTH_IN_CHARS = 2;

    /** Minimum width of the input field in pixels. */
    private static final int MINIMUM_WIDTH = 300;

    /** Minimum height of the input field in pixels. */
    private static final int MINIMUM_HEIGHT = 50;

    /**
     * Constructs an {@code InputField} with the specified key, label, and parent composite.
     * <p>
     * This constructor initializes the text field with predefined dimensions and attaches
     * it to the specified parent composite.
     * </p>
     *
     * @param key       the preference key associated with this input field.
     * @param labelText the label text displayed next to the input field.
     * @param parent    the parent {@link Composite} in which this field is created.
     */
    public InputField(final String key, final String labelText, final Composite parent) {
        super(key, labelText, WIDTH_IN_CHARS, HEIGTH_IN_CHARS, VALIDATE_ON_FOCUS_LOST, parent);
        setFixedWidth(parent);
    }

    /**
     * Sets a fixed width and height for the input field.
     * <p>
     * This method ensures the input field has a minimum width and height
     * by applying a {@link GridData} layout to its text control.
     * </p>
     *
     * @param parent the parent {@link Composite} containing the input field.
     */
    private void setFixedWidth(final Composite parent) {
        if (getTextControl(parent) != null) {
            GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
            gridData.widthHint = MINIMUM_WIDTH;
            gridData.heightHint = MINIMUM_HEIGHT;
            getTextControl(parent).setLayoutData(gridData);
        }
    }  
}