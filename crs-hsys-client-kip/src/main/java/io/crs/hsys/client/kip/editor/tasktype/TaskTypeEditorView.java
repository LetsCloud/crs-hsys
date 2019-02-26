/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialIntegerBox;
import gwt.material.design.client.ui.MaterialTextBox;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.task.TaskGroupDto;
import io.crs.hsys.shared.dto.task.TaskTodoDto;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author robi
 *
 */
public class TaskTypeEditorView extends ViewWithUiHandlers<TaskTypeEditorUiHandlers>
		implements TaskTypeEditorPresenter.MyView, Editor<TaskTypeDto> {
	private static Logger logger = Logger.getLogger(TaskTypeEditorView.class.getName());

	interface Binder extends UiBinder<Widget, TaskTypeEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<TaskTypeDto, TaskTypeEditorView> {
	}

	private final Driver driver;

// private final CoreConstants i18nCoreCnst;

	@Ignore
	@UiField
	MaterialComboBox<TaskGroupDto> taskGroupComboBox;
	TakesValueEditor<TaskGroupDto> taskGroup;

	@UiField
	MaterialTextBox code, description;

	@UiField
	MaterialIntegerBox timeRequired;

	@UiField(provided = true)
	TaskTodoListEditor todos;

	@UiField
	MaterialButton saveButton;

	@Ignore
	@UiField
	SimplePanel addTaskTodoPanel;

	/**
	* 
	*/
	@Inject
	TaskTypeEditorView(Binder uiBinder, Driver driver, CoreConstants i18nCoreCnst,
			TaskTodoListEditor taskTodoListEditor) {
		logger.info("TaskTypeEditorView()");

		this.todos = taskTodoListEditor;

		initWidget(uiBinder.createAndBindUi(this));

		initRoomTypeCombo();

		this.driver = driver;

		bindSlot(TaskTypeEditorPresenter.SLOT_ADD_TASKTODO, addTaskTodoPanel);

		driver.initialize(this);
	}

	private void initRoomTypeCombo() {

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

	@Override
	public void show(TaskTypeDto dto) {
// TODO Auto-generated method stub

	}

	@Override
	public void edit(TaskTypeDto dto) {
		driver.edit(dto);

		Timer t = new Timer() {
			@Override
			public void run() {
				code.setFocus(true);
			}
		};
		t.schedule(100);
	}

	@Override
	public void displayError(EntityPropertyCode code, String message) {
// TODO Auto-generated method stub

	}

	@UiHandler("saveButton")
	void onSaveClick(ClickEvent event) {
		TaskTypeDto dto = driver.flush();
		getUiHandlers().save(dto);
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent event) {
		getUiHandlers().cancel();
	}

	@Override
	public void setTaskGroupData(List<TaskGroupDto> data) {
		taskGroupComboBox.clear();
		for (TaskGroupDto tg : data) {
			taskGroupComboBox.addItem(tg.getCode() + " - " + tg.getDescription(), tg);
		}
	}

	@Override
	public void close() {
// TODO Auto-generated method stub

	}

	@Override
	public void setTaskTodoData(List<TaskTodoDto> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAddTaskTodo(AddTaskTodoPresenter addTaskTodo) {
		todos.setAddTaskTodo(addTaskTodo);
	}

	@Override
	public void addTaskTodos(List<TaskTodoDto> todos) {
		logger.info("TaskTypeEditorView().addTaskTodos()");
		for (TaskTodoDto tt : todos)
			logger.info("TaskTypeEditorView().addTaskTodos()->" + tt.getDescription());
		this.todos.addTaskTodos(todos);
	}
}
