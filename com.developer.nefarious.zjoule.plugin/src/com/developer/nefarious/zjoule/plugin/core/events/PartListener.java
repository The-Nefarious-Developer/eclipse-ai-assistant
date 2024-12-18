package com.developer.nefarious.zjoule.plugin.core.events;

import org.eclipse.swt.browser.Browser;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;

import com.developer.nefarious.zjoule.plugin.core.functions.TagHandler;

public class PartListener implements IPartListener2 {

	public static PartListener create(final Browser browser) {
		return new PartListener(browser);
	}

	private Browser browser;

	private PartListener(final Browser browser) {
		this.browser = browser;
	}

	@Override
	public void partClosed(final IWorkbenchPartReference partRef) {
		if (partRef instanceof IEditorReference) {
			TagHandler.update(browser);
		}
	}

}
