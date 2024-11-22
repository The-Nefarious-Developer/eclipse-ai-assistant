package com.developer.nefarious.zjoule.test.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.zjoule.auth.AuthClient;
import com.developer.nefarious.zjoule.auth.ServiceKey;

public class AuthClientTest {
	
	@Mock
	private ServiceKey mockServiceKey;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void shouldReturnAccessTokenFromMemoryWhenNoServiceKeyIsInjected() {
		// Arrange
		AuthClient cut = new AuthClient();
		cut.setServiceKey(mockServiceKey);
		// Act
		cut.getAccessToken();
		// Assert
	}
	
}
