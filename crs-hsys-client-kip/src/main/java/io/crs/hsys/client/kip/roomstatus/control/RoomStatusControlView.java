/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.control;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.common.collect.ImmutableList;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addext.client.ui.MaterialButton2;
import gwt.material.design.addins.client.overlay.MaterialOverlay;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialIcon;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.kip.roomstatus.RoomStatusUtils;
import io.crs.hsys.shared.cnst.RoomStatus;
import io.crs.hsys.shared.cnst.UserPerm;
import io.crs.hsys.shared.dto.hk.RoomStatusDto;
import io.crs.hsys.shared.dto.task.TaskDto;

/**
 * @author robi
 *
 */
public class RoomStatusControlView extends ViewWithUiHandlers<RoomStatusControlUiHandlers>
		implements RoomStatusControlPresenter.MyView {
	private static Logger logger = Logger.getLogger(RoomStatusControlView.class.getName());

	public static final ImmutableList<UserPerm> ADMIN_PERM = ImmutableList.of(UserPerm.UP_HKSUPERVISOR,
			UserPerm.UP_HKSUPERVISOR);

	interface Binder extends UiBinder<HTMLPanel, RoomStatusControlView> {
	}

	@UiField
	MaterialOverlay overlay;

	@UiField
	MaterialIcon statusIcon;

	@UiField
	InlineLabel roomNoLabel;

	@UiField
	MaterialButton btnClose;

	@UiField
	MaterialButton2 dirtyButton, cleanButton, inspectButton, minibarButton, chatButton, addTaskButton, showButton,
			oosButton, oooButton;;

	@UiField
	MaterialCollection collection;

	HandlerRegistration dirtyHandlerRegistration, cleanHandlerRegistration, inspectedHandlerRegistration;

	private final CurrentUser currentUser;

	@Inject
	RoomStatusControlView(Binder binder, CurrentUser currentUser) {
		logger.log(Level.INFO, "RoomStatusControlView()");

		this.currentUser = currentUser;

		initWidget(binder.createAndBindUi(this));
		initCloseButton();
		setButtonsStyle();
	}

	private void initCloseButton() {
		btnClose.getIcon().getElement().getStyle().setFontSize(3, Unit.EM);
		btnClose.addClickHandler(e -> getUiHandlers().navBack());
	}

	private void setButtonsStyle() {
		setButtonStyle(cleanButton, RoomStatusUtils.getStatusIcon2(RoomStatus.CLEAN));
		setButtonStyle(inspectButton, RoomStatusUtils.getStatusIcon2(RoomStatus.INSPECTED));
		setButtonStyle(dirtyButton, RoomStatusUtils.getStatusIcon2(RoomStatus.DIRTY));
		setButtonStyle(showButton, RoomStatusUtils.getStatusIcon2(RoomStatus.SHOW));
		setButtonStyle(oosButton, RoomStatusUtils.getStatusIcon2(RoomStatus.OOS));
		setButtonStyle(oooButton, RoomStatusUtils.getStatusIcon2(RoomStatus.OOO));

		setButtonStyle(minibarButton, IconType.KITCHEN);
		setButtonStyle(chatButton, IconType.CHAT);
		setButtonStyle(addTaskButton, IconType.PLAYLIST_ADD);
	}

	private void setButtonStyle(MaterialButton2 button, IconType iconType) {
		button.setHeight("110px");
		button.setIconType(iconType);
		button.getIcon().getElement().getStyle().setFontSize(6, Unit.EM);
		button.getIcon().getElement().getStyle().setMargin(0, Unit.PX);
		button.getIcon().getElement().getStyle().setMarginBottom(10, Unit.PX);
		button.getIcon().getElement().getStyle().setMarginTop(30, Unit.PX);
		button.getElement().getStyle().setMargin(5, Unit.PX);
	}

	@Override
	public void open(RoomStatusDto dto) {
		logger.log(Level.INFO, "RoomStatusControllView().open()");
		setClickHandlers(dto.getRoom().getWebSafeKey());
		setRoomStatus(dto);
		overlay.open();
	}

	@Override
	public void setRoomStatus(RoomStatusDto dto) {
		logger.log(Level.INFO, "RoomStatusControllView().setRoomStatus()");
		overlay.setBackgroundColor(RoomStatusUtils.getStatusBgColor(dto.getRoom().getRoomStatus()));
		hideButtons();
		statusIcon.setIconType(RoomStatusUtils.getStatusIcon2(dto.getRoom().getRoomStatus()));
		statusIcon.setIconColor(RoomStatusUtils.getStatusIconColor(dto.getRoom().getRoomStatus()));

		roomNoLabel.setText(dto.getRoom().getCode());

		setTasks(dto.getTasks());

		configButtons(currentUser.getAppUserDto().getPermissions().get(0), dto.getRoom().getRoomStatus());
	}

	private void hideButtons() {
		hideButton(cleanButton);
		hideButton(inspectButton);
		hideButton(dirtyButton);
		hideButton(showButton);
		hideButton(oosButton);
		hideButton(oooButton);

		hideButton(minibarButton);
	}

	private void hideButton(MaterialButton2 button) {
		button.setVisible(false);
	}

	private void setClickHandlers(String roomKey) {
		if (dirtyHandlerRegistration != null)
			dirtyButton.removeHandler(dirtyHandlerRegistration);

		if (cleanHandlerRegistration != null)
			cleanButton.removeHandler(cleanHandlerRegistration);

		if (inspectedHandlerRegistration != null)
			inspectButton.removeHandler(inspectedHandlerRegistration);

		dirtyHandlerRegistration = dirtyButton.addClickHandler(e -> getUiHandlers().makeDirty(roomKey));
		cleanHandlerRegistration = cleanButton.addClickHandler(e -> getUiHandlers().makeClean(roomKey));
		inspectedHandlerRegistration = inspectButton.addClickHandler(e -> getUiHandlers().makeInspected(roomKey));
	}

	private void setTasks(List<TaskDto> tasks) {
		if (tasks == null)
			return;
		Boolean oddLine = false;
		collection.clear();
		for (TaskDto task : tasks) {
			Boolean ownedBy = false;
			if (task.getReporter() != null)
				ownedBy = task.getReporter().getCode().equals(currentUser.getAppUserDto().getCode());
			Boolean assignedTo = false;
			if (task.getAssignee() != null)
				assignedTo = task.getAssignee().getCode().equals(currentUser.getAppUserDto().getCode());
			collection.add(new RoomStatusControlTaskWidget(ownedBy, assignedTo, task, oddLine));
			oddLine = !oddLine;
		}
	}

	private void configButtons(UserPerm permission, RoomStatus roomStatus) {
		if (roomStatus == null)
			return;

		switch (permission) {
		case UP_HKSUPERVISOR:
			setHkSvButtons(roomStatus);
			break;
		case UP_HOUSEKEEPER:
			setHkButtons(roomStatus);
			break;
		case UP_MAINTMANAGER:
			setCommonButtons(roomStatus);
			break;
		case UP_TECHNICIAN:
			setCommonButtons(roomStatus);
			break;
		case UP_RECEPTIONIST:
			setReceptionButtons(roomStatus);
			break;
		case UP_ADMIN:
			setCommonButtons(roomStatus);
			break;
		default:
			setCommonButtons(roomStatus);
			break;
		}
	}

	private void setHkSvButtons(RoomStatus roomStatus) {
		if (roomStatus == null)
			return;

		switch (roomStatus) {
		case DIRTY:
			cleanButton.setVisible(true);
			showButton.setVisible(true);
			oooButton.setVisible(true);
			oosButton.setVisible(true);
			minibarButton.setVisible(true);
			break;
		case CLEAN:
			inspectButton.setVisible(true);
			dirtyButton.setVisible(true);
			minibarButton.setVisible(true);
			showButton.setVisible(true);
			oooButton.setVisible(true);
			oosButton.setVisible(true);
			break;
		case INSPECTED:
			dirtyButton.setVisible(true);
			minibarButton.setVisible(true);
			showButton.setVisible(true);
			oooButton.setVisible(true);
			oosButton.setVisible(true);
			break;
		case OOO:
			cleanButton.setVisible(true);
			dirtyButton.setVisible(true);
			inspectButton.setVisible(true);
			break;
		case OOS:
			cleanButton.setVisible(true);
			dirtyButton.setVisible(true);
			inspectButton.setVisible(true);
			break;
		case SHOW:
			cleanButton.setVisible(true);
			dirtyButton.setVisible(true);
			inspectButton.setVisible(true);
			break;
		default:
			break;
		}
	}

	private void setHkButtons(RoomStatus roomStatus) {
		if (roomStatus == null)
			return;

		switch (roomStatus) {
		case DIRTY:
			cleanButton.setVisible(true);
//			minibarButton.setVisible(true);
			break;
		case CLEAN:
			dirtyButton.setVisible(true);
//			minibarButton.setVisible(true);
			break;
		case INSPECTED:
			cleanButton.setVisible(true);
			dirtyButton.setVisible(true);
//			minibarButton.setVisible(true);
			break;
		default:
			break;
		}
	}

	private void setReceptionButtons(RoomStatus roomStatus) {
		if (roomStatus == null)
			return;

		switch (roomStatus) {
		case DIRTY:
			oooButton.setVisible(true);
			oosButton.setVisible(true);
			break;
		case CLEAN:
			oooButton.setVisible(true);
			oosButton.setVisible(true);
			break;
		case INSPECTED:
			oooButton.setVisible(true);
			oosButton.setVisible(true);
			showButton.setVisible(true);
			break;
		case OOO:
			cleanButton.setVisible(true);
			dirtyButton.setVisible(true);
			break;
		case OOS:
			cleanButton.setVisible(true);
			dirtyButton.setVisible(true);
			break;
		case SHOW:
			cleanButton.setVisible(true);
			dirtyButton.setVisible(true);
			oosButton.setVisible(true);
			break;
		default:
			break;
		}
	}

	private void setCommonButtons(RoomStatus roomStatus) {
	}

	@Override
	public void close() {
		overlay.close();
	}
}
