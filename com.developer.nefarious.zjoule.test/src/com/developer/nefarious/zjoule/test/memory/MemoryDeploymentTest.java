package com.developer.nefarious.zjoule.test.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.memory.IMemoryDeployment;
import com.developer.nefarious.zjoule.memory.MemoryDeployment;

public class MemoryDeploymentTest {

	public static final String KEY = "deployment";
	
	private IMemoryDeployment cut;
	
	@Mock
	IEclipseMemory mockEclipseMemory;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		MemoryDeployment.resetInstance();
		MemoryDeployment.initialize(mockEclipseMemory);
		cut = MemoryDeployment.getInstance();
	}
	
	@Test
	public void shouldSaveDeployment() {
		// Arrange
		String mockDeployment = "selected deployment";
		// Act
		cut.save(mockDeployment);
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(KEY, mockDeployment);
	}
	
	@Test
	public void shouldDeployment() {
		// Arrange
		String expectedValue = "stored deployment";
		when(mockEclipseMemory.loadFromEclipsePreferences(KEY)).thenReturn(expectedValue);
		// Act
		String returnValue = cut.load();
		// Assert
		assertEquals(returnValue, expectedValue);
	}
}
