/**
 * 
 */
package io.crs.hsys.client.kip.browser.task.widget;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.shared.dto.task.TaskDto;

/**
 * @author CR
 *
 */
public interface TaskWidgetUiHandlers extends UiHandlers {
	void startTask(TaskDto task);

	void pauseTask(TaskDto task);

	void completeTask(TaskDto task);
	
	void reassignTask(TaskDto task);

	void modifyTask(TaskDto task);

	void deleteTask(TaskDto task);
}
