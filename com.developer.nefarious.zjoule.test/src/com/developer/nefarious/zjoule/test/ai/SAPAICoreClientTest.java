package com.developer.nefarious.zjoule.test.ai;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.developer.nefarious.zjoule.ai.ISAPAICoreClient;
import com.developer.nefarious.zjoule.ai.SAPAICoreClient;

public class SAPAICoreClientTest {
	
	private ISAPAICoreClient cut;
	
	@BeforeEach
	public void setUp() {
		cut = new SAPAICoreClient();
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
