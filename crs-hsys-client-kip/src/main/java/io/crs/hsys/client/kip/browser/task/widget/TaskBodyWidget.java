/**
 * 
 */
package io.crs.hsys.client.kip.browser.task.widget;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialColumn;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.dto.common.TranslationDto;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.shared.dto.task.TaskNoteDto;
import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
public class TaskBodyWidget extends Composite {

	interface Binder extends UiBinder<Widget, TaskBodyWidget> {
	}

	@UiField
	MaterialColumn descriptionPanel, todosPanel, notesPanel;

	@UiField
	Label description, todos;

	@Inject
	Provider<TaskNoteWidget> taskNoteWidgetProvider;

	private final CurrentUser currentUser;

	/**
	 */
	@Inject
	TaskBodyWidget(Binder uiBinder, CurrentUser currentUser) {
		initWidget(uiBinder.createAndBindUi(this));
		this.currentUser = currentUser;
	}

	public void setDescription(String text) {
		if ((text == null) || (text.isEmpty())) {
			descriptionPanel.setVisible(false);
			return;
		}
		description.setText(text);
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
			String tmp = getTranslated(currentUser.getLocale(), todo.getTranslations(), todo.getDescription());
			if (first) {
				text.append(tmp);
				first = false;
			} else
				text.append(", " + tmp);
		}
		this.todos.setText(text.toString());
		todosPanel.setVisible(true);
	}

	private String getTranslated(String locale, List<TranslationDto> translations, String defText) {
		if ((translations == null) || (translations.isEmpty()))
			return defText;

		TranslationDto t = null;
		try {
			t = translations.stream().filter(o -> o.getLanguage().equals(currentUser.getLocale())).findFirst().get();
		} catch (Exception e) {
			// Block of code to handle errors
		}

		if (t == null)
			return defText;

		return t.getText();
	}

	public void setNotes(List<TaskNoteDto> notes) {
		if ((notes == null) || (notes.isEmpty())) {
			notesPanel.setVisible(false);
			return;
		}

		Boolean isEven = true;
		for (TaskNoteDto note : notes) {
			TaskNoteWidget nw = taskNoteWidgetProvider.get();
			nw.setNote(note);
			notesPanel.add(nw);
			if (isEven)
				nw.setDarkerBackgorund();
			isEven = !isEven;
		}
		notesPanel.setVisible(true);
	}

	public void setTask(TaskDto task) {
		setDescription(task.getDescription());
		setTodos(task.getType().getTodos());
		setNotes(task.getNotes());
	}
}
