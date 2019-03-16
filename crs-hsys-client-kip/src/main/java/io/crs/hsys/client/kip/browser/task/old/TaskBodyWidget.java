/**
 * 
 */
package io.crs.hsys.client.kip.browser.task.old;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialColumn;
import io.crs.hsys.client.kip.browser.task.widget.TaskNoteWidget;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.shared.dto.task.TaskNoteDto;
import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author CR
 *
 */
public class TaskBodyWidget extends Composite {

	private static TaskBodyWidgetUiBinder uiBinder = GWT.create(TaskBodyWidgetUiBinder.class);

	interface TaskBodyWidgetUiBinder extends UiBinder<Widget, TaskBodyWidget> {
	}

	@UiField
	MaterialColumn descriptionPanel, todosPanel, notesPanel;
	
	@UiField
	Label description, todos;

	/**
	 * 
	 */
	public TaskBodyWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public TaskBodyWidget(TaskDto task) {
		this();
		setDescription(task.getDescription());
		setTodos(task.getType().getTodos());
		setNotes(task.getNotes());
	}

	public void setDescription(String description) {
		if ((description == null) || (description.isEmpty())) {
			descriptionPanel.setVisible(false);
			return;
		}
		this.description.setText(description);
		descriptionPanel.setVisible(true);
	}

	public void setTodos(List<TaskTodoDto> todos) {
		if ((todos == null) || (todos.isEmpty())) {
			todosPanel.setVisible(false);
			return;
		}
		
		Boolean first = true;
		StringBuilder text = new StringBuilder();
		for (TaskTodoDto todo : todos) {
			if (first) {
				text.append(todo.getDescription());
				first = false;
			}
			else
				text.append(", " + todo.getDescription());
		}
		this.todos.setText(text.toString());
		todosPanel.setVisible(true);
	}

	public void setNotes(List<TaskNoteDto> notes) {
		if ((notes == null) || (notes.isEmpty())) {
			notesPanel.setVisible(false);
			return;
		}
		
		for (TaskNoteDto note : notes) {
			notesPanel.add(new TaskNoteWidget(note));
		}
		notesPanel.setVisible(true);
	}
}
