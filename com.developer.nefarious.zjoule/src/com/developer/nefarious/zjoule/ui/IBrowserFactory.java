package com.developer.nefarious.zjoule.ui;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;

public interface IBrowserFactory {
	
	Browser createBrowser(Composite parent, int style);

}
