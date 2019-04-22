/**
 * 
 */
package io.crs.hsys.client.cfg.filter;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * @author robi
 *
 */
public class FilterChangeEvent extends GwtEvent<FilterChangeEvent.FilterChangeHandler> {

	public interface FilterChangeHandler extends EventHandler {
		void onFilterChange(FilterChangeEvent event);
	}

	public static final Type<FilterChangeHandler> TYPE = new Type<>();

	public FilterChangeEvent() {
	}

	public static void fire(HasHandlers source) {
		source.fireEvent(new FilterChangeEvent());
	}

	@Override
	public Type<FilterChangeHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(FilterChangeHandler handler) {
		handler.onFilterChange(this);
	}
}