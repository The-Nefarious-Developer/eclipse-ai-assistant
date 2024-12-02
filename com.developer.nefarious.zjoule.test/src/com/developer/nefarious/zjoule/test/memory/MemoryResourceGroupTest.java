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
import com.developer.nefarious.zjoule.models.ResourceGroup;
import com.developer.nefarious.zjoule.utils.IObjectSerializer;

public class MemoryResourceGroupTest {

	public static final String KEY = "resource-group";
	
	private IMemoryResourceGroup cut;
	
	@Mock
	IObjectSerializer mockObjectSerializer;
	
	@Mock
	IEclipseMemory mockEclipseMemory;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		MemoryResourceGroup.resetInstance();
		MemoryResourceGroup.initialize(mockObjectSerializer, mockEclipseMemory);
		cut = MemoryResourceGroup.getInstance();
	}
	
	@Test
	public void shouldSaveResourceGroup() {
		// Arrange
		ResourceGroup mockResourceGroup = new ResourceGroup();
		String mockSerializedObject = "It doesn't matter";
		when(mockObjectSerializer.serialize(mockResourceGroup)).thenReturn(mockSerializedObject);
		// Act
		cut.save(mockResourceGroup);
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(KEY, mockSerializedObject);
	}
	
	@Test
	public void shouldResourceGroup() {
		// Arrange
		ResourceGroup expectedValue = new ResourceGroup();
		String mockSerializedObject = "It doesn't matter";
		when(mockEclipseMemory.loadFromEclipsePreferences(KEY)).thenReturn(mockSerializedObject);
		when(mockObjectSerializer.deserialize(mockSerializedObject, ResourceGroup.class)).thenReturn(expectedValue);
		// Act
		ResourceGroup returnValue = cut.load();
		// Assert
		assertEquals(returnValue, expectedValue);
	}
}
