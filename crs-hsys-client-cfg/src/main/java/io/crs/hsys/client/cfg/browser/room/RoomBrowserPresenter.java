/**
 * 
 */
package io.crs.hsys.client.cfg.browser.room;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import io.crs.hsys.client.cfg.browser.roomtype.RoomTypeBrowserPresenter;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.cfg.filter.FilterPresenterFactory;
import io.crs.hsys.client.cfg.filter.room.RoomFilterPresenter;
import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.browser.AbstractBrowserPresenter;
import io.crs.hsys.client.core.util.AbstractAsyncCallback;
import io.crs.hsys.shared.api.RoomResource;
import io.crs.hsys.shared.dto.hotel.RoomDto;

import static io.crs.hsys.shared.api.ApiParameters.HOTEL_KEY;;

/**
 * @author robi
 *
 */
public class RoomBrowserPresenter extends AbstractBrowserPresenter<RoomDto, RoomBrowserPresenter.MyView>
		implements RoomBrowserUiHandlers, FilterChangeEvent.FilterChangeHandler {
	private static Logger logger = Logger.getLogger(RoomTypeBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<RoomBrowserUiHandlers> {
		void setData(List<RoomDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();

	private final ResourceDelegate<RoomResource> resourceDelegate;
	private final RoomFilterPresenter filter;

	@Inject
	RoomBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<RoomResource> resourceDelegate, FilterPresenterFactory filterPresenterFactory) {
		super(eventBus, view, placeManager);
		logger.info("RoomTablePresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filterPresenterFactory.createRoomFilterPresenter();

		addVisibleHandler(FilterChangeEvent.TYPE, this);

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_FILTER, filter);
	}

	@Override
	protected void loadData() {
	}

	@Override
	protected String getCreatorNameToken() {
		return CoreNameTokens.ROOM_EDITOR;
	}

	@Override
	protected String getEditorNameToken() {
		return CoreNameTokens.ROOM_EDITOR;
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
	public void onFilterChange(FilterChangeEvent event) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<RoomDto>>() {
			@Override
			public void onSuccess(List<RoomDto> result) {
				filter.setFloors(getFloors(result));
				if ((filter.getSelectedFloor() != null) && (!filter.getSelectedFloor().isEmpty()))
					result = result.stream().filter(room -> room.getFloor().equals(filter.getSelectedFloor()))
							.collect(Collectors.toList());
				if (!filter.getSelectedRoomTypeKeys().isEmpty())
					result = result.stream().filter(
							room -> filter.getSelectedRoomTypeKeys().contains(room.getRoomType().getWebSafeKey()))
							.collect(Collectors.toList());
				getView().setData(result);
			}
		}).getByHotel(filter.getSelectedHotel().getWebSafeKey(), filter.isOnlyActive());

		addFilter(HOTEL_KEY, filter.getSelectedHotel().getWebSafeKey());
	}

	private List<String> getFloors(List<RoomDto> rooms) {
		List<String> floors = new ArrayList<String>();
		for (RoomDto room : rooms) {
			if (!floors.contains(room.getFloor()))
				floors.add(room.getFloor());
		}
		floors.sort((p1, p2) -> p1.compareTo(p2));
		floors.add(0, "");
		return floors;
	}
}