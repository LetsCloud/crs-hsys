/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.list;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewWithUiHandlers;

import io.crs.hsys.shared.cnst.RoomStatus;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import gwt.material.design.client.ui.MaterialRadioButton;

/**
 * @author CR
 *
 */
public class RoomStatusEditorView extends PopupViewWithUiHandlers<RoomStatusEditorUiHandlers>
		implements RoomStatusEditorPresenter.MyView {
	private static final Logger LOGGER = Logger.getLogger(RoomStatusEditorView.class.getName());

	interface Binder extends UiBinder<Widget, RoomStatusEditorView> {
	}

	private String roomKey;
	private RoomStatus roomStatus;

	@UiField
	MaterialRadioButton dirtyMRB, cleanMRB, inspectedMRB, oosMRB, showMRB;

	@Inject
	RoomStatusEditorView(Binder uiBinder, EventBus eventBus) {
		super(eventBus);
		LOGGER.log(Level.INFO, "RoomStatusEditorView()");
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void display(RoomDto roomDto) {
		roomKey = roomDto.getWebSafeKey();
		roomStatus = roomDto.getRoomStatus();
		clearStatusButtons();
		switch (roomStatus) {
		case RS_DIRTY:
			dirtyMRB.setValue(true);
		case RS_CLEAN:
			cleanMRB.setValue(true);
		case RS_INSPECTED:
			inspectedMRB.setValue(true);
		default:
			break;
		}
	}

	private void clearStatusButtons() {
		dirtyMRB.setValue(false);
		cleanMRB.setValue(false);
		inspectedMRB.setValue(false);
		oosMRB.setValue(false);
		showMRB.setValue(false);
	}

	@UiHandler("dirtyMRB")
	public void onDirtyClick(ClickEvent event) {
		roomStatus = RoomStatus.RS_DIRTY;
	}

	@UiHandler("cleanMRB")
	public void onCleanClick(ClickEvent event) {
		roomStatus = RoomStatus.RS_CLEAN;
	}

	@UiHandler("inspectedMRB")
	public void onInspectedClick(ClickEvent event) {
		roomStatus = RoomStatus.RS_INSPECTED;
	}

	@UiHandler("oosMRB")
	public void onOosClick(ClickEvent event) {
		roomStatus = RoomStatus.RS_OOS;
	}

	@UiHandler("showMRB")
	public void onShowClick(ClickEvent event) {
		roomStatus = RoomStatus.RS_SHOW;
	}

	@UiHandler("changeButton")
	public void onChangeButtonClick(ClickEvent event) {
		LOGGER.log(Level.INFO, "onChangeButtonClick()");
		getUiHandlers().saveStatus(roomKey, roomStatus);
	}

}
