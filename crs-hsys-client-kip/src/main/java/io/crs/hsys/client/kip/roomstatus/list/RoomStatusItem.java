/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.list;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;

import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.client.kip.i18n.RoomStatusAbbrConstants;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusEditEvent;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusEditEvent.HasRoomStatusEditHandlers;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusEditEvent.RoomStatusEditHandler;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialLabel;

/**
 * @author CR
 *
 */
public class RoomStatusItem extends Composite implements HasRoomStatusEditHandlers {
	private static final Logger LOGGER = Logger.getLogger(RoomStatusItem.class.getName());

	private static RoomStatusItemUiBinder uiBinder = GWT.create(RoomStatusItemUiBinder.class);

	interface RoomStatusItemUiBinder extends UiBinder<Widget, RoomStatusItem> {
	}

	@UiField
	MaterialChip code;

	@UiField
	MaterialLabel floor, roomTypeCode;

	private RoomDto roomDto;
	private RoomStatusAbbrConstants i18nRoomStatus;

	/**
	 */
	public RoomStatusItem() {
		initWidget(uiBinder.createAndBindUi(this));
		LOGGER.log(Level.INFO, "RoomStatusItem()");
	}

	public RoomStatusItem(RoomDto roomDto, RoomStatusAbbrConstants i18nRoomStatus) {
		this();
		this.i18nRoomStatus = i18nRoomStatus;
		updateRoom(roomDto);
	}

	@UiHandler("changeIcon")
	public void onChangeClick(ClickEvent event) {
		RoomStatusEditEvent.fire(this, roomDto);
	}

	@Override
	public HandlerRegistration addRoomStatusEditHandler(RoomStatusEditHandler handler) {
		return addHandler(handler, RoomStatusEditEvent.getType());
	}

	public String getRoomKey() {
		return roomDto.getWebSafeKey();
	}

	public void updateRoom(RoomDto roomDto) {
		this.roomDto = roomDto;
		if (!roomDto.getOccupied())
			code.setBackgroundColor(Color.WHITE);
		code.setLetter(roomDto.getCode());
		code.setText(i18nRoomStatus.roomStatusAbbrMap().get(roomDto.getRoomStatus().name()) + "/"
				+ roomDto.getFoRoomStatus());
		// code.setText(roomDto.getRoomStatus().name());

		// code.setText(roomDto.getRoomStatus().name() + "/&#9786;");
		floor.setText(roomDto.getFloor());
		roomTypeCode.setText(roomDto.getRoomType().getCode());

		switch (roomDto.getRoomStatus()) {
		case DIRTY:
			code.setLetterBackgroundColor(Color.RED);
			break;
		case CLEAN:
			code.setLetterBackgroundColor(Color.YELLOW);
			break;
		case INSPECTED:
			code.setLetterBackgroundColor(Color.GREEN);
			break;
		default:
			break;
		}
	}
}
