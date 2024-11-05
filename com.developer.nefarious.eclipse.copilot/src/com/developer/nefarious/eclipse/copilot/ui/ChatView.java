package com.developer.nefarious.eclipse.copilot.ui;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;

import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

public class ChatView extends ViewPart implements ISelectionListener {
	
	private Browser browser;

	@Override
	public void selectionChanged(IWorkbenchPart arg0, ISelection arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createPartControl(Composite parent) {
		browser = browser != null ? browser : new Browser(parent, SWT.WEBKIT);
		browser.setText("test");
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
		
	}

}
