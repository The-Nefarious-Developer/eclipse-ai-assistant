package com.developer.nefarious.zjoule.plugin.chat.utils;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.core.resources.IFile;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public abstract class EditorContentReader {
	
	private EditorContentReader() { }

	public static String readActiveEditorContent() {
		try {
			// Get the active workbench window
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			if (window == null) {
				return null;
			}

			// Get the active editor
			IEditorPart editor = window.getActivePage().getActiveEditor();
			if (editor == null || !(editor.getEditorInput() instanceof FileEditorInput)) {
				return null;
			}

			// Get the file from the editor input
			FileEditorInput input = (FileEditorInput) editor.getEditorInput();
			IFile file = input.getFile();

			// Open the file as a stream and read its content
			try (InputStream stream = file.getContents();
					Scanner scanner = new Scanner(stream, StandardCharsets.UTF_8.name())) {
				scanner.useDelimiter("\\A"); // Read the entire content
				return scanner.hasNext() ? scanner.next() : "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getActiveEditorFileName() {
		try {
			// Get the active workbench window
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			if (window == null) {
				return null;
			}

			// Get the active editor
			IEditorPart editor = window.getActivePage().getActiveEditor();
			if (editor == null || !(editor.getEditorInput() instanceof FileEditorInput)) {
				return null;
			}

			// Get the file from the editor input
			FileEditorInput input = (FileEditorInput) editor.getEditorInput();
			IFile file = input.getFile();

			// Return the file name
			return file.getParent().getName();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
