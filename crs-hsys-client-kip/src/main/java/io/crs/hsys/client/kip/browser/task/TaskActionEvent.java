/**
 * 
 */
package io.crs.hsys.client.kip.browser.task;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;

import io.crs.hsys.shared.dto.task.TaskDto;

/**
 * @author robi
 *
 */
public class TaskActionEvent extends GwtEvent<TaskActionEvent.TaskActionEventHandler> {

	public enum TaskAction {
		CREATE, START, PAUSE, COMPLETE, EDIT, DELETE;
	}

	public interface TaskActionEventHandler extends EventHandler {

		public void onTaskActionEvent(TaskActionEvent event);

	}

	public static Type<TaskActionEventHandler> TYPE = new Type<TaskActionEventHandler>();

	private TaskAction action;
	private TaskDto task;
	private Widget source;

	public TaskActionEvent() {
	}

	public TaskActionEvent(TaskAction action, TaskDto task, Widget source) {
		this.action = action;
		this.task = task;
		this.source = source;
	}

	public TaskAction getAction() {
		return action;
	}

	public void setAction(TaskAction action) {
		this.action = action;
	}

	public TaskDto getTask() {
		return task;
	}

	public void setTask(TaskDto task) {
		this.task = task;
	}

	public void setSource(Widget source) {
		this.source = source;
	}

	@Override
	public Type<TaskActionEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(TaskActionEventHandler handler) {
		handler.onTaskActionEvent(this);
	}

	public Widget getSource() {
		return source;
	}
}