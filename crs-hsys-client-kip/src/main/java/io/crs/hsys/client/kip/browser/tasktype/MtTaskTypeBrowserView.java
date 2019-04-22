/**
 * 
 */
package io.crs.hsys.client.kip.browser.tasktype;

import javax.inject.Inject;

import io.crs.hsys.client.core.browser.AbstractBrowserView;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author CR
 *
 */
public class MtTaskTypeBrowserView extends TaskTypeBrowserView implements MtTaskTypeBrowserPresenter.MyView {

	@Inject
	MtTaskTypeBrowserView(AbstractBrowserView<TaskTypeDto> table, CoreMessages i18nCore) {
		super(table, i18nCore);
	}

	@Override
	protected Boolean isEnabledTaskKind(TaskKind kind) {
		return kind.equals(TaskKind.TK_MAINTENANCE);
	}

}
