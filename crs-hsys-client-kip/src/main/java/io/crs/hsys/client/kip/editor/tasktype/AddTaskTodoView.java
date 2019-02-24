/**
 * 
 */
package io.crs.hsys.client.kip.editor.tasktype;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialDialog;

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
		logger.info("RoomTypeEditorView()");
		initWidget(uiBinder.createAndBindUi(this));
		
		listPanel.add(new TaskTodoWidget("Ágynemű csere"));
		listPanel.add(new TaskTodoWidget("Pótágy elvitel"));
		listPanel.add(new TaskTodoWidget("Fürdőszoba takarítás"));
		listPanel.add(new TaskTodoWidget("Poharak elmosása"));
		listPanel.add(new TaskTodoWidget("Türülköző csere"));
		listPanel.add(new TaskTodoWidget("Portörlés"));
		listPanel.add(new TaskTodoWidget("Porszívózás"));
		listPanel.add(new TaskTodoWidget("Készülékek ellenőrzése"));
		listPanel.add(new TaskTodoWidget("Világítás lekapcsolása"));
		listPanel.add(new TaskTodoWidget("Szoba bezárása"));
	}

	@Override
	public void open() {
		modal.open();
	}

	@UiHandler("saveButton")
	public void onSaveClick(ClickEvent event) {
		modal.close();
	}
}
