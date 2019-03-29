/**
 * 
 */
package io.crs.hsys.client.kip.meditor.tasktodo;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;

import io.crs.hsys.client.core.datasource.TaskGroupDataSource;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.TaskTodoResource;
import io.crs.hsys.shared.cnst.TaskKind;

/**
 * @author robi
 *
 */
public class HkTaskTodoEditorPresenter extends TaskTodoEditorPresenter {

	@Inject
	HkTaskTodoEditorPresenter(EventBus eventBus, MyView view, ResourceDelegate<TaskTodoResource> resourceDelegate,
			TaskGroupDataSource taskGroupDataSource, CurrentUser currentUser) {
		super(eventBus, view, resourceDelegate, taskGroupDataSource, currentUser);
	}

	public interface MyView extends TaskTodoEditorPresenter.MyView {
	}

	@Override
	protected TaskKind getDefaultTaskKind() {
		return TaskKind.TK_CLEANING;
	}

}
