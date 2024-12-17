package com.developer.nefarious.zjoule.plugin.core.ui;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.part.ViewPart;
import com.developer.nefarious.zjoule.plugin.core.events.Initialization;
import com.developer.nefarious.zjoule.plugin.core.events.PartListener;
import com.developer.nefarious.zjoule.plugin.core.events.SelectionListener;
import com.developer.nefarious.zjoule.plugin.core.functions.ClearHandler;
import com.developer.nefarious.zjoule.plugin.core.functions.LoginHandler;
import com.developer.nefarious.zjoule.plugin.core.functions.LogoutHandler;
import com.developer.nefarious.zjoule.plugin.core.functions.PromptHandler;
import jakarta.inject.Inject;

public class ViewListener extends ViewPart {

	@Inject
	private Shell shell;

	private Browser browser;

	private ISelectionListener selectionListener;

	private PartListener partListener;

	@Override
	public void createPartControl(final Composite parent) {
		browser = BrowserFactory.create(parent, SWT.WEBKIT);
		selectionListener = SelectionListener.create(browser);
		IViewRender viewRender = ViewRender.create();
		partListener = PartListener.create(browser);

		browser.setText(viewRender.build());

		BrowserFunction promptHandler = PromptHandler.create(browser, "getAIResponse");
		browser.addDisposeListener(e -> {
			if (!browser.isDisposed()) {
				promptHandler.dispose();
			}
		});

		getSite().getPage().addPartListener(partListener);
		getSite().getPage().addSelectionListener(selectionListener);
		Display.getDefault().asyncExec(new Initialization(browser));

		setUpToolbar();
	}

	@Override
	public void setFocus() {
		browser.setFocus();
	}

	@Override
	public void dispose() {
		if (selectionListener != null) {
			getSite().getPage().removeSelectionListener(selectionListener);
		}

		if (browser != null && !browser.isDisposed()) {
			browser.dispose();
		}

		if (partListener != null) {
			getSite().getPage().removePartListener(partListener);
		}

		super.dispose();
	}

	public void setShell(final Shell shell) {
		this.shell = shell;
	}

	public void setBrowser(final Browser browser) {
		this.browser = browser;
	}
	
	public void setSelectionListener(final ISelectionListener selectionListener) {
		this.selectionListener = selectionListener;
	}
	
	public void setPartListener(final PartListener partListener) {
		this.partListener = partListener;
	}

	private IToolBarManager getToolbar() {
		return getViewSite().getActionBars().getToolBarManager();
	}

	private IMenuManager getMenu() {
		return getViewSite().getActionBars().getMenuManager();
	}

	private void setUpToolbar() {
		IToolBarManager toolbar = getToolbar();
		toolbar.add(LoginHandler.create(shell, browser));
		IMenuManager menu = getMenu();
		menu.add(ClearHandler.create(browser));
		menu.add(new Separator());
		menu.add(LogoutHandler.create(browser));
	}

}
