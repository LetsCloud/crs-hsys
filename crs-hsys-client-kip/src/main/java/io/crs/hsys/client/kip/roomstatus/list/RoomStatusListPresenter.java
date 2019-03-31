/**
 * 
 */
package io.crs.hsys.client.kip.roomstatus.list;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.dispatch.shared.ActionException;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import io.crs.hsys.client.core.security.LoggedInGatekeeper;
import io.crs.hsys.client.core.message.callback.AbstractAsyncCallback;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.api.RoomResource;
import io.crs.hsys.shared.dto.filter.RoomStatusFilterDto;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.client.kip.KipNameTokens;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusFilterEvent;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusRefreshEvent;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusFilterEvent.RoomStatusFilterHandler;
import io.crs.hsys.client.kip.roomstatus.event.RoomStatusRefreshEvent.RoomStatusRefreshHandler;

/**
 * @author CR
 *
 */
public class RoomStatusListPresenter extends Presenter<RoomStatusListPresenter.MyView, RoomStatusListPresenter.MyProxy>
		implements RoomStatusListUiHandlers, RoomStatusFilterHandler, RoomStatusRefreshHandler {
	private static final Logger LOGGER = Logger.getLogger(RoomStatusListPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<RoomStatusListUiHandlers> {
		void displayRooms(List<RoomDto> roomDtos);

		void updateRoom(RoomDto roomDto);
	}

	@ProxyStandard
	@NameToken(KipNameTokens.GUEST_ROOMS)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<RoomStatusListPresenter> {
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<RoomResource> roomDelegate;
	private final CurrentUser currentUser;
	private final RoomStatusEditorPresenter roomStatusEditorPresenter;
	private final String unauthorizedPlace;

	@Inject
	RoomStatusListPresenter(PlaceManager placeManager, EventBus eventBus, MyView view, MyProxy proxy,
			ResourceDelegate<RoomResource> roomDelegate, CurrentUser currentUser,
			RoomStatusEditorPresenter roomStatusEditorPresenter, @UnauthorizedPlace String unauthorizedPlace) {
		super(eventBus, view, proxy);
		LOGGER.log(Level.INFO, "RoomStatusListPresenter()");

		this.placeManager = placeManager;
		this.roomDelegate = roomDelegate;
		this.currentUser = currentUser;
		this.roomStatusEditorPresenter = roomStatusEditorPresenter;
		this.unauthorizedPlace = unauthorizedPlace;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		LOGGER.log(Level.INFO, "onBind()");
	}

	@Override
	protected void onReveal() {
		LOGGER.log(Level.INFO, "onReveal()");
		loadRooms(new RoomStatusFilterDto());
	}

	private void loadRooms(RoomStatusFilterDto filterDto) {
		filterDto.setHotelKey(currentUser.getCurrentHotel().getWebSafeKey());
//		filterDto.setDate(currentUser.getCurrentHotel().getBusinessDate());

		roomDelegate.withCallback(new AbstractAsyncCallback<List<RoomDto>>() {
			@Override
			public void onSuccess(List<RoomDto> rooms) {
				LOGGER.log(Level.INFO, "onSuccess()");
				displayRooms(rooms);
			}

			@Override
			public void onFailure(Throwable caught) {
				LOGGER.log(Level.INFO, "onFailure()->" + caught.getMessage());
				LOGGER.log(Level.INFO, "onFailure()->" + caught.getClass().getName());
				if (caught instanceof ActionException)
					if (((ActionException) caught).getCause() != null)
						LOGGER.log(Level.INFO, "((ActionException)caught).getCause()"
								+ ((ActionException) caught).getCause().getClass().getName());

				placeManager.revealPlace(new PlaceRequest.Builder().nameToken(unauthorizedPlace).build());

				/* snip */ }
		}).getAvailableRoomsOnDate(filterDto);
	}

	private void displayRooms(List<RoomDto> rooms) {
		LOGGER.log(Level.INFO, "displayRooms()");
		Collections.sort(rooms, RoomDto.ORDER_BY_CODE);
		getView().displayRooms(rooms);
	}

	@ProxyEvent
	@Override
	public void onFilter(RoomStatusFilterEvent event) {
		LOGGER.log(Level.INFO, "onFilter()");
		loadRooms(event.getFilterDto());
	}

	@Override
	public void editStatus(RoomDto roomDto) {
		LOGGER.log(Level.INFO, "editStatus()");
		roomStatusEditorPresenter.editStatus(roomDto);
	}

	@ProxyEvent
	@Override
	public void onRefresh(RoomStatusRefreshEvent event) {
		LOGGER.log(Level.INFO, "onRefresh()");
		getView().updateRoom(event.getRoomDto());
	}

}
