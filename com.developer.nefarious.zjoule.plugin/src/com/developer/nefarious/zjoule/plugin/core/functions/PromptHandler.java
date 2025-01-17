package com.developer.nefarious.zjoule.plugin.core.functions;

import java.util.concurrent.CompletableFuture;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.widgets.Display;

import com.developer.nefarious.zjoule.plugin.chat.ChatOrchestrator;
import com.developer.nefarious.zjoule.plugin.chat.utils.EditorContentReader;

/**
 * Handles user prompts from the browser and communicates with the chat orchestrator to generate responses.
 * <p>
 * This class extends {@link BrowserFunction} to enable JavaScript interaction with the
 * Eclipse SWT {@link Browser} component. It uses the {@link ChatOrchestrator} to process
 * user input and generate AI-driven responses.
 */
public class PromptHandler extends BrowserFunction {
	
	private Browser browser;

    /** The {@link ChatOrchestrator} instance responsible for processing user prompts. */
    private ChatOrchestrator chatOrchestrator;

    /**
     * Factory method to create a new {@code PromptHandler} instance.
     *
     * @param browser the {@link Browser} instance associated with this function.
     * @param name the name of the JavaScript function exposed to the browser.
     * @return a new {@code PromptHandler} instance.
     */
    public static PromptHandler create(final Browser browser, final String name) {
        return new PromptHandler(browser, name);
    }

    /**
     * Constructs a new {@code PromptHandler} instance.
     *
     * @param browser the {@link Browser} instance associated with this function.
     * @param name the name of the JavaScript function exposed to the browser.
     */
    public PromptHandler(final Browser browser, final String name) {
        super(browser, name);
        this.browser = browser;
        chatOrchestrator = new ChatOrchestrator();
    }

    /**
     * Handles a user prompt from the browser and initiates asynchronous processing to generate an AI response.
     *
     * <p>This method is invoked via the {@link BrowserFunction} interface when a corresponding JavaScript
     * function is called in the browser widget. It processes the first argument as a user prompt, read the editor
     * content, retrieves an AI-generated response asynchronously using the {@link ChatOrchestrator}, and delivers 
     * the response back to the browser environment through the JavaScript function {@code receiveMessage}.</p>
     *
     * <p>The response is escaped to ensure it is safe for inclusion in JavaScript code, avoiding issues
     * caused by special characters.</p>
     *
     * @param arguments an array of objects passed from JavaScript. The first element is expected to be 
     *                  a {@code String} representing the user prompt.
     * @return always {@code null}, as the operation is asynchronous and does not provide an immediate result.
     *
     * @throws IllegalArgumentException if {@code arguments} is {@code null}, empty, or if the first element is {@code null}.
     */
    @Override
    public Object function(final Object[] arguments) {
        String userPrompt = arguments[0].toString();
        String editorContent = EditorContentReader.readActiveEditorContent();
        
        CompletableFuture<String> futureResponse = CompletableFuture.supplyAsync(
        		() -> chatOrchestrator.getAnswer(userPrompt, editorContent));
        
        futureResponse.thenAccept(response -> {
            Display.getDefault().asyncExec(new Runnable() {
            	@Override
            	public void run() {
            		String escapedResponse = escapeForJavaScript(response);
            		browser.execute("receiveMessage(\"" + escapedResponse + "\");");
            	}
            });
        });

        return null; // no need for return as answer will run asynchronously
    }
    
    /**
     * Escapes a string for safe inclusion in JavaScript code.
     * 
     * @param input the string to escape
     * @return the escaped string
     */
    private String escapeForJavaScript(final String input) {
        if (input == null) {
            return "";
        }
        return input
            .replace("\\", "\\\\") // Escape backslashes
            .replace("\"", "\\\"") // Escape double quotes
            .replace("\n", "\\n")  // Escape newlines
            .replace("\r", "\\r")  // Escape carriage returns
            .replace("'", "\\'");  // Escape single quotes
    }
    
}
