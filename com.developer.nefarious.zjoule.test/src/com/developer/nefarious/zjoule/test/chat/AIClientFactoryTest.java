package com.developer.nefarious.zjoule.test.chat;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.plugin.chat.AIClientFactory;
import com.developer.nefarious.zjoule.plugin.chat.IAIClient;
import com.developer.nefarious.zjoule.plugin.chat.memory.MemoryMessageHistory;
import com.developer.nefarious.zjoule.plugin.chat.openai.OpenAIClient;
import com.developer.nefarious.zjoule.plugin.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.plugin.memory.MemoryDeployment;
import com.developer.nefarious.zjoule.plugin.memory.MemoryResourceGroup;
import com.developer.nefarious.zjoule.plugin.memory.MemoryServiceKey;
import com.developer.nefarious.zjoule.plugin.models.Deployment;

public class AIClientFactoryTest {
		
	private MockedStatic<MemoryAccessToken> mockStaticMemoryAccessToken;
	
	private MockedStatic<MemoryServiceKey> mockStaticMemoryServiceKey;
	
	private MockedStatic<MemoryResourceGroup> mockStaticMemoryResourceGroup;
	
	private MockedStatic<MemoryDeployment> mockStaticMemoryDeployment;
	
	private MockedStatic<MemoryMessageHistory> mockStaticMemoryMessageHistory;
	
	@Mock
	private MemoryAccessToken mockMemoryAccessToken;
	
	@Mock
	private MemoryServiceKey mockMemoryServiceKey;
	
	@Mock
	private MemoryResourceGroup mockMemoryResourceGroup;

	@Mock
	private MemoryDeployment mockMemoryDeployment;
	
	@Mock
	private MemoryMessageHistory mockMemoryMessageHistory;
	
	@Mock
	private Deployment mockDeployment;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		mockStaticMemoryAccessToken = mockStatic(MemoryAccessToken.class);
		mockStaticMemoryServiceKey = mockStatic(MemoryServiceKey.class);
		mockStaticMemoryResourceGroup = mockStatic(MemoryResourceGroup.class);
		mockStaticMemoryDeployment = mockStatic(MemoryDeployment.class);
		mockStaticMemoryMessageHistory = mockStatic(MemoryMessageHistory.class);
		
		mockStaticMemoryAccessToken.when(MemoryAccessToken::getInstance).thenReturn(mockMemoryAccessToken);
		mockStaticMemoryServiceKey.when(MemoryServiceKey::getInstance).thenReturn(mockMemoryServiceKey);
		mockStaticMemoryResourceGroup.when(MemoryResourceGroup::getInstance).thenReturn(mockMemoryResourceGroup);
		mockStaticMemoryDeployment.when(MemoryDeployment::getInstance).thenReturn(mockMemoryDeployment);
		mockStaticMemoryMessageHistory.when(MemoryMessageHistory::getInstance).thenReturn(mockMemoryMessageHistory);
		
		when(mockMemoryDeployment.load()).thenReturn(mockDeployment);
	}
	
	@AfterEach
	public void tearDown() {
		if (mockStaticMemoryAccessToken != null) {
			mockStaticMemoryAccessToken.close();
		}
		if (mockStaticMemoryServiceKey != null) {
			mockStaticMemoryServiceKey.close();
		}
		if (mockStaticMemoryResourceGroup != null) {
			mockStaticMemoryResourceGroup.close();
		}
		if (mockStaticMemoryDeployment != null) {
			mockStaticMemoryDeployment.close();
		}
		if (mockStaticMemoryMessageHistory != null) {
			mockStaticMemoryMessageHistory.close();
		}
	}
	
	@Test
	public void shouldReturnNullForOthers() {
		// Arrange
		when(mockDeployment.getModelName()).thenReturn("potato");
		// Act
		IAIClient returnValue = AIClientFactory.getClient();
		// Assert
		assertNull(returnValue);
	}
	
	@Test
	public void shouldReturnOpenAIForGPT4() {
		// Arrange
		when(mockDeployment.getModelName()).thenReturn("gpt-4");
		// Act
		IAIClient returnValue = AIClientFactory.getClient();
		// Assert
		assertInstanceOf(OpenAIClient.class, returnValue);
	}
	
	@Test
	public void shouldReturnOpenAIForGPT432K() {
		// Arrange
		when(mockDeployment.getModelName()).thenReturn("gpt-4-32k");
		// Act
		IAIClient returnValue = AIClientFactory.getClient();
		// Assert
		assertInstanceOf(OpenAIClient.class, returnValue);
	}
	
	@Test
	public void shouldReturnOpenAIForGPT35Turbo() {
		// Arrange
		when(mockDeployment.getModelName()).thenReturn("gpt-35-turbo");
		// Act
		IAIClient returnValue = AIClientFactory.getClient();
		// Assert
		assertInstanceOf(OpenAIClient.class, returnValue);
	}
	
	@Test
	public void shouldReturnOpenAIForGPT35Turbo16k() {
		// Arrange
		when(mockDeployment.getModelName()).thenReturn("gpt-35-turbo-16k");
		// Act
		IAIClient returnValue = AIClientFactory.getClient();
		// Assert
		assertInstanceOf(OpenAIClient.class, returnValue);
	}

}
