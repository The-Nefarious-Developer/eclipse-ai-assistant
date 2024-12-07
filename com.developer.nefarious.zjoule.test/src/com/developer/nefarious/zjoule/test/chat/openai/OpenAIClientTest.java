package com.developer.nefarious.zjoule.test.chat.openai;

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
import java.net.http.HttpResponse;
import java.util.List;
import java.net.http.HttpRequest.BodyPublisher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.auth.AuthClient;
import com.developer.nefarious.zjoule.chat.openai.IOpenAIClientHelper;
import com.developer.nefarious.zjoule.chat.openai.OpenAIClient;
import com.developer.nefarious.zjoule.chat.openai.OpenAIMessage;
import com.developer.nefarious.zjoule.memory.IMemoryDeployment;
import com.developer.nefarious.zjoule.memory.IMemoryResourceGroup;

public class OpenAIClientTest {

	private OpenAIClient cut;

	MockedStatic<HttpRequest> mockHttpRequest;
	
	MockedStatic<URI> mockURI;
	
	@Mock
	private AuthClient mockAuthClient;

	@Mock
	private HttpClient mockHttpClient;
	
	@Mock
	private IMemoryResourceGroup mockMemoryResourceGroup;
	
	@Mock
	private IMemoryDeployment mockMemoryDeployment;
	
	@Mock
	private IOpenAIClientHelper mockOpenAIClientHelper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		mockHttpRequest = mockStatic(HttpRequest.class);
		mockURI = mockStatic(URI.class);
		
		// @formatter:off
		cut = new OpenAIClient(
				mockAuthClient, 
				mockHttpClient, 
				mockMemoryResourceGroup, 
				mockMemoryDeployment, 
				mockOpenAIClientHelper);
		// @formatter:on
	}
	
	@AfterEach
	public void tearDown() {
		if (mockHttpRequest != null) {
			mockHttpRequest.close();
        }
		if (mockURI != null) {
			mockURI.close();
        }
	}
	// CHECKSTYLE:OFF: MethodLength
	@Test
	public void shouldPlumbChatCompletion() throws IOException, InterruptedException {
		// CHECKSTYLE:ON
		// Arrange
		HttpRequest.Builder mockHttpRequestBuilder = mock(HttpRequest.Builder.class);
		mockHttpRequest.when(HttpRequest::newBuilder).thenReturn(mockHttpRequestBuilder);
		
		String mockDeploymentId = "sap-ai-core-deployment-id";
		when(mockMemoryDeployment.load()).thenReturn(mockDeploymentId);
		
		String mockServiceUrl = "https://something.com";
		when(mockAuthClient.getServiceUrl()).thenReturn(mockServiceUrl);
		
		URI mockEndpoint = mock(URI.class);
		mockURI.when(() -> URI.create(mockServiceUrl + "/inference/deployments/" + mockDeploymentId + "/chat/completions?api-version=2023-05-15"))
			.thenReturn(mockEndpoint);
		
		String mockToken = "token";
		when(mockAuthClient.getAccessToken()).thenReturn(mockToken);
		
		String mockResourceGroup = "sap-ai-core-resource-group";
		when(mockMemoryResourceGroup.load()).thenReturn(mockResourceGroup);
		
		List<OpenAIMessage> messages = mock(List.class);
		BodyPublisher mockRequestBody = mock(BodyPublisher.class);
		when(mockOpenAIClientHelper.createRequestBody(messages)).thenReturn(mockRequestBody);
		
		HttpRequest mockHttpRequest = mock(HttpRequest.class);
		when(mockHttpRequestBuilder.build()).thenReturn(mockHttpRequest);
		
		HttpResponse<String> mockHttpResponse = mock(HttpResponse.class);
		when(mockHttpClient.send(eq(mockHttpRequest), eq(HttpResponse.BodyHandlers.ofString())))
			.thenReturn(mockHttpResponse);
		
		String mockResponseBody = "response-body-in-string-format";
		when(mockHttpResponse.body()).thenReturn(mockResponseBody);
		
		String expectedValue = "content-of-the-response";
		when(mockOpenAIClientHelper.convertResponseToObject(mockResponseBody)).thenReturn(expectedValue);
		
		when(mockHttpRequestBuilder.uri(any())).thenReturn(mockHttpRequestBuilder);
		when(mockHttpRequestBuilder.header(any(), any())).thenReturn(mockHttpRequestBuilder);
		when(mockHttpRequestBuilder.POST(any())).thenReturn(mockHttpRequestBuilder);

		// Act
		String returnValue = cut.chatCompletion(messages);

		// Assert
		verify(mockHttpRequestBuilder).uri(mockEndpoint);
		verify(mockHttpRequestBuilder).header("Authorization", "Bearer " + mockToken);
		verify(mockHttpRequestBuilder).header("AI-Resource-Group", mockResourceGroup);
		verify(mockHttpRequestBuilder).POST(mockRequestBody);
		assertEquals(returnValue, expectedValue);
	}
	// CHECKSTYLE:ON: MethodLength

}
