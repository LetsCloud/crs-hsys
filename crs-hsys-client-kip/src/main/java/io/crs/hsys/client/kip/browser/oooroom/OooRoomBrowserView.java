/**
 * 
 */
package io.crs.hsys.client.kip.browser.oooroom;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.Display;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.table.cell.Column;
import gwt.material.design.jquery.client.api.JQueryElement;

import io.crs.hsys.client.core.browser.AbstractColumnConfig;
import io.crs.hsys.client.core.browser.ActionColumn;
import io.crs.hsys.client.core.browser.DataColumn;
import io.crs.hsys.client.core.browser.room.RoomBrowserView;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.security.CurrentUser;
import io.crs.hsys.client.core.util.DateUtils;
import io.crs.hsys.client.kip.i18n.KipMessages;
import io.crs.hsys.client.kip.roomstatus.RoomStatusUtils;
import io.crs.hsys.shared.cnst.OooReturnWhen;
import io.crs.hsys.shared.dto.hotel.OooRoomDto;

/**
 * @author CR
 *
 */
public class OooRoomBrowserView extends ViewWithUiHandlers<OooRoomBrowserUiHandlers>
		implements OooRoomBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(RoomBrowserView.class.getName());

	private static final int COL_FROMDATE = 0;
	private static final int COL_TODATE = 1;
	private static final int COL_ROOM = 2;
	private static final int COL_STATUS = 3;
	private static final int COL_ROOMTYPE = 4;
	private static final int COL_FLOOR = 5;
	private static final int COL_RETURNWHEN = 6;
	private static final int COL_RETURNAS = 7;
	private static final int COL_REMARKS = 8;
	private static final int COL_CREATEDBY = 9;

	private Boolean[] colHide = new Boolean[10];

	public class ColumnConfig extends AbstractColumnConfig<OooRoomDto> {

		public ColumnConfig(Column<OooRoomDto, ?> column) {
			super(column);
		}

		public ColumnConfig(String header, Column<OooRoomDto, ?> column) {
			super(header, column);
		}
	}

	private final OooRoomTableView tableView = new OooRoomTableView();
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
		contentPanel.add(tableView);

		tableView.setTableHeight("calc(100vh - 395px)");

		initWidget(contentPanel);

//		this.browserView = table;
		this.currentUser = currentUser;
		this.i18n = i18n;
		this.cnst = cnst;

		bindSlot(OooRoomBrowserPresenter.FILTER_SLOT, tableView.getFilterPanel());

		initBrowswerView();
	}

	private void initBrowswerView() {
		Arrays.fill(colHide, Boolean.FALSE);

		tableView.setTableTitle(i18n.oooRoomBrowserTitle());

		tableView.getAddButton().addClickHandler(e -> getUiHandlers().createItem());
		tableView.getDeleteIcon().addClickHandler(e -> getUiHandlers().deleteItems(tableView.getSelectedItems()));

		tableView.clearColumnConfigs();

		// 0 - From Date Column
		tableView.addColumnConfigs(new ColumnConfig(i18n.oooRoomBrowserFromDateCol(), createFromDateColumn()));

		// 1- To Date Column
		tableView.addColumnConfigs(new ColumnConfig(i18n.oooRoomBrowserToDateCol(), createToDateColumn()));

		// 2 - Room Column
		tableView.addColumnConfigs(new ColumnConfig(i18n.oooRoomBrowserRoomCol(), createRoomColumn()));

		// 3 - Current Status Column
		tableView.addColumnConfigs(new ColumnConfig("Státusz", createStatusColumn()));

		// 4 - RoomType Column
		tableView.addColumnConfigs(new ColumnConfig("Típus", createRoomTypeColumn()));

		// 5 - Floor Column
		tableView.addColumnConfigs(new ColumnConfig("Emelet", createFloorColumn()));

		// 6- Return When Column
		tableView.addColumnConfigs(new ColumnConfig(i18n.oooRoomBrowserReturnWhenCol(), createReturnWhenColumn2()));

		// 7 - Return As Column
		tableView.addColumnConfigs(new ColumnConfig(i18n.oooRoomBrowserReturnAsCol(), createReturnAsColumn()));

		// 8 - Remarks Column
		tableView.addColumnConfigs(new ColumnConfig(i18n.oooRoomBrowserRemarksCol(), createReturnRemarks()));

		// 9 - CreatedBy Column
		tableView.addColumnConfigs(new ColumnConfig("Létrehozta", createCreatedByColumn()));

		// 10 -Edit Column
		tableView.addColumnConfigs(
				new ColumnConfig(new ActionColumn<OooRoomDto>((object) -> getUiHandlers().editItem(object))));

		// OOO change Column
//		Date today = new Date();
//		today.setHours(0);
//		browserView.addColumnConfigs(
//				new ColumnConfig(new ChangeOooColumn((object) -> getUiHandlers().editItem(object), today)));

		tableView.addAllColumns();

		// Here we are adding a row expansion handler.
		// This is invoked when a row is expanded.
//		tableView.setUseRowExpansion(true);
		tableView.addRowExpandingHandler(event -> {
			JQueryElement section = event.getExpansion().getOverlay();

			// Clear the content first.
			MaterialWidget content = new MaterialWidget(event.getExpansion().getContent().empty().asElement());

			// Fake Async Task
			// This is demonstrating a fake asynchronous call to load
			// the data inside the expansion element.
			new Timer() {
				@Override
				public void run() {
					MaterialLabel title = new MaterialLabel("Részletek");
					title.setFontSize("1.2em");
					title.setDisplay(Display.BLOCK);

					content.setPadding(20);
					content.add(title);

					if (colHide[COL_STATUS]) {
						ValueDisplay statusText = new ValueDisplay();
						statusText.setLabel("Státusz");
						statusText.setValue(cnst.roomStatusMap()
								.get(event.getExpansion().getModel().getRoom().getRoomStatus().toString()));
						statusText.setGrid("s12 m4 l3");
						content.add(statusText);
					}

					if (colHide[COL_ROOMTYPE]) {
						ValueDisplay roomTypeText = new ValueDisplay();
						roomTypeText.setLabel("Típus");
						roomTypeText.setValue(event.getExpansion().getModel().getRoom().getRoomType().getCode());
						roomTypeText.setGrid("s12 m4 l3");
						content.add(roomTypeText);
					}

					if (colHide[COL_FLOOR]) {
						ValueDisplay floorText = new ValueDisplay();
						floorText.setLabel("Emelet");
						floorText.setValue(event.getExpansion().getModel().getRoom().getFloor());
						floorText.setGrid("s12 m4 l3");
						content.add(floorText);
					}

					if (colHide[COL_RETURNWHEN]) {
						ValueDisplay returnWhenText = new ValueDisplay();
						returnWhenText.setLabel("Visszadás időszaka");
						returnWhenText.setValue(cnst.oooReturnWhenMap()
								.get(event.getExpansion().getModel().getReturnWhen().toString()));
						returnWhenText.setGrid("s12 m4 l3");
						content.add(returnWhenText);
					}

					if (colHide[COL_RETURNAS]) {
						ValueDisplay returnAsText = new ValueDisplay();
						returnAsText.setLabel("Visszadás státusza");
						returnAsText.setValue(
								cnst.roomStatusMap().get(event.getExpansion().getModel().getReturnAs().toString()));
						returnAsText.setGrid("s12 m4 l3");
						content.add(returnAsText);
					}

					if (colHide[COL_CREATEDBY]) {
						ValueDisplay createdByText = new ValueDisplay();
						createdByText.setLabel("Létrehozta");
						createdByText.setValue(event.getExpansion().getModel().getCreatedBy().getCode());
						createdByText.setGrid("s12 m4 l3");
						content.add(createdByText);
					}

					if (colHide[COL_REMARKS]) {
						ValueDisplay remarksText = new ValueDisplay();
						remarksText.setLabel("Megjegyzés");
						remarksText.setValue(event.getExpansion().getModel().getRemarks());
						remarksText.setGrid("s12 m8 l6");
						content.add(remarksText);
					}
					// Hide the expansion elements overlay section.
					// The overlay is retrieved using EowExpand#getOverlay()
					section.css("display", "none");
				}
			}.schedule(100);
		});
	}

	@Override
	public void reConfigColumns() {
		Arrays.fill(colHide, Boolean.FALSE);

		if ((Window.getClientWidth() > 520) && (Window.getClientWidth() <= 800)) {
			colHide[COL_RETURNWHEN] = true;
			tableView.hideColumn(COL_RETURNWHEN, true);
			colHide[COL_RETURNAS] = true;
			tableView.hideColumn(COL_RETURNAS, true);
		} else {
			tableView.hideColumn(COL_RETURNWHEN, false);
			tableView.hideColumn(COL_RETURNAS, false);
		}

		if ((Window.getClientWidth() > 520) && (Window.getClientWidth() <= 1100)) {
			colHide[COL_CREATEDBY] = true;
			tableView.hideColumn(COL_CREATEDBY, true);
		} else {
			tableView.hideColumn(COL_CREATEDBY, false);
		}

		if ((Window.getClientWidth() > 520) && (Window.getClientWidth() <= 1200)) {
			colHide[COL_STATUS] = true;
			tableView.hideColumn(COL_STATUS, true);
		} else {
			tableView.hideColumn(COL_STATUS, false);
		}

		if ((Window.getClientWidth() > 520) && (Window.getClientWidth() <= 1300)) {
			colHide[COL_REMARKS] = true;
			tableView.hideColumn(COL_REMARKS, true);
		} else {
			tableView.hideColumn(COL_REMARKS, false);
		}

		if ((Window.getClientWidth() > 520) && (Window.getClientWidth() <= 1400)) {
			colHide[COL_ROOMTYPE] = true;
			tableView.hideColumn(COL_ROOMTYPE, true);
		} else {
			tableView.hideColumn(COL_ROOMTYPE, false);
		}

		if ((Window.getClientWidth() > 520) && (Window.getClientWidth() <= 1500)) {
			colHide[COL_FLOOR] = true;
			tableView.hideColumn(COL_FLOOR, true);
		} else {
			tableView.hideColumn(COL_FLOOR, false);
		}
		boolean useRowExpansion = !allFalse(colHide);
		logger.info("OooRoomBrowserView().reConfigColumns()->useRowExpansion=" + useRowExpansion);
		tableView.setUseRowExpansion(useRowExpansion);
	}

	private static boolean allFalse (Boolean[] values) {
	    for (Boolean value : values) {
	        if (value)
	            return false;
	    }
	    return true;
	}
	
	private DataColumn<OooRoomDto> createRoomColumn() {
		return new DataColumn<OooRoomDto>((o) -> o.getRoom().getCode(),
				(o1, o2) -> o1.getData().getRoom().compareTo(o2.getData().getRoom()));
	}

	private ActionColumn<OooRoomDto> createStatusColumn() {
		return new ActionColumn<OooRoomDto>() {
			@Override
			protected MaterialIcon getIcon(OooRoomDto object) {
				MaterialIcon icon = new MaterialIcon();
				icon.setWaves(WavesType.DEFAULT);
				icon.setIconType(RoomStatusUtils.getStatusIcon2(object.getRoom().getRoomStatus()));
				icon.setTextColor(RoomStatusUtils.getStatusBgColor(object.getRoom().getRoomStatus()));
				return icon;
			}
		};
	}

	private DataColumn<OooRoomDto> createRoomTypeColumn() {
		return new DataColumn<OooRoomDto>((o) -> o.getRoom().getRoomType().getCode(),
				(o1, o2) -> o1.getData().getRoom().getRoomType().compareTo(o2.getData().getRoom().getRoomType()));
	}

	private DataColumn<OooRoomDto> createFloorColumn() {
		return new DataColumn<OooRoomDto>((o) -> o.getRoom().getFloor(),
				(o1, o2) -> o1.getData().getRoom().getFloor().compareTo(o2.getData().getRoom().getFloor()));
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

	private ActionColumn<OooRoomDto> createReturnWhenColumn2() {
//		return new DataColumn<OooRoomDto>((o) -> cnst.roomStatusMap().get(o.getReturnAs().toString()));
		return new ActionColumn<OooRoomDto>() {
			@Override
			protected MaterialIcon getIcon(OooRoomDto object) {
				MaterialIcon icon = new MaterialIcon();
				icon.setWaves(WavesType.DEFAULT);
				if (object.getReturnWhen().equals(OooReturnWhen.ORW_MORNING)) {
					icon.setIconType(IconType.WB_SUNNY);
					icon.setTextColor(Color.YELLOW);
				} else {
					icon.setIconType(IconType.BRIGHTNESS_3);
					icon.setTextColor(Color.BLUE);
				}
				return icon;
			}
		};
	}

	private ActionColumn<OooRoomDto> createReturnAsColumn() {
//		return new DataColumn<OooRoomDto>((o) -> cnst.roomStatusMap().get(o.getReturnAs().toString()));
		return new ActionColumn<OooRoomDto>() {
			@Override
			protected MaterialIcon getIcon(OooRoomDto object) {
				MaterialIcon icon = new MaterialIcon();
				icon.setWaves(WavesType.DEFAULT);
				icon.setIconType(RoomStatusUtils.getStatusIcon2(object.getReturnAs()));
				icon.setTextColor(RoomStatusUtils.getStatusBgColor(object.getReturnAs()));
				return icon;
			}
		};
	}

	private DataColumn<OooRoomDto> createReturnRemarks() {
		return new DataColumn<OooRoomDto>((o) -> o.getRemarks());
	}

	private DataColumn<OooRoomDto> createCreatedByColumn() {
		return new DataColumn<OooRoomDto>((o) -> o.getCreatedBy().getCode());
	}

	@Override
	public void setData(List<OooRoomDto> data) {
		tableView.setData(data);
	}
}
