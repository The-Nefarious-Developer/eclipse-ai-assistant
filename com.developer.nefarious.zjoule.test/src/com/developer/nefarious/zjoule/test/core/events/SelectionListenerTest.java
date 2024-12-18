package com.developer.nefarious.zjoule.test.core.events;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.browser.Browser;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.zjoule.plugin.core.events.SelectionListener;
import com.developer.nefarious.zjoule.plugin.core.functions.TagHandler;

public class SelectionListenerTest {

	private ISelectionListener cut;

	private MockedStatic<TagHandler> mockedStaticTagHandler;

	@Mock
	private Browser mockBrowser;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		mockedStaticTagHandler = mockStatic(TagHandler.class);

		cut = SelectionListener.create(mockBrowser);
	}

	@Test
	public void shouldHandleSelectionChanges() {
		// Arrange
		IWorkbenchPart mockWorkbenchPart = mock(IWorkbenchPart.class);
		ISelection mockSelection = mock(ISelection.class);
		// Act
		cut.selectionChanged(mockWorkbenchPart, mockSelection);
		// Assert
		mockedStaticTagHandler.verify(() -> TagHandler.update(mockBrowser));
	}

	@AfterEach
	public void tearDown() {
		if (mockedStaticTagHandler != null) {
			mockedStaticTagHandler.close();
		}
	}

}
