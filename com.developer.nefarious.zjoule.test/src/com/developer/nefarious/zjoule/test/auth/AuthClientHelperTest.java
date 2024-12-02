package com.developer.nefarious.zjoule.test.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import com.developer.nefarious.zjoule.auth.AuthClientHelper;
import com.developer.nefarious.zjoule.models.AccessToken;
import com.google.gson.Gson;

public class AuthClientHelperTest {

	private AuthClientHelper cut;

	private String randomWord() {
		final String[] WORDS = { "apple", "banana", "grape" };
		int randomIndex = ThreadLocalRandom.current().nextInt(WORDS.length);
		return WORDS[randomIndex];
	}

	@BeforeEach
	public void setUp() {
		cut = new AuthClientHelper();
	}

	@Test
	public void shouldCreateTheRequestBody() {
		// Arrange
		String mockClientId = randomWord();
		String mockClientSecret = randomWord();
		BodyPublisher expectedObject = mock(BodyPublisher.class);
		// @formatter:off
		String mockRequestBody = "grant_type=client_credentials" 
				+ "&client_id="	+ URLEncoder.encode(mockClientId, StandardCharsets.UTF_8) 
				+ "&client_secret="	+ URLEncoder.encode(mockClientSecret, StandardCharsets.UTF_8);
		// @formatter:on
		try (MockedStatic<BodyPublishers> bodyPublishersStatic = mockStatic(BodyPublishers.class)) {
			bodyPublishersStatic.when(() -> BodyPublishers.ofString(mockRequestBody)).thenReturn(expectedObject);
			// Act
			BodyPublisher returnObject = cut.createRequestBody(mockClientId, mockClientSecret);
			// Assert
			assertEquals(returnObject, expectedObject);
		}
	}

	@Test
	public void shouldCreateTheAuthUri() {
		// Arrange
		URI expectedObject = mock(URI.class);
		String mockTokenEndpoint = randomWord();
		try (MockedStatic<URI> uriStatic = mockStatic(URI.class)) {
			uriStatic.when(() -> URI.create(mockTokenEndpoint)).thenReturn(expectedObject);
			// Act
			URI returnObject = cut.createAuthUri(mockTokenEndpoint);
			// Assert
			assertEquals(returnObject, expectedObject);
		}
	}

	@Test
	public void shouldConvertTheResponseBodyToObject() {
		// Arrange
		Gson gson = new Gson();
		String mockResponseBody = "{ \"access_token\": \"some-value\" }";
		AccessToken expectedObject = gson.fromJson(mockResponseBody, AccessToken.class);
		// Act
		AccessToken returnObject = cut.convertResponseToObject(mockResponseBody);
		// Assert
		assertEquals(returnObject.getAccessToken(), expectedObject.getAccessToken());
	}

}
