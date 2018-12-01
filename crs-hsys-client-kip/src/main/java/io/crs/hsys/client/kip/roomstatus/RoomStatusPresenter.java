/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.kip.KipAppPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusEditEvent;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusFilterEvent;
import io.crs.hsys.shared.constans.MenuItemType;
import io.crs.hsys.shared.constans.OccStatus;
import io.crs.hsys.shared.constans.RoomStatus;
import io.crs.hsys.shared.constans.TaskKind;
import io.crs.hsys.shared.dto.common.AppUserDtor;
import io.crs.hsys.shared.dto.hk.GuestNumber;
import io.crs.hsys.shared.dto.hk.RoomStatusDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomOccDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;
import io.crs.hsys.shared.dto.task.TaskDto;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author CR
 *
 */
public class RoomStatusPresenter extends Presenter<RoomStatusPresenter.MyView, RoomStatusPresenter.MyProxy>
		implements RoomStatusUiHandlers, RoomStatusEditEvent.RoomStatusEditHandler {
	private static final Logger logger = Logger.getLogger(RoomStatusPresenter.class.getName());

	public static final SingleSlot<PresenterWidget<?>> FILTER_SLOT = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> EDITOR_SLOT = new SingleSlot<>();

	interface MyView extends View, HasUiHandlers<RoomStatusUiHandlers> {
		void loadData(List<RoomStatusDto> data);

		void doFilter(RoomStatusFilterEvent event);
	}

	@ProxyStandard
	@NameToken(KipNameTokens.GUEST_ROOMS)
	interface MyProxy extends ProxyPlace<RoomStatusPresenter> {
	}

	@Inject
	RoomStatusPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "RoomStatusPresenter()");

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
//		setInSlot(EDITOR_SLOT, chatEditorPresenter);
		addRegisteredHandler(RoomStatusEditEvent.getType(), this);
	}

	@Override
	protected void onReveal() {
		logger.log(Level.INFO, "onReveal()");
		SetPageTitleEvent.fire("Vendégszobák", "A gondnoknők kedvence...", MenuItemType.MENU_ITEM, this);
		getView().loadData(loadData());
	}

	private List<RoomStatusDto> loadData() {

		AppUserDtor hakaUser = new AppUserDtor.Builder().code("HAKA").name("Háká Kata").build();

		RoomTypeDtor dblbRT = new RoomTypeDtor.Builder().code("DBLB").name("Double bed room").build();
		RoomTypeDtor twinRT = new RoomTypeDtor.Builder().code("TWIN").name("Twin room").build();

		RoomDto r1001 = new RoomDto.Builder().code("1001").roomType(dblbRT).roomStatus(RoomStatus.DIRTY)
				.currOccStatus(new RoomOccDto(OccStatus.CHECKOUT, new GuestNumber(2, 0, 1, 0), ""))
				.nextOccStatus(new RoomOccDto(OccStatus.CHECKOUT, new GuestNumber(2, 0, 1, 0), "")).build();
		RoomDto r1002 = new RoomDto.Builder().code("1001").roomType(twinRT).roomStatus(RoomStatus.DIRTY)
				.currOccStatus(new RoomOccDto(OccStatus.CHECKOUT, new GuestNumber(2, 0, 1, 0), ""))
				.nextOccStatus(new RoomOccDto(OccStatus.CHECKOUT, new GuestNumber(2, 0, 1, 0), "")).build();

		TaskTypeDto dailyClTT = new TaskTypeDto.Builder().kind(TaskKind.CLEANING).code("DAILY")
				.description("Napi takarítás").build();
		TaskTypeDto linenChTT = new TaskTypeDto.Builder().kind(TaskKind.CLEANING).code("LINEN")
				.description("Ágynemű csere").build();

		List<TaskDto> r1001Tasks = new ArrayList<TaskDto>();
		r1001Tasks.add(new TaskDto.Builder().kind(TaskKind.CLEANING).type(dailyClTT).assignee(hakaUser).build());
		r1001Tasks.add(new TaskDto.Builder().kind(TaskKind.CLEANING).type(linenChTT).assignee(hakaUser).build());

		List<TaskDto> r1002Tasks = new ArrayList<TaskDto>();
		r1002Tasks.add(new TaskDto.Builder().kind(TaskKind.CLEANING).type(dailyClTT).assignee(hakaUser).build());
		r1002Tasks.add(new TaskDto.Builder().kind(TaskKind.CLEANING).type(linenChTT).assignee(hakaUser).build());

		List<RoomStatusDto> result = new ArrayList<RoomStatusDto>();
		result.add(new RoomStatusDto.Builder().room(r1001).tasks(r1001Tasks).build());
		result.add(new RoomStatusDto.Builder().room(r1002).tasks(r1002Tasks).build());

		return result;
	}

	@Override
	public void onEdit(RoomStatusEditEvent event) {
		// TODO Auto-generated method stub

	}
}
