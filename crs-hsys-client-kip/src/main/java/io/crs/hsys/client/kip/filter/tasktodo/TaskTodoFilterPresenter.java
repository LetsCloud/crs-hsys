/**
 * 
 */
package io.crs.hsys.client.kip.filter.tasktodo;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;
import io.crs.hsys.client.core.datasource.TaskGroupDataSource;
import io.crs.hsys.client.core.filter.AbstractFilterUiHandlers;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.kip.filter.taskgroup.AbstractTaskGroupFilterPresenter;
import io.crs.hsys.shared.dto.task.TaskGroupDto;

/**
 * @author robi
 *
 */
public class TaskTodoFilterPresenter extends AbstractTaskGroupFilterPresenter<TaskTodoFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(TaskTodoFilterPresenter.class.getName());

	public interface MyView extends AbstractTaskGroupFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
		void setTaskGroupData(List<TaskGroupDto> data);

		TaskGroupDto getSelectedTaskGroup();
	}

	private final TaskGroupDataSource taskGroupDataSource;

	@Inject
	TaskTodoFilterPresenter(EventBus eventBus, TaskTodoFilterPresenter.MyView view, CurrentUser currentUser,
			TaskGroupDataSource taskGroupDataSource) {
		super(eventBus, view, currentUser);
		logger.info("TaskTodoFilterPresenter()");
		this.taskGroupDataSource = taskGroupDataSource;
		getView().setUiHandlers(this);
	}

	@Override
	public void onBind() {
		super.onBind();

		loadTaskGroupData();
	}

	public TaskGroupDto getSelectedTaskGroup() {
		return getView().getSelectedTaskGroup();
	}

	private void loadTaskGroupData() {
		LoadCallback<TaskGroupDto> taskGroupLoadCallback = new LoadCallback<TaskGroupDto>() {
			@Override
			public void onSuccess(LoadResult<TaskGroupDto> loadResult) {
				getView().setTaskGroupData(loadResult.getData());
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};
		taskGroupDataSource.load(new LoadConfig<TaskGroupDto>(0, 0, null, null), taskGroupLoadCallback);
	}

	@Override
	public void filterChange() {
		// TODO Auto-generated method stub
		
	}
}
