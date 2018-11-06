/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

import io.crs.hsys.shared.dto.hotel.RoomDto;

/**
 * @author CR
 *
 */
public class RoomStatusRefreshEvent extends GwtEvent<RoomStatusRefreshEvent.RoomStatusRefreshHandler> {

	public interface RoomStatusRefreshHandler extends EventHandler {
		void onRefresh(RoomStatusRefreshEvent event);
	}

	private static final Type<RoomStatusRefreshHandler> TYPE = new Type<>();

	private final RoomDto roomDto;

	RoomStatusRefreshEvent(RoomDto roomDto) {
		this.roomDto = roomDto;
	}

	public static Type<RoomStatusRefreshHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, RoomDto roomDto) {
		source.fireEvent(new RoomStatusRefreshEvent(roomDto));
	}

	@Override
	public Type<RoomStatusRefreshHandler> getAssociatedType() {
		return TYPE;
	}

	public RoomDto getRoomDto() {
		return roomDto;
	}

	@Override
	protected void dispatch(RoomStatusRefreshHandler handler) {
		handler.onRefresh(this);
	}
}