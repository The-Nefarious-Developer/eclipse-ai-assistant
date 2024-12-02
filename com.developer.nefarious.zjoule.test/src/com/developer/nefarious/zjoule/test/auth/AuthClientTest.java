package com.developer.nefarious.zjoule.test.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.net.http.HttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.auth.AuthClient;
import com.developer.nefarious.zjoule.auth.AuthClientHelper;
import com.developer.nefarious.zjoule.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.memory.MemoryServiceKey;
import com.developer.nefarious.zjoule.models.AccessToken;
import com.developer.nefarious.zjoule.models.ServiceKey;

public class AuthClientTest {

	private AuthClient cut;

	@Mock
	private HttpClient mockHttpClient;

	@Mock
	private AuthClientHelper mockAuthClientHelper;

	@Mock
	private MemoryAccessToken mockMemoryAccessToken;

	@Mock
	private MemoryServiceKey mockMemoryServiceKey;

	@Mock
	private AccessToken mockAccessToken;

	@Mock
	private ServiceKey mockServiceKey;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		when(mockMemoryAccessToken.load()).thenReturn(mockAccessToken);
		when(mockMemoryServiceKey.load()).thenReturn(mockServiceKey);

		// HttpClient.newHttpClient
		try (MockedStatic<HttpClient> httpClientStatic = mockStatic(HttpClient.class)) {
			httpClientStatic.when(() -> HttpClient.newHttpClient()).thenReturn(mockHttpClient);
			cut = spy(new AuthClient(mockMemoryAccessToken, mockMemoryServiceKey, mockAuthClientHelper));
		}
	}

	@Test
	public void shouldReturnAccessTokenFromMemory() throws IOException, InterruptedException {
		// Arrange
		when(mockAccessToken.isValid()).thenReturn(true);
		String expectedValue = "super-secret-from-memory";
		when(mockAccessToken.getAccessToken()).thenReturn(expectedValue);
		// Act
		String returnValue = cut.getAccessToken();
		// Assert
		assertEquals(returnValue, expectedValue);
	}

	@Test
	public void shouldReturnNewAccessTokenIfMemoryIsInvalid() throws IOException, InterruptedException {
		// Arrange
		when(mockAccessToken.isValid()).thenReturn(false);
		String expectedValue = "super-secret-from-memory";
		doReturn(expectedValue).when(cut).getAccessToken();
		// Act
		String returnValue = cut.getAccessToken();
		// Assert
		assertEquals(returnValue, expectedValue);
	}
	
	//TODO: Implement test for getNewAccessToken
	
	//TODO: Implement test for getNewAccessToken with service key

}
