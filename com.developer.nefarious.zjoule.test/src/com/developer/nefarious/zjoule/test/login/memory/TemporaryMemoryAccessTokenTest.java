package com.developer.nefarious.zjoule.test.login.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.auth.AccessToken;
import com.developer.nefarious.zjoule.login.memory.TemporaryMemoryAccessToken;
import com.developer.nefarious.zjoule.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.utils.IObjectSerializer;

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
		cut = new TemporaryMemoryAccessToken(mockObjectSerializer, mockEclipseMemory);
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
	public void shouldAccessToken() {
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
		AccessToken mockAccessToken = new AccessToken();
		String mockSerializedObject = "It doesn't matter";
		when(mockObjectSerializer.serialize(mockAccessToken)).thenReturn(mockSerializedObject);
		// Act
		cut.persist(mockAccessToken);
		// Assert
		verify(mockEclipseMemory).saveOnEclipsePreferences(FINAL_KEY, mockSerializedObject);
	}
}
