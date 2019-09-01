/**
 * 
 */
package io.crs.hsys.client.fro.filter.ratemngr;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.dom.client.Style.Unit;
import com.google.web.bindery.event.shared.EventBus;

import gwt.material.design.client.constants.IconType;
import io.crs.hsys.client.core.filter.FilterChangeEvent;
import io.crs.hsys.client.core.filter.FilterChangeEvent.DataTable;
import io.crs.hsys.client.core.filter.hotelchild.AbstractHotelChildFilterView;
import io.crs.hsys.client.core.filter.widget.DateFilter;
import io.crs.hsys.client.core.filter.widget.RateCodeFilter;
import io.crs.hsys.client.core.filter.widget.RoomTypeFilter;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.fro.i18n.FroMessages;
import io.crs.hsys.shared.dto.hotel.RateCodeDtor;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author robi
 *
 */
public class RateMngrFilterView extends AbstractHotelChildFilterView implements RateMngrFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(RateMngrFilterView.class.getName());

	private DateFilter dateFilter;
	private RateCodeFilter rateCodeFilter;
	private RoomTypeFilter roomTypeFilter;

	@Inject
	Provider<RateCodeFilter> rateCodeFilterProvider;

	@Inject
	Provider<RoomTypeFilter> roomTypeFilterProvider;

	private final EventBus eventBus;
	private final CurrentUser currentUser;
	private final FroMessages i18n;

	@Inject
	RateMngrFilterView(EventBus eventBus, CurrentUser currentUser, FroMessages i18n, CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore, cnstCore);
		logger.info("RateCodeFilterView()");
		this.eventBus = eventBus;
		this.currentUser = currentUser;
		this.i18n = i18n;
	}

	@Override
	protected void initView() {
		super.initView();
		disableOnlyActive();

		initRateCodeFilter();
		initRoomTypeFilter();
		initDateFilter();
	}

	private void initDateFilter() {
		dateFilter = new DateFilter(currentUser.getLocale());
		dateFilter.setChipPanel(collapsibleHeader);
		dateFilter.setChipIconType(IconType.DATE_RANGE);
//		dateFilter.setChipLabel("Mettől:");
		dateFilter.setFilterLabel("Mettől");
		dateFilter.setFilterHeight(45, Unit.PX);
		dateFilter.setValue(new Date());
	}

	private void initRateCodeFilter() {
		rateCodeFilter = rateCodeFilterProvider.get();
		rateCodeFilter.setMultiple(true);
		rateCodeFilter.setChipPanel(collapsibleHeader);
		rateCodeFilter.setFilterLabel(i18n.rateMngrFilterRateCodeLabel());
		rateCodeFilter.setFilterPlaceholder(i18n.rateMngrFilterRateCodePlaceholder());
		rateCodeFilter.addSelectionHandler(e -> {
			logger.info("RateCodeFilterView()->rateCodeFilter.addSelectionHandler()");
			eventBus.fireEvent(new FilterChangeEvent(DataTable.TASK));
		});
	}

	private void initRoomTypeFilter() {
		roomTypeFilter = roomTypeFilterProvider.get();
		roomTypeFilter.setMultiple(true);
		roomTypeFilter.setChipPanel(collapsibleHeader);
		roomTypeFilter.setFilterLabel(i18n.rateMngrFilterRoomTypeLabel());
		roomTypeFilter.setFilterPlaceholder(i18n.rateMngrFilterRoomTypePlaceholder());
		roomTypeFilter.addSelectionHandler(e -> {
			logger.info("RateCodeFilterView()->roomTypeFilter.addSelectionHandler()");
			eventBus.fireEvent(new FilterChangeEvent(DataTable.TASK));
		});
	}

	@Override
	protected void createLayout() {
		hotelComboBox.setGrid("s12 m6");
		controlPanel.add(hotelComboBox);

		dateFilter.setGrid("s6 m4");
		controlPanel.add(dateFilter);

		rateCodeFilter.setGrid("s6 m4");
		controlPanel.add(rateCodeFilter);

		roomTypeFilter.setGrid("s6 m4");
		controlPanel.add(roomTypeFilter);
	}

	@Override
	public void reset() {
		logger.info("RateCodeFilterView().reset()");
	}

	@Override
	public void setRateCodeData(List<RateCodeDtor> data) {
		rateCodeFilter.setComboBoxData(data);
	}

	@Override
	public List<RateCodeDtor> getSelectedRateCodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRoomTypeData(List<RoomTypeDtor> data) {
		roomTypeFilter.setComboBoxData(data);
	}

	@Override
	public List<RoomTypeDtor> getSelectedRoomTypes() {
		// TODO Auto-generated method stub
		return null;
	}
}
