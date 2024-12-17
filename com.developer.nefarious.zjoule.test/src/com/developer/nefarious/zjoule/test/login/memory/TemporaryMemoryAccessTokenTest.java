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
import com.developer.nefarious.zjoule.plugin.login.memory.TemporaryMemoryAccessToken;
import com.developer.nefarious.zjoule.plugin.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.plugin.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.plugin.models.AccessToken;

public class TemporaryMemoryAccessTokenTest {
	
	public static final String FINAL_KEY = "access-token";
	
	public static final String TEMPORARY_KEY = "tmp-access-token";

	private TemporaryMemoryAccessToken cut;

	@Mock
	IObjectSerializer mockObjectSerializer;

	@Mock
	IEclipseMemory mockEclipseMemory;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		TemporaryMemoryAccessToken.resetInstance();
		TemporaryMemoryAccessToken.initialize(mockObjectSerializer, mockEclipseMemory);
		cut = spy(TemporaryMemoryAccessToken.getInstance());
	}

	@Test
	public void shouldSaveAccessToken() {
		// Arrange
		AccessToken mockAccessToken = new AccessToken();
		String mockSerializedObject = "It doesn't matter";
		when(mockObjectSerializer.serialize(mockAccessToken)).thenReturn(mockSerializedObject);
		// Act
		cut.save(mockAccessToken);
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(TEMPORARY_KEY, mockSerializedObject);
	}

	@Test
	public void shouldLoadAccessToken() {
		// Arrange
		AccessToken expectedValue = new AccessToken();
		String mockSerializedObject = "It doesn't matter";
		when(mockEclipseMemory.loadFromEclipsePreferences(TEMPORARY_KEY)).thenReturn(mockSerializedObject);
		when(mockObjectSerializer.deserialize(mockSerializedObject, AccessToken.class)).thenReturn(expectedValue);
		// Act
		AccessToken returnValue = cut.load();
		// Assert
		assertEquals(returnValue, expectedValue);
	}

	@Test
	public void shouldPersistAccessToken() {
		// Arrange
		String mockSerializedObject = "It doesn't matter";
		when(mockEclipseMemory.loadFromEclipsePreferences(TEMPORARY_KEY)).thenReturn(mockSerializedObject);
		// Act
		cut.persist();
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(FINAL_KEY, mockSerializedObject);
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
	public void shouldBeEmptyWhenAccessTokenIsNull() {
		// Arrange
		AccessToken mockAccessaToken = new AccessToken();
		mockAccessaToken.setAccessToken(null);
		doReturn(mockAccessaToken).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}
	
	@Test
	public void shouldBeEmptyWhenAccessTokenIsEmpty() {
		// Arrange
		AccessToken mockAccessaToken = new AccessToken();
		mockAccessaToken.setAccessToken("");
		doReturn(mockAccessaToken).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}
	
	@Test
	public void shouldBeEmptyWhenAccessTokenIsBlank() {
		// Arrange
		AccessToken mockAccessaToken = new AccessToken();
		mockAccessaToken.setAccessToken(" ");
		doReturn(mockAccessaToken).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertTrue(returnValue);
	}
	
	@Test
	public void shouldNotBeEmptyWhenAccessTokenIsSave() {
		// Arrange
		AccessToken mockAccessaToken = new AccessToken();
		mockAccessaToken.setAccessToken("some-access-token");
		doReturn(mockAccessaToken).when(cut).load();
		// Act
		Boolean returnValue = cut.isEmpty();
		// Assert
		verify(cut).load();
		assertFalse(returnValue);
	}
	
}
