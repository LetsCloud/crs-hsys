/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
public interface AddTaskTodoUiHandlers extends UiHandlers {
	void onClickTodo(TaskTodoDto todo);
	void onAddTodos();
}
