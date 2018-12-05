/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.controll;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.html.Div;
import io.crs.hsys.client.kip.roomstatus.RoomStatusUtils;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.task.TaskDto;

/**
 * @author robi
 *
 */
public class RoomStatusControllTaskWidget extends Composite {
	private static final Logger logger = Logger.getLogger(RoomStatusControllTaskWidget.class.getName());

	private static RoomStatusControllTaskWidgetUiBinder uiBinder = GWT
			.create(RoomStatusControllTaskWidgetUiBinder.class);

	interface RoomStatusControllTaskWidgetUiBinder extends UiBinder<Widget, RoomStatusControllTaskWidget> {
	}

	@UiField
	MaterialIcon leftIcon, rightIcon;

	@UiField
	Div leftCol, middleCol, rightCol, row;

	@UiField
	Label description;

	@UiField
	MaterialCollection toDoList;
	
	/**
	 * 
	 */
	public RoomStatusControllTaskWidget() {
		logger.log(Level.INFO, "RoomStatusControllTaskWidget()");
		initWidget(uiBinder.createAndBindUi(this));
//		initView(true, TaskKind.CLEANING, false);
	}

	public RoomStatusControllTaskWidget(Boolean admin, Boolean owner, TaskDto task, Boolean oddLine) {
		this();
		logger.log(Level.INFO, "RoomStatusControllTaskWidget()->admin=" + admin);
		initView(admin, owner, task.getKind(), oddLine);
		if (task.getDescription() != null)
			description.setText(task.getDescription());
		else
			description.setText(task.getType().getDescription());

		if (task.getAssignee() != null)
			description.setText(description.getText() + ">>" + task.getAssignee().getCode());
		else
			description.setText(description.getText() + ">>???");
	}

	private void initView(Boolean admin, Boolean owner, TaskKind kind, Boolean oddLine) {
		logger.log(Level.INFO, "RoomStatusControllTaskWidget().initView()->admin=" + admin);
		if (admin) {
//			leftIcon.setIconType(IconType.DELETE);
			rightIcon.setIconType(IconType.EDIT);
		} else {
			if (owner)
				rightIcon.setIconType(IconType.PLAY_ARROW);
			else
//			leftIcon.setIconType(IconType.STOP); KEYBOARD_ARROW_DOWN
				rightIcon.setIconType(IconType.ARROW_DROP_DOWN);
		}

		leftIcon.setIconType(RoomStatusUtils.getTaskIcon(kind));

//		leftCol.setBackgroundColor(RoomStatusUtils.getTaskBgColor(kind));
		leftCol.setTextColor(RoomStatusUtils.getTaskColor(kind));

		if (oddLine)
			row.setBackgroundColor(Color.GREY_LIGHTEN_4);

//		rightCol.setBackgroundColor(RoomStatusUtils.getTaskColor(kind));
//		rightCol.setTextColor(RoomStatusUtils.getTaskBgColor(kind));
	}
}
