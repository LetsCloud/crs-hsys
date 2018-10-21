/**
 * 
 */
package io.crs.hsys.client.core.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

import io.crs.hsys.client.core.ui.message.Message;

/**
 * @author CR
 *
 */
public class DisplayMessageEvent extends GwtEvent<DisplayMessageEvent.DisplayMessageHandler> {
	
	public interface DisplayMessageHandler extends EventHandler {
		void onDisplayMessage(DisplayMessageEvent event);
	}

	private static final Type<DisplayMessageHandler> TYPE = new Type<>();

	private Message message;

	DisplayMessageEvent(Message message) {
		this.message = message;
	}

	public static Type<DisplayMessageHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, Message message) {
		source.fireEvent(new DisplayMessageEvent(message));
	}

	@Override
	public Type<DisplayMessageHandler> getAssociatedType() {
		return TYPE;
	}

	public Message getMessage() {
		return message;
	}

	@Override
	protected void dispatch(DisplayMessageHandler handler) {
		handler.onDisplayMessage(this);
	}
}