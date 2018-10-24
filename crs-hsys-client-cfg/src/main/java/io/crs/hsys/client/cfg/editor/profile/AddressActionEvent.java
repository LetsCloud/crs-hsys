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
public class AddressActionEvent extends GwtEvent<AddressActionEvent.AddressActiEventHandler> {
	public interface AddressActiEventHandler extends EventHandler {
		public void onAddressAction(AddressActionEvent event);
	}

	public enum Action {
		OPEN, CLOSE, DELETE
	}

	public static Type<AddressActiEventHandler> TYPE = new Type<AddressActiEventHandler>();

	private Action action;

	private int index;

	public AddressActionEvent(Action action, int index) {
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
	public Type<AddressActiEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddressActiEventHandler handler) {
		handler.onAddressAction(this);
	}
}