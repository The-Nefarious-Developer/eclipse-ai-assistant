package com.developer.nefarious.zjoule.test.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.auth.ServiceKey;
import com.developer.nefarious.zjoule.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.memory.IObjectSerializer;
import com.developer.nefarious.zjoule.memory.MemoryServiceKey;

public class MemoryServiceKeyTest {
	
	public static final String KEY = "serviceKey";
	
	private MemoryServiceKey cut;
	
	@Mock
	IObjectSerializer mockObjectSerializer;
	
	@Mock
	IEclipseMemory mockEclipseMemory;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		cut = new MemoryServiceKey(mockObjectSerializer, mockEclipseMemory);
	}
	
	@Test
	public void shouldSaveServiceKey() {
		// Arrange
		ServiceKey mockServiceKey = new ServiceKey();
		String mockSerializedObject = "It doesn't matter";
		when(mockObjectSerializer.serialize(mockServiceKey)).thenReturn(mockSerializedObject);
		// Act
		cut.save(mockServiceKey);
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(KEY, mockSerializedObject);
	}
	
	@Test
	public void shouldLoadServiceKey() {
		// Arrange
		ServiceKey expectedValue = new ServiceKey();
		String mockSerializedObject = "It doesn't matter";
		when(mockEclipseMemory.loadFromEclipsePreferences(KEY)).thenReturn(mockSerializedObject);
		when(mockObjectSerializer.deserialize(mockSerializedObject, ServiceKey.class)).thenReturn(expectedValue);
		// Act
		ServiceKey returnValue = cut.load();
		// Assert
		assertEquals(returnValue, expectedValue);
	}

}
