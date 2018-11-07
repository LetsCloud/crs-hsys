/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

import io.crs.hsys.shared.dto.hotel.RoomDto;

/**
 * @author CR
 *
 */
public class RoomStatusEditEvent extends GwtEvent<RoomStatusEditEvent.RoomStatusEditHandler> {

	public interface RoomStatusEditHandler extends EventHandler {
		void onEdit(RoomStatusEditEvent event);
	}

	public interface HasRoomStatusEditHandlers extends HasHandlers {
		public HandlerRegistration addRoomStatusEditHandler(RoomStatusEditHandler handler);
	}

	private static final Type<RoomStatusEditHandler> TYPE = new Type<>();

	private final RoomDto roomDto;

	RoomStatusEditEvent(RoomDto roomDto) {
		this.roomDto = roomDto;
	}

	public static Type<RoomStatusEditHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, RoomDto roomDto) {
		source.fireEvent(new RoomStatusEditEvent(roomDto));
	}

	@Override
	public Type<RoomStatusEditHandler> getAssociatedType() {
		return TYPE;
	}

	public RoomDto getRoomDto() {
		return roomDto;
	}

	@Override
	protected void dispatch(RoomStatusEditHandler handler) {
		handler.onEdit(this);
	}
}