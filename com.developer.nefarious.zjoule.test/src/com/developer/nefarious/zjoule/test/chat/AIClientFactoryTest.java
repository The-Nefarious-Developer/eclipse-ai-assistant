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
import com.developer.nefarious.zjoule.chat.AIClientFactory;
import com.developer.nefarious.zjoule.chat.IAIClient;
import com.developer.nefarious.zjoule.chat.openai.OpenAIClient;
import com.developer.nefarious.zjoule.memory.MemoryDeployment;
import com.developer.nefarious.zjoule.models.Deployment;

public class AIClientFactoryTest {
	
	MockedStatic<MemoryDeployment> mockStaticMemoryDeployment;
	
	@Mock
	private Deployment mockDeployment;
	
	@Mock
	private MemoryDeployment mockMemoryDeployment;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		mockStaticMemoryDeployment = mockStatic(MemoryDeployment.class);
	}
	
	@AfterEach
	public void tearDown() {
		if (mockStaticMemoryDeployment != null) {
			mockStaticMemoryDeployment.close();
		}
	}
	
	@Test
	public void shouldReturnNullForOthers() {
		// Arrange
		mockStaticMemoryDeployment.when(MemoryDeployment::getInstance).thenReturn(mockMemoryDeployment);
		when(mockMemoryDeployment.load()).thenReturn(mockDeployment);
		when(mockDeployment.getModelName()).thenReturn("potato");
		// Act
		IAIClient<?> returnValue = AIClientFactory.getClient();
		// Assert
		assertNull(returnValue);
	}
	
	@Test
	public void shouldReturnOpenAIForGPT4() {
		// Arrange
		mockStaticMemoryDeployment.when(MemoryDeployment::getInstance).thenReturn(mockMemoryDeployment);
		when(mockMemoryDeployment.load()).thenReturn(mockDeployment);
		when(mockDeployment.getModelName()).thenReturn("gpt-4");
		// Act
		IAIClient<?> returnValue = AIClientFactory.getClient();
		// Assert
		assertInstanceOf(OpenAIClient.class, returnValue);
	}
	
	@Test
	public void shouldReturnOpenAIForGPT432K() {
		// Arrange
		mockStaticMemoryDeployment.when(MemoryDeployment::getInstance).thenReturn(mockMemoryDeployment);
		when(mockMemoryDeployment.load()).thenReturn(mockDeployment); 
		when(mockDeployment.getModelName()).thenReturn("gpt-4-32k");
		// Act
		IAIClient<?> returnValue = AIClientFactory.getClient();
		// Assert
		assertInstanceOf(OpenAIClient.class, returnValue);
	}
	
	@Test
	public void shouldReturnOpenAIForGPT35Turbo() {
		// Arrange
		mockStaticMemoryDeployment.when(MemoryDeployment::getInstance).thenReturn(mockMemoryDeployment);
		when(mockMemoryDeployment.load()).thenReturn(mockDeployment);
		when(mockDeployment.getModelName()).thenReturn("gpt-35-turbo");
		// Act
		IAIClient<?> returnValue = AIClientFactory.getClient();
		// Assert
		assertInstanceOf(OpenAIClient.class, returnValue);
	}
	
	@Test
	public void shouldReturnOpenAIForGPT35Turbo16k() {
		// Arrange
		mockStaticMemoryDeployment.when(MemoryDeployment::getInstance).thenReturn(mockMemoryDeployment);
		when(mockMemoryDeployment.load()).thenReturn(mockDeployment);
		when(mockDeployment.getModelName()).thenReturn("gpt-35-turbo-16k");
		// Act
		IAIClient<?> returnValue = AIClientFactory.getClient();
		// Assert
		assertInstanceOf(OpenAIClient.class, returnValue);
	}

}
