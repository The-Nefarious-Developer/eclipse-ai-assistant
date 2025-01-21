package com.developer.nefarious.zjoule.test.login.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.zjoule.plugin.login.api.GetOllamaModelsResponse;
import com.developer.nefarious.zjoule.plugin.login.api.IOllamaLoginClient;
import com.developer.nefarious.zjoule.plugin.login.api.IOllamaLoginClientHelper;
import com.developer.nefarious.zjoule.plugin.login.api.OllamaLoginClient;

public class OllamaLoginClientTest {
	
	private IOllamaLoginClient cut;

	MockedStatic<HttpClient> mockedStaticHttpClient;

	MockedStatic<HttpRequest> mockedStaticHttpRequest;

	@Mock
	private HttpClient mockHttpClient;

	@Mock
	private IOllamaLoginClientHelper mockOllamaLoginClientHelper;

	@Mock
	private Builder mockBuilder;

	@Mock
	private HttpRequest mockHttpRequest;

	@Mock
	private HttpResponse mockHttpResponse;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		mockedStaticHttpClient = mockStatic(HttpClient.class);
		mockedStaticHttpClient.when(HttpClient::newHttpClient).thenReturn(mockHttpClient);

		mockedStaticHttpRequest = mockStatic(HttpRequest.class);
		mockedStaticHttpRequest.when(HttpRequest::newBuilder).thenReturn(mockBuilder);

		cut = spy(new OllamaLoginClient(mockOllamaLoginClientHelper));
	}

	@Test
	public void shouldPlumbModels() throws IOException, InterruptedException {
		// Arrange
		GetOllamaModelsResponse expectedValue = mock(GetOllamaModelsResponse.class);

		String mockOllamaUrl = "https://some-url.com";

		String mockEndpointInStringFormat = mockOllamaUrl + "/api/tags";
		URI mockEndpointInURIFormat = mock(URI.class);
		when(mockOllamaLoginClientHelper.createUri(mockEndpointInStringFormat)).thenReturn(mockEndpointInURIFormat);
		when(mockBuilder.uri(mockEndpointInURIFormat)).thenReturn(mockBuilder);

		when(mockBuilder.GET()).thenReturn(mockBuilder);
		when(mockBuilder.build()).thenReturn(mockHttpRequest);
		when(mockHttpClient.send(mockHttpRequest, HttpResponse.BodyHandlers.ofString())).thenReturn(mockHttpResponse);

		String mockResponseBody = "response-content";
		when(mockHttpResponse.body()).thenReturn(mockResponseBody);
		when(mockOllamaLoginClientHelper.parseOllamaModelsResponseToObject(mockResponseBody)).thenReturn(expectedValue);

		// Act
		GetOllamaModelsResponse returnValue = cut.getModels(mockOllamaUrl);

		// Assert
		assertEquals(expectedValue, returnValue);
		verify(mockBuilder).uri(mockEndpointInURIFormat);
		verify(mockBuilder).GET();
	}

	@AfterEach
	public void tearDown() {
		if (mockedStaticHttpClient != null) {
			mockedStaticHttpClient.close();
		}
		if (mockedStaticHttpRequest != null) {
			mockedStaticHttpRequest.close();
		}
	}

}
