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

import io.crs.hsys.shared.dto.rate.RateRestrictionDto;

/**
 * @author robi
 *
 */
public class RestrictionListEditor extends Composite implements IsEditor<ListEditor<RateRestrictionDto, RestrictionEditor>> {

	interface Binder extends UiBinder<Widget, RestrictionListEditor> {
	}

	@Ignore
	@UiField
	FlowPanel listPanel;

	@Inject
	Provider<RestrictionEditor> editorProvider;

	/**
	 * An entity capable of creating and destroying instances of Editors. This type
	 * is used by Editors which operate on ordered data, sich as ListEditor.
	 * 
	 * @author cr
	 * 
	 */
	private class RestrictionEditorSource extends EditorSource<RestrictionEditor> {

		/**
		 * Create a new Editor. Parameters: index - the position at which the new Editor
		 * should be displayed Returns: an Editor of type E
		 */
		@Override
		public RestrictionEditor create(final int index) {
			RestrictionEditor subEditor = editorProvider.get();

			listPanel.insert(subEditor, index);

			return subEditor;
		}

		/**
		 * Called when an Editor no longer requires a sub-Editor. The default
		 * implementation is a no-op.
		 */
		@Override
		public void dispose(RestrictionEditor subEditor) {
			subEditor.removeFromParent();
		}

		/**
		 * Re-order a sub-Editor. The default implementation is a no-op.
		 */
		@Override
		public void setIndex(RestrictionEditor editor, int index) {
			listPanel.insert(editor, index);
		}
	}

	private ListEditor<RateRestrictionDto, RestrictionEditor> editor = ListEditor.of(new RestrictionEditorSource());

	/**
	*/
	@Inject
	RestrictionListEditor(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public ListEditor<RateRestrictionDto, RestrictionEditor> asEditor() {
		return editor;
	}
}
