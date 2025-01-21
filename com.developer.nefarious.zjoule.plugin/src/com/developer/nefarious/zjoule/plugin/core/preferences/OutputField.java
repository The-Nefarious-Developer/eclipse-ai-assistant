package com.developer.nefarious.zjoule.plugin.core.preferences;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class OutputField extends StringFieldEditor {
	
	private String currentValue = ""; // To hold the temporary value
	
	private static final int MINIMUM_WIDTH = 300;
	
	public OutputField(final String labelText, final String value, final Composite parent) {
        super("local", labelText, parent);
        setStringValue(value);
        setFixedWidth(parent);
    }

    @Override
    protected void doLoadDefault() {
        // Do nothing to prevent the value from being reset
    }
    
    @Override
    protected void createControl(final Composite parent) {
        super.createControl(parent);

        // Set the Text widget to read-only
        Text textField = getTextControl();
        if (textField != null) {
            textField.setEditable(false);
        }
    }
    
    @Override
    protected void doStore() {
        // Do nothing, as we don't want to store the value
    }

    @Override
    protected void doLoad() {
        // Do nothing, as we don't want to load from the preference store
    }

    @Override
    public void setStringValue(final String value) {
        super.setStringValue(value);
        this.currentValue = value; // Keep the value locally
    }

    @Override
    public String getStringValue() {
        return this.currentValue; // Return the locally stored value
    }
    
    private void setFixedWidth(final Composite parent) {
        // Retrieve the text control from the StringFieldEditor
        if (getTextControl(parent) != null) {
            GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
            gridData.widthHint = MINIMUM_WIDTH;
            getTextControl(parent).setLayoutData(gridData);
        }
    }

}
