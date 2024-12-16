package com.developer.nefarious.zjoule.test.login.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryResourceGroup;
import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;

public class TemporaryMemoryResourceGroupTest {

	public static final String FINAL_KEY = "resource-group";

	public static final String TEMPORARY_KEY = "tmp-resource-group";

	private TemporaryMemoryResourceGroup cut;

	@Mock
	IEclipseMemory mockEclipseMemory;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		TemporaryMemoryResourceGroup.resetInstance();
		TemporaryMemoryResourceGroup.initialize(mockEclipseMemory);
		cut = TemporaryMemoryResourceGroup.getInstance();
	}

	@Test
	public void shouldSaveResourceGroup() {
		// Arrange
		String mockResourceGroup = "selected resource group";
		// Act
		cut.save(mockResourceGroup);
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(TEMPORARY_KEY, mockResourceGroup);
	}

	@Test
	public void shouldLoadResourceGroup() {
		// Arrange
		String expectedValue = "stored resource group";
		when(mockEclipseMemory.loadFromEclipsePreferences(TEMPORARY_KEY)).thenReturn(expectedValue);
		// Act
		String returnValue = cut.load();
		// Assert
		assertEquals(returnValue, expectedValue);
	}

	@Test
	public void shouldPersistResourceGroup() {
		// Arrange
		String mockResourceGroup = "temporary resource group";
		when(mockEclipseMemory.loadFromEclipsePreferences(TEMPORARY_KEY)).thenReturn(mockResourceGroup);
		// Act
		cut.persist();
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(FINAL_KEY, mockResourceGroup);
	}
}
