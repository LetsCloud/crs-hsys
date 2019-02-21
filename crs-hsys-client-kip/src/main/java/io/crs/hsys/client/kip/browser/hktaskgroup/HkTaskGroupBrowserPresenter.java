/**
 * 
 */
package io.crs.hsys.client.kip.browser.hktaskgroup;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import io.crs.hsys.client.core.filter.FilterPresenterFactory;
import io.crs.hsys.client.core.filter.roomtype.RoomTypeFilterPresenter;
import io.crs.hsys.client.core.ui.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.core.util.AbstractAsyncCallback;
import io.crs.hsys.client.kip.meditor.hktaskgroup.HkTaskGroupEditorFactory;
import io.crs.hsys.client.kip.meditor.hktaskgroup.HkTaskGroupEditorPresenter;
import io.crs.hsys.shared.api.TaskGroupResource;
import io.crs.hsys.shared.dto.task.TaskGroupDto;

/**
 * @author robi
 *
 */
public class HkTaskGroupBrowserPresenter
		extends AbstractBrowserPresenter<TaskGroupDto, HkTaskGroupBrowserPresenter.MyView>
		implements HkTaskGroupBrowserUiHandlers, FilterChangeEvent.FilterChangeHandler {
	private static Logger logger = Logger.getLogger(HkTaskGroupBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<HkTaskGroupBrowserUiHandlers> {
		void setData(List<TaskGroupDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<TaskGroupResource> resourceDelegate;
	private final RoomTypeFilterPresenter filter;
	private final HkTaskGroupEditorPresenter editor;

	@Inject
	HkTaskGroupBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<TaskGroupResource> resourceDelegate, FilterPresenterFactory filterPresenterFactory,
			HkTaskGroupEditorFactory editorFactory) {
		super(eventBus, view, placeManager);
		logger.info("HkTaskGroupBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filterPresenterFactory.createRoomTypeFilterPresenter();
		this.editor = editorFactory.createHkTaskGroupEditor();

		addVisibleHandler(FilterChangeEvent.TYPE, this);

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_FILTER, filter);
		setInSlot(SLOT_EDITOR, editor);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
	}

	@Override
	protected void loadData() {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<TaskGroupDto>>() {
			@Override
			public void onSuccess(List<TaskGroupDto> result) {
				getView().setData(result);
			}
		}).getAll();
	}

	@Override
	public void addNew() {
		editor.create();
	}

	@Override
	public void edit(TaskGroupDto dto) {
		editor.edit(dto);
	}

	@Override
	protected void deleteData(String webSafeKey) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				loadData();
			}
		}).delete(webSafeKey);
	}

	@Override
	protected String getCreatorNameToken() {
		return null;
	}

	@Override
	protected String getEditorNameToken() {
		return null;
	}

	@Override
	public void onFilterChange(FilterChangeEvent event) {
		logger.info("RoomTypeTablePresenter().onFilterChange()");
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<TaskGroupDto>>() {
			@Override
			public void onSuccess(List<TaskGroupDto> result) {
				getView().setData(result);
			}
		}).getAll();
	}
}