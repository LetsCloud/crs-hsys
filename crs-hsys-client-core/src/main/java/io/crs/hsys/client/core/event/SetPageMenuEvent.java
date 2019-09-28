/**
 * 
 */
package io.crs.hsys.client.core.event;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

import gwt.material.design.client.ui.MaterialLink;

/**
 * @author robi
 *
 */
public class SetPageMenuEvent extends GwtEvent<SetPageMenuEvent.SetPageMenuHandler> {
	private static final Logger LOGGER = Logger.getLogger(SetPageMenuEvent.class.getName());

	public interface SetPageMenuHandler extends EventHandler {
		void onSetPageMenu(SetPageMenuEvent event);
	}

	public static final Type<SetPageMenuHandler> TYPE = new Type<>();

	private final List<MaterialLink> menuItems;

	public List<MaterialLink> getMenuItems() {
		return menuItems;
	}

	public SetPageMenuEvent(List<MaterialLink> menuItems) {
		this.menuItems = menuItems;
	}

	public static void fire(List<MaterialLink> menuItems, HasHandlers source) {
		LOGGER.info("SetPageMenuEvent().fire()");
		source.fireEvent(new SetPageMenuEvent(menuItems));
	}

	@Override
	public Type<SetPageMenuHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SetPageMenuHandler handler) {
		handler.onSetPageMenu(this);
	}
}