/**
 * 
 */
package io.crs.hsys.client.kip.browser.tasktype;

import javax.inject.Inject;

import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.browser.AbstractBrowserView;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author robi
 *
 */
public class HkTaskTypeBrowserView extends TaskTypeBrowserView implements HkTaskTypeBrowserPresenter.MyView {

	@Inject
	HkTaskTypeBrowserView(AbstractBrowserView<TaskTypeDto> table, CoreMessages i18nCore) {
		super(table, i18nCore);
	}

	@Override
	protected Boolean isEnabledTaskKind(TaskKind kind) {
		return kind.equals(TaskKind.TK_CLEANING);
	}

}
