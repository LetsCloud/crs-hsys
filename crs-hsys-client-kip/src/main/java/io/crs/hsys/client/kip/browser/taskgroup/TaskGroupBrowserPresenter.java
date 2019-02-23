/**
 * 
 */
package io.crs.hsys.client.kip.browser.taskgroup;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import io.crs.hsys.client.core.ui.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.core.util.AbstractAsyncCallback;
import io.crs.hsys.client.kip.filter.taskgroup.TaskGroupFilterPresenter;
import io.crs.hsys.client.kip.meditor.taskgroup.TaskGroupEditorPresenter;
import io.crs.hsys.shared.api.TaskGroupResource;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.task.TaskGroupDto;

/**
 * @author robi
 *
 */
public abstract class TaskGroupBrowserPresenter
		extends AbstractBrowserPresenter<TaskGroupDto, TaskGroupBrowserPresenter.MyView>
		implements TaskGroupBrowserUiHandlers, FilterChangeEvent.FilterChangeHandler {
	private static Logger logger = Logger.getLogger(TaskGroupBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<TaskGroupBrowserUiHandlers> {
		void setData(List<TaskGroupDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<TaskGroupResource> resourceDelegate;
	private final TaskGroupFilterPresenter filter;
	private final TaskGroupEditorPresenter editor;

	public TaskGroupBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<TaskGroupResource> resourceDelegate, TaskGroupFilterPresenter filter,
			TaskGroupEditorPresenter editor) {
		super(eventBus, view, placeManager);
		logger.info("TaskGroupBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filter;
		this.editor = editor;

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
				if (filter.isOnlyActive())
					result = result.stream().filter(tg -> tg.getActive().equals(true)).collect(Collectors.toList());
				if (!filter.getSelectedTaskKind().equals(TaskKind.TK_ALL))
					result = result.stream().filter(tg -> tg.getKind().equals(filter.getSelectedTaskKind()))
							.collect(Collectors.toList());
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
		loadData();
	}
}