/**
 * 
 */
package io.crs.hsys.client.kip.tasks.editor;

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
import gwt.material.design.client.ui.MaterialTextArea;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author robi
 *
 */
public class TaskEditorView extends ViewWithUiHandlers<TaskEditorUiHandlers>
		implements TaskEditorPresenter.MyView, Editor<TaskDto> {
	private static Logger logger = Logger.getLogger(TaskEditorView.class.getName());

	interface Binder extends UiBinder<Widget, TaskEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<TaskDto, TaskEditorView> {
	}

	private final Driver driver;

//private final CoreConstants i18nCoreCnst;

	@Ignore
	@UiField
	MaterialComboBox<TaskTypeDto> taskTypeComboBox;
	TakesValueEditor<TaskTypeDto> type;

	@UiField
	MaterialTextArea description;

	@Ignore
	@UiField
	SimplePanel addTaskTodoPanel;

	/**
	* 
	*/
	@Inject
	TaskEditorView(Binder uiBinder, Driver driver, CoreConstants i18nCoreCnst) {
		logger.info("TaskEditorView()");

		initWidget(uiBinder.createAndBindUi(this));

		initTaskTypeCombo();

		this.driver = driver;

		driver.initialize(this);
	}

	private void initTaskTypeCombo() {

		type = TakesValueEditor.of(new TakesValue<TaskTypeDto>() {

			@Override
			public void setValue(TaskTypeDto value) {
				taskTypeComboBox.setSingleValue(value);
			}

			@Override
			public TaskTypeDto getValue() {
				return taskTypeComboBox.getSingleValue();
			}
		});
	}

	@Override
	public void show(TaskDto dto) {
	}

	@Override
	public void edit(TaskDto dto) {
		driver.edit(dto);

		Timer t = new Timer() {
			@Override
			public void run() {
				description.setFocus(true);
			}
		};
		t.schedule(100);
	}

	@Override
	public void displayError(EntityPropertyCode code, String message) {
//TODO Auto-generated method stub

	}

	@UiHandler("saveButton")
	void onSaveClick(ClickEvent event) {
		TaskDto dto = driver.flush();
		getUiHandlers().save(dto);
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent event) {
		getUiHandlers().cancel();
	}

	@Override
	public void setTaskTypeData(List<TaskTypeDto> data) {
		taskTypeComboBox.clear();
		for (TaskTypeDto tg : data) {
			taskTypeComboBox.addItem(tg.getCode() + " - " + tg.getDescription(), tg);
		}
	}

	@Override
	public void close() {
//TODO Auto-generated method stub

	}
}
