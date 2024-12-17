package com.developer.nefarious.zjoule.plugin.memory.utils;

public interface IObjectSerializer {
	
	String serialize(final Object object);
	
	<T> T deserialize(final String jsonString, final Class<T> clazz);

}
