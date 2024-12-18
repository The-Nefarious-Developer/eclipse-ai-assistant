package com.developer.nefarious.zjoule.test.core.functions;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;

import org.eclipse.swt.browser.Browser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.zjoule.plugin.chat.utils.EditorContentReader;
import com.developer.nefarious.zjoule.plugin.core.functions.TagHandler;

public class TagHandlerTest {

	@Mock
	private Browser mockBrowser;

	MockedStatic<EditorContentReader> mockedStaticEditorContentReader;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		mockedStaticEditorContentReader = mockStatic(EditorContentReader.class);
	}

	@Test
	public void shouldUpdateTag() {
		// Arrange
		String mockFileName = "abap-class";
		String mockJsFunctionCall = "updateTag('" + mockFileName + "');";
		mockedStaticEditorContentReader.when(EditorContentReader::getActiveEditorFileName).thenReturn(mockFileName);
		// Act
		TagHandler.update(mockBrowser);
		// Assert
		verify(mockBrowser).execute(mockJsFunctionCall);
	}

	@AfterEach
	public void tearDown() {
		if (mockedStaticEditorContentReader != null) {
			mockedStaticEditorContentReader.close();
		}
	}

}
