/**
 * 
 */
package io.crs.hsys.client.kip.browser.taskgroup;

import javax.inject.Inject;

import io.crs.hsys.client.core.browser.AbstractBrowserView;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.dto.task.TaskGroupDto;

/**
 * @author robi
 *
 */
public class MtTaskGroupBrowserView extends TaskGroupBrowserView implements MtTaskGroupBrowserPresenter.MyView {

	@Inject
	MtTaskGroupBrowserView(AbstractBrowserView<TaskGroupDto> table, CoreMessages i18n) {
		super(table, i18n);
	}

	@Override
	protected Boolean isEnabledTaskKind(TaskKind kind) {
		return kind.equals(TaskKind.TK_MAINTENANCE);
	}

}
