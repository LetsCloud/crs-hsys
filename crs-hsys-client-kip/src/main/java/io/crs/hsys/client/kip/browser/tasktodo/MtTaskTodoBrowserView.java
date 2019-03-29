/**
 * 
 */
package io.crs.hsys.client.kip.browser.tasktodo;

import javax.inject.Inject;

import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.browser.AbstractBrowserView;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author CR
 *
 */
public class MtTaskTodoBrowserView extends TaskTodoBrowserView implements MtTaskTodoBrowserPresenter.MyView {

	@Inject
	MtTaskTodoBrowserView(AbstractBrowserView<TaskTodoDto> table, CoreMessages i18n) {
		super(table, i18n);
	}

	@Override
	protected Boolean isEnabledTaskKind(TaskKind kind) {
		return kind.equals(TaskKind.TK_CLEANING);
	}

}
