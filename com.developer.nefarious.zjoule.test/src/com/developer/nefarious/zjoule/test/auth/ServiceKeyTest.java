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
	public void shouldBeValid() {
		// Arrange
		// @formatter:off
		String mockServiceKeyInput = "{"
			+ "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
		    + "\"appname\": \"this will be ignored\", "
		    + "\"clientid\": \"this matters\", "
		    + "\"clientsecret\": \"this matters\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\", "
		    + "\"url\": \"this matters\""
		    + "}";
		// @formatter:on
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertTrue(returnValue);
	}

	@Test
	public void shouldBeInvalidForMissingServiceUrl() {
		// Arrange
		// @formatter:off
		String mockServiceKeyInput = "{"
			+ "\"appname\": \"this will be ignored\", "
		    + "\"clientid\": \"this matters\", "
		    + "\"clientsecret\": \"this matters\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\", "
		    + "\"url\": \"this matters\""
		    + "}";
		// @formatter:on
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}

	@Test
	public void shouldBeInvalidForEmptyServiceUrl() {
		// Arrange
		// @formatter:off
		String mockServiceKeyInput = "{"
			+ "\"serviceurls\": {\"AI_API_URL\": \"\"}, "
		    + "\"appname\": \"this will be ignored\", "
		    + "\"clientid\": \"this matters\", "
		    + "\"clientsecret\": \"this matters\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\", "
		    + "\"url\": \"this matters\""
		    + "}";
		// @formatter:on
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}

	@Test
	public void shouldBeInvalidForMissingClientId() {
		// Arrange
		// @formatter:off
		String mockServiceKeyInput = "{"
			+ "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
		    + "\"appname\": \"this will be ignored\", "
		    + "\"clientsecret\": \"this matters\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\", "
		    + "\"url\": \"this matters\""
		    + "}";
		// @formatter:on
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}

	@Test
	public void shouldBeInvalidForEmptyClientId() {
		// Arrange
		// @formatter:off
		String mockServiceKeyInput = "{"
			+ "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
		    + "\"appname\": \"this will be ignored\", "
		    + "\"clientid\": \"\", "
		    + "\"clientsecret\": \"this matters\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\", "
		    + "\"url\": \"this matters\""
		    + "}";
		// @formatter:on
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}

	@Test
	public void shouldBeInvalidForMissingClientSecret() {
		// Arrange
		// @formatter:off
		String mockServiceKeyInput = "{"
			+ "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
		    + "\"appname\": \"this will be ignored\", "
		    + "\"clientid\": \"this matters\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\", "
		    + "\"url\": \"this matters\""
		    + "}";
		// @formatter:on
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}

	@Test
	public void shouldBeInvalidForEmptyClientSecret() {
		// Arrange
		// @formatter:off
		String mockServiceKeyInput = "{"
			+ "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
		    + "\"appname\": \"this will be ignored\", "
		    + "\"clientid\": \"this matters\", "
		    + "\"clientsecret\": \"\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\", "
		    + "\"url\": \"this matters\""
		    + "}";
		// @formatter:on
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}

	@Test
	public void shouldBeInvalidForMissingTokenUrl() {
		// Arrange
		// @formatter:off
		String mockServiceKeyInput = "{"
			+ "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
		    + "\"appname\": \"this will be ignored\", "
		    + "\"clientid\": \"this matters\", "
		    + "\"clientsecret\": \"this matters\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\""
		    + "}";
		// @formatter:on
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}

	@Test
	public void shouldBeInvalidForEmptyTokenUrl() {
		// Arrange
		// @formatter:off
		String mockServiceKeyInput = "{"
			+ "\"serviceurls\": {\"AI_API_URL\": \"this matters\"}, "
		    + "\"appname\": \"this will be ignored\", "
		    + "\"clientid\": \"this matters\", "
		    + "\"clientsecret\": \"this matters\", "
		    + "\"identityzone\": \"this will be ignored\", "
		    + "\"identityzoneid\": \"this will be ignored\", "
		    + "\"url\": \"\""
		    + "}";
		// @formatter:on
		ServiceKey cut = gson.fromJson(mockServiceKeyInput, ServiceKey.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}

}
