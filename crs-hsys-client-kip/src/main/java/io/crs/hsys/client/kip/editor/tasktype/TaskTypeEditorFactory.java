package io.crs.hsys.client.kip.editor.tasktype;

import io.crs.hsys.client.kip.editor.tasktype.todo.AddTaskTodoPresenter;

public interface TaskTypeEditorFactory {

	AddTaskTodoPresenter createAddTaskTodo();

}
