/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype.todo;

import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
public class AddTaskTodoEvent extends GwtEvent<AddTaskTodoEvent.AddTaskTodoEventHandler> {
	public interface AddTaskTodoEventHandler extends EventHandler {
		void onAddTaskTodoEvent(AddTaskTodoEvent event);
	}

	public static Type<AddTaskTodoEventHandler> TYPE = new Type<AddTaskTodoEventHandler>();

	private List<TaskTodoDto> todos;

	AddTaskTodoEvent(List<TaskTodoDto> todos) {
		this.todos = todos;
	}

	@Override
	public Type<AddTaskTodoEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddTaskTodoEventHandler handler) {
		handler.onAddTaskTodoEvent(this);
	}

	public List<TaskTodoDto> getTodos() {
		return todos;
	}

	public static void fire(HasHandlers source, List<TaskTodoDto> todos) {
		source.fireEvent(new AddTaskTodoEvent(todos));
	}

}