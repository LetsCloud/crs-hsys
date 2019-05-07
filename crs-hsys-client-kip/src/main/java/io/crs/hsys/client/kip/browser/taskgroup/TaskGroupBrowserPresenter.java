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

import io.crs.hsys.client.core.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.core.event.DisplayMessageEvent;
import io.crs.hsys.client.core.event.DisplayMessageEvent.DisplayMessageHandler;
import io.crs.hsys.client.core.event.DisplayMessageEvent.MessageTarget;
import io.crs.hsys.client.core.event.RefreshTableEvent.TableType;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.message.MessageData;
import io.crs.hsys.client.core.message.callback.AbstractAsyncCallback;
import io.crs.hsys.client.core.message.callback.ErrorHandlerAsyncCallback;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.kip.filter.taskgroup.TaskGroupFilterPresenter;
import io.crs.hsys.client.kip.meditor.taskgroup.TaskGroupEditorPresenter;
import io.crs.hsys.shared.api.TaskGroupResource;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.dto.task.TaskGroupDto;

/**
 * @author robi
 *
 */
public abstract class TaskGroupBrowserPresenter
		extends AbstractBrowserPresenter<TaskGroupDto, TaskGroupBrowserPresenter.MyView>
		implements TaskGroupBrowserUiHandlers, FilterChangeEvent.FilterChangeHandler, DisplayMessageHandler {
	private static Logger logger = Logger.getLogger(TaskGroupBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<TaskGroupBrowserUiHandlers> {
		void setData(List<TaskGroupDto> data);

		void showMessage(MessageData message);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<TaskGroupResource> resourceDelegate;
	private final TaskGroupFilterPresenter filter;
	private final TaskGroupEditorPresenter editor;
	private final CoreMessages i18nCore;

	public TaskGroupBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<TaskGroupResource> resourceDelegate, TaskGroupFilterPresenter filter,
			TaskGroupEditorPresenter editor, CoreMessages i18nCore) {
		super(eventBus, view, placeManager);
		logger.info("TaskGroupBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filter;
		this.editor = editor;
		this.i18nCore = i18nCore;

		addVisibleHandler(FilterChangeEvent.TYPE, this);

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_FILTER, filter);
		setInSlot(SLOT_EDITOR, editor);
		addRegisteredHandler(DisplayMessageEvent.TYPE, this);
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
		resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<Void>(this, MessageTarget.TASK_GROUP, i18nCore) {
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

	@Override
	public void onDisplayMessage(DisplayMessageEvent event) {
		logger.info("TaskGroupBrowserPresenter().onDisplayMessage()");
		if (event.getTarget().equals(MessageTarget.TASK_GROUP)) {
			logger.info("TaskGroupBrowserPresenter().onDisplayMessage()-2");
			getView().showMessage(event.getMessage());
		}
	}

	@Override
	protected TableType getTableType() {
		return TableType.TASK_GROUP;
	}

}