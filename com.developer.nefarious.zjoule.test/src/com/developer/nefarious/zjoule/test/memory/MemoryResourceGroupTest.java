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
		cut = spy(MemoryResourceGroup.getInstance());
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
	public void shouldBeEmptyWheResourceGroupIsNull() {
		// Arrange
		String mockMockResourceGroup = null;
		doReturn(mockMockResourceGroup).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}
	
	@Test
	public void shouldBeEmptyWhenResourceGroupIsEmpty() {
		// Arrange
		String mockMockResourceGroup = "";
		doReturn(mockMockResourceGroup).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}
	
	@Test
	public void shouldBeEmptyWhenResourceGroupIsBlank() {
		// Arrange
		String mockMockResourceGroup = " ";
		doReturn(mockMockResourceGroup).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}
	
	@Test
	public void shouldNotBeEmptyWhenResourceGroupIsSave() {
		// Arrange
		String mockMockResourceGroup = "resource-group";
		doReturn(mockMockResourceGroup).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertFalse(returnValue);
	}
	
}
