/**
 * 
 */
package io.crs.hsys.client.kip.task;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.client.kip.resources.KipGssResources;
import io.crs.hsys.client.kip.ui.TaskCollapsible;

/**
 * @author robi
 *
 */
public class TaskMngrView extends ViewWithUiHandlers<TaskMngrUiHandlers> implements TaskMngrPresenter.MyView {
	private static Logger logger = Logger.getLogger(TaskMngrView.class.getName());

	interface Binder extends UiBinder<Widget, TaskMngrView> {
	}

	@UiField(provided = true)
	TaskCollapsible collapsible;

	@Inject
	Provider<TaskWidget2> taskWidget2Provider;

	@Inject
	TaskMngrView(Binder uiBinder, KipGssResources res) {
		logger.info("TaskMngrView()");
		collapsible = new TaskCollapsible(res);
		initWidget(uiBinder.createAndBindUi(this));
		initView(res);

	}

	private void initView(KipGssResources res) {
	}

	@Override
	public void setTasks(List<TaskDto> tasks, KipGssResources res) {
		collapsible.clear();
		for (TaskDto task : tasks) {
			TaskWidget2 tw2 = taskWidget2Provider.get();
			collapsible.add(tw2);
			tw2.setTask(task);
		}
	}
}
