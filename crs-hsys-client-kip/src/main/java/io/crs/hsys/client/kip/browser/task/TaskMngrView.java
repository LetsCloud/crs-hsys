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
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialCollapsibleBody;
import gwt.material.design.client.ui.MaterialCollapsibleHeader;
import gwt.material.design.client.ui.MaterialCollapsibleItem;

import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.kip.assignments.AssignmentsPresenter;
import io.crs.hsys.client.kip.browser.task.widget.TaskBodyWidget;
import io.crs.hsys.client.kip.browser.task.widget.TaskHeaderWidget;
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

	@Inject
	TaskMngrView(Binder uiBinder) {
		logger.info("TaskMngrView()");
		initWidget(uiBinder.createAndBindUi(this));
		bindSlot(TaskMngrPresenter.FILTER_SLOT, filterPanel);
		bindSlot(TaskMngrPresenter.SLOT_TASKS, collapsible);				
	}

	@UiHandler("addButton")
	public void onClickAddButton(ClickEvent event) {
		getUiHandlers().create();
	}
}
