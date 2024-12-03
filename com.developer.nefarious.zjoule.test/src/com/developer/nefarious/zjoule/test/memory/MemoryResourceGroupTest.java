package com.developer.nefarious.zjoule.test.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.memory.IMemoryResourceGroup;
import com.developer.nefarious.zjoule.memory.MemoryResourceGroup;

public class MemoryResourceGroupTest {

	public static final String KEY = "resource-group";
	
	private IMemoryResourceGroup cut;
	
	@Mock
	IEclipseMemory mockEclipseMemory;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		MemoryResourceGroup.resetInstance();
		MemoryResourceGroup.initialize(mockEclipseMemory);
		cut = MemoryResourceGroup.getInstance();
	}
	
	@Test
	public void shouldSaveResourceGroup() {
		// Arrange
		String mockResourceGroup = "selected resource group";
		// Act
		cut.save(mockResourceGroup);
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(KEY, mockResourceGroup);
	}
	
	@Test
	public void shouldLoadResourceGroup() {
		// Arrange
		String expectedValue = "stored resource group";
		when(mockEclipseMemory.loadFromEclipsePreferences(KEY)).thenReturn(expectedValue);
		// Act
		String returnValue = cut.load();
		// Assert
		assertEquals(returnValue, expectedValue);
	}
}
