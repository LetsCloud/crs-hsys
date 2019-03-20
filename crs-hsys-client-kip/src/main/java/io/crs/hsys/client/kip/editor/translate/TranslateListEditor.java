/**
 * 
 */
package io.crs.hsys.client.kip.editor.translate;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialPanel;
import io.crs.hsys.client.core.editor.room.DeleteEvent;
import io.crs.hsys.shared.dto.common.TranslationDto;

/**
 * @author robi
 *
 */
public class TranslateListEditor extends Composite implements IsEditor<ListEditor<TranslationDto, TranslateEditor>> {

	interface Binder extends UiBinder<Widget, TranslateListEditor> {
	}

	@Ignore
	@UiField
	MaterialPanel listPanel;

	@Inject
	Provider<TranslateEditor> editorProvider;

	/**
	 * An entity capable of creating and destroying instances of Editors. This type
	 * is used by Editors which operate on ordered data, sich as ListEditor.
	 * 
	 * @author cr
	 * 
	 */
	private class TranslateEditorSource extends EditorSource<TranslateEditor> {

		/**
		 * Create a new Editor. Parameters: index - the position at which the new Editor
		 * should be displayed Returns: an Editor of type E
		 */
		@Override
		public TranslateEditor create(final int index) {
			TranslateEditor subEditor = editorProvider.get();

			listPanel.insert(subEditor, index);

			subEditor.addDeleteHandler(new DeleteEvent.DeleteEventHandler() {
				public void onDeleteEvent(DeleteEvent event) {
					remove(index);
				}
			});

			return subEditor;
		}

		/**
		 * Called when an Editor no longer requires a sub-Editor. The default
		 * implementation is a no-op.
		 */
		@Override
		public void dispose(TranslateEditor subEditor) {
			subEditor.removeFromParent();
		}

		/**
		 * Re-order a sub-Editor. The default implementation is a no-op.
		 */
		@Override
		public void setIndex(TranslateEditor editor, int index) {
			listPanel.insert(editor, index);
		}
	}

	private ListEditor<TranslationDto, TranslateEditor> editor = ListEditor.of(new TranslateEditorSource());

	/**
	 */
	@Inject
	TranslateListEditor(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public ListEditor<TranslationDto, TranslateEditor> asEditor() {
		return editor;
	}

	@UiHandler("addButton")
	void onAddClick(ClickEvent event) {
		TranslationDto dto = new TranslationDto();
		editor.getList().add(dto);
	}

//	@UiHandler("deleteButton")
	void onDeleteClick(ClickEvent event) {
		remove(editor.getList().size() - 1);
	}

	private void remove(final int index) {
		editor.getList().remove(index);
	}
}
