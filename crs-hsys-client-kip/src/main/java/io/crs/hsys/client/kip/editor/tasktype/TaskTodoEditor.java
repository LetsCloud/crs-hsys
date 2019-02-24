/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialIntegerBox;
import gwt.material.design.client.ui.MaterialTextBox;

import io.crs.hsys.client.core.editor.room.DeleteEvent;
import io.crs.hsys.client.core.editor.room.DeleteEvent.DeleteEventHandler;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
public class TaskTodoEditor extends Composite implements Editor<TaskTodoDto> {

	interface Binder extends UiBinder<Widget, TaskTodoEditor> {
	}

	@UiField
	MaterialTextBox description;

	@UiField
	MaterialIntegerBox timeRequired;

	/**
	 */
	@Inject
	TaskTodoEditor(Binder uiBinder, CoreMessages i18nCore) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	/*
	 * private void fireDeleteEvent() { fireEvent(new DeleteEvent()); }
	 */
	public final HandlerRegistration addDeleteHandler(DeleteEventHandler handler) {
		return addHandler(handler, DeleteEvent.TYPE);
	}
}
