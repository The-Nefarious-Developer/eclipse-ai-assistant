package com.developer.nefarious.zjoule.test.login.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.login.memory.TemporaryMemoryDeployment;
import com.developer.nefarious.zjoule.memory.IEclipseMemory;

public class TemporaryMemoryDeploymentTest {

	public static final String FINAL_KEY = "deployment";
	
	public static final String TEMPORARY_KEY = "tmp-deployment";

	private TemporaryMemoryDeployment cut;

	@Mock
	IEclipseMemory mockEclipseMemory;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		TemporaryMemoryDeployment.resetInstance();
		TemporaryMemoryDeployment.initialize(mockEclipseMemory);
		cut = TemporaryMemoryDeployment.getInstance();
	}

	@Test
	public void shouldSaveDeployment() {
		// Arrange
		String mockDeployment = "selected deployment";
		// Act
		cut.save(mockDeployment);
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(TEMPORARY_KEY, mockDeployment);
	}

	@Test
	public void shouldLoadDeployment() {
		// Arrange
		String expectedValue = "stored deployment";
		when(mockEclipseMemory.loadFromEclipsePreferences(TEMPORARY_KEY)).thenReturn(expectedValue);
		// Act
		String returnValue = cut.load();
		// Assert
		assertEquals(returnValue, expectedValue);
	}

	@Test
	public void shouldPersistDeployment() {
		// Arrange
		String mockDeployment = "temporary deployment";
		when(mockEclipseMemory.loadFromEclipsePreferences(TEMPORARY_KEY)).thenReturn(mockDeployment);
		// Act
		cut.persist();
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(FINAL_KEY, mockDeployment);
	}
	
}
