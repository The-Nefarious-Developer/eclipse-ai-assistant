package com.developer.nefarious.zjoule.memory.utils;

public interface IObjectSerializer {
	
	String serialize(final Object object);
	
	<T> T deserialize(final String jsonString, final Class<T> clazz);

}
