package com.developer.nefarious.zjoule.test.login.api;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import java.net.http.HttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.login.api.ILoginClient;
import com.developer.nefarious.zjoule.login.api.LoginClient;

public class LoginClientTest {
	
	private ILoginClient cut;
	
	@Mock
	private HttpClient mockHttpClient;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		try (MockedStatic<HttpClient> httpClientStatic = mockStatic(HttpClient.class)) {
			httpClientStatic.when(() -> HttpClient.newHttpClient()).thenReturn(mockHttpClient);
			cut = spy(new LoginClient());
		}
		
	}
	
	@Test
	public void testGetResourceGroups() {
		// Arrange
		// Act
//		cut.getResourceGroups();
		// Assert
		assertTrue(true);
	}

}
