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

import io.crs.hsys.client.cfg.editor.profile.AddressActionEvent.AddressActiEventHandler;
import io.crs.hsys.shared.dto.profile.AddressDto;

/**
 * @author robi
 *
 */
public class AddressListEditor extends Composite
		implements IsEditor<ListEditor<AddressDto, AddressEditor>> {
	private static Logger logger = Logger.getLogger(AddressListEditor.class.getName());

	interface Binder extends UiBinder<Widget, AddressListEditor> {
	}

	@Ignore
	@UiField
	FlowPanel listPanel;

	@Inject
	Provider<AddressEditor> editorProvider;

	/**
	 * An entity capable of creating and destroying instances of Editors. This type
	 * is used by Editors which operate on ordered data, sich as ListEditor.
	 * 
	 * @author cr
	 * 
	 */
	private class AddressEditorSource extends EditorSource<AddressEditor> {

		/**
		 * Create a new Editor. Parameters: index - the position at which the new Editor
		 * should be displayed Returns: an Editor of type E
		 */
		@Override
		public AddressEditor create(final int index) {
			AddressEditor subEditor = editorProvider.get();
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
		public void dispose(AddressEditor subEditor) {
			subEditor.removeFromParent();
		}

		/**
		 * Re-order a sub-Editor. The default implementation is a no-op.
		 */
		@Override
		public void setIndex(AddressEditor editor, int index) {
			listPanel.insert(editor, index);
		}
	}

	private ListEditor<AddressDto, AddressEditor> editor = ListEditor.of(new AddressEditorSource());

	private Boolean readOnly = false;

	/**
	 */
	@Inject
	AddressListEditor(Binder uiBinder, EventBus eventBus) {
		logger.info("AddressListEditor()");
		initWidget(uiBinder.createAndBindUi(this));

		eventBus.addHandler(AddressActionEvent.TYPE, new AddressActiEventHandler() {

			@Override
			public void onAddressAction(AddressActionEvent event) {
				if (event.getAction().equals(AddressActionEvent.Action.DELETE))
					remove(event.getIndex());
			}

		});
	}

	@Override
	public ListEditor<AddressDto, AddressEditor> asEditor() {
		return editor;
	}

	public void addItem() {
		AddressDto e = new AddressDto();
		editor.getList().add(e);
	}

	private void remove(final int index) {
		editor.getList().remove(index);
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

}
