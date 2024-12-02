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
import com.developer.nefarious.zjoule.models.Deployment;
import com.developer.nefarious.zjoule.utils.IObjectSerializer;

public class MemoryDeploymentTest {

	public static final String KEY = "deployment";
	
	private IMemoryDeployment cut;
	
	@Mock
	IObjectSerializer mockObjectSerializer;
	
	@Mock
	IEclipseMemory mockEclipseMemory;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		MemoryDeployment.resetInstance();
		MemoryDeployment.initialize(mockObjectSerializer, mockEclipseMemory);
		cut = MemoryDeployment.getInstance();
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
		verify(mockEclipseMemory).saveOnEclipsePreferences(KEY, mockSerializedObject);
	}
	
	@Test
	public void shouldDeployment() {
		// Arrange
		Deployment expectedValue = new Deployment();
		String mockSerializedObject = "It doesn't matter";
		when(mockEclipseMemory.loadFromEclipsePreferences(KEY)).thenReturn(mockSerializedObject);
		when(mockObjectSerializer.deserialize(mockSerializedObject, Deployment.class)).thenReturn(expectedValue);
		// Act
		Deployment returnValue = cut.load();
		// Assert
		assertEquals(returnValue, expectedValue);
	}
}
