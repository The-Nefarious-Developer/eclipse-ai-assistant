package com.developer.nefarious.zjoule.test.memory;

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

import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;
import com.developer.nefarious.zjoule.plugin.memory.MemoryServiceKey;
import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.ServiceKey;

public class MemoryServiceKeyTest {

	public static final String KEY = "service-key";

	private IMemoryObject<ServiceKey> cut;

	@Mock
	IObjectSerializer mockObjectSerializer;

	@Mock
	IEclipseMemory mockEclipseMemory;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		MemoryServiceKey.resetInstance();
		MemoryServiceKey.initialize(mockObjectSerializer, mockEclipseMemory);
		cut = spy(MemoryServiceKey.getInstance());
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
	public void shouldEraseFromMemory() {
		// Arrange
		// Act
		cut.clear();
		// Assert
		verify(mockEclipseMemory).deleteFromEclipsePreferences(KEY);
	}

}
