/**
 * 
 */
package io.crs.hsys.client.kip.filter.taskgroup;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.ui.filter.AbstractFilterPresenter;
import io.crs.hsys.client.core.ui.filter.AbstractFilterUiHandlers;
import io.crs.hsys.shared.cnst.TaskKind;

/**
 * @author robi
 *
 */
public abstract class AbstractTaskGroupFilterPresenter<V extends AbstractTaskGroupFilterPresenter.MyView>
		extends AbstractFilterPresenter<V> {
	private static Logger logger = Logger.getLogger(AbstractTaskGroupFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
		void setTaskKindData(List<TaskKind> taskKindData);

		TaskKind getSelectedTaskKind();
	}

	protected AbstractTaskGroupFilterPresenter(EventBus eventBus, V view,
			CurrentUser currentUser) {
		super(eventBus, view, currentUser);
		logger.info("AbstractTaskGroupFilterPresenter()");

		getView().setUiHandlers(this);
	}

	@Override
	public void onBind() {
		super.onBind();
		getView().setTaskKindData(Arrays.asList(TaskKind.values()));
	}

	public TaskKind getSelectedTaskKind() {
		return getView().getSelectedTaskKind();
	}
}
