package com.developer.nefarious.zjoule.test.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.auth.SessionManager;
import com.developer.nefarious.zjoule.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.memory.MemoryDeployment;
import com.developer.nefarious.zjoule.memory.MemoryResourceGroup;
import com.developer.nefarious.zjoule.memory.MemoryServiceKey;

public class SessionManagerTest {
	
	private MockedStatic<MemoryAccessToken> mockedStaticMemoryAccessToken;
	
	private MockedStatic<MemoryServiceKey> mockedStaticMemoryServiceKey;
	
	private MockedStatic<MemoryResourceGroup> mockedStaticMemoryResourceGroup;
	
	private MockedStatic<MemoryDeployment> mockedStaticMemoryDeployment;
	
	@Mock
	private MemoryAccessToken mockMemoryAccessToken;
	
	@Mock
	private MemoryServiceKey mockMemoryServiceKey;
	
	@Mock
	private MemoryResourceGroup mockMemoryResourceGroup;
	
	@Mock
	private MemoryDeployment mockMemoryDeployment;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		mockedStaticMemoryAccessToken = mockStatic(MemoryAccessToken.class);
		mockedStaticMemoryServiceKey = mockStatic(MemoryServiceKey.class);
		mockedStaticMemoryResourceGroup = mockStatic(MemoryResourceGroup.class);
		mockedStaticMemoryDeployment = mockStatic(MemoryDeployment.class);
		
		mockedStaticMemoryAccessToken.when(MemoryAccessToken::getInstance).thenReturn(mockMemoryAccessToken);
		mockedStaticMemoryServiceKey.when(MemoryServiceKey::getInstance).thenReturn(mockMemoryServiceKey);
		mockedStaticMemoryResourceGroup.when(MemoryResourceGroup::getInstance).thenReturn(mockMemoryResourceGroup);
		mockedStaticMemoryDeployment.when(MemoryDeployment::getInstance).thenReturn(mockMemoryDeployment);
	}
	
	@AfterEach
	public void tearDown() {
		if (mockedStaticMemoryAccessToken != null) {
			mockedStaticMemoryAccessToken.close();
		}
		if (mockedStaticMemoryServiceKey != null) {
			mockedStaticMemoryServiceKey.close();
		}
		if (mockedStaticMemoryResourceGroup != null) {
			mockedStaticMemoryResourceGroup.close();
		}
		if (mockedStaticMemoryDeployment != null) {
			mockedStaticMemoryDeployment.close();
		}
	}
	
	@Test
	public void shouldReturnTrueIfUserIsLoggedIn() {
		// Arrange
		when(mockMemoryAccessToken.isEmpty()).thenReturn(false);
		when(mockMemoryServiceKey.isEmpty()).thenReturn(false);
		when(mockMemoryResourceGroup.isEmpty()).thenReturn(false);
		when(mockMemoryDeployment.isEmpty()).thenReturn(false);
		// Act
		Boolean returnValue = SessionManager.isUserLoggedIn();
		// Assert
		assertTrue(returnValue);
	}
	
	@Test
	public void shouldReturnFalseIfAccessTokenIsMissing() {
		// Arrange
		when(mockMemoryAccessToken.isEmpty()).thenReturn(false);
		when(mockMemoryServiceKey.isEmpty()).thenReturn(true);
		when(mockMemoryResourceGroup.isEmpty()).thenReturn(true);
		when(mockMemoryDeployment.isEmpty()).thenReturn(true);
		// Act
		Boolean returnValue = SessionManager.isUserLoggedIn();
		// Assert
		assertFalse(returnValue);
	}
	
	@Test
	public void shouldReturnFalseIfServiceKeyIsMissing() {
		// Arrange
		when(mockMemoryAccessToken.isEmpty()).thenReturn(true);
		when(mockMemoryServiceKey.isEmpty()).thenReturn(false);
		when(mockMemoryResourceGroup.isEmpty()).thenReturn(true);
		when(mockMemoryDeployment.isEmpty()).thenReturn(true);
		// Act
		Boolean returnValue = SessionManager.isUserLoggedIn();
		// Assert
		assertFalse(returnValue);
	}
	
	@Test
	public void shouldReturnFalseIfResourceGroupIsMissing() {
		// Arrange
		when(mockMemoryAccessToken.isEmpty()).thenReturn(true);
		when(mockMemoryServiceKey.isEmpty()).thenReturn(true);
		when(mockMemoryResourceGroup.isEmpty()).thenReturn(false);
		when(mockMemoryDeployment.isEmpty()).thenReturn(true);
		// Act
		Boolean returnValue = SessionManager.isUserLoggedIn();
		// Assert
		assertFalse(returnValue);
	}
	
	@Test
	public void shouldReturnFalseIfDeploymentIsMissing() {
		// Arrange
		when(mockMemoryAccessToken.isEmpty()).thenReturn(true);
		when(mockMemoryServiceKey.isEmpty()).thenReturn(true);
		when(mockMemoryResourceGroup.isEmpty()).thenReturn(true);
		when(mockMemoryDeployment.isEmpty()).thenReturn(false);
		// Act
		Boolean returnValue = SessionManager.isUserLoggedIn();
		// Assert
		assertFalse(returnValue);
	}

}
