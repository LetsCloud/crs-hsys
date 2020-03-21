/**
 * 
 */
package io.crs.hsys.client.inf.analytics;

import static io.crs.hsys.shared.api.ApiParameters.DATE_FORMAT;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.dispatch.shared.ActionException;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.event.ContentPushEvent.MenuState;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.inf.InfNameTokens;
import io.crs.hsys.shared.api.CubeResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.cnst.cube.AnalyticsType;
import io.crs.hsys.shared.cnst.cube.DataSource;
import io.crs.hsys.shared.cnst.cube.DataWidgetFieldType;
import io.crs.hsys.shared.cnst.cube.Dimension;
import io.crs.hsys.shared.cnst.cube.Measure;
import io.crs.hsys.shared.dto.cube.D3m6Dto;
import io.crs.hsys.shared.dto.cube.query.CubeMeasureParamDto;
import io.crs.hsys.shared.dto.cube.query.CubeQueryParamDto;
import io.crs.hsys.shared.dto.hotel.HotelDto;

/**
 * @author CR
 *
 */
public class AnalyticsPresenter extends Presenter<AnalyticsPresenter.MyView, AnalyticsPresenter.MyProxy>
		implements AnalyticsUiHandlers {
	private static final Logger LOGGER = Logger.getLogger(AnalyticsPresenter.class.getName());
	private static final String PARAM_NAME = "type";
	
	interface MyView extends View, HasUiHandlers<AnalyticsUiHandlers> {
		void setDataSeries(List<Double> actualSeries);

		void setDataSeries(List<Double> actualSeries, List<Double> baseSeries);

		void displayData(List<D3m6Dto> data);

		void refresh(MenuState menuState);
	}

	@ProxyCodeSplit
	@NameToken(InfNameTokens.ANALYTICS)
	interface MyProxy extends ProxyPlace<AnalyticsPresenter> {
	}

	private final PlaceManager placeManager;

	private final ResourceDelegate<CubeResource> dataCubeDelegate;
	private final CurrentUser currentUser;
	private final String unauthorizedPlace;

	@Inject
	AnalyticsPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<CubeResource> dataCubeDelegate, CurrentUser currentUser,
			@UnauthorizedPlace String unauthorizedPlace) {
		super(eventBus, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		LOGGER.log(Level.INFO, "AnalyticsPresenter()");

		this.placeManager = placeManager;
		this.dataCubeDelegate = dataCubeDelegate;
		this.currentUser = currentUser;
		this.unauthorizedPlace = unauthorizedPlace;

		getView().setUiHandlers(this);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);

		String type = request.getParameter(PARAM_NAME, AnalyticsType.UNKNOWN.toString());

	}

	@Override
	protected void onReveal() {
		SetPageTitleEvent.fire("Szobaéj teljesítmény", "", MenuItemType.SUB_MENU, this);
		loadTestData();
	}

	private void loadTestData() {
		List<Double> actualSeries = new ArrayList<Double>();
		actualSeries.add(58d);
		actualSeries.add(67d);
		actualSeries.add(83d);
		actualSeries.add(112d);
		actualSeries.add(90d);
		actualSeries.add(125d);
		actualSeries.add(117d);
		actualSeries.add(97d);
		actualSeries.add(89d);
		actualSeries.add(86d);
		actualSeries.add(75d);
		actualSeries.add(71d);
		actualSeries.add(84d);
		actualSeries.add(87d);
		actualSeries.add(82d);
		actualSeries.add(83d);
		actualSeries.add(162d);
		actualSeries.add(301d);
		actualSeries.add(276d);
		actualSeries.add(290d);
		actualSeries.add(300d);
		actualSeries.add(298d);
		actualSeries.add(319d);
		actualSeries.add(326d);
		actualSeries.add(159d);
		actualSeries.add(149d);
		actualSeries.add(140d);
		actualSeries.add(134d);
		actualSeries.add(127d);
		actualSeries.add(137d);
		actualSeries.add(128d);

		List<Double> baseSeries = new ArrayList<Double>();
		baseSeries.add(159d);
		baseSeries.add(190d);
		baseSeries.add(241d);
		baseSeries.add(276d);
		baseSeries.add(275d);
		baseSeries.add(177d);
		baseSeries.add(194d);
		baseSeries.add(214d);
		baseSeries.add(202d);
		baseSeries.add(195d);
		baseSeries.add(207d);
		baseSeries.add(228d);
		baseSeries.add(198d);
		baseSeries.add(256d);
		baseSeries.add(280d);
		baseSeries.add(303d);
		baseSeries.add(294d);
		baseSeries.add(273d);
		baseSeries.add(271d);
		baseSeries.add(234d);
		baseSeries.add(286d);
		baseSeries.add(284d);
		baseSeries.add(267d);
		baseSeries.add(294d);
		baseSeries.add(296d);
		baseSeries.add(309d);
		baseSeries.add(226d);
		baseSeries.add(259d);
		baseSeries.add(261d);
		baseSeries.add(268d);
		baseSeries.add(256d);

		getView().setDataSeries(actualSeries, baseSeries);

	}

	@Override
	public void loadData() {
		LOGGER.log(Level.INFO, "loadData()");
		DateTimeFormat fmt = DateTimeFormat.getFormat(DATE_FORMAT);

		List<Dimension> dimensions = new ArrayList<Dimension>();
		dimensions.add(Dimension.MARKET);
		dimensions.add(Dimension.CHANNEL);
		dimensions.add(Dimension.NONE);

		List<CubeMeasureParamDto> measures = new ArrayList<CubeMeasureParamDto>();
		measures.add(new CubeMeasureParamDto(DataWidgetFieldType.VALUE, DataSource.FRO_ACTL_PERF, Measure.RRES_ARR));
		measures.add(new CubeMeasureParamDto(DataWidgetFieldType.VALUE2, DataSource.FRO_ACTL_PERF, Measure.ROOM_NTS));
		measures.add(new CubeMeasureParamDto(DataWidgetFieldType.VALUE3, DataSource.FRO_ACTL_PERF, Measure.RRES_DEP));

		CubeQueryParamDto param = new CubeQueryParamDto();
		param.setHotelDto(new HotelDto(currentUser.getCurrentHotel()));
		param.setFromDate(fmt.parse("2015-06-01"));
		param.setToDate(fmt.parse("2015-06-01"));
		param.setDimensions(dimensions);
		param.setMeasures(measures);

		dataCubeDelegate.withCallback(new AsyncCallback<List<D3m6Dto>>() {

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

			@Override
			public void onSuccess(List<D3m6Dto> result) {
				LOGGER.log(Level.INFO, "onSuccess()");
				getView().displayData(result);
			}
		}).d3m6CubeQuery(param);
	}

}
