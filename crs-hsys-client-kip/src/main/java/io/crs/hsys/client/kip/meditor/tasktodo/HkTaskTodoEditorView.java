/**
 * 
 */
package io.crs.hsys.client.kip.meditor.tasktodo;

import java.util.Map;

import javax.inject.Inject;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.kip.editor.translate.TranslateListEditor;
import io.crs.hsys.shared.cnst.TaskKind;

/**
 * @author robi
 *
 */
public class HkTaskTodoEditorView extends TaskTodoEditorView implements HkTaskTodoEditorPresenter.MyView {

	@Inject
	HkTaskTodoEditorView(Binder uiBinder, Driver driver, CoreMessages i18n, CoreConstants i18nCoreCnst,
			TranslateListEditor translateListEditor) {
		super(uiBinder, driver, i18n, translateListEditor);

		initTaskKindCombo(i18nCoreCnst.taskKindMap());
	}

	private void initTaskKindCombo(Map<String, String> i18nTaskKinds) {
		kindComboBox.setVisible(false);
		kindComboBox.clear();
		kindComboBox.addItem(i18nTaskKinds.get(TaskKind.TK_CLEANING.toString()), TaskKind.TK_CLEANING);
	}

}
