package com.developer.nefarious.zjoule.plugin.core.functions;

import org.eclipse.swt.browser.Browser;
import com.developer.nefarious.zjoule.plugin.chat.utils.EditorContentReader;

public abstract class TagHandler {
	
	private TagHandler() { }
	
	public static void update(final Browser browser) {
		String nameOfTheCurrentActiveFile = EditorContentReader.getActiveEditorFileName();
		browser.execute("updateTag('" + nameOfTheCurrentActiveFile + "');");
	}

}
