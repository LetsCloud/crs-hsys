/**
 * 
 */
package io.crs.hsys.client.kip.browser.oooroom;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.client.core.browser.AbstractBrowserView;
import io.crs.hsys.client.core.browser.ActionColumn;
import io.crs.hsys.client.core.browser.DataColumn;
import io.crs.hsys.client.core.browser.room.RoomBrowserView;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.util.DateUtils;
import io.crs.hsys.shared.dto.hotel.OooRoomDto;

/**
 * @author CR
 *
 */
public class OooRoomBrowserView extends ViewWithUiHandlers<OooRoomBrowserUiHandlers>
		implements OooRoomBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(RoomBrowserView.class.getName());

	private final AbstractBrowserView<OooRoomDto> table;
	private final CurrentUser currentUser;
	private final CoreMessages i18nCore;

	/**
	* 
	*/
	@Inject
	OooRoomBrowserView(AbstractBrowserView<OooRoomDto> table, CurrentUser currentUser, CoreMessages i18nCore) {
		logger.info("OooRoomBrowserView()");
		initWidget(table);

		this.table = table;
		this.currentUser = currentUser;
		this.i18nCore = i18nCore;

//		bindSlot(OooRoomBrowserPresenter.SLOT_FILTER, table.getFilterPanel());

		initTable();
	}

	private void initTable() {

		table.setTableTitle(i18nCore.roomBrowserTitle());

		table.getAddButton().addClickHandler(e -> getUiHandlers().createItem());
		table.getDeleteIcon().addClickHandler(e -> getUiHandlers().deleteItems(table.getSelectedItems()));

		// Room Column
		table.addColumn(
				new DataColumn<OooRoomDto>((o) -> o.getRoom().getCode(),
						(o1, o2) -> o1.getData().getRoom().compareTo(o2.getData().getRoom())),
				i18nCore.oooRoomBrowserRoomCol());

		// From Date Column
		table.addColumn(
				new DataColumn<OooRoomDto>((o) -> DateUtils.formatDateTime(o.getFromDate(), currentUser.getLocale()),
						(o1, o2) -> o1.getData().getFromDate().compareTo(o2.getData().getFromDate())),
				i18nCore.oooRoomBrowserFromDateCol());

		// To Date Column
		table.addColumn(
				new DataColumn<OooRoomDto>((o) -> DateUtils.formatDateTime(o.getToDate(), currentUser.getLocale()),
						(o1, o2) -> o1.getData().getToDate().compareTo(o2.getData().getToDate())),
				i18nCore.oooRoomBrowserToDateCol());

		// Remarks Column
		table.addColumn(new DataColumn<OooRoomDto>((o) -> o.getRemarks()), i18nCore.roomsTableType());

		// Edit Column
		table.addColumn(new ActionColumn<OooRoomDto>((object) -> getUiHandlers().editItem(object)));
	}

	@Override
	public void setData(List<OooRoomDto> data) {
		table.setData(data);
	}
}
