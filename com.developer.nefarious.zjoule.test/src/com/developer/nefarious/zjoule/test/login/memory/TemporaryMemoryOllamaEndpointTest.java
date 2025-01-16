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

import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryOllamaEndpoint;
import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;

public class TemporaryMemoryOllamaEndpointTest {

	public static final String FINAL_KEY = "ollama-endpoint";

	public static final String TEMPORARY_KEY = "tmp-ollama-endpoint";

	private TemporaryMemoryOllamaEndpoint cut;

	@Mock
	IEclipseMemory mockEclipseMemory;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		TemporaryMemoryOllamaEndpoint.resetInstance();
		TemporaryMemoryOllamaEndpoint.initialize(mockEclipseMemory);
		cut = spy(TemporaryMemoryOllamaEndpoint.getInstance());
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
	public void shouldBeEmptyWhenOllamaEndpointIsBlank() {
		// Arrange
		String mockMockOllamaEndpoint = " ";
		doReturn(mockMockOllamaEndpoint).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}

	@Test
	public void shouldBeEmptyWhenOllamaEndpointIsEmpty() {
		// Arrange
		String mockMockOllamaEndpoint = "";
		doReturn(mockMockOllamaEndpoint).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}

	@Test
	public void shouldBeEmptyWheOllamaEndpointIsNull() {
		// Arrange
		String mockMockOllamaEndpoint = null;
		doReturn(mockMockOllamaEndpoint).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}

	@Test
	public void shouldLoadOllamaEndpoint() {
		// Arrange
		String expectedValue = "stored ollama endpoint";
		when(mockEclipseMemory.loadFromEclipsePreferences(TEMPORARY_KEY)).thenReturn(expectedValue);
		// Act
		String returnValue = cut.load();
		// Assert
		assertEquals(returnValue, expectedValue);
	}

	@Test
	public void shouldNotBeEmptyWhenOllamaEndpointIsSave() {
		// Arrange
		String mockMockOllamaEndpoint = "ollama-endpoint";
		doReturn(mockMockOllamaEndpoint).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertFalse(returnValue);
	}

	@Test
	public void shouldPersistOllamaEndpoint() {
		// Arrange
		String mockOllamaEndpoint = "temporary ollama endpoint";
		when(mockEclipseMemory.loadFromEclipsePreferences(TEMPORARY_KEY)).thenReturn(mockOllamaEndpoint);
		// Act
		cut.persist();
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(FINAL_KEY, mockOllamaEndpoint);
	}

	@Test
	public void shouldSaveOllamaEndpoint() {
		// Arrange
		String mockOllamaEndpoint = "selected ollama endpoint";
		// Act
		cut.save(mockOllamaEndpoint);
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(TEMPORARY_KEY, mockOllamaEndpoint);
	}

}
