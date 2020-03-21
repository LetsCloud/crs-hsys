/**
 * 
 */
package io.crs.hsys.client.fro.rate.updater;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import io.crs.hsys.shared.dto.rate.update.RoomRateOperationDto;

/**
 * @author robi
 *
 */
public class OperationListEditor extends Composite implements IsEditor<ListEditor<RoomRateOperationDto, OperationEditor>> {

	interface Binder extends UiBinder<Widget, OperationListEditor> {
	}

	@Ignore
	@UiField
	FlowPanel listPanel;

	@Inject
	Provider<OperationEditor> editorProvider;

	/**
	 * An entity capable of creating and destroying instances of Editors. This type
	 * is used by Editors which operate on ordered data, sich as ListEditor.
	 * 
	 * @author cr
	 * 
	 */
	private class RateEditorSource extends EditorSource<OperationEditor> {

		/**
		 * Create a new Editor. Parameters: index - the position at which the new Editor
		 * should be displayed Returns: an Editor of type E
		 */
		@Override
		public OperationEditor create(final int index) {
			OperationEditor subEditor = editorProvider.get();

			listPanel.insert(subEditor, index);

			return subEditor;
		}

		/**
		 * Called when an Editor no longer requires a sub-Editor. The default
		 * implementation is a no-op.
		 */
		@Override
		public void dispose(OperationEditor subEditor) {
			subEditor.removeFromParent();
		}

		/**
		 * Re-order a sub-Editor. The default implementation is a no-op.
		 */
		@Override
		public void setIndex(OperationEditor editor, int index) {
			listPanel.insert(editor, index);
		}
	}

	private ListEditor<RoomRateOperationDto, OperationEditor> editor = ListEditor.of(new RateEditorSource());

	/**
	*/
	@Inject
	OperationListEditor(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public ListEditor<RoomRateOperationDto, OperationEditor> asEditor() {
		return editor;
	}
}
