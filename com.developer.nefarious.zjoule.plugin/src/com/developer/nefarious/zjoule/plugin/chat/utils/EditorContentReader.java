package com.developer.nefarious.zjoule.plugin.chat.utils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Utility class for reading content and metadata from the active editor in the Eclipse IDE.
 * This class provides methods to retrieve the active editor's file name and its content.
 */
public abstract class EditorContentReader {

    /**
     * Retrieves the name of the file currently open in the active editor.
     *
     * @return the name of the active editor's file, or {@code null} if no editor is active
     *         or the file cannot be determined.
     */
    public static String getActiveEditorFileName() {
        try {
            IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            if (window == null) {
                return null;
            }

            IEditorPart editor = window.getActivePage().getActiveEditor();
            if (editor == null || !(editor.getEditorInput() instanceof FileEditorInput)) {
                return null;
            }

            FileEditorInput input = (FileEditorInput) editor.getEditorInput();
            IFile file = input.getFile();

            return file.getParent().getName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reads and returns the content of the file currently open in the active editor.
     *
     * @return the content of the active editor's file as a {@link String}, or {@code null}
     *         if no editor is active or the file cannot be read.
     */
    public static String readActiveEditorContent() {
        try {
            IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
            if (window == null) {
                return null;
            }

            IEditorPart editor = window.getActivePage().getActiveEditor();
            if (editor == null || !(editor.getEditorInput() instanceof FileEditorInput)) {
                return null;
            }

            FileEditorInput input = (FileEditorInput) editor.getEditorInput();
            IFile file = input.getFile();

            try (InputStream stream = file.getContents();
                 Scanner scanner = new Scanner(stream, StandardCharsets.UTF_8.name())) {
                scanner.useDelimiter("\\A");
                return scanner.hasNext() ? scanner.next() : "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private EditorContentReader() { }

}
