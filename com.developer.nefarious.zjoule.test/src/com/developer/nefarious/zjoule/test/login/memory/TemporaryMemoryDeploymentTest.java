package com.developer.nefarious.zjoule.test.login.memory;

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

import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryDeployment;
import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.Deployment;

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
		cut = spy(TemporaryMemoryDeployment.getInstance());
	}

	@Test
	public void shouldBeEmptyWhenDeploymentIsBlank() {
		// Arrange
		Deployment mockMockDeployment = new Deployment();
		mockMockDeployment.setConfigurationName(" ");
		doReturn(mockMockDeployment).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}

	@Test
	public void shouldBeEmptyWhenDeploymentIsEmpty() {
		// Arrange
		Deployment mockMockDeployment = new Deployment();
		mockMockDeployment.setConfigurationName("");
		doReturn(mockMockDeployment).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}

	@Test
	public void shouldBeEmptyWhenDeploymentIsNull() {
		// Arrange
		Deployment mockMockDeployment = new Deployment();
		mockMockDeployment.setConfigurationName(null);
		doReturn(mockMockDeployment).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
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
	public void shouldNotBeEmptyWhenDeploymentIsSave() {
		// Arrange
		Deployment mockMockDeployment = new Deployment();
		mockMockDeployment.setConfigurationName("some-deployment");
		doReturn(mockMockDeployment).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertFalse(returnValue);
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

}
