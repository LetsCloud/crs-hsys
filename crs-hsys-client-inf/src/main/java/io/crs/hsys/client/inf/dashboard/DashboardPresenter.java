/**
 * 
 */
package io.crs.hsys.client.inf.dashboard;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.dispatch.shared.ActionException;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import io.crs.hsys.client.core.CoreNameTokens;
import io.crs.hsys.client.core.app.AbstractAppPresenter;
import io.crs.hsys.client.core.event.ContentPushEvent;
import io.crs.hsys.client.core.event.ContentPushEvent.MenuState;
import io.crs.hsys.client.core.event.SetPageTitleEvent;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.inf.dashboard.widget.DataWidget;
import io.crs.hsys.client.inf.dashboard.widget.DataWidgetFactory;
import io.crs.hsys.client.inf.dashboard.widget.DataWidgetPresenter;
import io.crs.hsys.client.inf.dashboard.widget.DataWidgetViewFactory;
import io.crs.hsys.client.inf.gps.GpsState;
import io.crs.hsys.shared.api.CubeResource;
import io.crs.hsys.shared.cnst.MenuItemType;
import io.crs.hsys.shared.cnst.cube.Aggregation;
import io.crs.hsys.shared.cnst.cube.DataSource;
import io.crs.hsys.shared.cnst.cube.DataWidgetFieldType;
import io.crs.hsys.shared.cnst.cube.DataWidgetType;
import io.crs.hsys.shared.cnst.cube.Dimension;
import io.crs.hsys.shared.cnst.cube.Measure;
import io.crs.hsys.shared.cnst.cube.QueryPeriodType;
import io.crs.hsys.shared.dto.cube.DataWidgetValueM1Dto;
import io.crs.hsys.shared.dto.cube.dw.DataWidgetConfigDto;
import io.crs.hsys.shared.dto.cube.dw.DataWidgetFieldDto;
import io.crs.hsys.shared.dto.cube.query.CubeMeasureParamDto;
import io.crs.hsys.shared.dto.cube.query.CubeQueryParamDto;
import io.crs.hsys.shared.dto.hotel.HotelDto;

/**
 * @author CR
 *
 */
public class DashboardPresenter extends Presenter<DashboardPresenter.MyView, DashboardPresenter.MyProxy>
		implements DashboardUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(DashboardPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<DashboardUiHandlers> {

		void addDataWidget(DataWidgetPresenter.DataWidgetView widget);

		void setDataWidgets(Map<Integer, DataWidget> dataWidgets);

		void refresh(MenuState menuState);

		void setVisible(Boolean visible);
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.HOME)
	// @UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<DashboardPresenter> {
	}

	private final CoreMessages i18n;

	private final ResourceDelegate<CubeResource> dataCubeDelegate;

	private final DataWidgetFactory dataWidgetFactory;

	private final DataWidgetViewFactory dataWidgetViewFactory;

	private final GpsState gps;

	private final CurrentUser currentUser;

	private Map<Integer, DataWidget> dataWidgets = new HashMap<Integer, DataWidget>();

	@Inject
	DashboardPresenter(EventBus eventBus, MyView view, MyProxy proxy, CoreMessages i18n,
			ResourceDelegate<CubeResource> dataCubeDelegate, DataWidgetFactory dataWidgetFactory,
			DataWidgetViewFactory dataWidgetViewFactory, GpsState gps, CurrentUser currentUser) {
		super(eventBus, view, proxy, AbstractAppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "DashboardPresenter()");
		this.i18n = i18n;

		this.dataCubeDelegate = dataCubeDelegate;
		this.dataWidgetFactory = dataWidgetFactory;
		this.dataWidgetViewFactory = dataWidgetViewFactory;
		this.gps = gps;
		this.currentUser = currentUser;

		addRegisteredHandler(ContentPushEvent.TYPE, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		// Egyenlőre nem adatbázisból, hanem fixen legeneráljuk az Adat
		// Csempéket
		createDataWidgets(generateDataWidgetConfigs());
		generateDataWidgetValues();
		// A legenerált Adat Csempéket átadjuk a nézetnek
		getView().setDataWidgets(dataWidgets);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		getView().setVisible(true);
		SetPageTitleEvent.fire(i18n.pageDashboardTitle(), "", MenuItemType.MENU_ITEM, this);
	}

	@Override
	protected void onHide() {
		getView().setVisible(false);
	}

	/**
	 * Adat Csempék létrehozása a konfigurációk alapján.
	 * 
	 * @param configs
	 */
	private void createDataWidgets(List<DataWidgetConfigDto> configs) {
		for (DataWidgetConfigDto config : configs) {
			DataWidgetPresenter.DataWidgetView view = null;

			switch (config.getType()) {
			case VALUE_ONLY:
				view = dataWidgetViewFactory.getValueOnly();
				break;
			case VALUE_DELTA:
				view = dataWidgetViewFactory.getValueChange();
				break;
			case VALUE_CHART:
				view = dataWidgetViewFactory.getValueChart();
				break;
			case VALUE_DELTA_CHART:
				view = dataWidgetViewFactory.getValueChangeChart();
				break;
			case VALUE_DELTA_CHART2:
				view = dataWidgetViewFactory.getValueChangeChart2();
				break;
			case MULTI_VALUE:
				view = dataWidgetViewFactory.getMultiMeasure();
				break;
			case GEO_CHART:
				view = dataWidgetViewFactory.getGeoView();
				break;
			default:
				break;
			}

			if (view != null) {
				DataWidget dw = dataWidgetFactory.create(view, config);
				dataWidgets.put(config.getWidgetIndex(), dw);
			}
		}
	}

	/**
	 * Az Adat Csempe beállítások FIX legenerálása. A jövőben adatbázisból
	 * betöttendő.
	 * 
	 * @return
	 */
	private List<DataWidgetConfigDto> generateDataWidgetConfigs() {

		List<DataWidgetConfigDto> dataWidgetConfigs = new ArrayList<DataWidgetConfigDto>();

		// Widget1

		DataWidgetConfigDto revenue = new DataWidgetConfigDto(0, DataWidgetType.VALUE_DELTA_CHART2, "Bruttó árbevétel"); // Revenue

		revenue.addField(new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.VALUE).measure1(Measure.GRS_CHG)
				.measure2(Measure.DEP_ROOM).sufix(" eFt").dataSource(DataSource.FRO_ACTL_PERF)
				.collectionPeriod(QueryPeriodType.GPS).build());

		revenue.addField(new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.DELTA).measure1(Measure.GRS_REV)
				.measure2(Measure.DEP_ROOM).dataSource(DataSource.FRO_ACTL_PERF).collectionPeriod(QueryPeriodType.GPS)
				.build());

		revenue.addField(new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.ACTUAL_SERIES)
				.measure1(Measure.GRS_REV).measure2(Measure.DEP_ROOM).dataSource(DataSource.FRO_ACTL_PERF)
				.collectionPeriod(QueryPeriodType.GPS).build());

		revenue.addField(new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.BASE_SERIES)
				.measure1(Measure.GRS_REV).measure2(Measure.DEP_ROOM).dataSource(DataSource.FRO_ACTL_PERF)
				.collectionPeriod(QueryPeriodType.GPS).build());

		dataWidgetConfigs.add(revenue);

		// Widget2
		DataWidgetConfigDto occupancy = new DataWidgetConfigDto(1, DataWidgetType.VALUE_DELTA, "Foglaltság"); // Occupancy

		occupancy.addField(new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.VALUE)
				.measure1(Measure.ROOM_OCC_FULL).format("#,##0.00").sufix(" %").dataSource(DataSource.FRO_ACTL_CALC)
				.collectionPeriod(QueryPeriodType.GPS).build());

		occupancy.addField(
				new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.DELTA).measure1(Measure.ROOM_OCC_FULL)
						.dataSource(DataSource.FRO_ACTL_CALC).collectionPeriod(QueryPeriodType.GPS).build());

		dataWidgetConfigs.add(occupancy);

		// Widget3
		DataWidgetConfigDto roomNightsOnTheBook = new DataWidgetConfigDto(2, DataWidgetType.VALUE_DELTA_CHART,
				"Szobaéjszaka"); //

		roomNightsOnTheBook.addField(
				new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.VALUE).measure1(Measure.ROOM_NTS)
						.dataSource(DataSource.FRO_ACTL_PERF).collectionPeriod(QueryPeriodType.GPS).build());

		roomNightsOnTheBook.addField(
				new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.DELTA).measure1(Measure.ROOM_NTS)
						.dataSource(DataSource.FRO_ACTL_PERF).collectionPeriod(QueryPeriodType.GPS).build());

		roomNightsOnTheBook.addField(
				new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.ACTUAL_SERIES).measure1(Measure.ROOM_NTS)
						.dataSource(DataSource.FRO_ACTL_PERF).collectionPeriod(QueryPeriodType.GPS).build());

		dataWidgetConfigs.add(roomNightsOnTheBook);

		// Widget4
		DataWidgetConfigDto adr = new DataWidgetConfigDto(3, DataWidgetType.VALUE_DELTA, "Átlag bruttó szobaár"); // ADR

		adr.addField(new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.VALUE)
				.measure1(Measure.ROOM_OCC_FULL).sufix(" Ft").dataSource(DataSource.FRO_ACTL_PERF)
				.collectionPeriod(QueryPeriodType.GPS).build());

		adr.addField(
				new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.DELTA).measure1(Measure.ROOM_OCC_FULL)
						.dataSource(DataSource.FRO_ACTL_PERF).collectionPeriod(QueryPeriodType.GPS).build());

		dataWidgetConfigs.add(adr);

		// Field5
		// Yesterday
		DataWidgetConfigDto yesterdaySummary = new DataWidgetConfigDto(4, DataWidgetType.MULTI_VALUE, "Tegnap");

		// Occupancy
		yesterdaySummary.addField(new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.VALUE)
				.title("Foglaltság").measure1(Measure.ROOM_OCC_FULL).format("#,##0.00").sufix(" %")
				.dataSource(DataSource.FRO_ACTL_CALC).collectionPeriod(QueryPeriodType.PREV_DAY).build());

		yesterdaySummary.addField(new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.VALUE2).title("RevPAR")
				.measure1(Measure.GRS_REVPAR_FULL).sufix(" Ft").dataSource(DataSource.FRO_ACTL_CALC)
				.collectionPeriod(QueryPeriodType.PREV_DAY).build());

		// ADR
		yesterdaySummary.addField(new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.VALUE3)
				.title("Átlag bruttó szobaár").measure1(Measure.GRS_ADR).sufix(" Ft").dataSource(DataSource.FRO_ACTL_CALC)
				.collectionPeriod(QueryPeriodType.PREV_DAY).build());

		// Revenue
		yesterdaySummary.addField(
				new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.VALUE4).title("Bruttó árbevétel")
						.measure1(Measure.GRS_REV).measure2(Measure.DEP_TOTAL).multiplier((float) 0.001).sufix(" eFt")
						.dataSource(DataSource.FRO_ACTL_PERF).collectionPeriod(QueryPeriodType.PREV_DAY).build());

		// Room Nights Pick-Up
		yesterdaySummary.addField(
				new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.VALUE5).title("Szobaéjszaka felvét")
						.measure1(Measure.ROOM_NTS).aggregation(Aggregation.CHANGE).dataSource(DataSource.FRO_FCST_PERF)
						.collectionPeriod(QueryPeriodType.PREV_DAY).periodField(Dimension.FCST_DATE).build());
		/*
		 * yesterdaySummary.addField(new
		 * DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.VALUE6)
		 * .title("Revenue Pick-Up").measure1(Measure.GRS_REV).measure2(Measure.
		 * DEP_TOTAL)
		 * .aggregation(Aggregation.CHANGE).dataSource(DataSource.FRO_FCST_PERF)
		 * .collectionPeriod(QueryPeriodType.PREV_DAY).periodField(Dimension.
		 * FCST_DATE).build());
		 */
		dataWidgetConfigs.add(yesterdaySummary);

		// Widget6
		// Room Nights Pick-up
		DataWidgetConfigDto roomNightsPickUp = new DataWidgetConfigDto(5, DataWidgetType.VALUE_ONLY,
				"Szobaéjszaka felvét");

		roomNightsPickUp.addField(
				new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.VALUE).measure1(Measure.ROOM_NTS)
						.aggregation(Aggregation.CHANGE).remark("tegnap").dataSource(DataSource.FRO_FCST_PERF)
						.collectionPeriod(QueryPeriodType.PREV_DAY).periodField(Dimension.FCST_DATE).build());

		dataWidgetConfigs.add(roomNightsPickUp);

		// Widget7
		// Revenue Pick-up
		DataWidgetConfigDto revenuePickUp = new DataWidgetConfigDto(6, DataWidgetType.VALUE_ONLY,
				"Bruttó árbevétel felvét");

		revenuePickUp.addField(new DataWidgetFieldDto.Builder().fieldType(DataWidgetFieldType.VALUE)
				.measure1(Measure.GRS_REV).measure2(Measure.DEP_TOTAL).aggregation(Aggregation.CHANGE).sufix(" eFt")
				.multiplier((float) 0.001).remark("tegnap").dataSource(DataSource.FRO_FCST_PERF)
				.collectionPeriod(QueryPeriodType.PREV_DAY).periodField(Dimension.FCST_DATE).build());

		dataWidgetConfigs.add(revenuePickUp);

		// Widget8
		// Geo Chart
		DataWidgetConfigDto geoChart = new DataWidgetConfigDto(7, DataWidgetType.GEO_CHART,
				"Vendégéj országonként");

		dataWidgetConfigs.add(geoChart);

		return dataWidgetConfigs;
	}

	private void generateDataWidgetValues() {
		
		Map<Integer, List<DataWidgetValueM1Dto>> results = new HashMap<Integer, List<DataWidgetValueM1Dto>>();

		List<DataWidgetValueM1Dto> revenueValues = new ArrayList<DataWidgetValueM1Dto>();
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.VALUE, (double) 184871));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.DELTA, (double) 359362));

		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 2116136));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 2729135));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 3394875));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 4621245));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 3645598));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 4653543));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 4487442));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 3979794));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 3662484));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 3485101));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 3009736));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 2862287));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 3306272));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 3352634));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 3069637));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 3069637));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 6842164));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 11881951));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 11156443));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 11833263));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 12229891));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 12160300));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 12974840));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 13070894));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 6003407));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 5270774));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 5441915));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 5228679));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 4939668));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 5288744));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.ACTUAL_SERIES, (double) 4924038));

		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 7410002.3599));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 7576758.2858));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 10752280.0049));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 12739421.668));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 15605505.5713));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 8103122.5671));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 8266993.1118));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 8994251.4577));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 8866962.5046));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 8415055.5599));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 8840578.002));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 11222163.9289));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 8944193.7701));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 10085567.3022));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 12499735.1422));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 12784224.7391));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 12849501.5807));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 12026875.331));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 20541888.3049));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 10694143.1849));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 12538080.9577));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 12650430.9501));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 11616728.0442));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 14901382.9894));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 15092304.5899));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 17164385.1432));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 9825593.412));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 11569925.4462));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 12289341.2237));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 12471362.8452));
		revenueValues.add(new DataWidgetValueM1Dto(0, DataWidgetFieldType.BASE_SERIES, (double) 12023617.5252));

		results.put(0, revenueValues);

		/*
		 * select sum(EJSSZO) from ELOCUB where ELO_DATE = "2015.06.01" and FRO_DATE
		 * between "2015.07.01" and "2015.07.31" and (RESSTA in ("P", "N", "D", "V",
		 * "G", "L", "E","H"))
		 * 
		 * select sum(OSZSZO) from ELOCUB where ELO_DATE = "2015.06.01" and FRO_DATE
		 * between "2015.07.01" and "2015.07.31"
		 * 
		 * select sum(EJSSZO) from FROCUB where DATUMA between "2014.07.01" and
		 * "2014.07.31"
		 * 
		 * select sum(OSZSZO) from FROCUB where DATUMA between "2014.07.01" and
		 * "2014.07.31"
		 */
		List<DataWidgetValueM1Dto> occupancyValues = new ArrayList<DataWidgetValueM1Dto>();
		occupancyValues.add(new DataWidgetValueM1Dto(1, DataWidgetFieldType.VALUE, (double) 48.56));
		occupancyValues.add(new DataWidgetValueM1Dto(1, DataWidgetFieldType.DELTA, (double) 80.11));
		results.put(1, occupancyValues);

		List<DataWidgetValueM1Dto> roomNightsOTBValues = new ArrayList<DataWidgetValueM1Dto>();
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.VALUE, (double) 4652));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.DELTA, (double) 7673));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 58));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 67));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 83));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 112));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 90));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 125));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 117));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 97));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 89));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 86));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 75));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 71));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 84));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 87));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 82));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 83));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 162));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 301));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 276));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 290));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 300));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 298));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 319));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 326));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 159));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 149));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 140));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 134));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 127));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 137));
		roomNightsOTBValues.add(new DataWidgetValueM1Dto(2, DataWidgetFieldType.ACTUAL_SERIES, (double) 128));
		results.put(2, roomNightsOTBValues);

		List<DataWidgetValueM1Dto> AdrValues = new ArrayList<DataWidgetValueM1Dto>();
		AdrValues.add(new DataWidgetValueM1Dto(3, DataWidgetFieldType.VALUE, (double) 29560));
		AdrValues.add(new DataWidgetValueM1Dto(3, DataWidgetFieldType.DELTA, (double) 31200));
		results.put(3, AdrValues);

		List<DataWidgetValueM1Dto> yesterdayValues = new ArrayList<DataWidgetValueM1Dto>();

		// @formatter:off
		// select sum(EJSSZO) from FROCUB where DATUMA = "2015.06.01"
		// select sum(OSZSZO) from FROCUB where DATUMA = "2015.06.01"
		// @formatter:on
		yesterdayValues.add(new DataWidgetValueM1Dto(4, DataWidgetFieldType.VALUE, (double) 69.58));

		// @formatter:off
		// select sum(TERSZA-ENGSZA) from FROCUB where DATUMA = "2015.06.01"
		// select sum(OSZSZO) from FROCUB where DATUMA = "2015.06.01"
		// @formatter:on
		yesterdayValues.add(new DataWidgetValueM1Dto(4, DataWidgetFieldType.VALUE2, (double) 14365));

		// @formatter:off
		// select sum(TERSZA-ENGSZA) from FROCUB where DATUMA = "2015.06.01"
		// select sum(EJSSZO) from FROCUB where DATUMA = "2015.06.01"
		// @formatter:on
		yesterdayValues.add(new DataWidgetValueM1Dto(4, DataWidgetFieldType.VALUE3, (double) 20645));

		// @formatter:off
		// select
		// sum(TERSZA+TERIFA+TERVEN+TERREN+TERSPA+TERFIT+TEREGY-ENGSZA-ENGIFA-ENGVEN-ENGREN-ENGSPA-ENGFIT-ENGEGY)
		// from FROCUB
		// where DATUMA = "2015.06.01"
		// @formatter:on
		yesterdayValues.add(new DataWidgetValueM1Dto(4, DataWidgetFieldType.VALUE4, (double) 10002644));

		yesterdayValues.add(new DataWidgetValueM1Dto(4, DataWidgetFieldType.VALUE5, (double) 108));

		// yesterdayValues.add(new DataWidgetValueM1Dto(4,
		// DataWidgetFieldType.VALUE6, (double) 1560));
		results.put(4, yesterdayValues);

		/*
		 * select sum(EJSSZO) from ELOCUB where ELO_DATE = "2015.06.01" and (RESSTA in
		 * ("P", "N", "D", "V", "G", "L", "E","H"))
		 * 
		 * select sum(EJSSZO) from ELOCUB where ELO_DATE = "2015.05.31" and (RESSTA in
		 * ("P", "N", "D", "V", "G", "L", "E","H"))
		 */
		List<DataWidgetValueM1Dto> RNPickUpValues = new ArrayList<DataWidgetValueM1Dto>();
		RNPickUpValues.add(new DataWidgetValueM1Dto(5, DataWidgetFieldType.VALUE, (double) 108));
		results.put(5, RNPickUpValues);

		/*
		 * select sum(TERSZA+TERIFA+TERVEN+TERREN+TERSPA+TERFIT+TEREGY) from ELOCUB
		 * where ELO_DATE = "2015.06.01" and (RESSTA in ("P", "N", "D", "V", "G", "L",
		 * "E","H"))
		 * 
		 * select sum(TERSZA+TERIFA+TERVEN+TERREN+TERSPA+TERFIT+TEREGY) from ELOCUB
		 * where ELO_DATE = "2015.05.31" and (RESSTA in ("P", "N", "D", "V", "G", "L",
		 * "E","H"))
		 */

		List<DataWidgetValueM1Dto> RevPickUpValues = new ArrayList<DataWidgetValueM1Dto>();
		RevPickUpValues.add(new DataWidgetValueM1Dto(6, DataWidgetFieldType.VALUE, (double) 7013703));
		results.put(6, RevPickUpValues);



		List<DataWidgetValueM1Dto> geoChartValues = new ArrayList<DataWidgetValueM1Dto>();
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 16, "AE"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 114, "AT"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 705, "BE"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 6, "BR"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 41, "CH"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 178, "CZ"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 278, "DE"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 125, "DK"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 2, "ES"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 9, "FI"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 49, "FR"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 152, "GB"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 2, "HR"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 535, "HU"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 8, "IE"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 41, "IL"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 6, "IT"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 13, "LU"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 4, "MT"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 137, "NL"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 369, "NO"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 15, "PL"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 62, "RO"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 6, "RS"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 12, "RU"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 98, "SE"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 75, "SK"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 4, "UA"));
		geoChartValues.add(new DataWidgetValueM1Dto(7, DataWidgetFieldType.VALUE, (double) 1540, "US"));
		results.put(7, geoChartValues);

		
		for (Map.Entry<Integer, List<DataWidgetValueM1Dto>> entry : results.entrySet()) {
//			logger.log(Level.INFO, "DashboardPresenter.loadWidgetValues.onSuccess()->WidgetIndex" + entry.getKey());
			dataWidgets.get(entry.getKey()).setValues(entry.getValue());
		}
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, CubeQueryParamDto> buildQuery() {
		List<CubeQueryParamDto> result = new ArrayList<CubeQueryParamDto>();
		// Végig pásztázzuk a DataWidget-einket
		for (Map.Entry<Integer, DataWidget> entry : dataWidgets.entrySet()) {
			// Végigpásztázzuk az adott DataWidget mező konfigurációit
			for (DataWidgetFieldDto field : entry.getValue().getConfig().getFields()) {
				// A mező konfigok alapján legeneráljuk a CubeQueryParamDto-kat
				// és betöltjük egy listába.
				result.addAll(buildFieldQueries(entry.getValue().getConfig().getWidgetIndex(), field));
			}
		}
		/*
		 * for (CubeQueryParamDto p : result) LOGGER.log(Level.INFO,
		 * "buildQuery()->result=" + result.toString());
		 */
		Map<String, CubeQueryParamDto> map2 = new HashMap<String, CubeQueryParamDto>();
		for (CubeQueryParamDto cqpd : result) {
			CubeQueryParamDto temp = map2.get(cqpd.getKey());
			if (temp == null) {
				map2.put(cqpd.getKey(), cqpd);
			} else {
				temp.getMeasures().addAll(cqpd.getMeasures());
			}
		}
		return map2;
	}

	/**
	 * 
	 * @param widgetIndex
	 * @param field
	 * @return
	 */
	private List<CubeQueryParamDto> buildFieldQueries(Integer widgetIndex, DataWidgetFieldDto field) {
		List<CubeQueryParamDto> result = new ArrayList<CubeQueryParamDto>();

		CubeMeasureParamDto measure = new CubeMeasureParamDto();
		measure.setAggrFunc(field.getAggregation());
		measure.setDataSource(field.getDataSource());
		// measure.setFieldIndex(DataWidgetFieldType.VALUE.getIndex());
		measure.setFieldIndex(field.getFieldType());
		measure.setMeasure1(field.getMeasure1());
		measure.setMeasure2(field.getMeasure2());
		measure.setPeriodField(field.getPeriodDimension());
		measure.setWidgetIndex(widgetIndex);

		CubeQueryParamDto valueQuery = new CubeQueryParamDto();
		// valueQuery.setHotelDto(gps.getHotel());
		valueQuery.setHotelDto(new HotelDto(currentUser.getCurrentHotel()));
		valueQuery = setDates(valueQuery, field.getQueryPeriodType(), field.getDataSource(),
				field.getPeriodDimension());
		valueQuery.addMeasure(measure);
		result.add(valueQuery);

		return result;
	}

	private CubeQueryParamDto setDates(CubeQueryParamDto cqpd, QueryPeriodType cp, DataSource dataSource,
			Dimension periodDimension) {

		switch (cp) {
		case GPS:
			cqpd.setFromDate(gps.getFromCurrDate());
			cqpd.setToDate(gps.getToCurrDate());
			cqpd.setFromBaseDate(gps.getFromBaseDate());
			cqpd.setToBaseDate(gps.getToBaseDate());

			if (periodDimension.equals(Dimension.GPS_SCALE)) {
				cqpd.addDimension(gps.getPeriodDimension(dataSource));
			} else {
				cqpd.addDimension(periodDimension);
			}

			break;
		case CURR_DAY:
			break;
		case PREV_DAY:
			Date today = new Date();
			CalendarUtil.resetTime(today);

			Date yesterday = CalendarUtil.copyDate(today);
			CalendarUtil.addDaysToDate(yesterday, -1);

			Date dby = CalendarUtil.copyDate(today);
			CalendarUtil.addDaysToDate(yesterday, -2);

			cqpd.setFromDate(yesterday);
			cqpd.setToDate(yesterday);
			cqpd.setFromBaseDate(dby);
			cqpd.setToBaseDate(dby);
			break;
		default:
			break;
		}
		return cqpd;
	}

	/**
	 * 
	 * @param results
	 * @return
	 */
	private Map<Integer, List<DataWidgetValueM1Dto>> repackResults(List<DataWidgetValueM1Dto> results) {
		Map<Integer, List<DataWidgetValueM1Dto>> ret = new HashMap<Integer, List<DataWidgetValueM1Dto>>();
		for (DataWidgetValueM1Dto result : results) {
			Integer widgetIndex = result.getWidgetIndex();
			logger.log(Level.INFO, "DashboardPresenter.repackResults()->widgetIndex=" + widgetIndex);
			List<DataWidgetValueM1Dto> temp = ret.get(widgetIndex);
			if (temp == null) {
				temp = new ArrayList<DataWidgetValueM1Dto>();
				ret.put(widgetIndex, temp);
			}
			temp.add(result);
		}
		return ret;
	}

	@Override
	public void loadWidgetValues(Map<String, CubeQueryParamDto> queryParam) {

		for (Map.Entry<String, CubeQueryParamDto> param : queryParam.entrySet()) {
			dataCubeDelegate.withCallback(new AsyncCallback<List<DataWidgetValueM1Dto>>() {

				@Override
				public void onFailure(Throwable caught) {
					if (caught instanceof ActionException)
						if (((ActionException) caught).getCause() != null)
							logger.log(Level.INFO, "((ActionException)caught).getCause()"
									+ ((ActionException) caught).getCause().getClass().getName());
				}

				@Override
				public void onSuccess(List<DataWidgetValueM1Dto> result) {
					for (Map.Entry<Integer, List<DataWidgetValueM1Dto>> entry : repackResults(result).entrySet()) {
						dataWidgets.get(entry.getKey()).setValues(entry.getValue());
					}
				}
			}).dataWidgetValueM1Query(param.getValue());
		}
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
		getView().refresh(event.getMenuState());
	}
}
