/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.event;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

import io.crs.hsys.client.kip.roomstatus.RoomStatusPresenter;
import io.crs.hsys.shared.dto.hk.RoomStatusDto;

/**
 * @author CR
 *
 */
public class RoomStatusEditEvent extends GwtEvent<RoomStatusEditEvent.RoomStatusEditHandler> {
	private static final Logger logger = Logger.getLogger(RoomStatusEditEvent.class.getName());

	public interface RoomStatusEditHandler extends EventHandler {
		void onEdit(RoomStatusEditEvent event);
	}

	public interface HasRoomStatusEditHandlers extends HasHandlers {
		public HandlerRegistration addRoomStatusEditHandler(RoomStatusEditHandler handler);
	}

	private static final Type<RoomStatusEditHandler> TYPE = new Type<>();

	private final RoomStatusDto roomStatusDto;

	private final Boolean admin;

	RoomStatusEditEvent(RoomStatusDto roomStatusDto) {
		this(roomStatusDto, false);
		logger.log(Level.INFO, "RoomStatusEditEvent()");
	}

	RoomStatusEditEvent(RoomStatusDto roomStatusDto, Boolean admin) {
		logger.log(Level.INFO, "RoomStatusEditEvent()->admin=" + admin);
		this.roomStatusDto = roomStatusDto;
		this.admin = admin;
	}

	public static Type<RoomStatusEditHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, RoomStatusDto roomStatusDto) {
		logger.log(Level.INFO, "RoomStatusEditEvent().fire()");
		source.fireEvent(new RoomStatusEditEvent(roomStatusDto));
	}

	public static void fire(HasHandlers source, RoomStatusDto roomStatusDto, Boolean admin) {
		logger.log(Level.INFO, "RoomStatusEditEvent().fire()");
		source.fireEvent(new RoomStatusEditEvent(roomStatusDto, admin));
	}

	@Override
	public Type<RoomStatusEditHandler> getAssociatedType() {
		return TYPE;
	}

	public RoomStatusDto getRoomStatusDto() {
		return roomStatusDto;
	}

	public Boolean getAdmin() {
		return admin;
	}

	@Override
	protected void dispatch(RoomStatusEditHandler handler) {
		logger.log(Level.INFO, "RoomStatusEditEvent().dispatch()");
		handler.onEdit(this);
	}
}