/**
 * 
 */
package io.crs.hsys.client.kip.assignments;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author CR
 *
 */
public class AssignmentEditEvent extends GwtEvent<AssignmentEditEvent.AssignmentEditEventHandler> {
	private static Logger logger = Logger.getLogger(AssignmentEditEvent.class.getName());

	public interface AssignmentEditEventHandler extends EventHandler {
		void onEditAssignment(AssignmentEditEvent event);
	}

	public interface HasAssignmentEditEventHandlers {
		HandlerRegistration addComplexEventHandler(AssignmentEditEvent.AssignmentEditEventHandler handler,
				Object source);
	}

	public static final Type<AssignmentEditEventHandler> TYPE = new Type<>();

	public AssignmentEditEvent() {
	}

	public static void fire(HasHandlers source) {
		logger.log(Level.INFO, "AssignmentEditEvent.fire()");
		source.fireEvent(new AssignmentEditEvent());
	}

	@Override
	public Type<AssignmentEditEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AssignmentEditEventHandler handler) {
		handler.onEditAssignment(this);
	}
}