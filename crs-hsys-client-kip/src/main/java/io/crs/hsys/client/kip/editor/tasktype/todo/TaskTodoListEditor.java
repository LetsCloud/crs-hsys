/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype.todo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCollection;

import io.crs.hsys.client.core.editor.room.DeleteEvent;
import io.crs.hsys.shared.dto.task.TaskGroupDto;
import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
public class TaskTodoListEditor extends Composite implements IsEditor<ListEditor<TaskTodoDto, TaskTodoEditor>> {

	interface Binder extends UiBinder<Widget, TaskTodoListEditor> {
	}

	@Ignore
	@UiField
	MaterialCollection listPanel;

	@Inject
	Provider<TaskTodoEditor> editorProvider;

	/**
	 * An entity capable of creating and destroying instances of Editors. This type
	 * is used by Editors which operate on ordered data, sich as ListEditor.
	 * 
	 * @author cr
	 * 
	 */
	private class TaskTodoEditorSource extends EditorSource<TaskTodoEditor> {

		/**
		 * Create a new Editor. Parameters: index - the position at which the new Editor
		 * should be displayed Returns: an Editor of type E
		 */
		@Override
		public TaskTodoEditor create(final int index) {
			TaskTodoEditor subEditor = editorProvider.get();

			listPanel.insert(subEditor, index);

			subEditor.addDeleteHandler(new DeleteEvent.DeleteEventHandler() {
				public void onDeleteEvent(DeleteEvent event) {
					remove(index);
				}
			});

			return subEditor;
		}

		/**
		 * Called when an Editor no longer requires a sub-Editor. The default
		 * implementation is a no-op.
		 */
		@Override
		public void dispose(TaskTodoEditor subEditor) {
			subEditor.removeFromParent();
		}

		/**
		 * Re-order a sub-Editor. The default implementation is a no-op.
		 */
		@Override
		public void setIndex(TaskTodoEditor editor, int index) {
			listPanel.insert(editor, index);
		}
	}

	private ListEditor<TaskTodoDto, TaskTodoEditor> editor = ListEditor.of(new TaskTodoEditorSource());

	private TaskGroupDto taskGroup;
	private AddTaskTodoPresenter addTaskTodo;

	/**
	*/
	@Inject
	TaskTodoListEditor(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setAddTaskTodo(AddTaskTodoPresenter addTaskTodo) {
		this.addTaskTodo = addTaskTodo;
	}

	@Override
	public ListEditor<TaskTodoDto, TaskTodoEditor> asEditor() {
		return editor;
	}

	@UiHandler("addButton")
	void onAddClick(ClickEvent event) {
		addTaskTodo.open(taskGroup.getCode());
//		TaskTodoDto dto = new TaskTodoDto();
//		editor.getList().add(dto);
	}

	@UiHandler("deleteButton")
	void onDeleteClick(ClickEvent event) {
		for (int i = 0; i < editor.getEditors().size(); i++) {
			if (editor.getEditors().get(i).isSelected())
				remove(i);
		}
	}

	private void remove(final int index) {
		editor.getList().remove(index);
	}

	public void addTaskTodos(List<TaskTodoDto> todos) {
		editor.getList().addAll(todos);
	}
}
