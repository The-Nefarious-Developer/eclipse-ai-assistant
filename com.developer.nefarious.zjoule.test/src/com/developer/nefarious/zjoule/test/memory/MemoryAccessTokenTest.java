package com.developer.nefarious.zjoule.test.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.auth.AccessToken;
import com.developer.nefarious.zjoule.memory.IEclipseMemory;
import com.developer.nefarious.zjoule.memory.IObjectSerializer;
import com.developer.nefarious.zjoule.memory.MemoryAccessToken;

public class MemoryAccessTokenTest {

	public static final String KEY = "accessToken";
	
	private MemoryAccessToken cut;
	
	@Mock
	IObjectSerializer mockObjectSerializer;
	
	@Mock
	IEclipseMemory mockEclipseMemory;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		cut = new MemoryAccessToken(mockObjectSerializer, mockEclipseMemory);
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
	public void shouldAccessToken() {
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
}
