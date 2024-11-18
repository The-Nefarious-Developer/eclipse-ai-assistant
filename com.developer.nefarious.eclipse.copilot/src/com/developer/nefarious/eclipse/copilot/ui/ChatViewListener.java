package com.developer.nefarious.eclipse.copilot.ui;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISelectionListener;

import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.developer.nefarious.eclipse.copilot.functions.GetAIResponse;
import com.developer.nefarious.eclipse.copilot.functions.LoginHandler;

import jakarta.inject.Inject;

public class ChatViewListener extends ViewPart implements ISelectionListener {
	
	@Inject
	Shell shell;

	private Browser browser;
	private IBrowserFactory browserFactory;
	
	private IViewRender viewRender;

	public ChatViewListener() {
		browserFactory = new BrowserFactory();
		viewRender = new ChatViewRender();
	}
	
	public ChatViewListener( // Used for tests
			IBrowserFactory browserFactory, 
			IViewRender viewRender) {
		this.browserFactory = browserFactory;
		this.viewRender = viewRender;
	}
	
	/**
	 * Set a mocked browser instance for unit tests execution.
	 *
	 * @param A mocked browser instance
	 */
	public void setBrowser(Browser browser) { 
		this.browser = browser;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
	}
	
	public IToolBarManager getToolbar() {
		return getViewSite().getActionBars().getToolBarManager();
	}

	@Override
	public void createPartControl(Composite parent) {
		browser = browserFactory.createBrowser(parent, SWT.WEBKIT);
		browser.setText(viewRender.build());
		BrowserFunction getAIResponseFunction = GetAIResponse.create(browser, "getAIResponse");
		browser.addDisposeListener(e -> getAIResponseFunction.dispose());
		getSite().getPage().addSelectionListener(this);
		setUpToolbar();
	}

	private void setUpToolbar() {
		IToolBarManager toolbar = getToolbar();
		toolbar.add(new LoginHandler(browser, shell));
	}

	@Override
	public void setFocus() {
		browser.setFocus();
	}
	
	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose(); // TODO: Add code coverage for super method call
	}

}
