/**
 * 
 */
package io.crs.hsys.client.kip.browser.tasktype;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.kip.editor.tasktype.TaskTypeEditorPresenter;
import io.crs.hsys.client.kip.filter.KipFilterPresenterFactory;
import io.crs.hsys.shared.api.TaskTypeResource;
import io.crs.hsys.shared.cnst.TaskKind;

/**
 * @author CR
 *
 */
public class MtTaskTypeBrowserPresenter extends TaskTypeBrowserPresenter {

	@Inject
	MtTaskTypeBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<TaskTypeResource> resourceDelegate, KipFilterPresenterFactory filterFactory,
			CoreMessages i18nCore) {
		super(eventBus, placeManager, view, resourceDelegate, filterFactory, i18nCore);
		setFilter(TaskTypeEditorPresenter.PARAM_KIND, TaskKind.TK_MAINTENANCE.toString());
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

	@Override
	protected String getChildName() {
		return MtTaskTypeBrowserPresenter.class.getName();
	}

}
