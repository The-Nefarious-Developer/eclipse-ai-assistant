package com.developer.nefarious.zjoule.plugin.core.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.developer.nefarious.zjoule.plugin.core.Activator;

public class PluginPreferencesPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	public PluginPreferencesPage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
    }

	@Override
	public void init(final IWorkbench workbench) { }

	@Override
	protected void createFieldEditors() {
		
        Group group = new Group(getFieldEditorParent(), SWT.NONE);
        group.setText("Chat Settings");
        group.setLayout(new GridLayout(1, false));
        group.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

        Composite groupContent = new Composite(group, SWT.NONE);
        groupContent.setLayout(new GridLayout(1, false));
        groupContent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        addField(new StringFieldEditor(Instruction.KEY, "Instructions:", groupContent));
		
	}

}
