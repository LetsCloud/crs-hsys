/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype.todo;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
public interface AddTaskTodoUiHandlers extends UiHandlers {
	void onTodoSearchChange(String todo);
	void onTaskGroupFilterChange(String code);
	/**
	 * Tennivaló kiválasztása, vagy a kiválasztás törlése
	 * 
	 * @param todo
	 */
	void onClickTodo(TaskTodoDto todo);

	/**
	 * A kiválasztott tennivalók hozzáadása a feladat típushoz
	 */
	void onAddTodos();
}
