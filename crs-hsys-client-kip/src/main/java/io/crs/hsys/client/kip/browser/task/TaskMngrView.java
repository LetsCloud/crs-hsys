/**
 * 
 */
package io.crs.hsys.client.kip.browser.task;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialCollapsibleBody;
import gwt.material.design.client.ui.MaterialCollapsibleHeader;
import gwt.material.design.client.ui.MaterialCollapsibleItem;

import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.kip.browser.task.widget.TaskBodyWidget2;
import io.crs.hsys.client.kip.browser.task.widget.TaskHeaderWidget2;
import io.crs.hsys.shared.dto.task.TaskDto;

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

//	@Inject
//	Provider<TaskWidget> taskWidgetProvider;

	private final EventBus eventBus;
	private final CurrentUser currentUser;

	@Inject
	TaskMngrView(Binder uiBinder, EventBus eventBus, CurrentUser currentUser) {
		logger.info("TaskMngrView()");
		this.eventBus = eventBus;
		this.currentUser = currentUser;
		initWidget(uiBinder.createAndBindUi(this));
		bindSlot(TaskMngrPresenter.FILTER_SLOT, filterPanel);
	}

	@Override
	public void setTasks(List<TaskDto> tasks) {
		collapsible.clear();
		for (TaskDto task : tasks) {
			MaterialCollapsibleItem<TaskDto> item = createItem();
	//		TaskWidget taskWidget = taskWidgetProvider.get();
	//		taskWidget.setTask(task);
			item.getHeader().add(new TaskHeaderWidget2(eventBus, task, currentUser));
			item.getBody().add(new TaskBodyWidget2(task, currentUser));
			collapsible.add(item);
		}
	}

	private MaterialCollapsibleItem<TaskDto> createItem() {
		MaterialCollapsibleItem<TaskDto> item = new MaterialCollapsibleItem<TaskDto>();
		item.add(new MaterialCollapsibleHeader());
		item.add(new MaterialCollapsibleBody());
		item.getBody().setMarginTop(10);
		item.getBody().setMarginLeft(15);
		item.getBody().setMarginRight(15);
		item.getBody().setMarginBottom(0);
		item.getBody().setPadding(0);
		return item;
	}

	@UiHandler("addButton")
	public void onClickAddButton(ClickEvent event) {
		getUiHandlers().create();
	}
}
