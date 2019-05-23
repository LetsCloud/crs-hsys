/**
 * 
 */
package io.crs.hsys.client.kip.filter.oooroom;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gwt.dom.client.Style.Unit;
import com.google.web.bindery.event.shared.EventBus;
import io.crs.hsys.client.core.filter.TextFilter;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.filter.AbstractFilterView;
import io.crs.hsys.client.kip.filter.AppUserFilter;
import io.crs.hsys.client.kip.filter.DateFilter;
import io.crs.hsys.client.kip.filter.RoomStatusFilter;
import io.crs.hsys.client.kip.filter.RoomTypeFilter;
import io.crs.hsys.client.kip.filter.SwitchFilter;
import io.crs.hsys.client.kip.filter.TaskKindFilter;
import io.crs.hsys.client.kip.filter.TaskStatusFilter;
import io.crs.hsys.client.kip.i18n.KipMessages;

/**
 * @author robi
 *
 */
public class OooRoomFilterView extends AbstractFilterView implements OooRoomFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(OooRoomFilterView.class.getName());

	private DateFilter fromDateFilter;
	private DateFilter toDateFilter;

	private String currentUserKey;

	private final EventBus eventBus;
	private final KipMessages i18n;

	@Inject
	Provider<SwitchFilter> switchFilterProvider;

	@Inject
	Provider<AppUserFilter> appUserFilterProvider;

	@Inject
	Provider<TaskStatusFilter> taskStatusFilterProvider;

	@Inject
	Provider<RoomStatusFilter> roomStatusFilterProvider;

	@Inject
	Provider<DateFilter> dateFilterProvider;

	@Inject
	Provider<TextFilter> textFilterProvider;

	@Inject
	Provider<TaskKindFilter> tasKindFilterProvider;

	@Inject
	Provider<RoomTypeFilter> roomTypeFilterProvider;

	@Inject
	OooRoomFilterView(EventBus eventBus, CoreMessages i18nCore, KipMessages i18n) {
		super(i18nCore);
		logger.info("OooRoomFilterView()");
		this.eventBus = eventBus;
		this.i18n = i18n;
	}

	@Override
	protected void initView() {
		super.initView();
		disableOnlyActive();

		initFromDateFilter();
		initToDateFilter();
	}

	private void initFromDateFilter() {
		fromDateFilter = dateFilterProvider.get();
		fromDateFilter.setChipPanel(collapsibleHeader);
		fromDateFilter.setChipLabel("");
		fromDateFilter.setFilterLabel("Mettől");
		fromDateFilter.setFilterHeight(45, Unit.PX);
	}

	private void initToDateFilter() {
		toDateFilter = dateFilterProvider.get();
		toDateFilter.setChipPanel(collapsibleHeader);
		toDateFilter.setChipLabel("");
		toDateFilter.setFilterLabel("Meddig");
		toDateFilter.setFilterHeight(45, Unit.PX);
	}

	@Override
	protected void createLayout() {

		fromDateFilter.setGrid("s6 m4");
		controlPanel.add(fromDateFilter);

		toDateFilter.setGrid("s6 m4");
		controlPanel.add(toDateFilter);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}
}
