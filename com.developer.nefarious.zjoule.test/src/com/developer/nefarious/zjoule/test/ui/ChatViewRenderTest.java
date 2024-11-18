package com.developer.nefarious.zjoule.test.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.osgi.framework.Bundle;

import com.developer.nefarious.zjoule.ui.ChatViewRender;
import com.developer.nefarious.zjoule.ui.IViewRender;

public class ChatViewRenderTest {
	
	private IViewRender cut;
	
	private String randomWord() {
		final String[] WORDS = { "apple", "banana", "grape" };
		int randomIndex = ThreadLocalRandom.current().nextInt(WORDS.length);
		return WORDS[randomIndex];
	}
	
	@BeforeEach
	public void setUp() {
		cut = spy(new ChatViewRender());
	}
	
	@Test
	public void testGetContent() {
		// Arrange
		String mockJsFileContent = randomWord();
		doReturn(mockJsFileContent).when(cut).getResourceContent("ChatView.js");
		String expectedJsReference = "<script>" + mockJsFileContent + "</script>";
		
		String mockCssFileContent = randomWord();
		doReturn(mockCssFileContent).when(cut).getResourceContent("ChatView.css");
		String expectedCssReference = "<style>" + mockCssFileContent + "</style>";
		
		// Act
		String returnValue = cut.build();
		
		// Assert
		assertTrue(returnValue.contains(expectedJsReference));
		assertTrue(returnValue.contains(expectedCssReference));
	}
	
	@Test
	public void testGetResourceContent() throws IOException {
		// Arrange
		String expectedValue = randomWord() + "\n";
		InputStream mockInputStream = new ByteArrayInputStream(expectedValue.getBytes());
		
		String projectName = "com.developer.nefarious.zjoule";
		String viewFilesPath = "resources/views/";
		String mockFileName = randomWord();
		
	    Bundle mockBundle = mock(Bundle.class);
		URL mockUnresolvedURL = mock(URL.class);
		URL mockResolvedURL = mock(URL.class);
		
		// Mock the static call for Platform.getBundle(...)
		try (MockedStatic<Platform> mockedStaticPlatform = mockStatic(Platform.class)) {
			mockedStaticPlatform.when(() -> Platform.getBundle(projectName)).thenReturn(mockBundle);
			when(mockBundle.getEntry(viewFilesPath + mockFileName)).thenReturn(mockUnresolvedURL);
			
			// Mock the static call for FileLocator.toFileURL(...)
			try (MockedStatic<FileLocator> mockedStaticLocator = mockStatic(FileLocator.class)) {
				mockedStaticLocator.when(() -> FileLocator.toFileURL(mockUnresolvedURL)).thenReturn(mockResolvedURL);
				
				when(mockResolvedURL.openStream()).thenReturn(mockInputStream);
				
				// Act
				String returnValue = cut.getResourceContent(mockFileName);
				
				// Assert
				verify(mockBundle).getEntry(viewFilesPath + mockFileName);
				verify(mockResolvedURL).openStream();
				assertEquals(returnValue, expectedValue);
			}
		}
	}
	
}
