package com.developer.nefarious.zjoule.test.login.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import com.developer.nefarious.zjoule.plugin.login.api.GetResourceGroupsResponse;
import com.developer.nefarious.zjoule.plugin.login.utils.ResourceGroupIdExtractor;
import com.developer.nefarious.zjoule.plugin.models.ResourceGroup;

public class ResourceGroupIdExtractorTest {
	
	@Test
    public void shouldExtractResourceGroupIds() {
        // Arrange
        GetResourceGroupsResponse mockResponse = mock(GetResourceGroupsResponse.class);
        ResourceGroup mockResourceGroup1 = mock(ResourceGroup.class);
        ResourceGroup mockResourceGroup2 = mock(ResourceGroup.class);

        when(mockResourceGroup1.getResourceGroupId()).thenReturn("RG001");
        when(mockResourceGroup2.getResourceGroupId()).thenReturn("RG002");
        when(mockResponse.getResourceGroups()).thenReturn(Arrays.asList(mockResourceGroup1, mockResourceGroup2));

        // Act
        ArrayList<String> returnObject = ResourceGroupIdExtractor.extractResourceGroupIds(mockResponse);

        // Assert
        assertNotNull(returnObject, "The result should not be null");
        assertEquals(2, returnObject.size(), "The result should contain two resource group IDs");
        assertEquals("RG001", returnObject.get(0));
        assertEquals("RG002", returnObject.get(1));
    }

    @Test
    public void shouldReturnAnEmptyListIfTheResponseIsEmpty() {
        // Arrange
        GetResourceGroupsResponse mockResponse = mock(GetResourceGroupsResponse.class);
        when(mockResponse.getResourceGroups()).thenReturn(Collections.emptyList());

        // Act
        ArrayList<String> returnObject = ResourceGroupIdExtractor.extractResourceGroupIds(mockResponse);

        // Assert
        assertNotNull(returnObject, "The result should not be null");
        assertEquals(0, returnObject.size(), "The result should be empty");
    }

    @Test
    public void shouldReturnNullIfTheResponseIsNull() {
        // Arrange
        GetResourceGroupsResponse mockResponse = null;

        // Act
        ArrayList<String> result = ResourceGroupIdExtractor.extractResourceGroupIds(mockResponse);

        // Assert
        assertNull(result, "The result should be null when the response is null");
    }

}
