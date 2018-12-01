/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.addext.client.ui.MaterialDesignIcon;
import gwt.material.design.addext.client.ui.constants.MdiType;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.html.Div;

import io.crs.hsys.client.kip.roomstatus.event.RoomStatusEditEvent;
import io.crs.hsys.shared.constans.OccStatus;
import io.crs.hsys.shared.constans.RoomStatus;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.hk.RoomStatusDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.task.TaskDto;

/**
 * @author robi
 *
 */
public class RoomStatusWidget extends Composite {
	private static final Logger logger = Logger.getLogger(RoomStatusWidget.class.getName());

	private static RoomStatusWidgetUiBinder uiBinder = GWT.create(RoomStatusWidgetUiBinder.class);

	interface RoomStatusWidgetUiBinder extends UiBinder<Widget, RoomStatusWidget> {
	}

	@UiField
	MaterialRow roomStatusPanel;

	@UiField
	MaterialColumn currOccStatusPanel, nextOccStatusPanel;

	@UiField
	MaterialDesignIcon statusIcon, currOccStatusIcon, nextOccStatusIcon;

	@UiField
	Label roomNoLabel, roomTypeLabel, atendantLabel;

	@UiField
	InlineLabel guestNumberLabel, cleaningLabel, maintenanceLabel, currOccLabel, nextOccLabel;

	@UiField
	Div contentPanel, flexiPanel;

	/**
	 * 
	 */
	public RoomStatusWidget() {
		logger.log(Level.INFO, "RoomStatusWidget()");
		initWidget(uiBinder.createAndBindUi(this));
//		overlay.setBackfaceVisibility("50");
//		overlay.setDuration(500);
//		overlay.setOpacity(0.8);
	}

	public RoomStatusWidget(RoomStatusDto roomStatus) {
		this();

		roomStatusPanel.setBackgroundColor(getStatusBgColor(roomStatus.getRoom().getRoomStatus()));
		statusIcon.setTextColor(getStatusIconColor(roomStatus.getRoom().getRoomStatus()));
		statusIcon.setIconType(getStatusIcon(roomStatus.getRoom().getRoomStatus()));
		this.roomNoLabel.setText(roomStatus.getRoom().getCode());

		this.roomTypeLabel.setText(roomStatus.getRoom().getRoomType().getCode());
		this.guestNumberLabel.setText(roomStatus.getRoom().getCurrOccStatus().getGuestNumber().toString());

		Supplier<Stream<TaskDto>> cleaningTasks = () -> roomStatus.getTasks().stream()
				.filter(o -> o.getKind().equals(TaskKind.CLEANING));

		Supplier<Stream<TaskDto>> attendantTasks = () -> cleaningTasks.get().filter(o -> o.getAssignee() != null);

		if (attendantTasks.get().count() == 0) {
			atendantLabel.setText("Beosztatlan");
			atendantLabel.getElement().getStyle().setColor("#bdbdbd");
//			atendantLabel.getElement().getStyle().setFontSize(14, Unit.PX);
		} else {
			atendantLabel.setText(attendantTasks.get().findFirst().get().getAssignee().getName());
		}

		cleaningLabel.setText(Long.toString(cleaningTasks.get().count()));

		Supplier<Stream<TaskDto>> maintenanceTasks = () -> roomStatus.getTasks().stream()
				.filter(o -> o.getKind().equals(TaskKind.MAINTENANCE));
		
		maintenanceLabel.setText(Long.toString(maintenanceTasks.get().count()));

		currOccStatusPanel.setTextColor(getOccStatusColor(roomStatus.getRoom().getCurrOccStatus().getStatus()));
		currOccStatusIcon.setIconType(getOccStatusIcon(roomStatus.getRoom().getCurrOccStatus().getStatus()));
		currOccLabel.setText(roomStatus.getRoom().getCurrOccStatus().getNotice());

		if (roomStatus.getRoom().getCurrOccStatus().getStatus().equals(OccStatus.INHOUSE)
				|| roomStatus.getRoom().getCurrOccStatus().getStatus().equals(OccStatus.OOO)) {
			nextOccStatusPanel.setVisible(false);
			currOccStatusPanel.setGrid("s12");
		} else {
			nextOccStatusPanel.setTextColor(getOccStatusColor(roomStatus.getRoom().getNextOccStatus().getStatus()));
			nextOccStatusIcon.setIconType(getOccStatusIcon(roomStatus.getRoom().getNextOccStatus().getStatus()));
			nextOccLabel.setText(roomStatus.getRoom().getNextOccStatus().getNotice());
		}
	}

	public RoomStatusWidget(String roomNo, RoomStatus roomStatus, String roomType, String guestNumber, String atendant,
			Integer cleaningTasks, Integer maintTasks, OccStatus currOccStatus, String currOccText,
			OccStatus nextOccStatus, String nextOccText) {
		this();

		roomStatusPanel.setBackgroundColor(getStatusBgColor(roomStatus));
		statusIcon.setTextColor(getStatusIconColor(roomStatus));
		statusIcon.setIconType(getStatusIcon(roomStatus));
		this.roomNoLabel.setText(roomNo);

		this.roomTypeLabel.setText(roomType);
		this.guestNumberLabel.setText(guestNumber);

		if (atendant == null) {
			atendantLabel.setText("Beosztatlan");
			atendantLabel.getElement().getStyle().setColor("#bdbdbd");
//			atendantLabel.getElement().getStyle().setFontSize(14, Unit.PX);
		} else {
			atendantLabel.setText(atendant);
		}
		cleaningLabel.setText(cleaningTasks.toString());
		maintenanceLabel.setText(maintTasks.toString());

		currOccStatusPanel.setTextColor(getOccStatusColor(currOccStatus));
		currOccStatusIcon.setIconType(getOccStatusIcon(currOccStatus));
		currOccLabel.setText(currOccText);

		if (currOccStatus.equals(OccStatus.INHOUSE) || currOccStatus.equals(OccStatus.OOO)) {
			nextOccStatusPanel.setVisible(false);
			currOccStatusPanel.setGrid("s12");
		} else {
			nextOccStatusPanel.setTextColor(getOccStatusColor(nextOccStatus));
			nextOccStatusIcon.setIconType(getOccStatusIcon(nextOccStatus));
			nextOccLabel.setText(nextOccText);
		}
	}

	public RoomStatusWidget(String roomNo, RoomStatus roomStatus, String roomType, String guestNumber, String atendant,
			Integer cleaningTasks, Integer maintTasks, OccStatus currOccStatus, String currOccText,
			OccStatus nextOccStatus, String nextOccText, Boolean oddItem) {
		this(roomNo, roomStatus, roomType, guestNumber, atendant, cleaningTasks, maintTasks, currOccStatus, currOccText,
				nextOccStatus, nextOccText);
		if (oddItem)
			setBackgroundColor(Color.GREY_LIGHTEN_4);
	}

	private Color getStatusBgColor(RoomStatus roomStatus) {
		switch (roomStatus) {
		case DIRTY:
			return Color.RED;
		case CLEAN:
			return Color.BLUE;
		case INSPECTED:
			return Color.GREEN;
		case OOO:
			return Color.BLACK;
		case OOS:
			return Color.PURPLE;
		case SHOW:
			return Color.AMBER;
		default:
			break;
		}
		return null;
	}

	private Color getStatusIconColor(RoomStatus roomStatus) {
		switch (roomStatus) {
		case DIRTY:
			return Color.RED_LIGHTEN_4;
		case CLEAN:
			return Color.BLUE_LIGHTEN_4;
		case INSPECTED:
			return Color.GREEN_LIGHTEN_4;
		case OOO:
			return Color.GREY;
		case OOS:
			return Color.PURPLE_LIGHTEN_4;
		case SHOW:
			return Color.AMBER_LIGHTEN_4;
		default:
			break;
		}
		return null;
	}

	private MdiType getStatusIcon(RoomStatus roomStatus) {
		switch (roomStatus) {
		case DIRTY:
			return MdiType.DELETE_CIRCLE_OUTLINE;
		case CLEAN:
			return MdiType.STAR_CIRCLE_OUTLINE;
		case INSPECTED:
			return MdiType.CHECK_CIRCLE_OUTLINE;
		case OOO:
			return MdiType.SETTINGS_OUTLINE;
		case OOS:
			return MdiType.CLOSE_CIRCLE_OUTLINE;
		case SHOW:
			return MdiType.EYE_OUTLINE;
		default:
			break;
		}
		return null;
	}

	private Color getOccStatusColor(OccStatus occStatus) {
		switch (occStatus) {
		case VACANT:
			return Color.GREY;
		case EARLYCI:
			return Color.GREEN;
		case CHECKIN:
			return Color.GREEN;
		case INHOUSE:
			return Color.BLUE;
		case CHECKOUT:
			return Color.RED;
		case LATECO:
			return Color.RED;
		case OOO:
			return Color.BLACK;
		case OOS:
			return Color.PURPLE;
		case SHOW:
			return Color.AMBER;
		default:
			break;
		}
		return null;
	}

	private MdiType getOccStatusIcon(OccStatus occStatus) {
		switch (occStatus) {
		case VACANT:
			return MdiType.CHECKBOX_BLANK;
		case EARLYCI:
			return MdiType.ALPHA_E_BOX;
		case CHECKIN:
			return MdiType.ARROW_DOWN_BOX;
		case INHOUSE:
			return MdiType.ACCOUNT_BOX;
		case CHECKOUT:
			return MdiType.ARROW_UP_BOX;
		case LATECO:
			return MdiType.ALPHA_L_BOX;
		case OOO:
			return MdiType.SETTINGS_BOX;
		case OOS:
			return MdiType.CLOSE_BOX;
		case SHOW:
			return MdiType.EYE;
		default:
			break;
		}
		return null;
	}

	@UiHandler("contentPanel")
	public void onContentClick(ClickEvent event) {
		RoomStatusEditEvent.fire(this, new RoomDto());
	}

	public void setBackgroundColor(Color color) {
		flexiPanel.setBackgroundColor(color);
	}
}
