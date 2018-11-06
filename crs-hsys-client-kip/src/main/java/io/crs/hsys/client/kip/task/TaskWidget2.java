/**
 * 
 */
package io.crs.hsys.client.kip.task;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Provider;

import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.client.kip.resources.KipGssResources;
import io.crs.hsys.client.kip.task.TaskActionEvent.TaskAction;
import io.crs.hsys.client.kip.task.TaskActionEvent.TaskActionEventHandler;
import io.crs.hsys.client.kip.task.editor.TaskEditorView;
import io.crs.hsys.client.kip.ui.TaskCollapsibleBody;
import io.crs.hsys.client.kip.ui.TaskCollapsibleHeader;
import io.crs.hsys.client.kip.ui.TaskCollapsibleItem;

/**
 * @author robi
 *
 */
public class TaskWidget2 extends TaskCollapsibleItem implements TaskActionEventHandler {
	private static Logger logger = Logger.getLogger(TaskWidget2.class.getName());

	private TaskDisplay taskDisplay = new TaskDisplay();

	@Inject
	Provider<TaskEditorView> taskEditorProvider;
	private TaskEditorView taskEditor;
	
	private TaskDto task;

	@Inject
	TaskWidget2(KipGssResources res) {
		super();
		add(new TaskCollapsibleHeader(res));
		add(new TaskCollapsibleBody(res));

	}

	public void setTask(TaskDto task) {
		this.task = task;
		taskEditor = taskEditorProvider.get();
		taskEditor.setTask(task);
		
		taskDisplay = new TaskDisplay(task);
//		taskDisplay.setTask(task);
		
		taskDisplay.getEditLink().addClickHandler(e -> {
			logger.info("TaskWidget2()->openClick");
			this.setActive(true);
			// body.setVisible(true);
		});
		
		header.add(taskDisplay);
		
		taskEditor.getSaveButton().addClickHandler(e -> {
			logger.info("TaskWidget2()->closeClick");
			// fireCollapsibleHandler();
			this.setActive(false);
		});
		body.add(taskEditor);
	}
	
	public void setActive(boolean active) {
		logger.info("TaskWidget2().setActive(" + active + ")");
		super.setActive(active);
		if (active) {
			body.add(taskEditor);
			header.setVisible(false);
		} else {
			clearActive();
		}
	}

	@Override
	protected void clearActive() {
		logger.info("TaskWidget2().clearActive()");
		header.setVisible(true);
		body.setVisible(false);
		body.clear();
	}

	@Override
	public void onTaskActionEvent(TaskActionEvent event) {
		if (event.getTask().equals(this.task)) {
			if (event.getAction().equals(TaskAction.EDIT)) {
				
			}
		}
	}
}
