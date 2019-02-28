/**
 * 
 */
package io.crs.hsys.client.kip.browser.tasktodo;

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
import io.crs.hsys.client.kip.filter.tasktodo.TaskTodoFilterPresenter;
import io.crs.hsys.client.kip.filter.tasktodo.TaskTodoFilterView;
import io.crs.hsys.client.kip.meditor.tasktodo.TaskTodoEditorPresenter;
import io.crs.hsys.shared.api.TaskTodoResource;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.task.TaskTodoDto;

/**
 * @author robi
 *
 */
public class TaskTodoBrowserPresenter extends AbstractBrowserPresenter<TaskTodoDto, TaskTodoBrowserPresenter.MyView>
		implements TaskTodoBrowserUiHandlers, FilterChangeEvent.FilterChangeHandler {
	private static Logger logger = Logger.getLogger(TaskTodoBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<TaskTodoBrowserUiHandlers> {
		void setData(List<TaskTodoDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<TaskTodoResource> resourceDelegate;
	private final TaskTodoFilterPresenter filter;
	private final TaskTodoEditorPresenter editor;

	public TaskTodoBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<TaskTodoResource> resourceDelegate, TaskTodoFilterPresenter filter,
			TaskTodoEditorPresenter editor) {
		super(eventBus, view, placeManager);
		logger.info("TaskTodoBrowserPresenter()");

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
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<TaskTodoDto>>() {
			@Override
			public void onSuccess(List<TaskTodoDto> result) {
				if (filter.isOnlyActive())
					result = result.stream().filter(tg -> tg.getActive().equals(true)).collect(Collectors.toList());
				if (!filter.getSelectedTaskKind().equals(TaskKind.TK_ALL))
					result = result.stream().filter(tg -> tg.getKind().equals(filter.getSelectedTaskKind()))
							.collect(Collectors.toList());
				if (!filter.getSelectedTaskGroup().getCode().equals(TaskTodoFilterView.ALL_ITEMS))
					result = result.stream().filter(tg -> tg.getTaskGroup().equals(filter.getSelectedTaskGroup()))
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
	public void edit(TaskTodoDto dto) {
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