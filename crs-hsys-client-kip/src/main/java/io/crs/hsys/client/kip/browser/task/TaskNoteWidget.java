/**
 * 
 */
package io.crs.hsys.client.kip.browser.task;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import io.crs.hsys.shared.dto.task.TaskNoteDto;

/**
 * @author robi
 *
 */
public class TaskNoteWidget extends Composite {

	private static TaskNoteDisplayUiBinder uiBinder = GWT.create(TaskNoteDisplayUiBinder.class);

	interface TaskNoteDisplayUiBinder extends UiBinder<Widget, TaskNoteWidget> {
	}

	@UiField
	Label created, note, who;

	/**
	 * 
	 */
	public TaskNoteWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public TaskNoteWidget(TaskNoteDto note) {
		this();
		DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy.MM.dd. hh:mm");
		this.created.setText(fmt.format(note.getCreated()));
		this.note.setText(note.getText());
		this.who.setText(note.getUser().getCode());
	}

}
