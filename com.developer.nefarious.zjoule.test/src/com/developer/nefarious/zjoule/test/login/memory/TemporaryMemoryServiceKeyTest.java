package com.developer.nefarious.zjoule.test.login.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryServiceKey;
import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.ServiceKey;

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
		
		TemporaryMemoryServiceKey.resetInstance();
		TemporaryMemoryServiceKey.initialize(mockObjectSerializer, mockEclipseMemory);
		cut = spy(TemporaryMemoryServiceKey.getInstance());
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
		String mockSerializedObject = "It doesn't matter";
		when(mockEclipseMemory.loadFromEclipsePreferences(TEMPORARY_KEY)).thenReturn(mockSerializedObject);
		// Act
		cut.persist();
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(FINAL_KEY, mockSerializedObject);
	}
	
	@Test
	public void shouldBeEmptyWhenNoMemory() {
		// Arrange
		doReturn(null).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}
	
	@Test
	public void shouldBeEmptyWhenServiceKeyIsNull() {
		// Arrange
		ServiceKey mockServiceKey = new ServiceKey();
		mockServiceKey.setClientId(null);
		doReturn(mockServiceKey).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}
	
	@Test
	public void shouldBeEmptyWhenServiceKeyIsEmpty() {
		// Arrange
		ServiceKey mockServiceKey = new ServiceKey();
		mockServiceKey.setClientId("");
		doReturn(mockServiceKey).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}
	
	@Test
	public void shouldBeEmptyWhenServiceKeyIsBlank() {
		// Arrange
		ServiceKey mockServiceKey = new ServiceKey();
		mockServiceKey.setClientId(" ");
		doReturn(mockServiceKey).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}
	
	@Test
	public void shouldNotBeEmptyWhenServiceKeyIsSave() {
		// Arrange
		ServiceKey mockServiceKey = new ServiceKey();
		mockServiceKey.setClientId("some-service-key");
		doReturn(mockServiceKey).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertFalse(returnValue);
	}

}
