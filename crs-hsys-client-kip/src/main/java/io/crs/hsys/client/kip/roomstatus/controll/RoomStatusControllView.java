/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.controll;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.common.collect.ImmutableList;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addext.client.ui.MaterialButton2;
import gwt.material.design.addins.client.overlay.MaterialOverlay;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialIcon;

import io.crs.hsys.client.kip.roomstatus.RoomStatusUtils;
import io.crs.hsys.shared.constans.RoomStatus;
import io.crs.hsys.shared.constans.UserPerm;
import io.crs.hsys.shared.dto.common.AppUserDto;
import io.crs.hsys.shared.dto.hk.RoomStatusDto;
import io.crs.hsys.shared.dto.task.TaskDto;

/**
 * @author robi
 *
 */
public class RoomStatusControllView extends ViewWithUiHandlers<RoomStatusControllUiHandlers>
		implements RoomStatusControllPresenter.MyView {
	private static Logger logger = Logger.getLogger(RoomStatusControllView.class.getName());

	public static final ImmutableList<UserPerm> ADMIN_PERM = ImmutableList.of(UserPerm.UP_HKSUPERVISOR,
			UserPerm.UP_HKSUPERVISOR);

	interface Binder extends UiBinder<HTMLPanel, RoomStatusControllView> {
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

	@Inject
	RoomStatusControllView(Binder binder) {
		logger.log(Level.INFO, "RoomStatusControllView()");
		initWidget(binder.createAndBindUi(this));
		initCloseButton();
		initButtons();
	}

	private void initCloseButton() {
		btnClose.getIcon().getElement().getStyle().setFontSize(3, Unit.EM);
	}

	private void initButtons() {
		initButton2(cleanButton, RoomStatusUtils.getStatusIcon2(RoomStatus.CLEAN));
		initButton2(inspectButton, RoomStatusUtils.getStatusIcon2(RoomStatus.INSPECTED));
		initButton2(dirtyButton, RoomStatusUtils.getStatusIcon2(RoomStatus.DIRTY));
		initButton2(showButton, RoomStatusUtils.getStatusIcon2(RoomStatus.SHOW));
		initButton2(oosButton, RoomStatusUtils.getStatusIcon2(RoomStatus.OOS));
		initButton2(oooButton, RoomStatusUtils.getStatusIcon2(RoomStatus.OOO));

		initButton2(minibarButton, IconType.KITCHEN);
		initButton2(chatButton, IconType.CHAT);
		initButton2(addTaskButton, IconType.PLAYLIST_ADD);
	}

	private void initButton(MaterialButton button) {
		button.setHeight("100px");
		button.getIcon().getElement().getStyle().setFontSize(6, Unit.EM);
		button.getIcon().getElement().getStyle().setMargin(0, Unit.PX);
		button.getElement().getStyle().setMargin(5, Unit.PX);
	}

	private void initButton2(MaterialButton2 button, IconType iconType) {
		button.setHeight("110px");
		button.setIconType(iconType);
		button.getIcon().getElement().getStyle().setFontSize(6, Unit.EM);
		button.getIcon().getElement().getStyle().setMargin(0, Unit.PX);
		button.getIcon().getElement().getStyle().setMarginBottom(10, Unit.PX);
		button.getIcon().getElement().getStyle().setMarginTop(30, Unit.PX);
		button.getElement().getStyle().setMargin(5, Unit.PX);
	}

	private void reinitButtons() {
		reinitButton2(cleanButton);
		reinitButton2(inspectButton);
		reinitButton2(dirtyButton);
		reinitButton2(showButton);
		reinitButton2(oosButton);
		reinitButton2(oooButton);

		reinitButton2(minibarButton);
//		reinitButton(chatButton);
//		reinitButton(addTaskButton);		
	}

	private void reinitButton(MaterialButton button) {
		button.setVisible(false);
	}

	private void reinitButton2(MaterialButton2 button) {
		button.setVisible(false);
	}

	@Override
	public void open(RoomStatusDto dto, AppUserDto currentUser) {
		logger.log(Level.INFO, "RoomStatusControllView().open()");

		overlay.setBackgroundColor(RoomStatusUtils.getStatusBgColor(dto.getRoom().getRoomStatus()));
		reinitButtons();
		statusIcon.setIconType(RoomStatusUtils.getStatusIcon2(dto.getRoom().getRoomStatus()));
		statusIcon.setIconColor(RoomStatusUtils.getStatusIconColor(dto.getRoom().getRoomStatus()));
//		statusIcon.setText(dto.getRoom().getCode());
//		statusIcon.setTextColor(Color.WHITE);

		roomNoLabel.setText(dto.getRoom().getCode());

		Boolean oddLine = false;
		collection.clear();
		for (TaskDto task : dto.getTasks()) {
			Boolean ownedBy = false;
			if (task.getReporter() != null)
				ownedBy = task.getReporter().getCode().equals(currentUser.getCode());
			Boolean assignedTo = false;
			if (task.getAssignee() != null)
				assignedTo = task.getAssignee().getCode().equals(currentUser.getCode());
			collection.add(new RoomStatusControllTaskWidget(ownedBy, assignedTo, task, oddLine));
			oddLine = !oddLine;
		}
		logger.log(Level.INFO, "RoomStatusControllView().open()-2");

		initButtons(currentUser.getPermissions().get(0), dto.getRoom().getRoomStatus());
		logger.log(Level.INFO, "RoomStatusControllView().open()-3");

		overlay.open();
	}

	private void initButtons(UserPerm permission, RoomStatus roomStatus) {
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
		default:
			break;
		}
	}

	private void setHkButtons(RoomStatus roomStatus) {
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
		switch (roomStatus) {
		case OOS:
			cleanButton.setVisible(true);
			dirtyButton.setVisible(true);
			break;
		case SHOW:
			cleanButton.setVisible(true);
			dirtyButton.setVisible(true);
			break;
		default:
			break;
		}
	}

	private void setCommonButtons(RoomStatus roomStatus) {
	}

	@UiHandler("btnClose")
	public void onCloseClick(ClickEvent event) {
		close();
	}

	@Override
	public void close() {
		overlay.close();
	}
}
