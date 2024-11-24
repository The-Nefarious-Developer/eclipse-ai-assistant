package com.developer.nefarious.zjoule.test.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.auth.AccessToken;
import com.developer.nefarious.zjoule.auth.AuthClient;
import com.developer.nefarious.zjoule.auth.AuthClientHelper;
import com.developer.nefarious.zjoule.auth.ServiceKey;
import com.developer.nefarious.zjoule.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.memory.MemoryServiceKey;

public class AuthClientTest {

	private AuthClient cut;

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

	private String randomWord() {
		final String[] WORDS = { "apple", "banana", "grape" };
		int randomIndex = ThreadLocalRandom.current().nextInt(WORDS.length);
		return WORDS[randomIndex];
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		when(mockMemoryAccessToken.load()).thenReturn(mockAccessToken);
		when(mockMemoryServiceKey.load()).thenReturn(mockServiceKey);

		cut = new AuthClient(mockMemoryAccessToken, mockMemoryServiceKey, mockAuthClientHelper);
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
	
	// CHECKSTYLE:OFF
	@Test
	public void shouldReturnNewToken() throws IOException, InterruptedException {
		// Arrange
		when(mockAccessToken.isValid()).thenReturn(false);

		String mockTokenUrl = randomWord();
		String mockClientId = randomWord();
		String mockClientSecret = randomWord();
		when(mockServiceKey.getTokenURL()).thenReturn(mockTokenUrl);
		when(mockServiceKey.getClientId()).thenReturn(mockClientId);
		when(mockServiceKey.getClientSecret()).thenReturn(mockClientSecret);

		URI mockEndpoint = mock(URI.class);
		when(mockAuthClientHelper.createAuthUri(mockTokenUrl)).thenReturn(mockEndpoint);
		BodyPublisher mockRequestBody = mock(BodyPublisher.class);
		when(mockAuthClientHelper.createRequestBody(mockClientId, mockClientSecret)).thenReturn(mockRequestBody);

		// HttpClient.newHttpClient
		HttpClient mockHttpClient = mock(HttpClient.class);
		try (MockedStatic<HttpClient> httpClientStatic = mockStatic(HttpClient.class)) {
			httpClientStatic.when(() -> HttpClient.newHttpClient()).thenReturn(mockHttpClient);
			
			// HttpRequest.newBuilder
			HttpRequest.Builder mockHttpRequestBuilder = mock(HttpRequest.Builder.class);
			try (MockedStatic<HttpRequest> httpRequestStatic = mockStatic(HttpRequest.class)) {
				httpRequestStatic.when(() -> HttpRequest.newBuilder()).thenReturn(mockHttpRequestBuilder);
				
				when(mockHttpRequestBuilder.uri(mockEndpoint)).thenReturn(mockHttpRequestBuilder);
				when(mockHttpRequestBuilder.header("Content-Type", "application/x-www-form-urlencoded")).thenReturn(mockHttpRequestBuilder);
				when(mockHttpRequestBuilder.POST(mockRequestBody)).thenReturn(mockHttpRequestBuilder);
				
				HttpRequest mockHttpRequest = mock(HttpRequest.class);
				when(mockHttpRequestBuilder.build()).thenReturn(mockHttpRequest);
				
				HttpResponse<String> mockHttpResponse = mock(HttpResponse.class);
				when(mockHttpClient.send(eq(mockHttpRequest), any(HttpResponse.BodyHandler.class))).thenReturn(mockHttpResponse);
				
				String mockResponseBody = randomWord();
				when(mockHttpResponse.body()).thenReturn(mockResponseBody);
				
				AccessToken mockNewAccessToken = mock(AccessToken.class);
				String expectedValue = "new-super-secret";
				when(mockNewAccessToken.getAccessToken()).thenReturn(expectedValue);
				when(mockAuthClientHelper.convertResponseToObject(mockResponseBody)).thenReturn(mockNewAccessToken);
				
				// Act
				String returnValue = cut.getAccessToken();
				
				// Assert
				verify(mockHttpRequestBuilder).uri(mockEndpoint);
				verify(mockHttpRequestBuilder).header("Content-Type", "application/x-www-form-urlencoded");
				verify(mockHttpRequestBuilder).POST(mockRequestBody);
				verify(mockMemoryAccessToken).save(mockNewAccessToken);
				assertEquals(returnValue, expectedValue);
			}
		}
	}
	// CHECKSTYLE:ON

}
