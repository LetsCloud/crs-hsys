/**
 * 
 */
package io.crs.hsys.client.kip.browser.taskgroup;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import io.crs.hsys.client.kip.filter.KipFilterPresenterFactory;
import io.crs.hsys.client.kip.meditor.taskgroup.TaskGroupEditorFactory;
import io.crs.hsys.shared.api.TaskGroupResource;

/**
 * @author robi
 *
 */
public class MtTaskGroupBrowserPresenter extends TaskGroupBrowserPresenter {

	@Inject
	MtTaskGroupBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<TaskGroupResource> resourceDelegate, KipFilterPresenterFactory filterFactory,
			TaskGroupEditorFactory editorFactory) {
		super(eventBus, placeManager, view, resourceDelegate, filterFactory.createTaskGroupFilter(),
				editorFactory.createMtTaskGroupEditor());
	}

	public interface MyView extends TaskGroupBrowserPresenter.MyView {
	}

}
