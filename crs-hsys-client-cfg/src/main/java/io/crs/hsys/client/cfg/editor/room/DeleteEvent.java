/**
 * 
 */
package io.crs.hsys.client.cfg.editor.room;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author robi
 *
 */
public class DeleteEvent extends GwtEvent<DeleteEvent.DeleteEventHandler> {
	public interface DeleteEventHandler extends EventHandler {

		public void onDeleteEvent(DeleteEvent event);

	}

	public static Type<DeleteEventHandler> TYPE = new Type<DeleteEventHandler>();

	private Widget source;

	public DeleteEvent(Widget source) {
		this.source = source;
	}

	public DeleteEvent() {
	}

	@Override
	public Type<DeleteEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DeleteEventHandler handler) {
		handler.onDeleteEvent(this);
	}

	public Widget getSource() {
		return source;
	}
}