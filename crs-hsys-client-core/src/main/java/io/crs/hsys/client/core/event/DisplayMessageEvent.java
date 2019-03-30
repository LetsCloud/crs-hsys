/**
 * 
 */
package io.crs.hsys.client.core.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

import io.crs.hsys.client.core.message.MessageData;

/**
 * @author CR
 *
 */
public class DisplayMessageEvent extends GwtEvent<DisplayMessageEvent.DisplayMessageHandler> {
	
	public interface DisplayMessageHandler extends EventHandler {
		void onDisplayMessage(DisplayMessageEvent event);
	}

	public static final Type<DisplayMessageHandler> TYPE = new Type<>();

	private MessageData message;

	DisplayMessageEvent(MessageData message) {
		this.message = message;
	}

	public static Type<DisplayMessageHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, MessageData message) {
		source.fireEvent(new DisplayMessageEvent(message));
	}

	@Override
	public Type<DisplayMessageHandler> getAssociatedType() {
		return TYPE;
	}

	public MessageData getMessage() {
		return message;
	}

	@Override
	protected void dispatch(DisplayMessageHandler handler) {
		handler.onDisplayMessage(this);
	}
}