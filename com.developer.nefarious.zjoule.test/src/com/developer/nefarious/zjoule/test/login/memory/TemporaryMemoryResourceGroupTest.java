package com.developer.nefarious.zjoule.test.login.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.login.memory.TemporaryMemoryResourceGroup;
import com.developer.nefarious.zjoule.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.models.ResourceGroup;
import com.developer.nefarious.zjoule.utils.IObjectSerializer;

public class TemporaryMemoryResourceGroupTest {

	public static final String FINAL_KEY = "resource-group";

	public static final String TEMPORARY_KEY = "tmp-resource-group";

	private TemporaryMemoryResourceGroup cut;

	@Mock
	IObjectSerializer mockObjectSerializer;

	@Mock
	IEclipseMemory mockEclipseMemory;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		TemporaryMemoryResourceGroup.resetInstance();
		TemporaryMemoryResourceGroup.initialize(mockObjectSerializer, mockEclipseMemory);
		cut = TemporaryMemoryResourceGroup.getInstance();
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
		verify(mockEclipseMemory).saveOnEclipsePreferences(TEMPORARY_KEY, mockSerializedObject);
	}

	@Test
	public void shouldLoadResourceGroup() {
		// Arrange
		ResourceGroup expectedValue = new ResourceGroup();
		String mockSerializedObject = "It doesn't matter";
		when(mockEclipseMemory.loadFromEclipsePreferences(TEMPORARY_KEY)).thenReturn(mockSerializedObject);
		when(mockObjectSerializer.deserialize(mockSerializedObject, ResourceGroup.class)).thenReturn(expectedValue);
		// Act
		ResourceGroup returnValue = cut.load();
		// Assert
		assertEquals(returnValue, expectedValue);
	}

	@Test
	public void shouldPersistResourceGroup() {
		// Arrange
		ResourceGroup mockResourceGroup = new ResourceGroup();
		String mockSerializedObject = "It doesn't matter";
		when(mockEclipseMemory.loadFromEclipsePreferences(TEMPORARY_KEY)).thenReturn(mockSerializedObject);
		// Act
		cut.persist();
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(FINAL_KEY, mockSerializedObject);
	}
}
