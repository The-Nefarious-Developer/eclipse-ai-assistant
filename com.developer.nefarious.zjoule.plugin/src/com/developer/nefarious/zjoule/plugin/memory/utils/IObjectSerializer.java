package com.developer.nefarious.zjoule.plugin.memory.utils;

public interface IObjectSerializer {

    /**
     * Deserializes a JSON string into an object of the specified class type.
     *
     * @param <T> the type of the object to deserialize.
     * @param jsonString the JSON string to deserialize.
     * @param clazz the {@link Class} of the object to deserialize.
     * @return the deserialized object of type {@code T}.
     * @throws IllegalArgumentException if the JSON string is {@code null} or empty.
     */
	<T> T deserialize(final String jsonString, final Class<T> clazz);

    /**
     * Serializes an object into a JSON string.
     *
     * @param object the object to serialize.
     * @return the serialized JSON string.
     * @throws IllegalArgumentException if the object is {@code null}.
     */
	String serialize(final Object object);

}
