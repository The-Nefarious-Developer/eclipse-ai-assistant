package com.developer.nefarious.zjoule.core.events;

import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;

public class PartListener implements IPartListener2 {
	
	private static PartListener instance;
	
	private PartListener() { }
	
	public static PartListener getInstance() {
		if (instance == null) {
			instance = new PartListener();
		}
		return instance;
	}
	
	@Override
    public void partClosed(final IWorkbenchPartReference partRef) {
        if (partRef instanceof IEditorReference) {
            // Call the method when an editor is closed
            System.out.println("-----CLOSED!!!------");
        }
    }

}
