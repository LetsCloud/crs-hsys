/**
 * 
 */
package io.crs.hsys.client.kip.atendants;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author CR
 *
 */
public class AtendantItemEvent extends GwtEvent<AtendantItemEventHandler> {
	
	public static final Type<AtendantItemEventHandler> TYPE = new Type<AtendantItemEventHandler>();

	public static Type<AtendantItemEventHandler> getType() {
		return TYPE;
	}

	@Override
	public Type<AtendantItemEventHandler> getAssociatedType() {
		return TYPE;
	}

	public AtendantItemEvent() {
	}

	@Override
	protected void dispatch(AtendantItemEventHandler handler) {
		handler.onForwardClick(this);
	}
}
