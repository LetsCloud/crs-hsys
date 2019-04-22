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

import io.crs.hsys.client.core.editor.HasEditorSwitch;
import io.crs.hsys.shared.cnst.WebPresenceType;
import io.crs.hsys.shared.dto.profile.WebPresenceDto;

/**
 * @author robi
 *
 */
public class WebPresenceListEditor extends Composite
		implements IsEditor<ListEditor<WebPresenceDto, WebPresenceEditor>>, HasEditorSwitch {
	private static Logger logger = Logger.getLogger(WebPresenceListEditor.class.getName());

	interface Binder extends UiBinder<Widget, WebPresenceListEditor> {
	}

	@Ignore
	@UiField
	FlowPanel listPanel;

	@Inject
	Provider<WebPresenceEditor> editorProvider;

	/**
	 * An entity capable of creating and destroying instances of Editors. This type
	 * is used by Editors which operate on ordered data, sich as ListEditor.
	 * 
	 * @author cr
	 * 
	 */
	private class WebPresenceEditorSource extends EditorSource<WebPresenceEditor> {

		/**
		 * Create a new Editor. Parameters: index - the position at which the new Editor
		 * should be displayed Returns: an Editor of type E
		 */
		@Override
		public WebPresenceEditor create(final int index) {
			WebPresenceEditor subEditor = editorProvider.get();
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
		public void dispose(WebPresenceEditor subEditor) {
			subEditor.removeFromParent();
		}

		/**
		 * Re-order a sub-Editor. The default implementation is a no-op.
		 */
		@Override
		public void setIndex(WebPresenceEditor editor, int index) {
			listPanel.insert(editor, index);
		}
	}

	private ListEditor<WebPresenceDto, WebPresenceEditor> editor = ListEditor.of(new WebPresenceEditorSource());

	private Boolean readOnly = false;

	/**
	*/
	@Inject
	WebPresenceListEditor(Binder uiBinder, EventBus eventBus) {
		logger.info("CommunicationListEditor()");
		initWidget(uiBinder.createAndBindUi(this));

		eventBus.addHandler(CommunicationActionEvent.TYPE,
				new CommunicationActionEvent.CommunicationActionEventHandler() {

					@Override
					public void onCommunicationAction(CommunicationActionEvent event) {
						if (event.getAction().equals(CommunicationActionEvent.Action.DELETE))
							remove(event.getIndex());
					}

				});
	}

	@Override
	public ListEditor<WebPresenceDto, WebPresenceEditor> asEditor() {
		return editor;
	}

	public void addItem() {
		editor.getList().add(new WebPresenceDto(WebPresenceType.WEB_URL));
	}

	private void remove(final int index) {
		editor.getList().remove(index);
	}

	@Override
	public void toEditable() {
// TODO Auto-generated method stub

	}

	@Override
	public void toReadOnly() {
// TODO Auto-generated method stub

	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
		for (WebPresenceEditor e : editor.getEditors()) {
			e.setReadOnly(readOnly);
		}
	}
}
