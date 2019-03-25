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

import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialDialog;
import io.crs.hsys.client.kip.editor.tasktype.TaskTypeEditorView;
import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
public class AddTaskTodoView extends ViewWithUiHandlers<AddTaskTodoUiHandlers> implements AddTaskTodoPresenter.MyView {
	private static Logger logger = Logger.getLogger(TaskTypeEditorView.class.getName());

	interface Binder extends UiBinder<Widget, AddTaskTodoView> {
	}

	@UiField
	MaterialDialog modal;

	@UiField
	MaterialCollection listPanel;

	/**
	* 
	*/
	@Inject
	AddTaskTodoView(Binder uiBinder) {
		logger.info("AddTaskTodoView()");
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void open() {
		modal.open();
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
		listPanel.clear();

		for (TaskTodoDto todo : data) {
			TaskTodoWidget ttw = new TaskTodoWidget(todo.getDescription() + " (" + todo.getTimeRequired()+" perc)");
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
	}
}
