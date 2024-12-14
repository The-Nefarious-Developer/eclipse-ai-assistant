package com.developer.nefarious.zjoule.test.core.events;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import org.eclipse.swt.browser.Browser;
import org.eclipse.ui.IEditorReference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.core.events.PartListener;
import com.developer.nefarious.zjoule.core.functions.TagHandler;

public class PartListenerTest {

	private PartListener cut;
	
	private MockedStatic<TagHandler> mockedStaticTagHandler;
	
	@Mock
	private Browser mockBrowser;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		mockedStaticTagHandler = mockStatic(TagHandler.class);
		
		cut = PartListener.getInstance(mockBrowser);
	}
	
	@AfterEach
	public void tearDown() {
		if (mockedStaticTagHandler != null) {
			mockedStaticTagHandler.close();
		}
	}
	
	@Test
	public void shouldHandlePartClosed() {
		// Arrange
		IEditorReference mockWorkbenchPartReference = mock(IEditorReference.class);
		// Act
		cut.partClosed(mockWorkbenchPartReference);
		// Assert
		mockedStaticTagHandler.verify(() -> TagHandler.update(mockBrowser));
	}
}
