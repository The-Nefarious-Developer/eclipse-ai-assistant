package com.developer.nefarious.zjoule.test.chat;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.zjoule.plugin.auth.SessionManager;
import com.developer.nefarious.zjoule.plugin.chat.AIClientFactory;
import com.developer.nefarious.zjoule.plugin.chat.IAIClient;
import com.developer.nefarious.zjoule.plugin.memory.MemoryOllamaEndpoint;
import com.developer.nefarious.zjoule.plugin.memory.MemoryOllamaModel;

public class AiClientFactoryOthersTest {
	
	private MockedStatic<SessionManager> mockStaticSessionManager;
	
	private MockedStatic<MemoryOllamaEndpoint> mockStaticMemoryOllamaEndpoint;
	
	private MockedStatic<MemoryOllamaModel> mockStaticMemoryOllamaModel;
	
	@Mock
	private MemoryOllamaEndpoint memoryOllamaEndpoint;
	
	@Mock
	private MemoryOllamaModel memoryOllamaModel;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		mockStaticSessionManager = mockStatic(SessionManager.class);
		mockStaticMemoryOllamaEndpoint = mockStatic(MemoryOllamaEndpoint.class);
		mockStaticMemoryOllamaModel = mockStatic(MemoryOllamaModel.class);
		
		mockStaticMemoryOllamaEndpoint.when(MemoryOllamaEndpoint::getInstance).thenReturn(memoryOllamaEndpoint);
		mockStaticMemoryOllamaModel.when(MemoryOllamaModel::getInstance).thenReturn(memoryOllamaModel);
		
		mockStaticSessionManager.when(SessionManager::isOllamaSession).thenReturn(false);
		mockStaticSessionManager.when(SessionManager::isSapSession).thenReturn(false);
	}
	
	@AfterEach
	public void tearDown() {
		if (mockStaticSessionManager != null) {
			mockStaticSessionManager.close();
		}
		if (mockStaticMemoryOllamaEndpoint != null) {
			mockStaticMemoryOllamaEndpoint.close();
		}
		if (mockStaticMemoryOllamaModel != null) {
			mockStaticMemoryOllamaModel.close();
		}
	}
	
	@Test
	public void shouldReturnNullForOthers() {
		// Arrange
		// Act
		IAIClient returnValue = AIClientFactory.getClient();
		// Assert
		assertNull(returnValue);
	}

}
