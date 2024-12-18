package com.developer.nefarious.zjoule.plugin.memory.utils;

public interface IObjectSerializer {

	<T> T deserialize(final String jsonString, final Class<T> clazz);

	String serialize(final Object object);

}
