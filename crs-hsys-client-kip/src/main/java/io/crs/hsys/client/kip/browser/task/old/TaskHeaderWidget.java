/**
 * 
 */
package io.crs.hsys.client.kip.browser.task.old;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.kip.roomstatus.RoomStatusUtils;
import io.crs.hsys.shared.constans.RoomStatus;
import io.crs.hsys.shared.constans.TaskAttrType;
import io.crs.hsys.shared.constans.TaskStatus;
import io.crs.hsys.shared.constans.UserPerm;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.common.AppUserDtor;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.task.TaskDto;

/**
 * @author robi
 *
 */
public class TaskHeaderWidget extends Composite {

	private static TaskTemplateUiBinder uiBinder = GWT.create(TaskTemplateUiBinder.class);

	interface TaskTemplateUiBinder extends UiBinder<Widget, TaskHeaderWidget> {
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
	MaterialLabel title, desde;

//	@UiField
//	Label time;

	@UiField
	MaterialLink dueDate;

	/**
	 */
	public TaskHeaderWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		iniView();
	}

	public TaskHeaderWidget(TaskDto task, CurrentUser currentUser) {
		this();
		setTask(task, currentUser);
	}

	private void iniView() {
//		title.getElement().getFirstChildElement().getNextSiblingElement().getStyle().setFontSize(20, Unit.PX);
//		title.getElement().getFirstChildElement().getNextSiblingElement().getStyle().setColor("#616161");
		title.setFontSize(22, Unit.PX);
		title.setTextColor(Color.GREY_DARKEN_2);
		title.setTruncate(true);
		desde.setFontSize(16, Unit.PX);
		desde.setTextColor(Color.GREY_DARKEN_2);
		dueDate.getIcon().getElement().getStyle().setMarginRight(5, Unit.PX);

		menuDropDown.clear();
	}

	public void setTask(TaskDto task, CurrentUser currentUser) {
		menuIcon.addClickHandler(event -> {
			event.preventDefault();
			event.stopPropagation();
		});
		menuIcon.setActivates("tm-" + task.getWebSafeKey());
		menuDropDown.setActivator("tm-" + task.getWebSafeKey());
		title.setText(task.getType().getDescription());
//		title.setEnabled(false);
		desde.setText("1 perce");
		setTaskType(task.getKind());
		setTaskStatus(task.getStatus());

		if (task.getRoom() != null) {
			taskLine.add(createRoomLink(task.getRoom()));
		}

		if (task.getReporter() != null) {
			if (task.getReporter().getCode().equals(currentUser.getAppUserDto().getCode())) {
				switch (task.getStatus()) {
				case NOT_STARTED:
					initButtons(currentUser.getAppUserDto().getPermissions().get(0));
					menuDropDown.add(createModifyLink());
					menuDropDown.add(createDeleteLink());
					break;
				case IN_PROGRESS:
					break;
				case DEFFERED:
					initButtons(currentUser.getAppUserDto().getPermissions().get(0));
					break;
				case COMPLETED:
//					closeLink.setVisible(true);
					break;
				case DELETED:
//					closeLink.setVisible(true);
					break;
				default:
					break;
				}
			}
			taskLine.add(createReporterLink(task.getReporter()));
		}

		if (task.getAssignee() != null) {
			taskLine.add(createAssigneeLink(task.getAssignee()));
			if (task.getAssignee().getCode().equals(currentUser.getAppUserDto().getCode())) {
				switch (task.getStatus()) {
				case NOT_STARTED:
					menuDropDown.add(createStartLink());
					break;
				case IN_PROGRESS:
					menuDropDown.add(createPauseLink());
					menuDropDown.add(createCompleteLink());
					break;
				case DEFFERED:
					menuDropDown.add(createStartLink());
					menuDropDown.add(createCompleteLink());
					break;
				case COMPLETED:
//					closeLink.setVisible(true);
					break;
				case DELETED:
//					closeLink.setVisible(true);
					break;
				default:
					break;
				}
			}
		}
		menuDropDown.add(createBackLink());
	}

	private void setTaskType(TaskKind type) {
		taskKind.setIconType(RoomStatusUtils.getTaskIcon(type));
		taskKind.setBackgroundColor(Color.WHITE);
		taskKind.setTextColor(RoomStatusUtils.getTaskColor(type));
	}

	private void setTaskStatus(TaskStatus status) {
		switch (status) {
		case NOT_STARTED:
			taskStatus.setIconType(IconType.RADIO_BUTTON_UNCHECKED);
			// taskStatus.setBackgroundColor(Color.WHITE);
			taskStatus.setTextColor(Color.RED);
			break;
		case IN_PROGRESS:
			taskStatus.setIconType(IconType.PLAY_CIRCLE_OUTLINE);
			// taskStatus.setBackgroundColor(Color.WHITE);
			taskStatus.setTextColor(Color.BLUE);
			break;
		case DEFFERED:
			taskStatus.setIconType(IconType.PAUSE_CIRCLE_OUTLINE);
			// taskStatus.setBackgroundColor(Color.WHITE);
			taskStatus.setTextColor(Color.AMBER);
			break;
		case COMPLETED:
			taskStatus.setIconType(IconType.RADIO_BUTTON_CHECKED);
			// taskStatus.setBackgroundColor(Color.WHITE);
			taskStatus.setTextColor(Color.GREEN);

//			startLink.setDisplay(Display.BLOCK);
			break;
		case DELETED:
			taskStatus.setIconType(IconType.HIGHLIGHT_OFF);
			// taskStatus.setBackgroundColor(Color.WHITE);
			taskStatus.setTextColor(Color.GREY);
			break;
		default:
			break;
		}
	}

	public MaterialIcon getEditIcon() {
		return menuIcon;
	}

	public void setTitle(String title) {
		this.title.setText(title);
	}

	public void setTime(String time) {
		this.desde.setText(time);
	}

	private MaterialLink getBadgeLink(String text) {
		MaterialLink link = new MaterialLink(text);
		link.addStyleName(style.badgeStyle());
		link.getIcon().getElement().getStyle().setLineHeight(30, Unit.PX);
		link.setIconSize(IconSize.SMALL);
		link.setFontSize(20, Unit.PX);
		link.setTextColor(Color.BLACK);
		link.setIconPosition(IconPosition.LEFT);
		link.getIcon().setMarginRight(5);
		return link;
	}

	private MaterialLink createRoomLink(RoomDto room) {
		MaterialLink link = getBadgeLink(room.getCode());
		link.setBackgroundColor(RoomStatusUtils.getStatusIconColor(room.getRoomStatus()));
		link.setIconColor(RoomStatusUtils.getStatusBgColor(room.getRoomStatus()));
		link.setIconType(RoomStatusUtils.getStatusIcon2(room.getRoomStatus()));
		return link;
	}

	private MaterialLink createReporterLink(AppUserDtor user) {
		MaterialLink link = getBadgeLink(user.getCode());
		link.setBackgroundColor(Color.GREY_LIGHTEN_2);
		link.setIconType(IconType.RECORD_VOICE_OVER);
		return link;
	}

	private MaterialLink createAssigneeLink(AppUserDtor user) {
		MaterialLink link = getBadgeLink(user.getCode());
		link.setBackgroundColor(Color.GREY_LIGHTEN_2);
		link.setIconType(IconType.ASSIGNMENT_IND);
		return link;
	}

	private MaterialLink createAttLink(TaskAttrType type, String value) {
		MaterialLink link = getBadgeLink(value);
		link.setBackgroundColor(Color.GREY_LIGHTEN_2);

		switch (type) {
		case REPORTER:
			link.setIconType(IconType.PERSON_PIN);
			break;
//		case INSPECTOR:
//			link.setIconType(IconType.RECORD_VOICE_OVER);
//			break;
		case ROOM:
			link.setBackgroundColor(RoomStatusUtils.getStatusIconColor(RoomStatus.DIRTY));
			link.setIconColor(RoomStatusUtils.getStatusBgColor(RoomStatus.DIRTY));
			link.setIconType(IconType.DELETE);
			break;
//		case GUEST_RQ_TYPE:
//			link.setIconType(IconType.ADD_SHOPPING_CART);
//			break;
//		case MX_CAT:
//			link.setIconType(IconType.STYLE);
//			break;
//		case MX_TYPE:
//			link.setIconType(IconType.LOCAL_OFFER);
//			break;
		default:
			break;
		}
		return link;
	}

	private void initButtons(UserPerm permission) {
		switch (permission) {
		case UP_HKSUPERVISOR:
			menuDropDown.add(createReassignLink());
			break;
		case UP_HOUSEKEEPER:
			break;
		case UP_MAINTMANAGER:
			break;
		case UP_TECHNICIAN:
			break;
		case UP_RECEPTIONIST:
			break;
		case UP_ADMIN:
			break;
		default:
			break;
		}
	}

	private MaterialLink createDropDown(String text, IconType type) {
		MaterialLink link = new MaterialLink(text);
		link.setIconType(type);
		link.setIconPosition(IconPosition.LEFT);
		link.setSeparator(true);
		link.setBackgroundColor(Color.GREY_DARKEN_2);
		link.setTextColor(Color.WHITE);
		link.setIconColor(Color.WHITE);
		link.addClickHandler(event -> {
			event.preventDefault();
			event.stopPropagation();
			MaterialToast.fireToast("I Love Material Design");
		});
		return link;
	}

	private MaterialLink createStartLink() {
		return createDropDown("Indít", IconType.PLAY_ARROW);
	}

	private MaterialLink createPauseLink() {
		return createDropDown("Szünetelet", IconType.PAUSE);
	}

	private MaterialLink createCompleteLink() {
		return createDropDown("Befejez", IconType.DONE);
	}

	private MaterialLink createReassignLink() {
		return createDropDown("Átoszt", IconType.SYNC);
	}

	private MaterialLink createModifyLink() {
		return createDropDown("Módosít", IconType.EDIT);
	}

	private MaterialLink createDeleteLink() {
		return createDropDown("Töröl", IconType.DELETE);
	}

	private MaterialLink createBackLink() {
		return createDropDown("Vissza", IconType.KEYBOARD_RETURN);
	}
}
