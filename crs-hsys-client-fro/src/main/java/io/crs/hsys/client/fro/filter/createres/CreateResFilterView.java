/**
 * 
 */
package io.crs.hsys.client.fro.filter.createres;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.dom.client.Style.Unit;
import com.google.web.bindery.event.shared.EventBus;

import io.crs.hsys.client.core.filter.FilterChangeEvent;
import io.crs.hsys.client.core.filter.FilterChangeEvent.DataTable;
import io.crs.hsys.client.core.filter.hotelchild.AbstractHotelChildFilterView;
import io.crs.hsys.client.core.filter.widget.DateFilter;
import io.crs.hsys.client.core.filter.widget.RoomTypeFilter;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.shared.dto.hotel.RoomTypeDtor;

/**
 * @author robi
 *
 */
public class CreateResFilterView extends AbstractHotelChildFilterView implements CreateResFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(CreateResFilterView.class.getName());

	private DateFilter fromDateFilter;
	private DateFilter toDateFilter;
	private RoomTypeFilter roomTypeFilter;

	@Inject
	Provider<RoomTypeFilter> roomTypeFilterProvider;

	private final EventBus eventBus;
	private final CurrentUser currentUser;

	@Inject
	CreateResFilterView(EventBus eventBus, CurrentUser currentUser, CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore, cnstCore);
		logger.info("CreateResFilterView()");
		this.eventBus = eventBus;
		this.currentUser = currentUser;
	}

	@Override
	protected void initView() {
		super.initView();
		disableOnlyActive();

		initFromDateFilter();
		initToDateFilter();
		initRoomTypeFilter();
	}

	private void initFromDateFilter() {
		fromDateFilter = new DateFilter(currentUser.getLocale());
		fromDateFilter.setChipPanel(collapsibleHeader);
		fromDateFilter.setChipLabel("Mettől:");
		fromDateFilter.setFilterLabel("Mettől");
		fromDateFilter.setFilterHeight(45, Unit.PX);
	}

	private void initToDateFilter() {
		toDateFilter = new DateFilter(currentUser.getLocale());
		toDateFilter.setChipPanel(collapsibleHeader);
		toDateFilter.setChipLabel("Mettől:");
		toDateFilter.setFilterLabel("Meddig");
		toDateFilter.setFilterHeight(45, Unit.PX);
	}

	private void initRoomTypeFilter() {
		roomTypeFilter = roomTypeFilterProvider.get();
		roomTypeFilter.setChipPanel(collapsibleHeader);
		roomTypeFilter.setFilterLabel("Szobatípus");
		roomTypeFilter.setFilterPlaceholder("Válassz szobatípust");
		roomTypeFilter.addSelectionHandler(e -> {
			eventBus.fireEvent(new FilterChangeEvent(DataTable.ROOM_TYPE));
		});
	}

	@Override
	protected void createLayout() {
		hotelComboBox.setGrid("s12 m6");
		controlPanel.add(hotelComboBox);

		fromDateFilter.setGrid("s6 m4");
		controlPanel.add(fromDateFilter);

		toDateFilter.setGrid("s6 m4");
		controlPanel.add(toDateFilter);

		roomTypeFilter.setGrid("s6 m4");
		controlPanel.add(roomTypeFilter);
	}

	@Override
	public Date getArrivalDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDepartureDate() {
		// TODO Auto-generated method stub
		return null;
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
	public void reset() {
		logger.info("RoomFilterView().reset()");
	}
}
