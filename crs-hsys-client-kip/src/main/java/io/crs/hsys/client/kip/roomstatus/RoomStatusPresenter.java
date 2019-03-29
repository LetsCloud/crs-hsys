/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
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

import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.kip.KipAppPresenter;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.filter.KipFilterPresenterFactory;
import io.crs.hsys.client.kip.filter.roomstatus.RoomStatusFilterPresenter2;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusFilterEvent;
import io.crs.hsys.client.kip.search.SearchPresenterFactory;
import io.crs.hsys.client.kip.search.roomstatus.RoomStatusSearchPresenter;
import io.crs.hsys.shared.api.RoomResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.cnst.OccStatus;
import io.crs.hsys.shared.cnst.RoomStatus;
import io.crs.hsys.shared.cnst.TaskKind;
import io.crs.hsys.shared.cnst.UserPerm;
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
		implements RoomStatusUiHandlers {
	private static final Logger logger = Logger.getLogger(RoomStatusPresenter.class.getName());

	public static final SingleSlot<PresenterWidget<?>> SEARCH_SLOT = new SingleSlot<>();
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

	private final PlaceManager placeManager;
	private final ResourceDelegate<RoomResource> resourceDelegate;
	private final RoomStatusSearchPresenter searchPresenter;;
	private final RoomStatusFilterPresenter2 roomStatusfilter;
	private final CurrentUser currentUser;
	private final KipMessages i18n;

	@Inject
	RoomStatusPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager,
			ResourceDelegate<RoomResource> resourceDelegate, SearchPresenterFactory searchFactory,
			KipFilterPresenterFactory filterFactory, CurrentUser currentUser, KipMessages i18n) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "RoomStatusPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.searchPresenter = searchFactory.createRoomStatusSearch();
		this.roomStatusfilter = filterFactory.createRoomStatusFilter();
		this.currentUser = currentUser;
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
//		setInSlot(SEARCH_SLOT, searchPresenter);
		setInSlot(FILTER_SLOT, roomStatusfilter);
//		addRegisteredHandler(RoomStatusEditEvent.getType(), this);
	}

	@Override
	protected void onReveal() {
		logger.log(Level.INFO, "onReveal()");
		SetPageTitleEvent.fire(i18n.roomStatusTitle(), i18n.roomStatusSubTitle(), MenuItemType.MENU_ITEM, this);
		loadData2();
	}

	private void loadData2() {
		resourceDelegate.withCallback(new AsyncCallback<List<RoomStatusDto>>() {
			@Override
			public void onSuccess(List<RoomStatusDto> result) {
				result.sort((o1, o2) -> o1.getRoom().getCode().compareTo(o2.getRoom().getCode()));
				getView().loadData(result);
			}

			@Override
			public void onFailure(Throwable caught) {
//				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).getRoomStatusesByHotel(currentUser.getCurrentHotel().getWebSafeKey());
	}

	private List<RoomStatusDto> loadData() {

		List<UserPerm> techManPerm = new ArrayList<UserPerm>();
		techManPerm.add(UserPerm.UP_MAINTMANAGER);
		AppUserDtor adminUser = new AppUserDtor.Builder().code("ADMIN").name("AdminÁdám").permissions(techManPerm)
				.build();

		List<UserPerm> hkSvPerm = new ArrayList<UserPerm>();
		hkSvPerm.add(UserPerm.UP_HKSUPERVISOR);
		AppUserDtor hakaUser = new AppUserDtor.Builder().code("HAKA").name("Háká Kata").permissions(hkSvPerm).build();

		List<UserPerm> hkPerm = new ArrayList<UserPerm>();
		hkPerm.add(UserPerm.UP_HOUSEKEEPER);
		AppUserDtor kipiUser = new AppUserDtor.Builder().code("KIPI").name("Kipi Viki").permissions(hkPerm).build();

		List<UserPerm> techPerm = new ArrayList<UserPerm>();
		techPerm.add(UserPerm.UP_TECHNICIAN);
		AppUserDtor karaUser = new AppUserDtor.Builder().code("KARA").name("Kara Karesz").permissions(techPerm).build();

		RoomTypeDtor dblbRT = RoomTypeDtor.builder().code("DBLB").name("Double bed room").build();
		RoomTypeDtor twinRT = RoomTypeDtor.builder().code("TWIN").name("Twin room").build();

		RoomDto r1001 = new RoomDto.Builder().code("1001").roomType(dblbRT).roomStatus(RoomStatus.DIRTY).build();
		RoomDto r1002 = new RoomDto.Builder().code("1002").roomType(twinRT).roomStatus(RoomStatus.CLEAN).build();
		RoomDto r1003 = new RoomDto.Builder().code("1003").roomType(twinRT).roomStatus(RoomStatus.INSPECTED).build();
		RoomDto r1004 = new RoomDto.Builder().code("1004").roomType(twinRT).roomStatus(RoomStatus.OOO).build();
		RoomDto r1005 = new RoomDto.Builder().code("1005").roomType(dblbRT).roomStatus(RoomStatus.OOS).build();
		RoomDto r1006 = new RoomDto.Builder().code("1006").roomType(dblbRT).roomStatus(RoomStatus.SHOW).build();
		RoomDto r1007 = new RoomDto.Builder().code("1007").roomType(dblbRT).roomStatus(RoomStatus.DIRTY).build();
		RoomDto r1008 = new RoomDto.Builder().code("1008").roomType(dblbRT).roomStatus(RoomStatus.DIRTY).build();
		RoomDto r1009 = new RoomDto.Builder().code("1009").roomType(twinRT).roomStatus(RoomStatus.CLEAN).build();
		RoomDto r1010 = new RoomDto.Builder().code("1010").roomType(twinRT).roomStatus(RoomStatus.INSPECTED).build();
		RoomDto r1011 = new RoomDto.Builder().code("1011").roomType(twinRT).roomStatus(RoomStatus.INSPECTED).build();

		List<String> inspectTD = new ArrayList<String>();
		inspectTD.add("ROOM – Temperature room comfortable upon arrival (between X & Xº)");
		inspectTD.add("ROOM – HVAC functional and in good condition");
		inspectTD.add("ROOM – DND Sign in good condition");
		inspectTD.add("ROOM – Entry door good condition");
		inspectTD.add("ROOM – Entry door functional");
		inspectTD.add("ROOM – Entry door lock functional");
		inspectTD.add("ROOM – Entry door frame clean");
		inspectTD.add("ROOM – Telephone works");
		inspectTD.add("ROOM – TV works");
		inspectTD.add("ROOM – TV programmed correctly");
		inspectTD.add("ROOM – TV remote works");
		inspectTD.add("ROOM – Thermostat operational");
		inspectTD.add("ROOM – All electrical outlets functional");
		inspectTD.add("CLOSET – Safe instructions posted");
		inspectTD.add("ROOM – Minibar clean");
		inspectTD.add("ROOM – Minibar functional");
		inspectTD.add("BED – Frame good condition");
		inspectTD.add("BED – Bedding clean and free of stains");
		inspectTD.add("BED – Frame good condition");
		inspectTD.add("BED – Headboard good condition");
		inspectTD.add("ROOM – All flooring/carpet clean");
		inspectTD.add("ROOM – All flooring/carpet in good condition");
		inspectTD.add("ROOM – Carpet not fraying");
		inspectTD.add("ROOM – All grouting and caulking lines clean");
		inspectTD.add("FURNITURE – Nightstands good condition");
		inspectTD.add("FURNITURE – Nightstand drawers functional");
		inspectTD.add("FURNITURE – Trash bin clean");
		inspectTD.add("FURNITURE – Desk chair good condition");
		inspectTD.add("FURNITURE – Desk chair clean");
		inspectTD.add("FURNITURE – Other chairs clean");
		inspectTD.add("FURNITURE – Other chairs good condition");
		inspectTD.add("FURNITURE – Desk clean");
		inspectTD.add("FURNITURE – Desk good condition");
		inspectTD.add("FURNITURE – Desk drawers functional");
		inspectTD.add("CLOSET – All accessories in closet present and in good condition");
		inspectTD.add("CLOSET – Light(s) functional");
		inspectTD.add("CLOSET – Doors open and close properly");
		inspectTD.add("CLOSET – Door good condition");
		inspectTD.add("CLOSET – Interior clean");
		inspectTD.add("ROOM – Art hung straight");
		inspectTD.add("ROOM – Art/frame good condition");
		inspectTD.add("ROOM – Mirrors clean");
		inspectTD.add("ROOM – Mirrors good condition");
		inspectTD.add("ROOM – General area lighting clean");
		inspectTD.add("ROOM – General area lighting functional");
		inspectTD.add("ROOM – Light shades sturdy and straight");
		inspectTD.add("ROOM – USB charging ports work throughout room");
		inspectTD.add("FURNITURE – Tables good condition");
		inspectTD.add("FURNITURE – Tables clean");
		inspectTD.add("FURNITURE – Sofa clean");
		inspectTD.add("FURNITURE – Sofa good condition");
		inspectTD.add("ROOM – Wall paint good condition");
		inspectTD.add("ROOM – Walls damage free");
		inspectTD.add("ROOM – Windows clean");
		inspectTD.add("ROOM – Windows good condition");
		inspectTD.add("ROOM – Windows function (if they can be opened)");
		inspectTD.add("ROOM – Drapes/sheers functional");
		inspectTD.add("ROOM – Drapes/sheers clean");
		inspectTD.add("ROOM – Ceiling clean");
		inspectTD.add("ROOM – Ceiling good condition");
		inspectTD.add("ROOM – Ceiling paint good condition");
		inspectTD.add("ROOM – WiFi functional throughout room");
		inspectTD.add("ROOM – Floor baseboards good condition");
		inspectTD.add("ROOM – Vents clean");
		inspectTD.add("ROOM – Vents good condition");
		inspectTD.add("BATH – All surfaces clean");
		inspectTD.add("BATH – All bathroom tile and floor clean");
		inspectTD.add("BATH – All tile and floor in good condition");
		inspectTD.add("BATH – Shower glass streak free and clean");
		inspectTD.add("BATH – All drains in room clean and non-obstructed");
		inspectTD.add("BATH – Sink(s) clean");
		inspectTD.add("BATH – All countertops and surfaces clean and streak free");
		inspectTD.add("BATH – All lighting clean");
		inspectTD.add("BATH – All lights functional");
		inspectTD.add("BATH – All towels clean and in good condition");
		inspectTD.add("BATH – All amenities refreshed and present");
		inspectTD.add("BATH – Trash bin clean and in good condition");
		inspectTD.add("BATH – Toilet clean");
		inspectTD.add("BATH – Toilet functional");
		inspectTD.add("BATH – Water pressure functional");
		inspectTD.add("BATH – Hair dryer functional");

		List<String> dailyTD = new ArrayList<String>();
		dailyTD.add("Cleaning Door Locks, Chains");
		dailyTD.add("Vacuum Clean");
		dailyTD.add("Mop Floors Tiles");
		dailyTD.add("Dust all wood works");
		dailyTD.add("Dust LCD TV, Set top box and DVD players");
		dailyTD.add("Dust Ipad and other docking Stations");
		dailyTD.add("Dust and Disinfectant TV, DVD Remote");
		dailyTD.add("Dust and Disinfectant Telephone");
		dailyTD.add("Wet Dust Paintings and mirrors");
		dailyTD.add("Replenish all Contents");
		dailyTD.add("Replenish all Guest Amenities");
		dailyTD.add("Damp-dust bathroom doors");
		dailyTD.add("Mop Bathroom Floors");
		dailyTD.add("Clean Bath Tub and Grab Bars");
		dailyTD.add("Damp-dust Shower Curtain");
		dailyTD.add("Check and Clean Bathroom Fixture");
		dailyTD.add("Check and Clean Faucets");
		dailyTD.add("Clean Toilet, Flush Handle and Seats");
		dailyTD.add("Empty and Clean Sani-bins");
		dailyTD.add("Replenish Bathroom Amenities");

		TaskTypeDto dailyClTT = TaskTypeDto.builder().kind(TaskKind.TK_CLEANING).code("DAILY")
				.description("Napi takarítás").build();

		List<String> linenTD = new ArrayList<String>();
		linenTD.add("Change Bed Spreads");
		linenTD.add("Change Bedding - Once");
		TaskTypeDto linenChTT = TaskTypeDto.builder().kind(TaskKind.TK_CLEANING).code("LINEN")
				.description("Ágynemű csere").build();

		TaskTypeDto tapRepairTT = TaskTypeDto.builder().kind(TaskKind.TK_MAINTENANCE).code("TAPREP")
				.description("Csaptelep javítás").build();
		TaskTypeDto fruitRqTT = TaskTypeDto.builder().kind(TaskKind.TK_CLEANING).code("FRUIT")
				.description("Gyümölcskosár").build();
		TaskTypeDto turcsiRqTT = TaskTypeDto.builder().kind(TaskKind.TK_CLEANING).code("TÜRCSI")
				.description("Extra törölköző").build();
//		TaskTypeDto receptionTT = new TaskTypeDto.Builder().kind(TaskKind.TK_COMMON).code("REC").description("Recepció")
//				.build();

		List<TaskDto> r1001Tasks = new ArrayList<TaskDto>();
		r1001Tasks.add(TaskDto.builder().kind(TaskKind.TK_CLEANING).type(dailyClTT).reporter(hakaUser)
				.assignee(kipiUser).build());
		r1001Tasks.add(TaskDto.builder().kind(TaskKind.TK_CLEANING).type(linenChTT).reporter(hakaUser)
				.assignee(kipiUser).build());
		r1001Tasks.add(TaskDto.builder().kind(TaskKind.TK_MAINTENANCE).type(tapRepairTT).reporter(adminUser)
				.assignee(karaUser).build());

		List<TaskDto> r1002Tasks = new ArrayList<TaskDto>();
		r1002Tasks.add(TaskDto.builder().kind(TaskKind.TK_CLEANING).reporter(hakaUser).assignee(hakaUser)
				.type(fruitRqTT).build());
		r1002Tasks.add(TaskDto.builder().kind(TaskKind.TK_MAINTENANCE).type(tapRepairTT).reporter(adminUser)
				.assignee(karaUser).build());

		List<TaskDto> r1003Tasks = new ArrayList<TaskDto>();
		r1003Tasks.add(TaskDto.builder().kind(TaskKind.TK_CLEANING).reporter(hakaUser).assignee(kipiUser)
				.type(turcsiRqTT).build());
//		r1003Tasks.add(TaskDto.builder().kind(TaskKind.TK_COMMON).type(receptionTT).description("Holnaptól OOO").build());

		List<TaskDto> r1004Tasks = new ArrayList<TaskDto>();
		r1004Tasks.add(TaskDto.builder().kind(TaskKind.TK_MAINTENANCE).type(tapRepairTT).reporter(adminUser)
				.assignee(karaUser).build());
//		r1004Tasks.add(
//				TaskDto.builder().kind(TaskKind.TK_COMMON).type(receptionTT).description("Holnaptól visszaáll").build());

		List<RoomStatusDto> result = new ArrayList<RoomStatusDto>();
		result.add(RoomStatusDto.builder().room(r1001)
				.currOccStatus(new RoomOccDto(OccStatus.LATECO, new GuestNumber(2, 0, 1, 0), "18:00"))
				.nextOccStatus(new RoomOccDto(OccStatus.VACANT, new GuestNumber(2, 0, 1, 0), "")).tasks(r1001Tasks)
				.build());
		result.add(RoomStatusDto.builder().room(r1002)
				.currOccStatus(new RoomOccDto(OccStatus.INHOUSE, new GuestNumber(2, 0, 1, 0), ""))
				.nextOccStatus(new RoomOccDto(OccStatus.UNCHANGED, new GuestNumber(2, 0, 1, 0), "")).tasks(r1002Tasks)
				.build());
		result.add(RoomStatusDto.builder().room(r1003)
				.currOccStatus(new RoomOccDto(OccStatus.CHECKOUT, new GuestNumber(2, 0, 1, 0), "OUT"))
				.nextOccStatus(new RoomOccDto(OccStatus.CHECKIN, new GuestNumber(2, 0, 1, 0), "IN")).tasks(r1003Tasks)
				.build());
		result.add(RoomStatusDto.builder().room(r1004)
				.currOccStatus(new RoomOccDto(OccStatus.OOO, new GuestNumber(2, 0, 1, 0), "dec.15"))
				.nextOccStatus(new RoomOccDto(OccStatus.UNCHANGED, new GuestNumber(0, 0, 0, 0), "")).tasks(r1004Tasks)
				.build());
		result.add(RoomStatusDto.builder().room(r1005)
				.currOccStatus(new RoomOccDto(OccStatus.CHECKOUT, new GuestNumber(2, 0, 1, 0), "OUT"))
				.nextOccStatus(new RoomOccDto(OccStatus.CHECKIN, new GuestNumber(0, 0, 0, 0), "")).tasks(r1004Tasks)
				.build());
		result.add(RoomStatusDto.builder().room(r1006)
				.currOccStatus(new RoomOccDto(OccStatus.VACANT, new GuestNumber(2, 0, 1, 0), ""))
				.nextOccStatus(new RoomOccDto(OccStatus.VACANT, new GuestNumber(0, 0, 0, 0), "")).tasks(r1004Tasks)
				.build());
		result.add(RoomStatusDto.builder().room(r1007)
				.currOccStatus(new RoomOccDto(OccStatus.CHECKOUT, new GuestNumber(2, 0, 1, 0), ""))
				.nextOccStatus(new RoomOccDto(OccStatus.VACANT, new GuestNumber(0, 0, 0, 0), "")).tasks(r1004Tasks)
				.build());
		result.add(RoomStatusDto.builder().room(r1008)
				.currOccStatus(new RoomOccDto(OccStatus.CHECKOUT, new GuestNumber(2, 0, 1, 0), "OUT"))
				.nextOccStatus(new RoomOccDto(OccStatus.VACANT, new GuestNumber(0, 0, 0, 0), "")).tasks(r1004Tasks)
				.build());
		result.add(RoomStatusDto.builder().room(r1009)
				.currOccStatus(new RoomOccDto(OccStatus.CHECKOUT, new GuestNumber(2, 0, 1, 0), "OUT"))
				.nextOccStatus(new RoomOccDto(OccStatus.CHECKIN, new GuestNumber(0, 0, 0, 0), "")).tasks(r1004Tasks)
				.build());
		result.add(RoomStatusDto.builder().room(r1010)
				.currOccStatus(new RoomOccDto(OccStatus.CHECKOUT, new GuestNumber(2, 0, 1, 0), "OUT"))
				.nextOccStatus(new RoomOccDto(OccStatus.CHECKIN, new GuestNumber(0, 0, 0, 0), "IN")).tasks(r1004Tasks)
				.build());
		result.add(RoomStatusDto.builder().room(r1011)
				.currOccStatus(new RoomOccDto(OccStatus.CHECKOUT, new GuestNumber(2, 0, 1, 0), "OUT"))
				.nextOccStatus(new RoomOccDto(OccStatus.CHECKIN, new GuestNumber(0, 0, 0, 0), "IN")).tasks(r1004Tasks)
				.build());

		return result;
	}

	@Override
	public void onEdit(String webSafeKey) {
		logger.log(Level.INFO, "RoomStatusPresenter().onEdit()");
		Builder placeBuilder = new Builder().nameToken(KipNameTokens.ROOM_CONTROL);
		placeBuilder.with(WEBSAFEKEY, String.valueOf(webSafeKey));
		placeManager.revealPlace(placeBuilder.build());
	}
}
