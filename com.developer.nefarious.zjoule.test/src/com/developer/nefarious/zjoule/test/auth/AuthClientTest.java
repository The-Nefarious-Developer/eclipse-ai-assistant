package com.developer.nefarious.zjoule.test.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.zjoule.auth.AccessToken;
import com.developer.nefarious.zjoule.auth.AuthClient;
import com.developer.nefarious.zjoule.login.ServiceKey;
import com.developer.nefarious.zjoule.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.memory.MemoryServiceKey;

public class AuthClientTest {
	
	private AuthClient cut;
	
	@Mock private MemoryAccessToken mockMemoryAccessToken;
	@Mock private MemoryServiceKey mockMemoryServiceKey;
	@Mock private AccessToken mockAccessToken;
	@Mock private ServiceKey mockServiceKey;
	
	private String randomWord() {
		final String[] WORDS = { "apple", "banana", "grape" };
		int randomIndex = ThreadLocalRandom.current().nextInt(WORDS.length);
		return WORDS[randomIndex];
	}
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		cut = new AuthClient(mockMemoryAccessToken, mockMemoryServiceKey);
	}
	
	@Test
	public void shouldReturnAccessTokenFromMemory() {
		// Arrange
		String expectedValue = "super-secret-from-memory";
		when(mockMemoryAccessToken.load()).thenReturn(mockAccessToken);
		when(mockAccessToken.getAccessToken()).thenReturn(expectedValue);
		when(mockAccessToken.isValid()).thenReturn(true);
		// Act
		String returnValue = cut.getAccessToken();
		// Assert
		assertEquals(returnValue, expectedValue);
	}
	
	@Test
	public void shouldRequestNewTokenIfMemoryIsInvalid() {
		// Arrange
		when(mockMemoryAccessToken.load()).thenReturn(mockAccessToken);
		when(mockAccessToken.isValid()).thenReturn(false);
		
		String mockTokenUrl = randomWord();
		String mockClientId = randomWord();
		String mockClientSecret = randomWord();
		when(mockMemoryServiceKey.load()).thenReturn(mockServiceKey);
		when(mockServiceKey.getTokenURL()).thenReturn(mockTokenUrl);
		when(mockServiceKey.getClientId()).thenReturn(mockClientId);
		when(mockServiceKey.getClientSecret()).thenReturn(mockClientSecret);
		
		String mockRequestBody = "grant_type=client_credentials" 
			+ "&client_id=" + URLEncoder.encode(mockServiceKey.getClientId(), StandardCharsets.UTF_8) 
			+ "&client_secret="	+ URLEncoder.encode(mockServiceKey.getClientSecret(), StandardCharsets.UTF_8);
		
		// Setup HttpClient.newHttpClient
		HttpClient mockHttpClient = mock(HttpClient.class);
		try (MockedStatic<HttpClient> httpClientStatic = mockStatic(HttpClient.class)) {
			httpClientStatic.when(() -> HttpClient.newHttpClient()).thenReturn(mockHttpClient);
			
			// Setup HttpRequest.newBuilder
			HttpRequest.Builder mockRequestBuilder = mock(HttpRequest.Builder.class);
			try (MockedStatic<HttpRequest> httpRequestStatic = mockStatic(HttpRequest.class)) {
				httpRequestStatic.when(() -> HttpRequest.newBuilder()).thenReturn(mockRequestBuilder);
				
				// Setup URI.create
				URI mockUri = mock(URI.class);
				try (MockedStatic<URI> uriStatic = mockStatic(URI.class)) {
					uriStatic.when(() -> URI.create(mockTokenUrl)).thenReturn(mockUri);
				
					BodyPublisher mockBodyPublisher = mock(BodyPublisher.class);
					try (MockedStatic<BodyPublishers> bodyPublishersStatic = mockStatic(BodyPublishers.class)) {
						bodyPublishersStatic.when(() -> BodyPublishers.ofString(mockRequestBody)).thenReturn(mockBodyPublisher);
						
						when(mockRequestBuilder.uri(mockUri)).thenReturn(mockRequestBuilder);
						when(mockRequestBuilder.header("Content-Type", "application/x-www-form-urlencoded")).thenReturn(mockRequestBuilder);
						when(mockRequestBuilder.POST(mockBodyPublisher)).thenReturn(mockRequestBuilder);
				
						HttpRequest mockRequest = mock(HttpRequest.class);
						when(mockRequestBuilder.build()).thenReturn(mockRequest);
				
						// Act
						String returnValue = cut.getAccessToken();
			
						// Assert
						verify(mockRequestBuilder).uri(mockUri);
						verify(mockRequestBuilder).header("Content-Type", "application/x-www-form-urlencoded");
						verify(mockRequestBuilder).POST(mockBodyPublisher);
						assertTrue(true);
					}
				}
			
			}
			
		}

	}
	
}
