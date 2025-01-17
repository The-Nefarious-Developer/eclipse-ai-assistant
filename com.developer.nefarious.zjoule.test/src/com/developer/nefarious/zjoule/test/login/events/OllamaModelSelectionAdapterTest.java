package com.developer.nefarious.zjoule.test.login.events;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.developer.nefarious.zjoule.plugin.login.events.OllamaModelSelectionAdapter;
import com.developer.nefarious.zjoule.plugin.login.pages.SecondOllamaLoginWizardPage;
import com.developer.nefarious.zjoule.plugin.memory.IMemoryObject;
import com.developer.nefarious.zjoule.plugin.models.OllamaModel;

public class OllamaModelSelectionAdapterTest {

	private OllamaModelSelectionAdapter cut;

	@Mock
	private SecondOllamaLoginWizardPage mockSecondOllamaLoginWizardPage;

	@Mock
	private IMemoryObject<OllamaModel> mockMemoryOllamaModel;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		cut = spy(new OllamaModelSelectionAdapter(mockSecondOllamaLoginWizardPage, mockMemoryOllamaModel));
	}

	@Test
	public void shouldSetAsCompleteWhenThereIsText() {
		// Arrange
		SelectionEvent mockSelectionEvent = mock(SelectionEvent.class);

		Combo mockOllamaModelDropdown = mock(Combo.class);
		when(mockSecondOllamaLoginWizardPage.getOllamaModelDropdown()).thenReturn(mockOllamaModelDropdown);
		String mockSelectedOllamaModelName = "Selected Ollama Model";
		when(mockOllamaModelDropdown.getText()).thenReturn(mockSelectedOllamaModelName);

		OllamaModel ollamaModel1 = new OllamaModel();
		ollamaModel1.setName("Not this one");
		OllamaModel ollamaModel2 = new OllamaModel();
		ollamaModel2.setName(mockSelectedOllamaModelName);
		OllamaModel ollamaModel3 = new OllamaModel();
		ollamaModel3.setName("Note this one");
		List<OllamaModel> mockOllamaModelsForselection = Arrays.asList(ollamaModel1, ollamaModel2, ollamaModel3);
		when(mockSecondOllamaLoginWizardPage.getOllamaModelsForSelection()).thenReturn(mockOllamaModelsForselection);

		// Act
		cut.widgetSelected(mockSelectionEvent);

		// Assert
		verify(mockSecondOllamaLoginWizardPage).setPageComplete(true);
		verify(mockMemoryOllamaModel).save(ollamaModel2);
	}

	@Test
	public void shouldSetAsIncompleteWhenThereIsNoText() {
		// Arrange
		SelectionEvent mockSelectionEvent = mock(SelectionEvent.class);

		Combo mockOllamaModelDropdown = mock(Combo.class);
		when(mockSecondOllamaLoginWizardPage.getOllamaModelDropdown()).thenReturn(mockOllamaModelDropdown);
		String mockText = "";
		when(mockOllamaModelDropdown.getText()).thenReturn(mockText);

		// Act
		cut.widgetSelected(mockSelectionEvent);

		// Assert
		verify(mockSecondOllamaLoginWizardPage).setPageComplete(false);
	}

}
