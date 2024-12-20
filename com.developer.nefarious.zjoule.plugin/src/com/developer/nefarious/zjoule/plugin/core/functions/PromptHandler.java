package com.developer.nefarious.zjoule.plugin.core.functions;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import com.developer.nefarious.zjoule.plugin.chat.ChatOrchestrator;

/**
 * Handles user prompts from the browser and communicates with the chat orchestrator to generate responses.
 * <p>
 * This class extends {@link BrowserFunction} to enable JavaScript interaction with the
 * Eclipse SWT {@link Browser} component. It uses the {@link ChatOrchestrator} to process
 * user input and generate AI-driven responses.
 */
public class PromptHandler extends BrowserFunction {

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
        chatOrchestrator = new ChatOrchestrator();
    }

    /**
     * Handles the JavaScript function call from the browser.
     * <p>
     * This method takes the first argument as the user prompt, passes it to the
     * {@link ChatOrchestrator#getAnswer(String)} method, and returns the generated AI response.
     *
     * @param arguments the arguments passed from the JavaScript function call.
     * @return the AI-generated response as an {@link Object}.
     */
    @Override
    public Object function(final Object[] arguments) {
        String userPrompt = arguments[0].toString();
        return chatOrchestrator.getAnswer(userPrompt);
    }
}
