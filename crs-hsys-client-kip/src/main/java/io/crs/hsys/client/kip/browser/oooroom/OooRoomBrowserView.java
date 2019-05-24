/**
 * 
 */
package io.crs.hsys.client.kip.browser.oooroom;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.Window;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.table.cell.Column;

import io.crs.hsys.client.core.browser.AbstractColumnConfig;
import io.crs.hsys.client.core.browser.ActionColumn;
import io.crs.hsys.client.core.browser.DataColumn;
import io.crs.hsys.client.core.browser.room.RoomBrowserView;
import io.crs.hsys.client.core.i18n.CoreConstants;
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

//	private static final int COL_KIND = 0;
//	private static final int COL_GROUP = 1;
//	private static final int COL_DESCRIPRION = 2;
//	private static final int COL_TIME = 3;
	private static final int COL_REMARKS = 6;

	public class ColumnConfig extends AbstractColumnConfig<OooRoomDto> {

		public ColumnConfig(Column<OooRoomDto, ?> column) {
			super(column);
		}

		public ColumnConfig(String header, Column<OooRoomDto, ?> column) {
			super(header, column);
		}
	}

	private final OooRoomTableView browserView = new OooRoomTableView();
	private final CurrentUser currentUser;
	private final KipMessages i18n;
	private final CoreConstants cnst;

	/**
	* 
	*/
	@Inject
	OooRoomBrowserView(CurrentUser currentUser, KipMessages i18n, CoreConstants cnst) {
		logger.info("OooRoomBrowserView()");

		MaterialPanel contentPanel = new MaterialPanel();
		contentPanel.setMarginLeft(20);
		contentPanel.setMarginRight(20);
		contentPanel.setPaddingTop(20);
		contentPanel.setPaddingBottom(20);
		contentPanel.add(browserView);

		initWidget(contentPanel);

//		this.browserView = table;
		this.currentUser = currentUser;
		this.i18n = i18n;
		this.cnst = cnst;

		bindSlot(OooRoomBrowserPresenter.FILTER_SLOT, browserView.getFilterPanel());

		initBrowswerView();
	}

	private void initBrowswerView() {

		browserView.setTableTitle(i18n.oooRoomBrowserTitle());

		browserView.getAddButton().addClickHandler(e -> getUiHandlers().createItem());
		browserView.getDeleteIcon().addClickHandler(e -> getUiHandlers().deleteItems(browserView.getSelectedItems()));

		browserView.clearColumnConfigs();

		// 1 - Room Column
		browserView.addColumnConfigs(new ColumnConfig(i18n.oooRoomBrowserRoomCol(), createRoomColumn()));

		// 2 - From Date Column
		browserView.addColumnConfigs(new ColumnConfig(i18n.oooRoomBrowserFromDateCol(), createFromDateColumn()));

		// 3- To Date Column
		browserView.addColumnConfigs(new ColumnConfig(i18n.oooRoomBrowserToDateCol(), createToDateColumn()));

		// 4- Return When Column
		browserView.addColumnConfigs(new ColumnConfig(i18n.oooRoomBrowserReturnWhenCol(), createReturnWhenColumn()));

		// 5 - Return As Column
		browserView.addColumnConfigs(new ColumnConfig(i18n.oooRoomBrowserReturnAsCol(), createReturnAsColumn()));

		// 6 - Remarks Column
		browserView.addColumnConfigs(new ColumnConfig(i18n.oooRoomBrowserRemarksCol(), createReturnRemarks()));

		// Edit Column
		browserView.addColumnConfigs(
				new ColumnConfig(new ActionColumn<OooRoomDto>((object) -> getUiHandlers().editItem(object))));

		// OOO change Column
		Date today = new Date();
		today.setHours(0);
		browserView.addColumnConfigs(
				new ColumnConfig(new ChangeOooColumn((object) -> getUiHandlers().editItem(object), today)));

		browserView.addAllColumns();
	}

	@Override
	public void reConfigColumns() {
//		browserView.hideColumn(COL_TIME, Window.getClientWidth() <= 1024);
		browserView.hideColumn(COL_REMARKS, Window.getClientWidth() <= 1240);
	}

	private DataColumn<OooRoomDto> createRoomColumn() {
		return new DataColumn<OooRoomDto>((o) -> o.getRoom().getCode(),
				(o1, o2) -> o1.getData().getRoom().compareTo(o2.getData().getRoom()));
	}

	private DataColumn<OooRoomDto> createFromDateColumn() {
		return new DataColumn<OooRoomDto>((o) -> DateUtils.formatDateTime(o.getFromDate(), currentUser.getLocale()),
				(o1, o2) -> o1.getData().getFromDate().compareTo(o2.getData().getFromDate()));
	}

	private DataColumn<OooRoomDto> createToDateColumn() {
		return new DataColumn<OooRoomDto>((o) -> DateUtils.formatDateTime(o.getToDate(), currentUser.getLocale()),
				(o1, o2) -> o1.getData().getToDate().compareTo(o2.getData().getToDate()));
	}

	private DataColumn<OooRoomDto> createReturnWhenColumn() {
		return new DataColumn<OooRoomDto>((o) -> cnst.oooReturnWhenMap().get(o.getReturnWhen().toString()));
	}

	private DataColumn<OooRoomDto> createReturnAsColumn() {
		return new DataColumn<OooRoomDto>((o) -> cnst.roomStatusMap().get(o.getReturnAs().toString()));
	}

	private DataColumn<OooRoomDto> createReturnRemarks() {
		return new DataColumn<OooRoomDto>((o) -> o.getRemarks());
	}

	@Override
	public void setData(List<OooRoomDto> data) {
		browserView.setData(data);
	}
}
