package com.developer.nefarious.zjoule.plugin.models;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Represents different roles in the system.
 * <p>
 * The {@code Role} enum defines three distinct roles:
 * <ul>
 *   <li>{@code USER} - Represents a standard user.</li>
 *   <li>{@code ASSISTANT} - Represents an assistant role.</li>
 *   <li>{@code SYSTEM} - Represents a system-level role.</li>
 * </ul>
 * Each role has an associated string value for serialization and display purposes.
 * </p>
 */
public enum Role {

    /** Represents a standard user role. */
    USER("user"),

    /** Represents an assistant role. */
    ASSISTANT("assistant"),

    /** Represents a system-level role. */
    SYSTEM("system");

    /** The string value associated with the role. */
    private final String value;

    /**
     * Constructs a {@code Role} enum with the specified string value.
     *
     * @param value the string value associated with the role.
     */
    Role(final String value) {
        this.value = value;
    }

    /**
     * Retrieves the string value associated with the role.
     *
     * @return the string value of the role.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the string representation of the role.
     *
     * @return the string value of the role.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Custom serializer for the {@code Role} enum to be used with Gson.
     * <p>
     * Converts a {@code Role} enum instance into its corresponding JSON representation,
     * which is the string value of the role.
     * </p>
     */
    public static class RoleSerializer implements JsonSerializer<Role> {

        /**
         * Serializes a {@code Role} enum instance into a JSON element.
         *
         * @param src the {@code Role} instance to serialize.
         * @param typeOfSrc the type of the source object.
         * @param context the serialization context.
         * @return a {@link JsonPrimitive} containing the string value of the role.
         */
        @Override
        public JsonElement serialize(final Role src, final Type typeOfSrc, final JsonSerializationContext context) {
            return new JsonPrimitive(src.getValue());
        }
    }
}
