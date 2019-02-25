/**
 * 
 */
package io.crs.hsys.client.kip.meditor.tasktodo;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialDialog;
import gwt.material.design.client.ui.MaterialIntegerBox;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialTitle;
import gwt.material.design.client.ui.MaterialToast;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.task.TaskGroupDto;
import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
public class TaskTodoEditorView extends ViewWithUiHandlers<TaskTodoEditorUiHandlers>
		implements TaskTodoEditorPresenter.MyView, Editor<TaskTodoDto> {
	private static Logger logger = Logger.getLogger(TaskTodoEditorView.class.getName());

	interface Binder extends UiBinder<Widget, TaskTodoEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<TaskTodoDto, TaskTodoEditorView> {
	}

	@UiField
	MaterialDialog modal;

	@UiField
	@Ignore
	MaterialTitle title;

	@UiField
	@Ignore
	MaterialComboBox<TaskKind> kindComboBox;
	TakesValueEditor<TaskKind> kind;

	@UiField
	@Ignore
	MaterialComboBox<TaskGroupDto> taskGroupComboBox;
	TakesValueEditor<TaskGroupDto> taskGroup;

	@UiField
	MaterialTextBox description;

	@UiField
	MaterialIntegerBox timeRequired;

	@UiField
	MaterialCheckBox active;

	private final Driver driver;
	private final CoreMessages i18nCore;

	TaskTodoEditorView(Binder uiBinder, Driver driver, CoreMessages i18nCore) {
		logger.info("TaskTodoEditorView()");
		initWidget(uiBinder.createAndBindUi(this));

		this.driver = driver;
		this.i18nCore = i18nCore;

		kind = TakesValueEditor.of(new TakesValue<TaskKind>() {

			@Override
			public void setValue(TaskKind value) {
				kindComboBox.setSingleValue(value);
			}

			@Override
			public TaskKind getValue() {
				return kindComboBox.getSingleValue();
			}
		});

		initTaskGroupComboBox();
		
		driver.initialize(this);
	}

	private void initTaskGroupComboBox() {

		taskGroup = TakesValueEditor.of(new TakesValue<TaskGroupDto>() {

			@Override
			public void setValue(TaskGroupDto value) {
				taskGroupComboBox.setSingleValue(value);
			}

			@Override
			public TaskGroupDto getValue() {
				return taskGroupComboBox.getSingleValue();
			}
		});
	}

	@Ignore
	protected MaterialComboBox<TaskKind> getKindCombo() {
		return kindComboBox;
	}

	@Override
	public void open(Boolean isNew, TaskTodoDto dto) {
		if (isNew) {
			title.setTitle(i18nCore.taskTodoEditorCreateTitle());
		} else {
			title.setTitle(i18nCore.taskTodoEditorModifyTitle());
		}
		driver.edit(dto);
//name.clearErrorOrSuccess();
		modal.open();

		Timer t = new Timer() {
			@Override
			public void run() {
				description.setFocus(true);
			}
		};
		t.schedule(500);

	}

	@Override
	public void close() {
		modal.close();
	}

	@UiHandler("saveButton")
	void onSaveClick(ClickEvent event) {
		getUiHandlers().save(driver.flush());
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent event) {
		close();
	}

	@Override
	public void displayError(EntityPropertyCode code, String message) {
		switch (code) {
		case NONE:
			break;
		case USER_GROUP_NAME:
//name.setError(message);
			break;
		}
		MaterialToast.fireToast(message);
	}

	@Override
	public void setTaskGroupData(List<TaskGroupDto> data) {
		taskGroupComboBox.clear();
		for (TaskGroupDto dto : data) {
			taskGroupComboBox.addItem(dto.getCode() + " - " + dto.getDescription(), dto);
		}
	}
}