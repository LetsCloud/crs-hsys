/**
 * 
 */
package io.crs.hsys.client.kip.browser.task.widget;

import java.util.Date;
import java.util.Map;

import javax.inject.Inject;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.shared.constans.TaskNoteType;
import io.crs.hsys.shared.dto.task.TaskNoteDto;

/**
 * @author robi
 *
 */
public class TaskNoteWidget extends Composite {

	interface Binder extends UiBinder<Widget, TaskNoteWidget> {
	}

	@UiField
	Label created, note, who;

	private final CoreConstants coreCnst;

	/**
	 * 
	 */
	@Inject
	TaskNoteWidget(Binder uiBinder, CoreConstants coreCnst) {
		this.coreCnst = coreCnst;
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setNote(TaskNoteDto note) {
		DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy.MM.dd. hh:mm");
		this.created.setText(fmt.format(note.getCreated()));
		this.note.setText(getText(note));
		this.who.setText(note.getUser().getCode());
	}

	private String getText(TaskNoteDto note) {
		Map<String, String> tntMap = coreCnst.taskNoteTypeMap();

		if (note.getType().equals(TaskNoteType.TNT_CREATED))
			return tntMap.get(TaskNoteType.TNT_CREATED.toString());

		if (note.getType().equals(TaskNoteType.TNT_MOD_DUEDATE))
			return createDueDateText(tntMap.get(TaskNoteType.TNT_MOD_DUEDATE.toString()), note.getText());

		if (note.getType().equals(TaskNoteType.TNT_MOD_STATUS))
			return coreCnst.taskStatusMap().get(note.getText()) ;

		return tntMap.get(note.getType().toString()) + ": " + note.getText();
	}

	private String createDueDateText(String label, String dueDateMiliSec) {
		Date dueDate = new Date(new Long(dueDateMiliSec));
		DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy.MM.dd. hh:mm");
		return label + ": " + fmt.format(dueDate);
	}
}
