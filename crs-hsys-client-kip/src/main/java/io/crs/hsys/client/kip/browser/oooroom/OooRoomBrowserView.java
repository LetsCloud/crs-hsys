/**
 * 
 */
package io.crs.hsys.client.kip.browser.oooroom;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialPanel;
import io.crs.hsys.client.core.browser.AbstractBrowserView;
import io.crs.hsys.client.core.browser.ActionColumn;
import io.crs.hsys.client.core.browser.DataColumn;
import io.crs.hsys.client.core.browser.room.RoomBrowserView;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.util.DateUtils;
import io.crs.hsys.client.kip.i18n.KipMessages;
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
	private final KipMessages i18n;
	private final CoreMessages i18nCore;
	private final CoreConstants cnst;

	/**
	* 
	*/
	@Inject
	OooRoomBrowserView(AbstractBrowserView<OooRoomDto> table, CurrentUser currentUser, KipMessages i18n,
			CoreMessages i18nCore, CoreConstants cnst) {
		logger.info("OooRoomBrowserView()");
		
		MaterialPanel contentPanel = new MaterialPanel();
		contentPanel.setMarginLeft(20);
		contentPanel.setMarginRight(20);
		contentPanel.setPaddingTop(20);
		contentPanel.setPaddingBottom(20);
		contentPanel.add(table);

		initWidget(contentPanel);

		this.table = table;
		this.currentUser = currentUser;
		this.i18n = i18n;
		this.i18nCore = i18nCore;
		this.cnst = cnst;

//		bindSlot(OooRoomBrowserPresenter.SLOT_FILTER, table.getFilterPanel());

		initTable();
	}

	private void initTable() {

		table.setTableTitle(i18n.oooRoomBrowserTitle());

		table.getAddButton().addClickHandler(e -> getUiHandlers().createItem());
		table.getDeleteIcon().addClickHandler(e -> getUiHandlers().deleteItems(table.getSelectedItems()));

		// Room Column
		table.addColumn(
				new DataColumn<OooRoomDto>((o) -> o.getRoom().getCode(),
						(o1, o2) -> o1.getData().getRoom().compareTo(o2.getData().getRoom())),
				i18n.oooRoomBrowserRoomCol());

		// From Date Column
		table.addColumn(
				new DataColumn<OooRoomDto>((o) -> DateUtils.formatDateTime(o.getFromDate(), currentUser.getLocale()),
						(o1, o2) -> o1.getData().getFromDate().compareTo(o2.getData().getFromDate())),
				i18n.oooRoomBrowserFromDateCol());

		// To Date Column
		table.addColumn(
				new DataColumn<OooRoomDto>((o) -> DateUtils.formatDateTime(o.getToDate(), currentUser.getLocale()),
						(o1, o2) -> o1.getData().getToDate().compareTo(o2.getData().getToDate())),
				i18n.oooRoomBrowserToDateCol());

		// Return When Column
		table.addColumn(new DataColumn<OooRoomDto>((o) -> o.getReturnTime().toString()),
				i18n.oooRoomBrowserReturnWhenCol());

		// Return As Column
		table.addColumn(new DataColumn<OooRoomDto>((o) -> cnst.roomStatusMap().get(o.getReturnAs().toString())),
				i18n.oooRoomBrowserReturnAsCol());

		// Remarks Column
		table.addColumn(new DataColumn<OooRoomDto>((o) -> o.getRemarks()), i18n.oooRoomBrowserRemarksCol());

		// Edit Column
		table.addColumn(new ActionColumn<OooRoomDto>((object) -> getUiHandlers().editItem(object)));
	}

	@Override
	public void setData(List<OooRoomDto> data) {
		table.setData(data);
	}
}
