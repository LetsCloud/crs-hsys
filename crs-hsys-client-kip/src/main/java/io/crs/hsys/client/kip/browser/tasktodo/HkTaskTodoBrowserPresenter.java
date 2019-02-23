/**
 * 
 */
package io.crs.hsys.client.kip.browser.tasktodo;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import io.crs.hsys.client.kip.filter.KipFilterPresenterFactory;
import io.crs.hsys.client.kip.meditor.tasktodo.TaskTodoEditorFactory;
import io.crs.hsys.shared.api.TaskTodoResource;

/**
 * @author robi
 *
 */
public class HkTaskTodoBrowserPresenter extends TaskTodoBrowserPresenter {

	@Inject
	HkTaskTodoBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<TaskTodoResource> resourceDelegate, KipFilterPresenterFactory filterFactory,
			TaskTodoEditorFactory editorFactory) {
		super(eventBus, placeManager, view, resourceDelegate, filterFactory.createTaskGroupFilterPresenter(),
				editorFactory.createHkTaskTodoEditor());
	}

	public interface MyView extends TaskTodoBrowserPresenter.MyView {
	}

}
