package com.developer.nefarious.zjoule.test.ai;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

import java.net.http.HttpClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.zjoule.ai.ISAPAICoreClient;
import com.developer.nefarious.zjoule.ai.SAPAICoreClient;

public class SAPAICoreClientTest {
	
	private ISAPAICoreClient cut;
	
	@Mock
	private HttpClient mockHttpClient;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		cut = spy(new SAPAICoreClient(mockHttpClient));
	}
	
	@Test
	public void testGetResourceGroups() {
		// Arrange
		// Act
//		cut.getResourceGroups();
		// Assert
		assertTrue(true);
	}
	
	@Test
	public void testGetDeployments() {
		// Arrange
		// Act
//		cut.getDeployments();
		// Assert
		assertTrue(true);
	}	

}
