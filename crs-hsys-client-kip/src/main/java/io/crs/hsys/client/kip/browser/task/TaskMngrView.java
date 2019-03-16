/**
 * 
 */
package io.crs.hsys.client.kip.browser.task;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCollapsible;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.client.kip.resources.KipGssResources;

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
	MaterialCollapsible collapsible;

	@UiField
	MaterialButton addButton;

	@Inject
	Provider<TaskWidget> taskWidget2Provider;

	@Inject
	TaskMngrView(Binder uiBinder, KipGssResources res) {
		logger.info("TaskMngrView()");
		initWidget(uiBinder.createAndBindUi(this));
		bindSlot(TaskMngrPresenter.FILTER_SLOT, filterPanel);
		initView(res);
	}

	private void initView(KipGssResources res) {
	}

	@Override
	public void setTasks(List<TaskDto> tasks, KipGssResources res) {
		collapsible.clear();
		for (TaskDto task : tasks) {
			TaskWidget tw2 = taskWidget2Provider.get();
			collapsible.add(tw2);
			tw2.setTask(task);
		}
	}

	@UiHandler("addButton")
	public void onClickAddButton(ClickEvent event) {
		getUiHandlers().create();
	}
}
