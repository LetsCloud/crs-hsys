/**
 * 
 */
package io.crs.hsys.client.cfg.editor.profile;

import java.util.logging.Logger;

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
import com.google.web.bindery.event.shared.EventBus;

import io.crs.hsys.shared.dto.profile.ProfileLinkDto;

/**
 * @author robi
 *
 */
public class ProfileLinkListEditor extends Composite
		implements IsEditor<ListEditor<ProfileLinkDto, ProfileLinkEditor>> {
	private static Logger logger = Logger.getLogger(ProfileLinkListEditor.class.getName());

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
			if (index % 2 == 0)
				subEditor.setBackgeoundColor();
			subEditor.setIndex(index);
			subEditor.setReadOnly(readOnly);

			listPanel.insert(subEditor, index);
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

	private Boolean readOnly = false;

	/**
	 */
	@Inject
	ProfileLinkListEditor(Binder uiBinder, EventBus eventBus) {
		logger.info("ProfileLinkListEditor()");
		initWidget(uiBinder.createAndBindUi(this));
		/*
		 * eventBus.addHandler(AddressActionEvent.TYPE, new AddressActiEventHandler() {
		 * 
		 * @Override public void onAddressAction(AddressActionEvent event) { if
		 * (event.getAction().equals(AddressActionEvent.Action.DELETE))
		 * remove(event.getIndex()); }
		 * 
		 * });
		 */
	}

	@Override
	public ListEditor<ProfileLinkDto, ProfileLinkEditor> asEditor() {
		return editor;
	}

	public void addItem() {
		ProfileLinkDto e = new ProfileLinkDto();
		editor.getList().add(e);
	}

	private void remove(final int index) {
		editor.getList().remove(index);
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}
}
