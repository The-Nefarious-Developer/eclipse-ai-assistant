package com.developer.nefarious.zjoule.test.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.zjoule.auth.AccessToken;
import com.developer.nefarious.zjoule.auth.AuthClient;
import com.developer.nefarious.zjoule.memory.MemoryAccessToken;

public class AuthClientTest {
	
	private AuthClient cut;
	
	@Mock
	private MemoryAccessToken mockMemoryAccessToken;
	
	@Mock
	private AccessToken mockAccessToken;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		cut = new AuthClient(mockMemoryAccessToken);
	}
	
	@Test
	public void shouldReturnAccessTokenFromMemory() {
		// Arrange
		String expectedValue = "super-secret";
		when(mockMemoryAccessToken.load()).thenReturn(mockAccessToken);
		when(mockAccessToken.getAccessToken()).thenReturn(expectedValue);
		// Act
		String returnValue = cut.getAccessToken();
		// Assert
		assertEquals(returnValue, expectedValue);
	}
	
}
