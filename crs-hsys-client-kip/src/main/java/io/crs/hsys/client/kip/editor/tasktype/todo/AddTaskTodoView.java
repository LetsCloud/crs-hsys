/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype.todo;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addext.client.ui.MaterialTextBoxAdd;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.base.viewport.Resolution;
import gwt.material.design.client.base.viewport.ViewPort;
import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialDialog;
import io.crs.hsys.client.kip.editor.tasktype.TaskTypeEditorView;
import io.crs.hsys.shared.dto.task.TaskGroupDto;
import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
public class AddTaskTodoView extends ViewWithUiHandlers<AddTaskTodoUiHandlers> implements AddTaskTodoPresenter.MyView {
	private static Logger logger = Logger.getLogger(TaskTypeEditorView.class.getName());

	public static final String ALL_ITEMS = "*MIND";

	interface Binder extends UiBinder<Widget, AddTaskTodoView> {
	}

	@UiField
	MaterialDialog modal;

	@UiField
	MaterialComboBox<TaskGroupDto> taskGroupComboBox;

	@UiField
	MaterialTextBoxAdd todoSearch;

	@UiField
	MaterialCollection listPanel;

	/**
	* 
	*/
	@Inject
	AddTaskTodoView(Binder uiBinder) {
		logger.info("AddTaskTodoView()");
		initWidget(uiBinder.createAndBindUi(this));

		taskGroupComboBox.addSelectionHandler(e -> {
			getUiHandlers().onTaskGroupFilterChange(e.getSelectedValues().get(0).getCode());
		});

		todoSearch.addValueChangeHandler(e -> {
			getUiHandlers().onTaskGroupFilterChange(e.getValue());
		});
	}

	@Override
	public void open() {
		logger.info("AddTaskTodoView().open()->start");
		modal.setFullscreen(false);
		ViewPort.when(Resolution.MOBILE_SMALL).then(viewPortChange -> {
			modal.setFullscreen(true);
		});
		ViewPort.when(Resolution.MOBILE_MEDIUM).then(viewPortChange -> {
			modal.setFullscreen(true);
		});
		ViewPort.when(Resolution.MOBILE_LARGE).then(viewPortChange -> {
			modal.setFullscreen(true);
		});
		modal.open();
		logger.info("AddTaskTodoView().open()->end");
	}

	@UiHandler("saveButton")
	public void onSaveClick(ClickEvent event) {
		getUiHandlers().onAddTodos();
		modal.close();
	}

	@UiHandler("cancelButton")
	public void onCancelClick(ClickEvent event) {
		modal.close();
	}

	@Override
	public void setTaskTodoData(List<TaskTodoDto> data) {
		logger.info("AddTaskTodoView().setTaskTodoData()->start");
		listPanel.clear();

		for (TaskTodoDto todo : data) {
			TaskTodoWidget ttw = new TaskTodoWidget(todo.getDescription() + " (" + todo.getTimeRequired() + " perc)");
			ttw.getCheckBox().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					logger.info("AddTaskTodoView().onClick()");
					getUiHandlers().onClickTodo(todo);
				}
			});
			ttw.getMcs().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					logger.info("AddTaskTodoView().onClick2()");
					getUiHandlers().onClickTodo(todo);
				}
			});
			listPanel.add(ttw);
		}
		logger.info("AddTaskTodoView().setTaskTodoData()->end");
	}

	@Override
	public void setTaskGroupData(List<TaskGroupDto> data) {
		taskGroupComboBox.clear();
		taskGroupComboBox.addItem("Mind", new TaskGroupDto(ALL_ITEMS, "Mind"));
		for (TaskGroupDto tg : data) {
			taskGroupComboBox.addItem(tg.getCode() + "-" + tg.getDescription(), tg);
		}
	}
}
