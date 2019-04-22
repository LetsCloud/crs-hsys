/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

import io.crs.hsys.shared.cnst.RoomStatus;

/**
 * @author CR
 *
 */
public class RoomStatusSaveEvent extends GwtEvent<RoomStatusSaveEvent.RoomStatusSaveHandler> {

	public interface RoomStatusSaveHandler extends EventHandler {
		void onSave(RoomStatusSaveEvent event);
	}

	private static final Type<RoomStatusSaveHandler> TYPE = new Type<>();

	private final String roomKey;
	private final RoomStatus roomStatus;

	RoomStatusSaveEvent(String roomKey, RoomStatus roomStatus) {
		this.roomKey = roomKey;
		this.roomStatus = roomStatus;
	}

	public static Type<RoomStatusSaveHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, String roomKey, RoomStatus roomStatus) {
		source.fireEvent(new RoomStatusSaveEvent(roomKey, roomStatus));
	}

	@Override
	public Type<RoomStatusSaveHandler> getAssociatedType() {
		return TYPE;
	}

	public String getRoomKey() {
		return roomKey;
	}

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	@Override
	protected void dispatch(RoomStatusSaveHandler handler) {
		handler.onSave(this);
	}
}