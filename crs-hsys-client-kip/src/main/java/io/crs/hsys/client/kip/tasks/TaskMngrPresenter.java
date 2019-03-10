/**
 * 
 */
package io.crs.hsys.client.kip.tasks;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.datasource.AppUserDataSource;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.constans.MenuItemType;
import io.crs.hsys.shared.constans.TaskAttrType;
import io.crs.hsys.shared.constans.TaskStatus;
import io.crs.hsys.shared.constans.UserPerm;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.common.AppUserDto;
import io.crs.hsys.shared.dto.common.AppUserDtor;
import io.crs.hsys.shared.dto.task.TaskAttrDto;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.client.kip.KipAppPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.filter.KipFilterPresenterFactory;
import io.crs.hsys.client.kip.filter.tasks.TasksFilterPresenter;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.client.kip.model.DataBuilder;
import io.crs.hsys.client.kip.resources.KipGssResources;

/**
 * @author robi
 *
 */
public class TaskMngrPresenter extends Presenter<TaskMngrPresenter.MyView, TaskMngrPresenter.MyProxy>
		implements TaskMngrUiHandlers {
	private static Logger logger = Logger.getLogger(TaskMngrPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<TaskMngrUiHandlers> {
		void setTasks(List<TaskDto> tasks, KipGssResources res);
	}

	@ProxyStandard
	@NameToken(KipNameTokens.TASK_MANAGER)
	interface MyProxy extends ProxyPlace<TaskMngrPresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> FILTER_SLOT = new SingleSlot<>();

	private final PlaceManager placeManager;
	private KipGssResources res;
	private AppUserDataSource appUserDataSource;
	private CurrentUser currentUser;
	private KipMessages i18n;
	private final DataBuilder dataBuilder;
	private final TasksFilterPresenter filter;

	@Inject
	TaskMngrPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, KipGssResources res,
			AppUserDataSource appUserDataSource, CurrentUser currentUser, KipMessages i18n, DataBuilder dataBuilder,
			KipFilterPresenterFactory filterFactory) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		logger.info("TaskMngrPresenter()");
		this.placeManager = placeManager;
		this.res = res;
		this.appUserDataSource = appUserDataSource;
		this.currentUser = currentUser;
		this.i18n = i18n;
		this.dataBuilder = dataBuilder;
		this.filter = filterFactory.createTasksFilter();
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(FILTER_SLOT, filter);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire(i18n.tasksTitle(), i18n.tasksSubTitle(), MenuItemType.MENU_ITEM, this);

		loadTasks();
	}

	private void loadTasks() {
		/*
		 * List<TaskDto> tasks = new ArrayList<TaskDto>();
		 * 
		 * tasks.add(createCleaningTask("1", "203", "Daily Cleaning",
		 * currentUser.getAppUserDto())); tasks.add(createCleaningTask("2", "204",
		 * "Daily Cleaning", currentUser.getAppUserDto()));
		 * tasks.add(createCleaningTask("3", "205", "Departure Cleaning",
		 * currentUser.getAppUserDto())); tasks.add(createGuestRequestTask("4", "204",
		 * "Champagne")); tasks.add(createGuestRequestTask("5", "205", "Fruit basket"));
		 * tasks.add(createMaintenanceTask("6", "301", "Shower curtain change",
		 * currentUser.getAppUserDto(), "Bathroom", "Curtain"));
		 */
		getView().setTasks(dataBuilder.getTaskDtos(), res);
	}

	private TaskDto createCleaningTask(String key, String room, String cleaningType, AppUserDto inspector) {

		AppUserDtor froSys = new AppUserDtor.Builder().code("FRO").name("Front Office").build();

		List<TaskAttrDto> taskAttrDtos = new ArrayList<TaskAttrDto>();
		taskAttrDtos.add(new TaskAttrDto(TaskAttrType.ROOM, room));
		taskAttrDtos.add(new TaskAttrDto(TaskAttrType.INSPECTOR, inspector.getCode()));

		TaskDto taskDto = new TaskDto();
		taskDto.setWebSafeKey(key);
		taskDto.setTitle(cleaningType);
		taskDto.setKind(TaskKind.TK_CLEANING);
		taskDto.setStatus(TaskStatus.NOT_STARTED);
		taskDto.setAttributes(taskAttrDtos);
		taskDto.setReporter(froSys);
		return taskDto;
	}

	private TaskDto createGuestRequestTask(String key, String room, String guestRequest) {

		AppUserDtor froSys = new AppUserDtor.Builder().code("FRO").name("Front Office").build();

		List<TaskAttrDto> taskAttrDtos = new ArrayList<TaskAttrDto>();
		taskAttrDtos.add(new TaskAttrDto(TaskAttrType.ROOM, room));
		taskAttrDtos.add(new TaskAttrDto(TaskAttrType.GUEST_RQ_TYPE, guestRequest));

		TaskDto taskDto = new TaskDto();
		taskDto.setWebSafeKey(key);
		taskDto.setTitle(guestRequest);
		taskDto.setKind(TaskKind.TK_CLEANING);
		taskDto.setStatus(TaskStatus.IN_PROGRESS);
		taskDto.setAttributes(taskAttrDtos);
		taskDto.setReporter(froSys);
		return taskDto;
	}

	private TaskDto createMaintenanceTask(String key, String room, String text, AppUserDto reporter, String cat,
			String type) {

		List<UserPerm> techPerm = new ArrayList<UserPerm>();
		techPerm.add(UserPerm.UP_TECHNICIAN);
		AppUserDtor karaUser = new AppUserDtor.Builder().code("KARA").name("Kara Karesz").permissions(techPerm).build();

		List<TaskAttrDto> taskAttrDtos = new ArrayList<TaskAttrDto>();
		taskAttrDtos.add(new TaskAttrDto(TaskAttrType.ROOM, room));
		taskAttrDtos.add(new TaskAttrDto(TaskAttrType.MX_CAT, cat));
		taskAttrDtos.add(new TaskAttrDto(TaskAttrType.MX_TYPE, type));

		TaskDto taskDto = new TaskDto();
		taskDto.setWebSafeKey(key);
		taskDto.setTitle(text);
		taskDto.setReporter(karaUser);
		taskDto.setKind(TaskKind.TK_MAINTENANCE);
		taskDto.setStatus(TaskStatus.COMPLETED);
		taskDto.setAttributes(taskAttrDtos);
		return taskDto;
	}

	@Override
	public void create() {
		Builder placeBuilder = new Builder().nameToken(CoreNameTokens.TASK_EDITOR);
		placeManager.revealPlace(placeBuilder.build());
	}

	@Override
	public void modify(String webSafeKey) {
		Builder placeBuilder = new Builder().nameToken(CoreNameTokens.TASK_EDITOR);
		placeBuilder.with(WEBSAFEKEY, String.valueOf(webSafeKey));
		placeManager.revealPlace(placeBuilder.build());
	}
}
