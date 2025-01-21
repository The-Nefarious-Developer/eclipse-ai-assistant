package com.developer.nefarious.zjoule.plugin.core.preferences;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public class InputField extends StringFieldEditor {
	
	private static final int WIDTH_IN_CHARS = 10;
	
	private static final int HEIGTH_IN_CHARS = 2;
	
	private static final int MINIMUM_WIDTH = 300;
	
	private static final int MINIMUM_HEIGHT = 50;
	
	public InputField(final String key, final String labelText, final Composite parent) {
		super(key, labelText, WIDTH_IN_CHARS, HEIGTH_IN_CHARS, VALIDATE_ON_FOCUS_LOST, parent);
		setFixedWidth(parent);
    }
    
    private void setFixedWidth(final Composite parent) {
        if (getTextControl(parent) != null) {
            GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
            gridData.widthHint = MINIMUM_WIDTH;
            gridData.heightHint = MINIMUM_HEIGHT;
            getTextControl(parent).setLayoutData(gridData);
        }
    }  
    
}
