/**
 * 
 */
package io.crs.hsys.client.fro.reservation.header;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import io.crs.hsys.client.fro.event.DeleteEvent;
import io.crs.hsys.shared.dto.reservation.ProfileLinkDto;

/**
 * @author robi
 *
 */
public class ProfileLinkListEditor extends Composite
		implements IsEditor<ListEditor<ProfileLinkDto, ProfileLinkEditor>> {

	interface Binder extends UiBinder<Widget, ProfileLinkListEditor> {
	}

	@Ignore
	@UiField
	FlowPanel listPanel;

	@Inject
	Provider<ProfileLinkEditor> editorProvider;

	/**
	 * An entity capable of creating and destroying instances of Editors. This type
	 * is used by Editors which operate on ordered data, sich as ListEditor.
	 * 
	 * @author cr
	 * 
	 */
	private class ProfileLinkEditorSource extends EditorSource<ProfileLinkEditor> {

		/**
		 * Create a new Editor. Parameters: index - the position at which the new Editor
		 * should be displayed Returns: an Editor of type E
		 */
		@Override
		public ProfileLinkEditor create(final int index) {
			ProfileLinkEditor subEditor = editorProvider.get();

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
		public void dispose(ProfileLinkEditor subEditor) {
			subEditor.removeFromParent();
		}

		/**
		 * Re-order a sub-Editor. The default implementation is a no-op.
		 */
		@Override
		public void setIndex(ProfileLinkEditor editor, int index) {
			listPanel.insert(editor, index);
		}
	}

	private ListEditor<ProfileLinkDto, ProfileLinkEditor> editor = ListEditor.of(new ProfileLinkEditorSource());

	/**
	 */
	@Inject
	ProfileLinkListEditor(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public ListEditor<ProfileLinkDto, ProfileLinkEditor> asEditor() {
		return editor;
	}

	@UiHandler("addButton")
	void onAddClick(ClickEvent event) {
		ProfileLinkDto e = new ProfileLinkDto();
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
