/**
 * 
 */
package io.crs.hsys.client.fro.rate.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.event.ContentPushEvent;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.event.ContentPushEvent.MenuState;
import io.crs.hsys.client.core.filter.FilterChangeEvent;
import io.crs.hsys.client.fro.NameTokens;
import io.crs.hsys.client.fro.filter.FroFilterFactory;
import io.crs.hsys.client.fro.filter.ratemngr.RateMngrFilterPresenter;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.cnst.RatePriceType;
import io.crs.hsys.shared.cnst.RateRestrictionType;
import io.crs.hsys.shared.cnst.RateSubject;
import io.crs.hsys.shared.dto.common.CurrencyDtor;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;
import io.crs.hsys.shared.dto.rate.RateCodeDtor;
import io.crs.hsys.shared.dto.rate.RateRestrictionDto;
import io.crs.hsys.shared.dto.rate.query.RateByDateDto;
import io.crs.hsys.shared.dto.rate.query.RateQueryRespDto;
import io.crs.hsys.shared.util.DateUtils;

/**
 * @author robi
 *
 */
public class RateManagerPresenter extends Presenter<RateManagerPresenter.MyView, RateManagerPresenter.MyProxy>
		implements RateManagerUiHandlers, FilterChangeEvent.FilterChangeHandler, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(RateManagerPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<RateManagerUiHandlers> {
		void setData(List<RateQueryRespDto> data);

		void resizePanls(MenuState menuState);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.RATE_MANAGER)
// @UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<RateManagerPresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();

	private final PlaceManager placeManager;
	private final RateMngrFilterPresenter filter;

	@Inject
	RateManagerPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy, FroFilterFactory filterFactory) {
		super(eventBus, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.info("RateBrowserPresenter()");

		this.placeManager = placeManager;
		this.filter = filterFactory.createRateMngrFilter();

		addRegisteredHandler(ContentPushEvent.TYPE, this);
		addVisibleHandler(FilterChangeEvent.TYPE, this);
		
		getView().setUiHandlers(this);
		
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_FILTER, filter);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire("Rate Manager", "", MenuItemType.MENU_ITEM, this);
		loadData();
	}

	private void loadData() {
		Date startDate = new Date();

		CurrencyDtor cEur = CurrencyDtor.builder().code("EUR").description("Euro").build();
		CurrencyDtor cHuf = CurrencyDtor.builder().code("HUF").description("Magyar forint").build();

		RateCodeDtor rcPublic = RateCodeDtor.builder().webSafeKey("PUBLIC").code("PUBLIC").description("Public Rate")
				.subject(RateSubject.ROOM).currency(cHuf).build();
		RateCodeDtor rcNonref = RateCodeDtor.builder().webSafeKey("NONREF").code("NONREF")
				.description("Non Refundable Rate").subject(RateSubject.ROOM).currency(cEur).build();
		RateCodeDtor rcCompany = RateCodeDtor.builder().webSafeKey("COMPANY").code("COMPANY")
				.description("Company Rate").subject(RateSubject.ROOM).currency(cEur).build();
		RateCodeDtor rcFlash = RateCodeDtor.builder().webSafeKey("FLASH").code("FLASH")
				.description("Company Rate").subject(RateSubject.ROOM).currency(cHuf).build();

		RoomTypeDtor dblbRTD = RoomTypeDtor.builder().webSafeKey("DBLB").code("DBLB").name("Double bed room").build();
		RoomTypeDtor twinRTD = RoomTypeDtor.builder().webSafeKey("TWIN").code("TWIN").name("Twin bed room").build();

		List<RateQueryRespDto> ratesGridData = new ArrayList<RateQueryRespDto>();

//		RateRestrictionDto noRest = new RateRestrictionDto();
		ratesGridData.add(createRateLine(startDate, rcPublic, dblbRTD, cHuf, 999999d, null));

		RateRestrictionDto closedRest = new RateRestrictionDto(RateRestrictionType.RRT_CLOSED);
		ratesGridData.add(createRateLine(startDate, rcFlash, twinRTD, cHuf, 125000d, closedRest));

		RateRestrictionDto minlosRest = new RateRestrictionDto(RateRestrictionType.RRT_MINLOS, 3);
		ratesGridData.add(createRateLine(startDate, rcPublic, twinRTD, cHuf, 125000d, minlosRest));

		RateRestrictionDto maxlosRest = new RateRestrictionDto(RateRestrictionType.RRT_MAXLOS, 5);
		ratesGridData.add(createRateLine(startDate, rcCompany, dblbRTD, cHuf, 99999d, maxlosRest));

		RateRestrictionDto minStRest = new RateRestrictionDto(RateRestrictionType.RRT_MINST, 2);
		ratesGridData.add(createRateLine(startDate, rcCompany, dblbRTD, cEur, 99d, minStRest));

		RateRestrictionDto ctaRest = new RateRestrictionDto(RateRestrictionType.RRT_CTA);
		ratesGridData.add(createRateLine(startDate, rcNonref, dblbRTD, cEur, 120d, ctaRest));

		RateRestrictionDto ctdRest = new RateRestrictionDto(RateRestrictionType.RRT_CTD);
		ratesGridData.add(createRateLine(startDate, rcNonref, twinRTD, cEur, 130d, ctdRest));

		getView().setData(ratesGridData);
	}

	private RateQueryRespDto createRateLine(Date date, RateCodeDtor rateCode, RoomTypeDtor roomType, CurrencyDtor currency,
			Double rate, RateRestrictionDto restriction) {
		Map<RatePriceType, Double> rates = new HashMap<RatePriceType, Double>();
		rates.put(RatePriceType.DOUBLE, rate);

		List<RateByDateDto> ratesByDate = new ArrayList<RateByDateDto>();
		for (int i = 0; i < 21; i++) {
			ratesByDate.add(new RateByDateDto(DateUtils.addDay(date, i), rates, restriction));
		}
		return new RateQueryRespDto(rateCode, roomType, ratesByDate);
	}

	@Override
	public void onFilterChange(FilterChangeEvent event) {
		logger.info("RateBrowserPresenter()->onFilterChange()");
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
		getView().resizePanls(event.getMenuState());
	}

	@Override
	public void update() {
		Builder placeBuilder = new Builder().nameToken(NameTokens.RATE_UPDATER);
		placeManager.revealPlace(placeBuilder.build());
	}
}
