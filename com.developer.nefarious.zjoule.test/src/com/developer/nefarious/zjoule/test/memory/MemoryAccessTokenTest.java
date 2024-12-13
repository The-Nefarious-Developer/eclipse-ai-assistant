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
import com.developer.nefarious.zjoule.memory.IMemoryAccessToken;
import com.developer.nefarious.zjoule.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.memory.utils.IObjectSerializer;
import com.developer.nefarious.zjoule.models.AccessToken;

public class MemoryAccessTokenTest {

	public static final String KEY = "access-token";
	
	private IMemoryAccessToken cut;
	
	@Mock
	IObjectSerializer mockObjectSerializer;
	
	@Mock
	IEclipseMemory mockEclipseMemory;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		MemoryAccessToken.resetInstance();
		MemoryAccessToken.initialize(mockObjectSerializer, mockEclipseMemory);
		cut = spy(MemoryAccessToken.getInstance());
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
		verify(mockEclipseMemory).saveOnEclipsePreferences(KEY, mockSerializedObject);
	}
	
	@Test
	public void shouldLoadAccessToken() {
		// Arrange
		AccessToken expectedValue = new AccessToken();
		String mockSerializedObject = "It doesn't matter";
		when(mockEclipseMemory.loadFromEclipsePreferences(KEY)).thenReturn(mockSerializedObject);
		when(mockObjectSerializer.deserialize(mockSerializedObject, AccessToken.class)).thenReturn(expectedValue);
		// Act
		AccessToken returnValue = cut.load();
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
