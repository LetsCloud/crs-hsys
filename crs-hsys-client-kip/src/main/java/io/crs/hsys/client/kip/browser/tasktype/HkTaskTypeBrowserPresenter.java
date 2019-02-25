/**
 * 
 */
package io.crs.hsys.client.kip.browser.tasktype;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.kip.editor.tasktype.TaskTypeEditorPresenter;
import io.crs.hsys.client.kip.filter.KipFilterPresenterFactory;
import io.crs.hsys.shared.api.TaskTypeResource;
import io.crs.hsys.shared.constans.TaskKind;

/**
 * @author robi
 *
 */
public class HkTaskTypeBrowserPresenter extends TaskTypeBrowserPresenter {

	@Inject
	HkTaskTypeBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<TaskTypeResource> resourceDelegate, KipFilterPresenterFactory filterFactory) {
		super(eventBus, placeManager, view, resourceDelegate, filterFactory);
		setFilter(TaskTypeEditorPresenter.PARAM_KIND, TaskKind.TK_CLEANING.toString());
	}

	public interface MyView extends TaskTypeBrowserPresenter.MyView {
	}

	@Override
	protected String getCreatorNameToken() {
		return CoreNameTokens.TASK_TYPE_EDITOR;
	}

	@Override
	protected String getEditorNameToken() {
		return CoreNameTokens.TASK_TYPE_EDITOR;
	}

}
