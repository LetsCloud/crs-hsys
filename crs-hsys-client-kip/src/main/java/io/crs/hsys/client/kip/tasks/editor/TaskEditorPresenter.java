/**
 * 
 */
package io.crs.hsys.client.kip.tasks.editor;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.datasource.TaskTypeDataSource;
import io.crs.hsys.client.core.editor.AbstractEditorPresenter;
import io.crs.hsys.client.core.editor.AbstractEditorView;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.shared.api.TaskResource;
import io.crs.hsys.shared.constans.MenuItemType;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.EntityPropertyCode;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author robi
 *
 */
public class TaskEditorPresenter
		extends AbstractEditorPresenter<TaskDto, TaskEditorPresenter.MyView, TaskEditorPresenter.MyProxy>
		implements TaskEditorUiHandlers {
	private static Logger logger = Logger.getLogger(TaskEditorPresenter.class.getName());

	public static final String PARAM_KIND = "paramKind";

	public static final SingleSlot<PresenterWidget<?>> SLOT_ADD_TASKTODO = new SingleSlot<>();

	public interface MyView extends AbstractEditorView<TaskDto>, HasUiHandlers<TaskEditorUiHandlers> {
		void setTaskTypeData(List<TaskTypeDto> data);

		void displayError(EntityPropertyCode code, String message);
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.TASK_EDITOR)
	interface MyProxy extends ProxyPlace<TaskEditorPresenter> {
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<TaskResource> resourceDelegate;
	private final TaskTypeDataSource taskTypeDataSource;
	private final CurrentUser currentUser;
	private final CoreMessages i18nCore;
	private final KipMessages i18n;

	@Inject
	TaskEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<TaskResource> resourceDelegate, TaskTypeDataSource taskTypeDataSource,
			CurrentUser currentUser, CoreMessages i18nCore, KipMessages i18n) {
		super(eventBus, placeManager, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("TaskEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.taskTypeDataSource = taskTypeDataSource;
		this.currentUser = currentUser;
		this.i18nCore = i18nCore;
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	private void start() {
		if (isNew()) {
			SetPageTitleEvent.fire(i18n.taskEditorCreateTitle(), i18n.taskEditorCreateSubTitle(),
					MenuItemType.MENU_ITEM, TaskEditorPresenter.this);
			create();
		} else {
			SetPageTitleEvent.fire(i18n.taskEditorModifyTitle(), i18n.taskEditorModifySubTitle(),
					MenuItemType.MENU_ITEM, TaskEditorPresenter.this);
			edit(filters.get(WEBSAFEKEY));
		}
	}

	private void loadTaskTypeData() {
		LoadCallback<TaskTypeDto> taskGroupLoadCallback = new LoadCallback<TaskTypeDto>() {
			@Override
			public void onSuccess(LoadResult<TaskTypeDto> loadResult) {
				getView().setTaskTypeData(loadResult.getData());
				start();
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};
		taskTypeDataSource.load(new LoadConfig<TaskTypeDto>(0, 0, null, null), taskGroupLoadCallback);
	}

	@Override
	protected void loadData() {
		loadTaskTypeData();
	}

	@Override
	protected TaskDto createDto() {
		TaskDto dto = new TaskDto();
		dto.setAccount(currentUser.getAppUserDto().getAccount());
		if (filters.containsKey(PARAM_KIND))
			dto.setKind(TaskKind.valueOf(filters.get(PARAM_KIND)));
		return dto;
	}

	private void edit(String webSafeKey) {
		logger.info("TaskEditorPresenter().edit()->webSafeKey=" + webSafeKey);

		resourceDelegate.withCallback(new AsyncCallback<TaskDto>() {
			@Override
			public void onSuccess(TaskDto dto) {
				SetPageTitleEvent.fire(i18nCore.taskTypeEditorModifyTitle(), "", MenuItemType.MENU_ITEM,
						TaskEditorPresenter.this);
				getView().edit(dto);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).read(webSafeKey);
	}

	@Override
	public void save(TaskDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<TaskDto>() {
			@Override
			public void onSuccess(TaskDto dto) {
				placeManager.navigateBack();
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}
}