/**
 * 
 */
package io.crs.hsys.client.kip.tasks;

import java.util.logging.Logger;

import javax.inject.Inject;

import gwt.material.design.client.ui.MaterialCollapsibleBody;
import gwt.material.design.client.ui.MaterialCollapsibleHeader;
import gwt.material.design.client.ui.MaterialCollapsibleItem;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.kip.resources.KipGssResources;

/**
 * @author robi
 *
 */
public class TaskWidget2 extends MaterialCollapsibleItem<TaskDto> {
	private static Logger logger = Logger.getLogger(TaskWidget2.class.getName());

	private TaskHeaderWidget taskDisplay;
	private TaskBodyWidget taskBody;

	private CurrentUser currentUser;
	
	@Inject
	TaskWidget2(KipGssResources res, CurrentUser currentUser) {
		super();
		logger.info("TaskWidget2()");
		this.currentUser = currentUser;
		add(new MaterialCollapsibleHeader());
		add(new MaterialCollapsibleBody());
		getBody().setMarginTop(10);
		getBody().setMarginLeft(15);
		getBody().setMarginRight(15);
		getBody().setMarginBottom(0);
		getBody().setPadding(0);
	}

	public void setTask(TaskDto task) {
		taskDisplay = new TaskHeaderWidget(task, currentUser);
		getHeader().add(taskDisplay);

		taskBody = new TaskBodyWidget(task);
		getBody().add(taskBody);
	}
}
