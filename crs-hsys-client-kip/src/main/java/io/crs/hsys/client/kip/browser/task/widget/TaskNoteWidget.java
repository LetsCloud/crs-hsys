/**
 * 
 */
package io.crs.hsys.client.kip.browser.task.widget;

import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialRow;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.shared.cnst.Constants;
import io.crs.hsys.shared.cnst.TaskNoteType;
import io.crs.hsys.shared.dto.task.TaskNoteDto;

/**
 * @author robi
 *
 */
public class TaskNoteWidget extends Composite {
	private static Logger logger = Logger.getLogger(TaskNoteWidget.class.getName());

	interface Binder extends UiBinder<Widget, TaskNoteWidget> {
	}

	@UiField
	MaterialRow panel;

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
			return coreCnst.taskStatusMap().get(note.getText());

		return tntMap.get(note.getType().toString()) + ": " + note.getText();
	}

	private String createDueDateText(String label, String dueDateMiliSec) {
		logger.info("createDueDateText()->label=" + label);
		logger.info("createDueDateText()->dueDateMiliSec=" + dueDateMiliSec);
		if ((dueDateMiliSec == null) || (dueDateMiliSec.isEmpty()) || (dueDateMiliSec.equals(Constants.NO_PREV_NOTE)))
			return label + ": -";
		logger.info("createDueDateText()->3");
		Date dueDate = new Date(new Long(dueDateMiliSec));
		logger.info("createDueDateText()->dueDate=" + dueDate);
		DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy.MM.dd. hh:mm");
		return label + ": " + fmt.format(dueDate);
	}

	public void setDarkerBackgorund() {
		panel.setBackgroundColor(Color.GREY_LIGHTEN_4);
	}

}
