/**
 * 
 */
package io.crs.hsys.client.kip.tasks;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author robi
 *
 */
public class TaskNoteDisplay extends Composite {

	private static TaskNoteDisplayUiBinder uiBinder = GWT.create(TaskNoteDisplayUiBinder.class);

	interface TaskNoteDisplayUiBinder extends UiBinder<Widget, TaskNoteDisplay> {
	}

	@UiField
	Label created, note, who;

	/**
	 * 
	 */
	public TaskNoteDisplay() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public TaskNoteDisplay(Date created, String note, String who) {
		this();
		this.created.setText(created.toString());
		this.note.setText(note);
		this.who.setText(who);
	}

}
