/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;
import io.crs.hsys.client.core.datasource.TaskTodoDataSource;
import io.crs.hsys.client.kip.browser.tasktodo.TaskTodoBrowserPresenter;
import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
public class AddTaskTodoPresenter extends PresenterWidget<AddTaskTodoPresenter.MyView>
		implements AddTaskTodoUiHandlers {
	private static Logger logger = Logger.getLogger(TaskTodoBrowserPresenter.class.getName());

	private final TaskTodoDataSource taskTodoDataSource;
	private final List<TaskTodoDto> todos = new ArrayList<TaskTodoDto>();

	public interface MyView extends View, HasUiHandlers<AddTaskTodoUiHandlers> {

		void setTaskTodoData(List<TaskTodoDto> data);

		void open();
	}

	@Inject
	AddTaskTodoPresenter(EventBus eventBus, AddTaskTodoPresenter.MyView view, PlaceManager placeManager,
			TaskTodoDataSource taskTodoDataSource) {
		super(eventBus, view);
		logger.info("AddTaskTodoPresenter()");
		this.taskTodoDataSource = taskTodoDataSource;
		getView().setUiHandlers(this);
	}

	public void open() {
		loadTaskTodoData();
	}

	private void loadTaskTodoData() {
		LoadCallback<TaskTodoDto> taskTodoLoadCallback = new LoadCallback<TaskTodoDto>() {
			@Override
			public void onSuccess(LoadResult<TaskTodoDto> loadResult) {
				getView().setTaskTodoData(loadResult.getData());
				getView().open();
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};
		taskTodoDataSource.load(new LoadConfig<TaskTodoDto>(0, 0, null, null), taskTodoLoadCallback);
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

}
