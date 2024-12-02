package com.developer.nefarious.zjoule.test.login.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.auth.ServiceKey;
import com.developer.nefarious.zjoule.login.memory.TemporaryMemoryServiceKey;
import com.developer.nefarious.zjoule.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.utils.IObjectSerializer;

public class TemporaryMemoryServiceKeyTest {
	
	public static final String FINAL_KEY = "service-key";
	
	public static final String TEMPORARY_KEY = "tmp-service-key";
	
	private TemporaryMemoryServiceKey cut;
	
	@Mock
	IObjectSerializer mockObjectSerializer;
	
	@Mock
	IEclipseMemory mockEclipseMemory;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		cut = new TemporaryMemoryServiceKey(mockObjectSerializer, mockEclipseMemory);
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
		verify(mockEclipseMemory).saveOnEclipsePreferences(TEMPORARY_KEY, mockSerializedObject);
	}
	
	@Test
	public void shouldLoadServiceKey() {
		// Arrange
		ServiceKey expectedValue = new ServiceKey();
		String mockSerializedObject = "It doesn't matter";
		when(mockEclipseMemory.loadFromEclipsePreferences(TEMPORARY_KEY)).thenReturn(mockSerializedObject);
		when(mockObjectSerializer.deserialize(mockSerializedObject, ServiceKey.class)).thenReturn(expectedValue);
		// Act
		ServiceKey returnValue = cut.load();
		// Assert
		assertEquals(returnValue, expectedValue);
	}
	
	@Test
	public void shouldPersistServiceKey() {
		// Arrange
		ServiceKey mockServiceKey = new ServiceKey();
		String mockSerializedObject = "It doesn't matter";
		when(mockObjectSerializer.serialize(mockServiceKey)).thenReturn(mockSerializedObject);
		// Act
		cut.persist(mockServiceKey);
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(FINAL_KEY, mockSerializedObject);
	}

}
