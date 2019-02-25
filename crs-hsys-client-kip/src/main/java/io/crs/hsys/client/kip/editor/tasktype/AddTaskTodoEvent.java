/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author robi
 *
 */
public class AddTaskTodoEvent extends GwtEvent<AddTaskTodoEvent.AddTaskTodoEventHandler> {
	public interface AddTaskTodoEventHandler extends EventHandler {

		public void onAddTaskTodoEvent(AddTaskTodoEvent event);

	}

	public static Type<AddTaskTodoEventHandler> TYPE = new Type<AddTaskTodoEventHandler>();

	private Widget source;

	public AddTaskTodoEvent(Widget source) {
		this.source = source;
	}

	public AddTaskTodoEvent() {
	}

	@Override
	public Type<AddTaskTodoEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddTaskTodoEventHandler handler) {
		handler.onAddTaskTodoEvent(this);
	}

	public Widget getSource() {
		return source;
	}
}