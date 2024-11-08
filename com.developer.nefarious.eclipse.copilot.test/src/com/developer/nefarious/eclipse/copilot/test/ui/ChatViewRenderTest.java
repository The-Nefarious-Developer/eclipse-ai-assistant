package com.developer.nefarious.eclipse.copilot.test.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.developer.nefarious.eclipse.copilot.ui.ChatViewRender;
import com.developer.nefarious.eclipse.copilot.ui.IViewRender;

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
		String mockJsFileContent = this.randomWord();
		doReturn(mockJsFileContent).when(cut).getResourceContent("ChatView.js");
		String expectedJsReference = "<script>" + mockJsFileContent + "</script>";
		
		String mockCssFileContent = this.randomWord();
		doReturn(mockCssFileContent).when(cut).getResourceContent("ChatView.css");
		String expectedCssReference = "<style>" + mockCssFileContent + "</style>";
		
		// Act
		String returnValue = cut.build();
		
		// Assert
		assertTrue(returnValue.contains(expectedJsReference));
		assertTrue(returnValue.contains(expectedCssReference));
	}
	
	
}
