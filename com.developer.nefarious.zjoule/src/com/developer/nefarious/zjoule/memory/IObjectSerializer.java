package com.developer.nefarious.zjoule.memory;

public interface IObjectSerializer {
	
	String serialize(Object object);
	
	<T> T deserialize(String jsonString, Class<T> clazz);

}
