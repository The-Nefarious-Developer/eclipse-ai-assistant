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

import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;
import com.developer.nefarious.zjoule.plugin.memory.MemoryOllamaEndpoint;

public class MemoryOllamaEndpointTest {

	public static final String KEY = "ollama-endpoint";

	private IMemoryObject<String> cut;

	@Mock
	private IEclipseMemory mockEclipseMemory;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		MemoryOllamaEndpoint.resetInstance();
		MemoryOllamaEndpoint.initialize(mockEclipseMemory);
		cut = spy(MemoryOllamaEndpoint.getInstance());
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
		when(mockEclipseMemory.loadFromEclipsePreferences(KEY)).thenReturn(expectedValue);
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
	public void shouldSaveOllamaEndpoint() {
		// Arrange
		String mockOllamaEndpoint = "selected ollama endpoint";
		// Act
		cut.save(mockOllamaEndpoint);
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(KEY, mockOllamaEndpoint);
	}

}
