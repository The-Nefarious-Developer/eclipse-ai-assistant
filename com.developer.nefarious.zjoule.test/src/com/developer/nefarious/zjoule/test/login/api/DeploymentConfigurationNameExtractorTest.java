package com.developer.nefarious.zjoule.test.login.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import com.developer.nefarious.zjoule.login.api.DeploymentConfigurationNameExtractor;
import com.developer.nefarious.zjoule.login.api.GetDeploymentsResponse;
import com.developer.nefarious.zjoule.models.Deployment;

public class DeploymentConfigurationNameExtractorTest {

	@Test
	public void shouldExtractConfigurationNames() {
		// Arrange
		GetDeploymentsResponse mockResponse = mock(GetDeploymentsResponse.class);
		Deployment mockDeployment1 = mock(Deployment.class);
		Deployment mockDeployment2 = mock(Deployment.class);

		when(mockDeployment1.getConfigurationName()).thenReturn("RG001");
		when(mockDeployment2.getConfigurationName()).thenReturn("RG002");
		when(mockResponse.getDeployments()).thenReturn(Arrays.asList(mockDeployment1, mockDeployment2));

		// Act
		ArrayList<String> returnObject = DeploymentConfigurationNameExtractor.extractResourceGroupIds(mockResponse);

		// Assert
		assertNotNull(returnObject, "The result should not be null");
		assertEquals(2, returnObject.size(), "The result should contain two resource group IDs");
		assertEquals("RG001", returnObject.get(0));
		assertEquals("RG002", returnObject.get(1));
	}

	@Test
	public void shouldReturnAnEmptyListIfTheResponseIsEmpty() {
		// Arrange
		GetDeploymentsResponse mockResponse = mock(GetDeploymentsResponse.class);
		when(mockResponse.getDeployments()).thenReturn(Collections.emptyList());

		// Act
		ArrayList<String> returnObject = DeploymentConfigurationNameExtractor.extractResourceGroupIds(mockResponse);

		// Assert
		assertNotNull(returnObject, "The result should not be null");
		assertEquals(0, returnObject.size(), "The result should be empty");
	}

	@Test
	public void shouldReturnNullIfTheResponseIsNull() {
		// Arrange
		GetDeploymentsResponse mockResponse = null;

		// Act
		ArrayList<String> result = DeploymentConfigurationNameExtractor.extractResourceGroupIds(mockResponse);

		// Assert
		assertNull(result, "The result should be null when the response is null");
	}
}
