/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;
import io.crs.hsys.client.core.datasource.TaskGroupDataSource;
import io.crs.hsys.client.core.datasource.TaskTodoDataSource;
import io.crs.hsys.client.kip.browser.tasktodo.TaskTodoBrowserPresenter;
import io.crs.hsys.client.kip.filter.tasktodo.TaskTodoFilterView;
import io.crs.hsys.shared.dto.task.TaskGroupDto;
import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
public class AddTaskTodoPresenter extends PresenterWidget<AddTaskTodoPresenter.MyView>
		implements AddTaskTodoUiHandlers {
	private static Logger logger = Logger.getLogger(TaskTodoBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<AddTaskTodoUiHandlers> {
		void setTaskGroupData(List<TaskGroupDto> data);

		void setTaskTodoData(List<TaskTodoDto> data);

		void open();
	}

	private String filterTaskGroupKey, todoSearch;
	private final TaskTodoDataSource taskTodoDataSource;
	private final List<TaskTodoDto> todos = new ArrayList<TaskTodoDto>();
	private final TaskGroupDataSource taskGroupDataSource;

	@Inject
	AddTaskTodoPresenter(EventBus eventBus, AddTaskTodoPresenter.MyView view, PlaceManager placeManager,
			TaskTodoDataSource taskTodoDataSource, TaskGroupDataSource taskGroupDataSource) {
		super(eventBus, view);
		logger.info("AddTaskTodoPresenter()");
		this.taskTodoDataSource = taskTodoDataSource;
		this.taskGroupDataSource = taskGroupDataSource;
		getView().setUiHandlers(this);
	}

	public void open(String taskGroupKey) {
		this.filterTaskGroupKey = taskGroupKey;
		logger.info("AddTaskTodoPresenter().open()->start");
		todos.clear();
		loadTaskTodoData(true);
		loadTaskGroupData();
		logger.info("AddTaskTodoPresenter().open()->end");
	}

	private void loadTaskTodoData(Boolean open) {
		LoadCallback<TaskTodoDto> taskTodoLoadCallback = new LoadCallback<TaskTodoDto>() {
			@Override
			public void onSuccess(LoadResult<TaskTodoDto> loadResult) {
				logger.info("AddTaskTodoPresenter().loadTaskTodoData().onSuccess()->start");
				List<TaskTodoDto> result = loadResult.getData();
				if ((todoSearch != null) && (!todoSearch.isEmpty()))
					result = result.stream().filter(tg -> tg.getDescription().contains(todoSearch))
							.collect(Collectors.toList());
				logger.info("AddTaskTodoPresenter().loadTaskTodoData().onSuccess()->1");

				if (!filterTaskGroupKey.equals(TaskTodoFilterView.ALL_ITEMS))
					result = result.stream().filter(tg -> tg.getTaskGroup().getWebSafeKey().equals(filterTaskGroupKey))
							.collect(Collectors.toList());
				logger.info("AddTaskTodoPresenter().loadTaskTodoData().onSuccess()->2");

				getView().setTaskTodoData(result);
				if (open)
					getView().open();
				logger.info("AddTaskTodoPresenter().loadTaskTodoData().onSuccess()->end");
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};
		taskTodoDataSource.load(new LoadConfig<TaskTodoDto>(0, 0, null, null), taskTodoLoadCallback);
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
	public void onClickTodo(TaskTodoDto todo) {
		if (todos.contains(todo)) {
			todos.remove(todo);
			return;
		}
		todos.add(todo);
	}

	@Override
	public void onAddTodos() {
		logger.info("AddTaskTodoPresenter().onAddTodos()");
		for (TaskTodoDto tt : todos)
			logger.info("AddTaskTodoPresenter().onAddTodos()->" + tt.getDescription());
		AddTaskTodoEvent.fire(this, todos);
	}

	@Override
	public void onTaskGroupFilterChange(String code) {
		filterTaskGroupKey = code;
		loadTaskTodoData(false);
	}

	@Override
	public void onTodoSearchChange(String todo) {
		todoSearch = todo;
		loadTaskTodoData(false);
	}

}
