package com.developer.nefarious.zjoule.utils;

public interface IObjectSerializer {
	
	String serialize(Object object);
	
	<T> T deserialize(String jsonString, Class<T> clazz);

}
