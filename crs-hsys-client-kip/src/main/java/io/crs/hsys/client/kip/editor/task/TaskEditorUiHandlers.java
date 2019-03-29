package io.crs.hsys.client.kip.editor.task;

import io.crs.hsys.client.core.editor.AbstractEditorUiHandlers;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.dto.task.TaskDto;

public interface TaskEditorUiHandlers extends AbstractEditorUiHandlers<TaskDto> {
	
	void filterTaskTypes(TaskKind kind); 

}
