/**
 * 
 */
package io.crs.hsys.client.cfg.editor.profile;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author robi
 *
 */
public class CommunicationActionEvent extends GwtEvent<CommunicationActionEvent.CommunicationActionEventHandler> {
	
	public interface CommunicationActionEventHandler extends EventHandler {
		public void onCommunicationAction(CommunicationActionEvent event);
	}

	public enum Action {
		OPEN, CLOSE, DELETE
	}

	public static Type<CommunicationActionEventHandler> TYPE = new Type<CommunicationActionEventHandler>();

	private Action action;

	private int index;

	public CommunicationActionEvent(Action action, int index) {
		this.action = action;
		this.index = index;
	}

	public Action getAction() {
		return action;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public Type<CommunicationActionEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CommunicationActionEventHandler handler) {
		handler.onCommunicationAction(this);
	}
}