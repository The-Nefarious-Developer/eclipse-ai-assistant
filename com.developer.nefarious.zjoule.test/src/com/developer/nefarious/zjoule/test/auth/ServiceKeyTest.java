package com.developer.nefarious.zjoule.test.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.developer.nefarious.zjoule.auth.ServiceKey;
import com.google.gson.Gson;

public class ServiceKeyTest {
	
	private Gson gson;
	
	@BeforeEach
	public void setUp() {
		gson = new Gson();
	}
	
	@Test
	public void testIsValid() {
		// Arrange
		String mockServiceKeyInput = "{"
			+ "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
		    + "\"appname\": \"this will be ignored\", "
		    + "\"clientid\": \"this matters\", "
		    + "\"clientsecret\": \"this matters\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\", "
		    + "\"url\": \"this matters\""
		    + "}";
		
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertTrue(returnValue);
	}
	
	@Test
	public void testIsNotValid_missingServiceUrl() {
		// Arrange
		String mockServiceKeyInput = "{"
			+ "\"appname\": \"this will be ignored\", "
		    + "\"clientid\": \"this matters\", "
		    + "\"clientsecret\": \"this matters\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\", "
		    + "\"url\": \"this matters\""
		    + "}";
		
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}
	
	@Test
	public void testIsNotValid_emptyServiceUrl() {
		// Arrange
		String mockServiceKeyInput = "{"
			+ "\"serviceurls\": {\"AI_API_URL\": \"\"}, "
		    + "\"appname\": \"this will be ignored\", "
		    + "\"clientid\": \"this matters\", "
		    + "\"clientsecret\": \"this matters\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\", "
		    + "\"url\": \"this matters\""
		    + "}";
		
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}
	
	@Test
	public void testIsNotValid_missingClientId() {
		// Arrange
		String mockServiceKeyInput = "{"
			+ "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
		    + "\"appname\": \"this will be ignored\", "
		    + "\"clientsecret\": \"this matters\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\", "
		    + "\"url\": \"this matters\""
		    + "}";
		
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}
	
	@Test
	public void testIsNotValid_emptyClientId() {
		// Arrange
		String mockServiceKeyInput = "{"
			+ "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
		    + "\"appname\": \"this will be ignored\", "
		    + "\"clientid\": \"\", "
		    + "\"clientsecret\": \"this matters\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\", "
		    + "\"url\": \"this matters\""
		    + "}";
		
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}
	
	@Test
	public void testIsNotValid_missingClientSecret() {
		// Arrange
		String mockServiceKeyInput = "{"
			+ "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
		    + "\"appname\": \"this will be ignored\", "
		    + "\"clientid\": \"this matters\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\", "
		    + "\"url\": \"this matters\""
		    + "}";
		
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}
	
	@Test
	public void testIsNotValid_emptyClientSecret() {
		// Arrange
		String mockServiceKeyInput = "{"
			+ "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
		    + "\"appname\": \"this will be ignored\", "
		    + "\"clientid\": \"this matters\", "
		    + "\"clientsecret\": \"\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\", "
		    + "\"url\": \"this matters\""
		    + "}";
		
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}
	
	@Test
	public void testIsNotValid_missingTokenURL() {
		// Arrange
		String mockServiceKeyInput = "{"
			+ "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
		    + "\"appname\": \"this will be ignored\", "
		    + "\"clientid\": \"this matters\", "
		    + "\"clientsecret\": \"this matters\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\""
		    + "}";
		
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}
	
	@Test
	public void testIsNotValid_emptyTokenURL() {
		// Arrange
		String mockServiceKeyInput = "{"
			+ "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
		    + "\"appname\": \"this will be ignored\", "
		    + "\"clientid\": \"this matters\", "
		    + "\"clientsecret\": \"this matters\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\", "
		    + "\"url\": \"\""
		    + "}";
		
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}

}
