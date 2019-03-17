/**
 * 
 */
package io.crs.hsys.client.kip.browser.task.widget;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;

import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.util.DateUtils;
import io.crs.hsys.client.kip.browser.task.TaskActionEvent;
import io.crs.hsys.client.kip.browser.task.TaskActionEvent.TaskAction;
import io.crs.hsys.client.kip.roomstatus.RoomStatusUtils;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.constans.TaskStatus;
import io.crs.hsys.shared.constans.UserPerm;
import io.crs.hsys.shared.dto.common.TranslationDto;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author robi
 *
 */
public class TaskHeaderWidget extends Composite {
	private static Logger logger = Logger.getLogger(TaskHeaderWidget.class.getName());

	interface Binder extends UiBinder<Widget, TaskHeaderWidget> {
	}

	interface MyStyle extends CssResource {
		String badgeStyle();
	}

	@UiField
	MyStyle style;

	@UiField
	HTMLPanel taskLine;

	@UiField
	MaterialIcon menuIcon, taskKind, taskStatus;

	@UiField
	MaterialDropDown<?> menuDropDown;

	@UiField
	MaterialLabel title, elapsed;

	@UiField
	MaterialLink dueDate;

	private final EventBus eventBus;
	private final CurrentUser currentUser;

	/**
	 */
	@Inject
	TaskHeaderWidget(Binder uiBinder, EventBus eventBus, CurrentUser currentUser) {
		logger.info("TaskHeaderWidget2()");
		initWidget(uiBinder.createAndBindUi(this));
		this.eventBus = eventBus;
		this.currentUser = currentUser;
		iniView();
	}

	private void iniView() {
		dueDate.getIcon().getElement().getStyle().setMarginRight(5, Unit.PX);
		elapsed.setFontSize(16, Unit.PX);
		elapsed.setTextColor(Color.GREY_DARKEN_2);
	}

	private MaterialLink createDropDownLink(String text, IconType type) {
		MaterialLink link = new MaterialLink(text);
		link.setIconType(type);
		link.setIconPosition(IconPosition.LEFT);
		link.setSeparator(true);
		link.setBackgroundColor(Color.GREY_DARKEN_2);
		link.setTextColor(Color.WHITE);
		link.setIconColor(Color.WHITE);
		return link;
	}

	private MaterialLink createStartLink() {
		return createDropDownLink("Indít", IconType.PLAY_ARROW);
	}

	private MaterialLink createPauseLink() {
		return createDropDownLink("Szünetelet", IconType.PAUSE);
	}

	private MaterialLink createCompleteLink() {
		return createDropDownLink("Befejez", IconType.DONE);
	}

	private MaterialLink createReassignLink() {
		return createDropDownLink("Átoszt", IconType.SYNC);
	}

	private MaterialLink createModifyLink(TaskDto task) {
		MaterialLink link = createDropDownLink("Módosít", IconType.EDIT);
		link.addClickHandler(event -> {
			logger.info("TaskHeaderWidget2().onModifyClick()");
			event.preventDefault();
			event.stopPropagation();
			eventBus.fireEvent(new TaskActionEvent(TaskAction.EDIT, task));
		});
		return link;
	}

	private MaterialLink createDeleteLink() {
		return createDropDownLink("Töröl", IconType.DELETE);
	}

	private MaterialLink createBackLink() {
		return createDropDownLink("Vissza", IconType.KEYBOARD_RETURN);
	}

	private void buildReporterDropDown(TaskDto task) {
		switch (task.getStatus()) {
		case TS_NOT_STARTED:
			if (currentUser.getAppUserDto().getPermissions().get(0).equals(UserPerm.UP_HKSUPERVISOR))
				menuDropDown.add(createReassignLink());
			menuDropDown.add(createModifyLink(task));
			menuDropDown.add(createDeleteLink());
			break;
		case TS_IN_PROGRESS:
			break;
		case TS_DEFFERED:
			if (currentUser.getAppUserDto().getPermissions().get(0).equals(UserPerm.UP_HKSUPERVISOR))
				menuDropDown.add(createReassignLink());
			break;
		case TS_COMPLETED:
			break;
		case TS_DELETED:
			break;
		default:
			break;
		}
	}

	private void buildAssigneeDropDown(TaskStatus status) {
		switch (status) {
		case TS_NOT_STARTED:
			menuDropDown.add(createStartLink());
			break;
		case TS_IN_PROGRESS:
			menuDropDown.add(createPauseLink());
			menuDropDown.add(createCompleteLink());
			break;
		case TS_DEFFERED:
			menuDropDown.add(createStartLink());
			menuDropDown.add(createCompleteLink());
			break;
		case TS_COMPLETED:
			break;
		case TS_DELETED:
			break;
		default:
			break;
		}
	}

	private void dropDownConfig(TaskDto task) {
		String activatorId = "tm-" + task.getWebSafeKey();
		menuIcon.setActivates(activatorId);
		menuDropDown.setActivator(activatorId);

		menuIcon.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				event.preventDefault();
				event.stopPropagation();
			}
		});

		menuDropDown.clear();

		if (task.getReporter() != null) {
			if (task.getReporter().getCode().equals(currentUser.getAppUserDto().getCode())) {
				buildReporterDropDown(task);
			}
		}

		if (task.getAssignee() != null) {
			if (task.getAssignee().getCode().equals(currentUser.getAppUserDto().getCode())) {
				buildAssigneeDropDown(task.getStatus());
			}
		}
		menuDropDown.add(createBackLink());
	}

	private void setTaskKind(TaskKind type) {
		taskKind.setIconType(RoomStatusUtils.getTaskIcon(type));
		taskKind.setBackgroundColor(Color.WHITE);
		taskKind.setTextColor(RoomStatusUtils.getTaskColor(type));
	}

	private void setTaskTitle(TaskTypeDto taskType) {
		title.setFontSize(22, Unit.PX);
		title.setTextColor(Color.GREY_DARKEN_2);
		title.setTruncate(true);
		title.setText(getTranslated(currentUser.getLocale(), taskType.getTranslations(), taskType.getDescription()));
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
		link.setBackgroundColor(Color.GREY_LIGHTEN_2);
		return link;
	}

	private void createRoomLink(TaskDto task) {
		if (task.getRoom() == null)
			return;
		MaterialLink link = getBadgeLink(task.getRoom().getCode());
		if (task.getRoom().getRoomStatus() != null) {
			link.setBackgroundColor(RoomStatusUtils.getStatusIconColor(task.getRoom().getRoomStatus()));
			link.setIconColor(RoomStatusUtils.getStatusBgColor(task.getRoom().getRoomStatus()));
			link.setIconType(RoomStatusUtils.getStatusIcon2(task.getRoom().getRoomStatus()));
		}
		taskLine.add(link);
	}

	private void createReporterLink(TaskDto task) {
		if (task.getReporter() == null)
			return;
		MaterialLink link = getBadgeLink(task.getReporter().getCode());
		link.setIconType(IconType.RECORD_VOICE_OVER);
		taskLine.add(link);
	}

	private void createAssigneeLink(TaskDto task) {
		if (task.getAssignee() == null)
			return;
		MaterialLink link = getBadgeLink(task.getAssignee().getCode());
		link.setIconType(IconType.ASSIGNMENT_IND);
		taskLine.add(link);
	}

	private void createTaskLine(TaskDto task) {
		createRoomLink(task);
		createReporterLink(task);
		createAssigneeLink(task);
	}

	private void setTaskStatus(TaskStatus status) {
		switch (status) {
		case TS_NOT_STARTED:
			taskStatus.setIconType(IconType.RADIO_BUTTON_UNCHECKED);
			taskStatus.setTextColor(Color.RED);
			break;
		case TS_IN_PROGRESS:
			taskStatus.setIconType(IconType.PLAY_CIRCLE_OUTLINE);
			taskStatus.setTextColor(Color.BLUE);
			break;
		case TS_DEFFERED:
			taskStatus.setIconType(IconType.PAUSE_CIRCLE_OUTLINE);
			taskStatus.setTextColor(Color.AMBER);
			break;
		case TS_COMPLETED:
			taskStatus.setIconType(IconType.RADIO_BUTTON_CHECKED);
			taskStatus.setTextColor(Color.GREEN);
			break;
		case TS_DELETED:
			taskStatus.setIconType(IconType.HIGHLIGHT_OFF);
			taskStatus.setTextColor(Color.GREY);
			break;
		default:
			break;
		}
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

	public void setTask(TaskDto task) {
		dropDownConfig(task);

		setTaskKind(task.getKind());
		setTaskTitle(task.getType());

		createTaskLine(task);

		setTaskStatus(task.getStatus());

		elapsed.setText(DateUtils.elapsedText(task.getUpdated()));

		if (task.getDueDate() != null)
			dueDate.setText(DateUtils.formatDateTime(task.getDueDate(), currentUser.getLocale()));
	}

}
