package com.developer.nefarious.zjoule.test.login.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

import java.net.URI;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import com.developer.nefarious.zjoule.plugin.login.api.GetOllamaModelsResponse;
import com.developer.nefarious.zjoule.plugin.login.api.OllamaLoginClientHelper;
import com.google.gson.Gson;

public class OllamaLoginClientHelperTest {
	
	private OllamaLoginClientHelper cut;

	private String randomWord() {
		final String[] WORDS = { "apple", "banana", "grape" };
		int randomIndex = ThreadLocalRandom.current().nextInt(WORDS.length);
		return WORDS[randomIndex];
	}

	@BeforeEach
	public void setUp() {
		cut = new OllamaLoginClientHelper();
	}

	@Test
	public void shouldConvertTheModelsResponseBodyToObject() {
		// Arrange
		Gson gson = new Gson();
		String mockResponseBody = "{\"models\": [{\"name\": \"llama3.2:latest\"}]}";
		GetOllamaModelsResponse expectedObject = gson.fromJson(mockResponseBody, GetOllamaModelsResponse.class);
		// Act
		GetOllamaModelsResponse returnObject = cut.parseOllamaModelsResponseToObject(mockResponseBody);
		// Assert
		assertEquals(returnObject.getModels().getFirst().getName(), expectedObject.getModels().getFirst().getName());
	}

	@Test
	public void shouldCreateTheUri() {
		// Arrange
		URI expectedObject = mock(URI.class);
		String mockEndpoint = randomWord();
		try (MockedStatic<URI> uriStatic = mockStatic(URI.class)) {
			uriStatic.when(() -> URI.create(mockEndpoint)).thenReturn(expectedObject);
			// Act
			URI returnObject = cut.createUri(mockEndpoint);
			// Assert
			assertEquals(returnObject, expectedObject);
		}
	}

}
