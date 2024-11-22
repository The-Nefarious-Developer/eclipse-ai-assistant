package com.developer.nefarious.zjoule.test.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.osgi.service.prefs.BackingStoreException;

import com.developer.nefarious.zjoule.memory.EclipseMemoryStorage;
import com.developer.nefarious.zjoule.memory.MemoryObject;

public class MemoryObjectTest {
	
	private MemoryObject cut;
	
	@Mock
	private EclipseMemoryStorage mockEclipseMemory;
	
	@Mock
	private IEclipsePreferences mockPreferences;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		try(MockedStatic<EclipseMemoryStorage> eclipseMemoryStatic = mockStatic(EclipseMemoryStorage.class)) {
			eclipseMemoryStatic.when(() -> EclipseMemoryStorage.getInstance()).thenReturn(mockEclipseMemory);
			when(mockEclipseMemory.getEclipsePreferences()).thenReturn(mockPreferences);
			cut = new MemoryObject() {};
		}
	}
	
	@Test
	public void shouldSaveOnEclipsePreferences() {
		// Arrange
		String mockKey = "some key";
		String mockValue = "some random value";
		// Act
		cut.saveOnEclipsePreferences(mockKey, mockValue);
		// Assert
		verify(mockPreferences).put(mockKey, mockValue);
		try {
			verify(mockPreferences).flush();
		} catch (BackingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldDoNothingInCaseOfErrors() throws BackingStoreException {
		// Arrange
		String mockKey = "some key";
		String mockValue = "some random value";
		doThrow(new BackingStoreException("Accidents happen")).when(mockPreferences).flush();
		// Act
		cut.saveOnEclipsePreferences(mockKey, mockValue);
		// Assert
		verify(mockPreferences).put(mockKey, mockValue);
		assertThrows(BackingStoreException.class, () -> mockPreferences.flush());
	}
	
	@Test
	public void shouldLoadValueFromKeyAndReturnNullIfItDoesntExist() {
		// Arrange
		String mockKey = "some key";
		String expectedValue = "some random value";
		when(mockPreferences.get(mockKey, null)).thenReturn(expectedValue);
		// Act
		String returnValue = cut.loadFromEclipsePreferences(mockKey);
		// Assert
		assertEquals(returnValue, expectedValue);
	}

}
