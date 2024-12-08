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
import com.developer.nefarious.zjoule.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.models.Deployment;

public class TemporaryMemoryDeploymentTest {

	public static final String FINAL_KEY = "deployment";
	
	public static final String TEMPORARY_KEY = "tmp-deployment";

	private TemporaryMemoryDeployment cut;
	
	@Mock
	IObjectSerializer mockObjectSerializer;

	@Mock
	IEclipseMemory mockEclipseMemory;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		TemporaryMemoryDeployment.resetInstance();
		TemporaryMemoryDeployment.initialize(mockObjectSerializer, mockEclipseMemory);
		cut = TemporaryMemoryDeployment.getInstance();
	}

	@Test
	public void shouldSaveDeployment() {
		// Arrange
		Deployment mockDeployment = new Deployment();
		String mockSerializedObject = "It doesn't matter";
		when(mockObjectSerializer.serialize(mockDeployment)).thenReturn(mockSerializedObject);
		// Act
		cut.save(mockDeployment);
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(TEMPORARY_KEY, mockSerializedObject);
	}

	@Test
	public void shouldLoadDeployment() {
		// Arrange
		Deployment expectedValue = new Deployment();
		String mockSerializedObject = "It doesn't matter";
		when(mockEclipseMemory.loadFromEclipsePreferences(TEMPORARY_KEY)).thenReturn(mockSerializedObject);
		when(mockObjectSerializer.deserialize(mockSerializedObject, Deployment.class)).thenReturn(expectedValue);
		// Act
		Deployment returnValue = cut.load();
		// Assert
		assertEquals(returnValue, expectedValue);
	}

	@Test
	public void shouldPersistDeployment() {
		// Arrange
		String mockSerializedObject = "It doesn't matter";
		when(mockEclipseMemory.loadFromEclipsePreferences(TEMPORARY_KEY)).thenReturn(mockSerializedObject);
		// Act
		cut.persist();
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(FINAL_KEY, mockSerializedObject);
	}
	
}
