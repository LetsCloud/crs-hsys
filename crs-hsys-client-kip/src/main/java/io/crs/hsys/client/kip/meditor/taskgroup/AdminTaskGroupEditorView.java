/**
 * 
 */
package io.crs.hsys.client.kip.meditor.taskgroup;

import java.util.Map;

import javax.inject.Inject;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.cnst.TaskKind;

/**
 * @author robi
 *
 */
public class AdminTaskGroupEditorView extends TaskGroupEditorView implements HkTaskGroupEditorPresenter.MyView {

	@Inject
	AdminTaskGroupEditorView(Binder uiBinder, Driver driver, CoreMessages i18n, CoreConstants i18nCoreCnst) {
		super(uiBinder, driver, i18n);

		initTaskKindCombo(i18nCoreCnst.taskKindMap());
	}

	private void initTaskKindCombo(Map<String, String> i18nTaskKinds) {
		kindCombo.clear();
		kindCombo.addItem(i18nTaskKinds.get(TaskKind.TK_CLEANING.toString()), TaskKind.TK_CLEANING);
		kindCombo.addItem(i18nTaskKinds.get(TaskKind.TK_MAINTENANCE.toString()), TaskKind.TK_MAINTENANCE);
	}

}
