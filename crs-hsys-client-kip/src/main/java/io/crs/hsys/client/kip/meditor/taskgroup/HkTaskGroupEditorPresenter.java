package io.crs.hsys.client.kip.meditor.taskgroup;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;

import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.TaskGroupResource;
import io.crs.hsys.shared.constans.TaskKind;

public class HkTaskGroupEditorPresenter extends TaskGroupEditorPresenter {

	@Inject
	HkTaskGroupEditorPresenter(EventBus eventBus, MyView view, ResourceDelegate<TaskGroupResource> resourceDelegate,
			CurrentUser currentUser) {
		super(eventBus, view, resourceDelegate, currentUser);
	}

	public interface MyView extends TaskGroupEditorPresenter.MyView {
	}

	@Override
	protected TaskKind getDefaultTaskKind() {
		return TaskKind.CLEANING;
	}

}
