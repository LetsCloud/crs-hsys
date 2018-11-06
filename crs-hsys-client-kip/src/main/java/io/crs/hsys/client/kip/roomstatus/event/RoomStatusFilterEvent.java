/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

import io.crs.hsys.shared.dto.filter.RoomStatusFilterDto;

/**
 * @author CR
 *
 */
public class RoomStatusFilterEvent extends GwtEvent<RoomStatusFilterEvent.RoomStatusFilterHandler> {

	public interface RoomStatusFilterHandler extends EventHandler {
		void onFilter(RoomStatusFilterEvent event);
	}

	private static final Type<RoomStatusFilterHandler> TYPE = new Type<>();

	private final RoomStatusFilterDto filterDto;

	RoomStatusFilterEvent(RoomStatusFilterDto filterDto) {
		this.filterDto = filterDto;
	}

	public static Type<RoomStatusFilterHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, RoomStatusFilterDto filterDto) {
		source.fireEvent(new RoomStatusFilterEvent(filterDto));
	}

	@Override
	public Type<RoomStatusFilterHandler> getAssociatedType() {
		return TYPE;
	}

	public RoomStatusFilterDto getFilterDto() {
		return filterDto;
	}

	@Override
	protected void dispatch(RoomStatusFilterHandler handler) {
		handler.onFilter(this);
	}
}