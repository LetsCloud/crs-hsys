/**
 * 
 */
package io.crs.hsys.client.kip.task.editor;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialTextBox;
import io.crs.hsys.shared.dto.common.AppUserDtor;
import io.crs.hsys.shared.dto.task.TaskDto;

/**
 * @author robi
 *
 */
public class TaskEditorView extends Composite implements Editor<TaskDto> {

	interface Binder extends UiBinder<Widget, TaskEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<TaskDto, TaskEditorView> {
	}

	private final Driver driver;

	@UiField
	MaterialButton saveButton;

	@UiField
	MaterialTextBox title;

	@UiField
	@Ignore
	MaterialComboBox<AppUserDtor> reporterCombo;
	TakesValueEditor<AppUserDtor> reporter;

	@UiField
	@Ignore
	MaterialComboBox<AppUserDtor> assigneeCombo;
	TakesValueEditor<AppUserDtor> assignee;

	/**
	 * 
	 */
	@Inject
	TaskEditorView(Binder uiBinder, Driver driver) {
		initWidget(uiBinder.createAndBindUi(this));

		assignee = TakesValueEditor.of(new TakesValue<AppUserDtor>() {
			@Override
			public void setValue(AppUserDtor value) {
				assigneeCombo.setSingleValue(value);
			}
			@Override
			public AppUserDtor getValue() {
				return assigneeCombo.getSingleValue();
			}
		});

		this.driver = driver;
		driver.initialize(this);
	}

	public MaterialButton getSaveButton() {
		return saveButton;
	}

	public void setTask(TaskDto task) {
		driver.edit(task);
	}
}
