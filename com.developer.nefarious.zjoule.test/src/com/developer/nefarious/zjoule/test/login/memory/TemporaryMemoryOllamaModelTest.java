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

import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryOllamaModel;
import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.OllamaModel;

public class TemporaryMemoryOllamaModelTest {

	public static final String FINAL_KEY = "ollama-model";

	public static final String TEMPORARY_KEY = "tmp-ollama-model";

	private TemporaryMemoryOllamaModel cut;

	@Mock
	private IObjectSerializer mockObjectSerializer;

	@Mock
	private IEclipseMemory mockEclipseMemory;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		TemporaryMemoryOllamaModel.resetInstance();
		TemporaryMemoryOllamaModel.initialize(mockObjectSerializer, mockEclipseMemory);
		cut = spy(TemporaryMemoryOllamaModel.getInstance());
	}

	@Test
	public void shouldBeEmptyWhenOllamaModelIsBlank() {
		// Arrange
		OllamaModel mockMockOllamaModel = new OllamaModel();
		mockMockOllamaModel.setName(" ");
		doReturn(mockMockOllamaModel).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}

	@Test
	public void shouldBeEmptyWhenOllamaModelIsEmpty() {
		// Arrange
		OllamaModel mockMockOllamaModel = new OllamaModel();
		mockMockOllamaModel.setName("");
		doReturn(mockMockOllamaModel).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}

	@Test
	public void shouldBeEmptyWhenOllamaModelIsNull() {
		// Arrange
		OllamaModel mockMockOllamaModel = new OllamaModel();
		mockMockOllamaModel.setName(null);
		doReturn(mockMockOllamaModel).when(cut).load();
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
	public void shouldLoadOllamaModel() {
		// Arrange
		OllamaModel expectedValue = new OllamaModel();
		String mockSerializedObject = "It doesn't matter";
		when(mockEclipseMemory.loadFromEclipsePreferences(TEMPORARY_KEY)).thenReturn(mockSerializedObject);
		when(mockObjectSerializer.deserialize(mockSerializedObject, OllamaModel.class)).thenReturn(expectedValue);
		// Act
		OllamaModel returnValue = cut.load();
		// Assert
		assertEquals(returnValue, expectedValue);
	}

	@Test
	public void shouldNotBeEmptyWhenOllamaModelIsSave() {
		// Arrange
		OllamaModel mockMockOllamaModel = new OllamaModel();
		mockMockOllamaModel.setName("some-ollama-model");
		doReturn(mockMockOllamaModel).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertFalse(returnValue);
	}

	@Test
	public void shouldPersistOllamaModel() {
		// Arrange
		String mockSerializedObject = "It doesn't matter";
		when(mockEclipseMemory.loadFromEclipsePreferences(TEMPORARY_KEY)).thenReturn(mockSerializedObject);
		// Act
		cut.persist();
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(FINAL_KEY, mockSerializedObject);
	}

	@Test
	public void shouldSaveOllamaModel() {
		// Arrange
		OllamaModel mockOllamaModel = new OllamaModel();
		String mockSerializedObject = "It doesn't matter";
		when(mockObjectSerializer.serialize(mockOllamaModel)).thenReturn(mockSerializedObject);
		// Act
		cut.save(mockOllamaModel);
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(TEMPORARY_KEY, mockSerializedObject);
	}

}
