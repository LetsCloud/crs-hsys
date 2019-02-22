/**
 * 
 */
package io.crs.hsys.client.kip.browser.taskgroup;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import io.crs.hsys.client.core.filter.FilterPresenterFactory;
import io.crs.hsys.client.kip.meditor.taskgroup.TaskGroupEditorFactory;
import io.crs.hsys.shared.api.TaskGroupResource;

/**
 * @author robi
 *
 */
public class HkTaskGroupBrowserPresenter extends TaskGroupBrowserPresenter {

	@Inject
	HkTaskGroupBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<TaskGroupResource> resourceDelegate, FilterPresenterFactory filterPresenterFactory,
			TaskGroupEditorFactory editorFactory) {
		super(eventBus, placeManager, view, resourceDelegate, filterPresenterFactory,
				editorFactory.createHkTaskGroupEditor());
	}

	public interface MyView extends TaskGroupBrowserPresenter.MyView {
	}

}
