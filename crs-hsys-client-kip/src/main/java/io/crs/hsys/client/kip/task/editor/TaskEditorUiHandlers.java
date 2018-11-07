/**
 * 
 */
package io.crs.hsys.client.kip.task.editor;

import com.gwtplatform.mvp.client.UiHandlers;

import io.crs.hsys.shared.dto.task.TaskDto;

/**
 * @author robi
 *
 */
public interface TaskEditorUiHandlers extends UiHandlers {

	void create();

	void edit(TaskDto dto);

	void save(TaskDto dto);

}
