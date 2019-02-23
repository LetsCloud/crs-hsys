/**
 * 
 */
package io.crs.hsys.client.kip.filter.taskgroup;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.ui.filter.AbstractFilterPresenter;
import io.crs.hsys.client.core.ui.filter.AbstractFilterUiHandlers;
import io.crs.hsys.shared.constans.TaskKind;

/**
 * @author robi
 *
 */
public class TaskGroupFilterPresenter extends AbstractFilterPresenter<TaskGroupFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(TaskGroupFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
		void setTaskKindData(List<TaskKind> taskKindData);

		TaskKind getSelectedTaskKind();
	}

	@Inject
	TaskGroupFilterPresenter(EventBus eventBus, TaskGroupFilterPresenter.MyView view, CurrentUser currentUser) {
		super(eventBus, view, currentUser);
		logger.info("TaskGroupFilterPresenter()");

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().setTaskKindData(Arrays.asList(TaskKind.values()));
	}

	public TaskKind getSelectedTaskKind() {
		return getView().getSelectedTaskKind();
	}
}
