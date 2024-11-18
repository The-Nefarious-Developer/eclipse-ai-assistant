package com.developer.nefarious.eclipse.copilot.test.functions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.developer.nefarious.eclipse.copilot.functions.LoginHandler;

public class LoginHandlerTest {
	
	private LoginHandler cut;
	
	@BeforeEach
	public void setUp() {
		cut = new LoginHandler();
	}
	
	@Test
	public void testRun() {
		// Arrange
		// Act
		cut.run();
		// Assert
		assertTrue(true);
	}

}
