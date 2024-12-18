package com.developer.nefarious.zjoule.plugin.core.events;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.browser.Browser;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

import com.developer.nefarious.zjoule.plugin.core.functions.TagHandler;

public class SelectionListener implements ISelectionListener {

	public static ISelectionListener create(final Browser browser) {
		return new SelectionListener(browser);
	}

	private Browser browser;

	private SelectionListener(final Browser browser) {
		this.browser = browser;
	}

	@Override
	public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
		TagHandler.update(browser);

	}

}
