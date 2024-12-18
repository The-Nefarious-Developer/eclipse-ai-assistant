package com.developer.nefarious.zjoule.test.login.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.developer.nefarious.zjoule.plugin.login.utils.JsonValidator;
import com.google.gson.JsonSyntaxException;

public class JsonValidatorTest {

	@Test
	public void shouldBeInvalid() {
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

	@Test
	public void shouldBeValid() {
		// Arrange
		String validJson = "{\"name\": \"John\", \"age\": 30}";
		// Act
		boolean returnValue = JsonValidator.isValidJson(validJson);
		// Assert
		assertTrue(returnValue);
	}

}
