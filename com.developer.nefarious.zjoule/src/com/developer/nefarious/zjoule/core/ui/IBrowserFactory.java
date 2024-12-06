package com.developer.nefarious.zjoule.core.ui;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;

public interface IBrowserFactory {
	
	Browser createBrowser(final Composite parent, final int style);

}
