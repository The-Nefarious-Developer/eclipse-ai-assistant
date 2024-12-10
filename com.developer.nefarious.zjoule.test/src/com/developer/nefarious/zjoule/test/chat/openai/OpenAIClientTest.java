package com.developer.nefarious.zjoule.test.chat.openai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
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
import java.util.Arrays;
import java.util.List;
import java.net.http.HttpRequest.BodyPublisher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.auth.AuthClient;
import com.developer.nefarious.zjoule.chat.IChatMessage;
import com.developer.nefarious.zjoule.chat.models.Message;
import com.developer.nefarious.zjoule.chat.models.MessageHistory;
import com.developer.nefarious.zjoule.chat.memory.IMemoryMessageHistory;
import com.developer.nefarious.zjoule.chat.openai.IOpenAIClientHelper;
import com.developer.nefarious.zjoule.chat.openai.OpenAIClient;
import com.developer.nefarious.zjoule.chat.openai.OpenAIChatMessage;
import com.developer.nefarious.zjoule.memory.IMemoryDeployment;
import com.developer.nefarious.zjoule.memory.IMemoryResourceGroup;
import com.developer.nefarious.zjoule.models.Deployment;
import com.developer.nefarious.zjoule.models.Role;

public class OpenAIClientTest {

	private OpenAIClient cut;
	
	MockedStatic<HttpClient> mockStaticHttpClient;

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
	private IMemoryMessageHistory mockMemoryMessageHistory;
	
	@Mock
	private IOpenAIClientHelper mockOpenAIClientHelper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		mockHttpRequest = mockStatic(HttpRequest.class);
		mockURI = mockStatic(URI.class);
		mockStaticHttpClient = mockStatic(HttpClient.class);
		
		mockStaticHttpClient.when(HttpClient::newHttpClient).thenReturn(mockHttpClient);
		
		// @formatter:off
		cut = new OpenAIClient(
				mockAuthClient,
				mockMemoryMessageHistory,
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
		if (mockStaticHttpClient != null) {
			mockStaticHttpClient.close();
		}
	}
	
	// CHECKSTYLE:OFF: MethodLength
	@Test
	public void shouldPlumbChatCompletion() throws IOException, InterruptedException {
		// CHECKSTYLE:ON
		// Arrange
		HttpRequest.Builder mockHttpRequestBuilder = mock(HttpRequest.Builder.class);
		mockHttpRequest.when(HttpRequest::newBuilder).thenReturn(mockHttpRequestBuilder);
		
		Deployment mockDeployment = mock(Deployment.class);
		when(mockMemoryDeployment.load()).thenReturn(mockDeployment);
		
		String mockDeploymentId = "sap-ai-core-deployment-id";
		when(mockDeployment.getId()).thenReturn(mockDeploymentId);
		
		String mockServiceUrl = "https://something.com";
		when(mockAuthClient.getServiceUrl()).thenReturn(mockServiceUrl);
		
		String mockEndpointInStringFormat = mockServiceUrl + "/inference/deployments/" + mockDeploymentId + "/chat/completions?api-version=2023-05-15";
		URI mockEndpointInURIFormat = mock(URI.class);
		mockURI.when(() -> URI.create(mockEndpointInStringFormat)).thenReturn(mockEndpointInURIFormat);
		
		String mockToken = "token";
		when(mockAuthClient.getAccessToken()).thenReturn(mockToken);
		
		String mockResourceGroup = "sap-ai-core-resource-group";
		when(mockMemoryResourceGroup.load()).thenReturn(mockResourceGroup);
		
		List<OpenAIChatMessage> messages = mock(List.class);
		BodyPublisher mockRequestBody = mock(BodyPublisher.class);
		when(mockOpenAIClientHelper.createRequestBody(messages)).thenReturn(mockRequestBody);
		
		HttpRequest mockHttpRequest = mock(HttpRequest.class);
		when(mockHttpRequestBuilder.build()).thenReturn(mockHttpRequest);
		
		HttpResponse<String> mockHttpResponse = mock(HttpResponse.class);
		when(mockHttpClient.send(eq(mockHttpRequest), eq(HttpResponse.BodyHandlers.ofString()))).thenReturn(mockHttpResponse);
		
		String mockResponseBody = "response-body-in-string-format";
		when(mockHttpResponse.body()).thenReturn(mockResponseBody);
		
		OpenAIChatMessage expectedValue = mock(OpenAIChatMessage.class);
		when(mockOpenAIClientHelper.convertResponseToObject(mockResponseBody)).thenReturn(expectedValue);
		
		when(mockHttpRequestBuilder.uri(any())).thenReturn(mockHttpRequestBuilder);
		when(mockHttpRequestBuilder.header(any(), any())).thenReturn(mockHttpRequestBuilder);
		when(mockHttpRequestBuilder.POST(any())).thenReturn(mockHttpRequestBuilder);

		// Act
		IChatMessage returnValue = cut.chatCompletion(messages);

		// Assert
		verify(mockHttpRequestBuilder).uri(mockEndpointInURIFormat);
		verify(mockHttpRequestBuilder).header("Authorization", "Bearer " + mockToken);
		verify(mockHttpRequestBuilder).header("AI-Resource-Group", mockResourceGroup);
		verify(mockHttpRequestBuilder).POST(mockRequestBody);
		assertEquals(returnValue, expectedValue);
	}
	// CHECKSTYLE:ON: MethodLength
	
	@Test
	public void shouldCreateMessage() {
		// Arrange
		String expectedValue = "some-random-user-prompt";
		Role randomRole = Role.USER;
		// Act
		IChatMessage returnValue = cut.createMessage(randomRole, expectedValue);
		// Assert
		assertEquals(returnValue.getMessage(), expectedValue);
		assertInstanceOf(OpenAIChatMessage.class, returnValue);
	}
	
	@Test
	public void shouldSetMessageHistory() {
		// Arrange
		Role expectedRole1 = Role.ASSISTANT;
		Role expectedRole2 = Role.USER;
		String expectedMessageContent1 = "value-1";
		String expectedMessageContent2 = "value-2";
		
		List<IChatMessage> mockChatMessages = Arrays.asList(
				new OpenAIChatMessage(expectedRole1, expectedMessageContent1), 
				new OpenAIChatMessage(expectedRole2, expectedMessageContent2));
		// Act
		cut.setMessageHistory(mockChatMessages);
		// Assert
		ArgumentCaptor<MessageHistory> captor = ArgumentCaptor.forClass(MessageHistory.class);
		verify(mockMemoryMessageHistory).save(captor.capture());
		
		List<Message> capturedMessages = captor.getValue().getMessages();
		assertEquals(2, capturedMessages.size());
		assertEquals(expectedRole1, capturedMessages.get(0).getRole());
		assertEquals(expectedRole2, capturedMessages.get(1).getRole());
		assertEquals(expectedMessageContent1, capturedMessages.get(0).getContent());
		assertEquals(expectedMessageContent2, capturedMessages.get(1).getContent());
		
	}
	
	@Test
	public void shouldGetMessageHistory() {
		// Arrange
		Role expectedRole1 = Role.ASSISTANT;
		Role expectedRole2 = Role.USER;
		String expectedMessageContent1 = "value-1";
		String expectedMessageContent2 = "value-2";
		
		MessageHistory mockMessageHistory = new MessageHistory();
		mockMessageHistory.setMessages(Arrays.asList(
				new Message(expectedRole1, expectedMessageContent1),
				new Message(expectedRole2, expectedMessageContent2)));
		
		when(mockMemoryMessageHistory.load()).thenReturn(mockMessageHistory);
		
		// Act
		List<IChatMessage> returnValue = cut.getMessageHistory();
		
		// Assert
		IChatMessage returnMessage1 = returnValue.getFirst();
		assertEquals(returnMessage1.getRole(), expectedRole1);
		assertEquals(returnMessage1.getMessage(), expectedMessageContent1);
		
		IChatMessage returnMessage2 = returnValue.getLast();
		assertEquals(returnMessage2.getRole(), expectedRole2);
		assertEquals(returnMessage2.getMessage(), expectedMessageContent2);
	}
	
	

}
