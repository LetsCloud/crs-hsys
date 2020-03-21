/**
 * 
 */
package io.crs.hsys.client.fro.reservation;

import static io.crs.hsys.shared.api.ApiParameters.WEBSAFEKEY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import io.crs.hsys.client.core.config.AbstractConfigPresenter;
import io.crs.hsys.client.core.editor.AbstractDisplayPresenterWidget;
import io.crs.hsys.client.core.event.ContentPushEvent;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.util.DateUtils;
import io.crs.hsys.client.fro.FroAppPresenter;
import io.crs.hsys.client.fro.NameTokens;
import io.crs.hsys.client.fro.reservation.room.RoomStayPresenter;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.cnst.RoomStatus;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;
import io.crs.hsys.shared.dto.reservation.ReservationDto;
import io.crs.hsys.shared.dto.reservation.RoomStayDto;

/**
 * @author robi
 *
 */
public class ReservationPresenter extends AbstractConfigPresenter<ReservationPresenter.MyView, ReservationPresenter.MyProxy>
		implements ReservationUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(ReservationPresenter.class.getName());

	public static final String SP_MAIN = "main";
	public static final String SP_ROOM = "room";
	public static final String SP_EXTRAS = "extras";
	public static final String SP_GUESTS = "guests";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.RESERVATION)
	// @UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<ReservationPresenter> {
	}

	private String webSafeKey;

	private final RoomStayPresenter roomResPresenter;
	
	@Inject
	ReservationPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy, ResWidgetPresenterFactory widgetFactgory) {
		super(eventBus, placeManager, view, proxy, FroAppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "ReservationPresenter()");

		roomResPresenter = widgetFactgory.createRoomResPresenter();

//		setCaption(i18nCore.organizationConfigTitle());
//		setDescription(i18nCore.organizationConfigDescription());
		setPlaceToken(NameTokens.RESERVATION);

		addContent("Main", widgetFactgory.createMainResPresenter(),
				SP_MAIN);
		addContent("Room", roomResPresenter,
				SP_ROOM);
		addContent("Extra", widgetFactgory.createExtraResPresenter(),
				SP_EXTRAS);
		addContent("Guests", widgetFactgory.createGuestResPresenter(), SP_GUESTS);
		
		getView().setUiHandlers(this);
		addRegisteredHandler(ContentPushEvent.TYPE, this);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		webSafeKey = request.getParameter(WEBSAFEKEY, null);
		logger.info("OrganizationConfigPresenter().prepareFromRequest()->webSafeKey=" + webSafeKey);

		Integer index = placeParams.indexOf(request.getParameter(PLACE_PARAM, null));
		if (index == -1)
			index = 0;
		getView().setDesktopMenu(index);
		getView().setMobileButtonText(captions.get(index));
		setInSlot(SLOT_CONTENT, beforeShowContent(browsers.get(index)));
	}

	@Override
	protected PresenterWidget<?> beforeShowContent(PresenterWidget<?> widget) {
		logger.info("OrganizationConfigPresenter().beforeShowContent()");
//		((AbstractDisplayPresenterWidget<?>) widget).setReadOnly(true);
//		((AbstractDisplayPresenterWidget<?>) widget).setWebSafeKey(webSafeKey);
//		((AbstractDisplayPresenterWidget<?>) widget).setTitle(title);
//		((AbstractDisplayPresenterWidget<?>) widget).setDescription(description);
		return widget;
	}

	@Override
	public void onReveal() {
		super.onReveal();
		logger.log(Level.INFO, "onReveal()");
		SetPageTitleEvent.fire("R-No: 465465", "Mr. John Smith", MenuItemType.MENU_ITEM, this);
		
		roomResPresenter.setData(getData());
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
		// TODO Auto-generated method stub

	}

	private ReservationDto getData() {

		RoomTypeDtor dblbRTD = RoomTypeDtor.builder().code("DBLB").name("Double bed room").build();
		RoomTypeDtor twinRTD = RoomTypeDtor.builder().code("TWIN").name("Twin bed room").build();

		RoomDto r101D =	new RoomDto.Builder().code("101").description("asdsadasd").floor("1").roomStatus(RoomStatus.RS_CLEAN).roomType(dblbRTD).build();
		RoomDto r105D =	new RoomDto.Builder().code("105").description("asdsadasd").floor("1").roomStatus(RoomStatus.RS_CLEAN).roomType(twinRTD).build();
		
		Date arrivalDate = DateUtils.setTime(new Date(), 0);
		Date moveDate = DateUtils.addDaysToDate(arrivalDate, 2);
		Date departureDate = DateUtils.addDaysToDate(arrivalDate, 3);

		ReservationDto result = ReservationDto.builder().
				addRoomStay(new RoomStayDto.Builder().arrival(arrivalDate).eci(true).movedInto(false)
						.departure(moveDate).lco(false).movedOut(true)
						.room(r101D).roomType(dblbRTD).build()).
				addRoomStay(new RoomStayDto.Builder().arrival(moveDate).eci(false).movedInto(true)
						.departure(departureDate).lco(true).movedOut(false)
						.room(r105D).roomType(twinRTD).build()).
				build();

		return result;
	}
}
