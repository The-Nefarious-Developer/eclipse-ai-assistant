package com.developer.nefarious.zjoule.test.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.developer.nefarious.zjoule.auth.JsonValidator;
import com.google.gson.JsonSyntaxException;

public class JsonValidatorTest {
	
	@Test
	public void testIsValidJsonWithValidJson() {
		// Arrange
		String validJson = "{\"name\": \"John\", \"age\": 30}";
		// Act
		boolean returnValue = JsonValidator.isValidJson(validJson);
		// Assert
		assertTrue(returnValue);
	}
	
	@Test
	public void testIsValidJsonWithInvalidJson() {
		// Arrange
		String invalidJson = "{name: John, age: 30";
		boolean gotAnError = false;
		boolean returnValue = true;
		// Act
		try {
			returnValue = JsonValidator.isValidJson(invalidJson);	
		} catch (JsonSyntaxException error) {
			gotAnError = true;
		}
		// Assert
		assertFalse(gotAnError);
		assertFalse(returnValue);
	}

}
