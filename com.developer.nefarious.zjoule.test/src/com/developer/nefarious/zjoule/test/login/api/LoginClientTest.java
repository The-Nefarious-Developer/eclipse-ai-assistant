package com.developer.nefarious.zjoule.test.login.api;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import java.io.IOException;
import java.net.http.HttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.auth.ServiceKey;
import com.developer.nefarious.zjoule.login.api.ILoginClient;
import com.developer.nefarious.zjoule.login.api.LoginClient;

public class LoginClientTest {
	
	private ILoginClient cut;
	
	@Mock
	private HttpClient mockHttpClient;
	
	@Mock
	private ServiceKey mockServiceKey;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		try (MockedStatic<HttpClient> httpClientStatic = mockStatic(HttpClient.class)) {
			httpClientStatic.when(() -> HttpClient.newHttpClient()).thenReturn(mockHttpClient);
			cut = spy(new LoginClient());
		}
		
	}
	
	@Test
	public void testGetResourceGroups() throws IOException, InterruptedException {
		// Arrange
		// Act
//		GetResourceGroupsResponse returnOject = cut.getResourceGroups(mockServiceKey);
		// Assert
		assertTrue(true);
	}

}
