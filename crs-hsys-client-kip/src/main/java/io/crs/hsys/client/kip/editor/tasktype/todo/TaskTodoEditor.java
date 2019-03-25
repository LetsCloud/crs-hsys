/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype.todo;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialLabel;

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

	@Ignore
	@UiField
	MaterialLabel label;

	TakesValueEditor<String> description;

	TakesValueEditor<Integer> timeRequired;

	@Ignore
	@UiField
	MaterialCheckBox checkBox;

	private String descriptionText;
	private Integer timeRequredInt;

	/**
	 */
	@Inject
	TaskTodoEditor(Binder uiBinder, CoreMessages i18nCore) {
		initWidget(uiBinder.createAndBindUi(this));

		descriptionText = "";
		timeRequredInt = 0;

		description = TakesValueEditor.of(new TakesValue<String>() {

			@Override
			public void setValue(String value) {
				descriptionText = value;
				setLabelText();
			}

			@Override
			public String getValue() {
				return descriptionText;
			}
		});

		timeRequired = TakesValueEditor.of(new TakesValue<Integer>() {

			@Override
			public void setValue(Integer value) {
				timeRequredInt = value;
				setLabelText();
			}

			@Override
			public Integer getValue() {
				return timeRequredInt;
			}
		});
	}

	private void setLabelText() {
		label.setText(descriptionText + " / " + timeRequredInt);
	}

	/*
	 * private void fireDeleteEvent() { fireEvent(new DeleteEvent()); }
	 */
	public final HandlerRegistration addDeleteHandler(DeleteEventHandler handler) {
		return addHandler(handler, DeleteEvent.TYPE);
	}
	
	public Boolean isSelected() {
		return checkBox.getValue();
	}
}
