/**
 * 
 */
package io.crs.hsys.client.kip.filter.oooroom;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.dom.client.Style.Unit;
import com.google.web.bindery.event.shared.EventBus;

import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconType;
import io.crs.hsys.client.core.filter.DateFilter;
import io.crs.hsys.client.core.filter.RoomTypeFilter;
import io.crs.hsys.client.core.filter.TextFilter;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.ui.filter.AbstractFilterView;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent;
import io.crs.hsys.client.core.ui.filter.FilterChangeEvent.DataTable;
import io.crs.hsys.client.core.util.DateUtils;
import io.crs.hsys.client.kip.filter.AppUserFilter;
import io.crs.hsys.client.kip.filter.CheckBoxFilter;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.shared.dto.common.AppUserDtor;
import io.crs.hsys.shared.dto.hotel.RoomDto;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author robi
 *
 */
public class OooRoomFilterView extends AbstractFilterView implements OooRoomFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(OooRoomFilterView.class.getName());

	private DateFilter fromDateFilter;
	private DateFilter toDateFilter;
	private RoomTypeFilter roomTypeFilter;
	private TextFilter roomFilter, floorFilter;
	private CheckBoxFilter onlyOooFilter, expiredNoFilter;
	private AppUserFilter reporterFilter;

	private final EventBus eventBus;
	private final CurrentUser currentUser;
	private final KipMessages i18n;

	@Inject
	Provider<CheckBoxFilter> checkBoxFilterProvider;

	@Inject
	Provider<AppUserFilter> appUserFilterProvider;

	@Inject
	Provider<TextFilter> textFilterProvider;

	@Inject
	Provider<RoomTypeFilter> roomTypeFilterProvider;

	@Inject
	OooRoomFilterView(EventBus eventBus, CurrentUser currentUser, CoreMessages i18nCore, KipMessages i18n) {
		super(i18nCore);
		logger.info("OooRoomFilterView()");
		this.eventBus = eventBus;
		this.currentUser = currentUser;
		this.i18n = i18n;
	}

	@Override
	protected void initView() {
		super.initView();
		disableOnlyActive();

		initFromDateFilter();
		initToDateFilter();
		initRoomTypeFilter();
		initRoomNumberFilter();
		initFloorFilter();
		initOnlyOooFilter();
		initExpiredNoFilter();
		initReporterFilter();
	}

	private void initFromDateFilter() {
		fromDateFilter = new DateFilter(currentUser.getLocale()) {
			@Override
			protected String createChipText(Date value) {
				return i18n.oooRoomFilterFromDateChipLabel(DateUtils.formatDateTime(value, currentUser.getLocale()));
			}
		};
		fromDateFilter.setChipPanel(collapsibleHeader);
		fromDateFilter.setFilterLabel(i18n.oooRoomFilterFromDateFilterLabel());
		fromDateFilter.setFilterHeight(45, Unit.PX);
	}

	private void initToDateFilter() {
		toDateFilter = new DateFilter(currentUser.getLocale()) {
			@Override
			protected String createChipText(Date value) {
				return i18n.oooRoomFilterToDateChipLabel(DateUtils.formatDateTime(value, currentUser.getLocale()));
			}
		};
		toDateFilter.setChipPanel(collapsibleHeader);
		toDateFilter.setFilterLabel(i18n.oooRoomFilterFromDateFilterLabel());
		toDateFilter.setFilterHeight(45, Unit.PX);
	}

	private void initRoomTypeFilter() {
		roomTypeFilter = roomTypeFilterProvider.get();
		roomTypeFilter.setChipPanel(collapsibleHeader);
		roomTypeFilter.setChipIconType(IconType.HOTEL);
		roomTypeFilter.setChipIconPosition(IconPosition.LEFT);
		roomTypeFilter.setChipIconFontSize(20d, Unit.PX);
		roomTypeFilter.setFilterLabel(i18n.roomStatusFilterRoomTypeLabel());
		roomTypeFilter.setFilterPlaceholder(i18n.roomStatusFilterRoomTypePlaceholder());
		/*
		roomTypeFilter.addSelectionHandler(e -> {
			eventBus.fireEvent(new FilterChangeEvent(DataTable.TASK));
		});
		*/
	}

	private void initRoomNumberFilter() {
		roomFilter = textFilterProvider.get();
		roomFilter.setChipPanel(collapsibleHeader);
		roomFilter.setChipIconType(IconType.ROOM);
		roomFilter.setChipIconPosition(IconPosition.LEFT);
		roomFilter.setChipIconFontSize(20d, Unit.PX);
		roomFilter.setFilterLabel(i18n.roomStatusFilterRoomNumberLabel());
		roomFilter.setFilterPlaceholder(i18n.roomStatusFilterRoomNumberPlaceholder());
		roomFilter.setFilterMarginTop(14);
		roomFilter.setFilterHeight(45, Unit.PX);
		roomFilter.addValueChangeHandler(e -> {
			eventBus.fireEvent(new FilterChangeEvent(DataTable.TASK));
		});
	}

	private void initFloorFilter() {
		floorFilter = textFilterProvider.get();
		floorFilter.setChipPanel(collapsibleHeader);
		floorFilter.setChipIconType(IconType.LIST);
		floorFilter.setChipIconPosition(IconPosition.LEFT);
		floorFilter.setChipIconFontSize(20d, Unit.PX);
		floorFilter.setFilterLabel(i18n.roomStatusFilterRoomFloorLabel());
		floorFilter.setFilterPlaceholder(i18n.roomStatusFilterRoomFloorPlaceholder());
		floorFilter.setFilterMarginTop(14);
		floorFilter.setFilterHeight(45, Unit.PX);
		floorFilter.addValueChangeHandler(e -> {
			eventBus.fireEvent(new FilterChangeEvent(DataTable.TASK));
		});
	}

	private void initOnlyOooFilter() {
		onlyOooFilter = checkBoxFilterProvider.get();
		onlyOooFilter.setChipPanel(collapsibleHeader);
		onlyOooFilter.setChipLabel("Csak OOO-k");
		onlyOooFilter.setFilterLabel("Csak OOO státuszú szobák");
//		onlyOooFilter.setFilterHeight(45, Unit.PX);
	}

	private void initExpiredNoFilter() {
		expiredNoFilter = checkBoxFilterProvider.get();
		expiredNoFilter.setChipPanel(collapsibleHeader);
		expiredNoFilter.setChipLabel("Lejárók nem");
		expiredNoFilter.setFilterLabel("Lejáró OOO szobák kiszűrése");
		expiredNoFilter.setFilterMarginTop(15);
//		toDateFilter.setFilterHeight(45, Unit.PX);
	}

	private void initReporterFilter() {
		reporterFilter = appUserFilterProvider.get();
//		ownerFilter.setChipLabel("Owner");
		reporterFilter.setChipPanel(collapsibleHeader);
		reporterFilter.setFilterLabel("Létrehozta");
		reporterFilter.setFilterPlaceholder("Válassz felhasználót");
		reporterFilter.setMarginTop(15);
		reporterFilter.setAllowClear(true);
//		reporterFilter.setMarginLeft(10);
//		reporterFilter.setMarginRight(10);
//		ownerFilter.setChipLetter("L");
//		ownerFilter.setChipLetterColor(Color.WHITE);
//		ownerFilter.setChipLetterBackgroundColor(Color.BLUE);
		reporterFilter.setChipIconType(IconType.RECORD_VOICE_OVER);
		reporterFilter.setChipIconPosition(IconPosition.LEFT);
		reporterFilter.setChipIconFontSize(20d, Unit.PX);
		reporterFilter.addSelectionHandler(e -> {
			eventBus.fireEvent(new FilterChangeEvent(DataTable.TASK));
		});
		reporterFilter.addRemoveItemHandler(e -> {
//			eventBus.fireEvent(new FilterChangeEvent(DataTable.TASK));
		});
	}

	@Override
	protected void createLayout() {
		controlPanel.setMarginBottom(0);

		fromDateFilter.setGrid("s6 m4");
		controlPanel.add(fromDateFilter);

		toDateFilter.setGrid("s6 m4");
		controlPanel.add(toDateFilter);

		roomTypeFilter.setGrid("s12 m4");
		controlPanel.add(roomTypeFilter);

		roomFilter.setGrid("s6 m2");
		controlPanel.add(roomFilter);

		floorFilter.setGrid("s6 m2");
		controlPanel.add(floorFilter);

		reporterFilter.setGrid("s12 m4");
		controlPanel.add(reporterFilter);

		onlyOooFilter.setGrid("s12 m4");
		controlPanel.add(onlyOooFilter);

		expiredNoFilter.setGrid("s12 m4");
		controlPanel.add(expiredNoFilter);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRoomTypeData(List<RoomTypeDtor> data) {
		roomTypeFilter.setComboBoxData(data);
	}

	@Override
	public RoomTypeDtor getSelectedRoomType() {
		return roomTypeFilter.getSelectedItem();
	}

	@Override
	public void setRoomData(List<RoomDto> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public RoomDto getSelectedRoom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAppUserData(List<AppUserDtor> data) {
		reporterFilter.setComboBoxData(data);
	}

	@Override
	public String getSelectedReporterKey() {
		return reporterFilter.getSelectedKey();
	}

	@Override
	public void setFromDate(Date fromDate) {
		fromDateFilter.setValue(fromDate);
	}

	@Override
	public void setToDate(Date toDate) {
		toDateFilter.setValue(toDate);
	}

}
