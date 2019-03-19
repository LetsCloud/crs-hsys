/**
 * 
 */
package io.crs.hsys.client.kip.browser.task;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialCollapsible;

/**
 * @author robi
 *
 */
public class TaskMngrView extends ViewWithUiHandlers<TaskMngrUiHandlers> implements TaskMngrPresenter.MyView {
	private static Logger logger = Logger.getLogger(TaskMngrView.class.getName());

	interface Binder extends UiBinder<Widget, TaskMngrView> {
	}

	@UiField
	SimplePanel filterPanel;

	@UiField
	MaterialCollapsible tasksPanel;

	@Inject
	TaskMngrView(Binder uiBinder) {
		logger.info("TaskMngrView()");
		initWidget(uiBinder.createAndBindUi(this));
		bindSlot(TaskMngrPresenter.FILTER_SLOT, filterPanel);
		bindSlot(TaskMngrPresenter.SLOT_TASKS, tasksPanel);				
	}

	@Override
	public void clearTasksPanel() {
		tasksPanel.clear();
	}

	@UiHandler("addButton")
	public void onClickAddButton(ClickEvent event) {
		getUiHandlers().create();
	}
}
