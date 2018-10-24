/**
 * 
 */
package io.crs.hsys.client.cfg.editor.room;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import io.crs.hsys.shared.dto.hotel.RoomAvailabilityDto;

/**
 * @author robi
 *
 */
public class AvailabilityListEditor extends Composite
		implements IsEditor<ListEditor<RoomAvailabilityDto, AvailabilityEditor>> {

	interface Binder extends UiBinder<Widget, AvailabilityListEditor> {
	}

	@Ignore
	@UiField
	FlowPanel listPanel;

	@Inject
	Provider<AvailabilityEditor> editorProvider;

	/**
	 * An entity capable of creating and destroying instances of Editors. This type
	 * is used by Editors which operate on ordered data, sich as ListEditor.
	 * 
	 * @author cr
	 * 
	 */
	private class AvailabilityEditorSource extends EditorSource<AvailabilityEditor> {

		/**
		 * Create a new Editor. Parameters: index - the position at which the new Editor
		 * should be displayed Returns: an Editor of type E
		 */
		@Override
		public AvailabilityEditor create(final int index) {
			AvailabilityEditor subEditor = editorProvider.get();

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
		public void dispose(AvailabilityEditor subEditor) {
			subEditor.removeFromParent();
		}

		/**
		 * Re-order a sub-Editor. The default implementation is a no-op.
		 */
		@Override
		public void setIndex(AvailabilityEditor editor, int index) {
			listPanel.insert(editor, index);
		}
	}

	private ListEditor<RoomAvailabilityDto, AvailabilityEditor> editor = ListEditor.of(new AvailabilityEditorSource());

	/**
	 */
	@Inject
	AvailabilityListEditor(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public ListEditor<RoomAvailabilityDto, AvailabilityEditor> asEditor() {
		return editor;
	}

	@UiHandler("addButton")
	void onAddClick(ClickEvent event) {
		Boolean lastAvailable = false;
		if (!editor.getList().isEmpty())
			lastAvailable = editor.getList().get(editor.getList().size() - 1).getAvailable();
		RoomAvailabilityDto e = new RoomAvailabilityDto();
		if (lastAvailable == null)
			lastAvailable = false;
		e.setAvailable(!lastAvailable);
		editor.getList().add(e);
	}

	@UiHandler("deleteButton")
	void onDeleteClick(ClickEvent event) {
		remove(editor.getList().size() - 1);
	}

	private void remove(final int index) {
		editor.getList().remove(index);
	}

}
