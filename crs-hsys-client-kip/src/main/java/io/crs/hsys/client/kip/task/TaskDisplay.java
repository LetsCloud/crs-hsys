/**
 * 
 */
package io.crs.hsys.client.kip.task;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.Display;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLink;

import io.crs.hsys.shared.constans.TaskAttrType;
import io.crs.hsys.shared.constans.TaskStatus;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.task.TaskAttrDto;
import io.crs.hsys.shared.dto.task.TaskDto;

/**
 * @author robi
 *
 */
public class TaskDisplay extends Composite {

	private static TaskTemplateUiBinder uiBinder = GWT.create(TaskTemplateUiBinder.class);

	interface TaskTemplateUiBinder extends UiBinder<Widget, TaskDisplay> {
	}

	interface MyStyle extends CssResource {
		String lineThrough();

		String titleStyle();

		String badgeStyle();

		String floatLeft();

		String floatRight();

		String boxEnd();
	}

	@UiField
	MyStyle style;

	@UiField
	HTMLPanel taskLine;

	@UiField
	MaterialIcon menuIcon, taskKind, taskStatus;

	@UiField
	MaterialDropDown menuDropDown;

	@UiField
	MaterialCheckBox title;

	@UiField
	Label time;

	@UiField
	MaterialLink startLink, closeLink, editTask, deleteLink;

	/**
	 */
	public TaskDisplay() {
		initWidget(uiBinder.createAndBindUi(this));
		iniView();
	}

	public TaskDisplay(TaskDto task) {
		this();
		setTask(task);
	}

	private void iniView() {
		title.getElement().getFirstChildElement().getNextSiblingElement().getStyle().setFontSize(22, Unit.PX);
		title.getElement().getFirstChildElement().getNextSiblingElement().getStyle().setColor("#616161");
	}

	public void setTask(TaskDto task) {
		menuIcon.setActivates("tm-" + task.getWebSafeKey());
		menuDropDown.setActivator("tm-" + task.getWebSafeKey());
		title.setText(task.getTitle());
		time.setText(task.getReporter().getName());
		setTaskType(task.getKind());
		setTaskStatus(task.getStatus());

		for (TaskAttrDto taskAttr : task.getAttributes()) {
			taskLine.add(createAttLink(taskAttr.getType(), taskAttr.getValue()));
		}

//		if (task.getReporter() != null)
//			taskLine.add(createAttLink(TaskAttrType.REPORTER, task.getReporter().getCode()));
	}

	private void setTaskType(TaskKind type) {
		switch (type) {
		case TK_CLEANING:
			taskKind.setIconType(IconType.BRUSH);
			taskKind.setBackgroundColor(Color.WHITE);
			taskKind.setTextColor(Color.GREY_DARKEN_2);
			break;
/*
		case TK_REQUEST:
			taskKind.setIconType(IconType.ADD_SHOPPING_CART);
			taskKind.setBackgroundColor(Color.WHITE);
			taskKind.setTextColor(Color.GREY_DARKEN_2);
			break;
*/
		case TK_MAINTENANCE:
			taskKind.setIconType(IconType.BUILD);
			taskKind.setBackgroundColor(Color.WHITE);
			taskKind.setTextColor(Color.GREY_DARKEN_2);
			break;
		default:
			break;
		}
	}

	private void setTaskStatus(TaskStatus status) {
		switch (status) {
		case NOT_STARTED:
			taskStatus.setIconType(IconType.RADIO_BUTTON_UNCHECKED);
			// taskStatus.setBackgroundColor(Color.WHITE);
			taskStatus.setTextColor(Color.GREY);
			
			startLink.setEnabled(true);
			closeLink.setEnabled(true);
			deleteLink.setEnabled(true);
			break;
		case IN_PROGRESS:
			taskStatus.setIconType(IconType.REFRESH);
			// taskStatus.setBackgroundColor(Color.WHITE);
			taskStatus.setTextColor(Color.BLUE);
			
//			pauseLink.setDisplay(Display.BLOCK);
			closeLink.setDisplay(Display.BLOCK);
			break;
		case DEFFERED:
			taskStatus.setIconType(IconType.PAUSE_CIRCLE_OUTLINE);
			// taskStatus.setBackgroundColor(Color.WHITE);
			taskStatus.setTextColor(Color.AMBER);

			startLink.setDisplay(Display.BLOCK);
			closeLink.setDisplay(Display.BLOCK);
			break;
		case COMPLETED:
			taskStatus.setIconType(IconType.CHECK);
			// taskStatus.setBackgroundColor(Color.WHITE);
			taskStatus.setTextColor(Color.GREEN);

			startLink.setDisplay(Display.BLOCK);
			break;
		case DELETED:
			taskStatus.setIconType(IconType.HIGHLIGHT_OFF);
			// taskStatus.setBackgroundColor(Color.WHITE);
			taskStatus.setTextColor(Color.RED);
			break;
		default:
			break;
		}
	}

	public MaterialIcon getEditIcon() {
		return menuIcon;
	}

	public MaterialLink getEditLink() {
		return editTask;
	}

	public void setTitle(String title) {
		this.title.setText(title);
	}

	public void setTime(String time) {
		this.time.setText(time);
	}

	private MaterialLink createAttLink(TaskAttrType type, String value) {
		MaterialLink link = new MaterialLink(value);

		link.addStyleName(style.badgeStyle());
		link.setIconSize(IconSize.TINY);
		link.setTextColor(Color.BLACK);
		link.setBackgroundColor(Color.GREY_LIGHTEN_2);
		link.setIconPosition(IconPosition.LEFT);
		link.getIcon().setMarginRight(5);

		switch (type) {
		case REPORTER:
			link.setIconType(IconType.PERSON_PIN);
			break;
		case INSPECTOR:
			link.setIconType(IconType.RECORD_VOICE_OVER);
			break;
		case ROOM:
			link.setIconType(IconType.ROOM);
			break;
		case GUEST_RQ_TYPE:
			link.setIconType(IconType.ADD_SHOPPING_CART);
			break;
		case MX_CAT:
			link.setIconType(IconType.STYLE);
			break;
		case MX_TYPE:
			link.setIconType(IconType.LOCAL_OFFER);
			break;
		default:
			break;
		}
		return link;
	}

	@UiHandler("title")
	public void onTitleClick(ClickEvent e) {
		crossOutTitle(title.getValue());
	}

	private void crossOutTitle(Boolean crossOut) {

		if (crossOut) {
			title.getElement().getFirstChildElement().getNextSiblingElement().addClassName(style.lineThrough());
			// title.addStyleName(style.lineThrough());
		} else {
			title.getElement().getFirstChildElement().getNextSiblingElement().removeClassName(style.lineThrough());
			// title.removeStyleName(style.lineThrough());

		}
	}
}
