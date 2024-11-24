package com.developer.nefarious.zjoule.test.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.developer.nefarious.zjoule.auth.AccessToken;
import com.google.gson.Gson;

public class AccessTokenTest {
	
	private Gson gson;
	
	@BeforeEach
	public void setUp() {
		gson = new Gson();
	}
	
	@Test
	public void testIsValid() {
		// Arrange
		// @formatter:off
		String mockAccessTokenResponse = "{"
			+ "\"access_token\": \"this doesn't matter\", "
			+ "\"token_type\": \"this doesn't matter\", "
			+ "\"expires_in\": 3600, "
			+ "\"scope\": \"this doesn't matter\", "
			+ "\"jti\": \"this doesn't matter\""
			+ "}";
		// @formatter:on
		AccessToken cut = gson.fromJson(mockAccessTokenResponse, AccessToken.class);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertTrue(returnValue);
	}
	
	@Test
	public void testIsNotValid() {
		// Arrange
		// @formatter:off
		String mockAccessTokenResponse = "{"
			+ "\"access_token\": \"this doesn't matter\", "
			+ "\"token_type\": \"this doesn't matter\", "
			+ "\"expires_in\": 3600, "
			+ "\"scope\": \"this doesn't matter\", "
			+ "\"jti\": \"this doesn't matter\""
			+ "}";
		// @formatter:on
		AccessToken cut = gson.fromJson(mockAccessTokenResponse, AccessToken.class);
		// CHECKSTYLE:OFF
		Date pastDate = new Date(System.currentTimeMillis() - 7200 * 1000); // 2 hours ago
		// CHECKSTYLE:ON
        cut.setReceivedAt(pastDate);
		// Act
		Boolean returnValue = cut.isValid();
		// Assert
		assertFalse(returnValue);
	}

}
