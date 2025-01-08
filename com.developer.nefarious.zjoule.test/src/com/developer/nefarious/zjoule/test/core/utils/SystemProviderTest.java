package com.developer.nefarious.zjoule.test.core.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.developer.nefarious.zjoule.plugin.core.utils.SystemProvider;

public class SystemProviderTest {
	
	public void shouldReturnTheCurrentSystem() {
		// Arrange
		// Act
		String returnValue = SystemProvider.getCurrentSystem();
		// Assert
		assertNotNull(returnValue);
		assertFalse(returnValue.isBlank());
	}

}
