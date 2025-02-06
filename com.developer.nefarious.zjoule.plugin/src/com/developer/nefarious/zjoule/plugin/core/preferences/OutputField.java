package com.developer.nefarious.zjoule.plugin.core.preferences;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * Represents a read-only output field for displaying values in an Eclipse preference page.
 * <p>
 * The {@code OutputField} extends {@link StringFieldEditor} but overrides its behavior
 * to prevent storing, loading, or modifying values through the preference store. The field
 * is displayed as a read-only text field with a fixed width.
 * </p>
 */
public class OutputField extends StringFieldEditor {
    
    /** Holds the temporary value for the field since it is not stored in preferences. */
    private String currentValue = "";

    /** Minimum width of the output field in pixels. */
    private static final int MINIMUM_WIDTH = 300;

    /**
     * Constructs an {@code OutputField} with the specified label and initial value.
     * <p>
     * The field is initialized with the given value and displayed in the specified parent composite.
     * </p>
     *
     * @param labelText the label text displayed next to the field.
     * @param value     the initial value to display in the field.
     * @param parent    the parent {@link Composite} in which this field is created.
     */
    public OutputField(final String labelText, final String value, final Composite parent) {
        super("local", labelText, parent);
        setStringValue(value);
        setFixedWidth(parent);
    }

    /**
     * Overrides the default load behavior to prevent resetting the value.
     * <p>
     * This method is intentionally left empty to ensure that the field
     * does not load any default values from the preference store.
     * </p>
     */
    @Override
    protected void doLoadDefault() {
        // Do nothing to prevent the value from being reset
    }

    /**
     * Creates the UI control for the output field and sets it to read-only.
     * <p>
     * This method ensures that the text field cannot be edited by the user.
     * </p>
     *
     * @param parent the parent {@link Composite} in which this field is created.
     */
    @Override
    protected void createControl(final Composite parent) {
        super.createControl(parent);

        // Set the Text widget to read-only
        Text textField = getTextControl();
        if (textField != null) {
            textField.setEditable(false);
        }
    }

    /**
     * Overrides the store behavior to prevent saving the value.
     * <p>
     * This method is intentionally left empty to ensure that the field
     * does not store any values in the preference store.
     * </p>
     */
    @Override
    protected void doStore() {
        // Do nothing, as we don't want to store the value
    }

    /**
     * Overrides the load behavior to prevent loading a value from preferences.
     * <p>
     * This method is intentionally left empty to ensure that the field
     * does not retrieve any stored values from the preference store.
     * </p>
     */
    @Override
    protected void doLoad() {
        // Do nothing, as we don't want to load from the preference store
    }

    /**
     * Sets the displayed value of the output field.
     * <p>
     * This value is stored locally and not persisted to the preference store.
     * </p>
     *
     * @param value the new value to display in the field.
     */
    @Override
    public void setStringValue(final String value) {
        super.setStringValue(value);
        this.currentValue = value; // Keep the value locally
    }

    /**
     * Retrieves the current value displayed in the output field.
     * <p>
     * This method returns the locally stored value instead of fetching from preferences.
     * </p>
     *
     * @return the current value of the field as a {@link String}.
     */
    @Override
    public String getStringValue() {
        return this.currentValue; // Return the locally stored value
    }

    /**
     * Sets a fixed width for the output field.
     * <p>
     * This method ensures the field has a minimum width by applying a {@link GridData} layout.
     * </p>
     *
     * @param parent the parent {@link Composite} containing the output field.
     */
    private void setFixedWidth(final Composite parent) {
        // Retrieve the text control from the StringFieldEditor
        if (getTextControl(parent) != null) {
            GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
            gridData.widthHint = MINIMUM_WIDTH;
            getTextControl(parent).setLayoutData(gridData);
        }
    }
}