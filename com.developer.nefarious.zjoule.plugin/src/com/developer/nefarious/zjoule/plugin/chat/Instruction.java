package com.developer.nefarious.zjoule.plugin.chat;

/**
 * Provides system-level instructions for AI interactions.
 * The {@code Instruction} class contains predefined instructions used
 * to guide the AI's behavior and ensure responses adhere to best practices.
 * This is a utility class and cannot be instantiated.
 */
public abstract class Instruction {

    /**
     * The predefined system message text used to guide the AI's behavior.
     * The message encourages the AI to act as a friendly and informative SAP ABAP developer,
     * adhering to best practices and clean-code guidelines.
     */
    private static final String SYSTEM_MESSAGE_TEXT =
            "Act as a friendly and informative SAP ABAP "
            + "Developer expert, always considering the "
            + "best practices and clean-code guidelines "
            + "when providing answers.";

    /**
     * Retrieves the predefined system message text.
     *
     * @return the system message text as a {@link String}.
     */
    public static String getMessage() {
        return SYSTEM_MESSAGE_TEXT;
    }

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private Instruction() { }

}
