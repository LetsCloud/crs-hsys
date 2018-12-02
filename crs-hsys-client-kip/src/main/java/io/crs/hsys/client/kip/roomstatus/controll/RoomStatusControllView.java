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

import gwt.material.design.addins.client.overlay.MaterialOverlay;
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
	MaterialButton cleanButton, inspectButton, minibarButton, chatButton, addTaskButton, dirtyButton, showButton,
			oosButton, oooButton;

	@UiField
	MaterialCollection collection;

	@Inject
	RoomStatusControllView(Binder binder) {
		logger.log(Level.INFO, "RoomStatusControllView()");
		initWidget(binder.createAndBindUi(this));
		initButtons();
	}

	private void initButtons() {
		initButton(cleanButton);
		initButton(inspectButton);
		initButton(dirtyButton);
		initButton(showButton);
		initButton(oosButton);
		initButton(oooButton);

		initButton(minibarButton);
		initButton(chatButton);
		initButton(addTaskButton);		
	}
	
	private void initButton(MaterialButton button) {
		button.setHeight("100px");
		button.getIcon().getElement().getStyle().setFontSize(6, Unit.EM);
		button.getIcon().getElement().getStyle().setMargin(0, Unit.PX);
		button.getElement().getStyle().setMargin(5, Unit.PX);
	}

	private void reinitButtons() {
		reinitButton(cleanButton);
		reinitButton(inspectButton);
		reinitButton(dirtyButton);
		reinitButton(showButton);
		reinitButton(oosButton);
		reinitButton(oooButton);

		reinitButton(minibarButton);
//		reinitButton(chatButton);
//		reinitButton(addTaskButton);		
	}
	
	private void reinitButton(MaterialButton button) {
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

		Boolean admin = currentUser.getPermissions().containsAll(ADMIN_PERM);
		Boolean oddLine = false;
		collection.clear();
		for (TaskDto task : dto.getTasks()) {
			Boolean owner = false;
			if (task.getAssignee() != null)
				owner = task.getAssignee().getCode().equals(currentUser.getCode());
			collection.add(new RoomStatusControllTaskWidget(admin, owner, task, oddLine));
			oddLine = !oddLine;
		}

		initButtons(admin, dto.getRoom().getRoomStatus());

		overlay.open();
	}

	private void initButtons(Boolean admin, RoomStatus roomStatus) {
		if (admin) {
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
		} else {
			switch (roomStatus) {
			case DIRTY:
				cleanButton.setVisible(true);
				minibarButton.setVisible(true);
				break;
			case CLEAN:
				dirtyButton.setVisible(true);
				minibarButton.setVisible(true);
				break;
			case INSPECTED:
				minibarButton.setVisible(true);
				break;
			case OOO:
				break;
			default:
				break;
			}
		}
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
