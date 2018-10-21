/**
 * 
 */
package io.crs.hsys.client.cfg.browser.roomtype;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.HideOn;

import io.crs.hsys.client.cfg.browser.AbstractBrowserView;
import io.crs.hsys.client.cfg.browser.ActionColumn;
import io.crs.hsys.client.cfg.browser.DataColumn;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.constans.InventoryType;
import io.crs.hsys.shared.dto.hotel.RoomTypeDto;

/**
 * @author robi
 *
 */
public class RoomTypeBrowserView extends ViewWithUiHandlers<RoomTypeBrowserUiHandlers>
		implements RoomTypeBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(RoomTypeBrowserView.class.getName());

	private final AbstractBrowserView<RoomTypeDto> table;

	private final CoreMessages i18nCore;
	private final CoreConstants cnstCore;

	/**
	* 
	*/
	@Inject
	RoomTypeBrowserView(AbstractBrowserView<RoomTypeDto> table, CoreMessages i18nCore, CoreConstants cnstCore) {
		logger.info("RoomTypeTableView()");
		initWidget(table);

		this.table = table;
		this.i18nCore = i18nCore;
		this.cnstCore = cnstCore;

		bindSlot(RoomTypeBrowserPresenter.SLOT_FILTER, table.getFilterPanel());

		initTable();
	}

	private void initTable() {

		table.setTableTitle(i18nCore.roomTypeBrowserTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		table.getDeleteIcon().addClickHandler(e -> {
			getUiHandlers().delete(table.getSelected());
		});

		// Code Column
		table.addColumn(
				new DataColumn<RoomTypeDto>((object) -> object.getCode(),
						(o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())),
				i18nCore.roomTypesTableCode());

		// Name Column
		table.addColumn(
				new DataColumn<RoomTypeDto>((object) -> object.getName(),
						(o1, o2) -> o1.getData().getName().compareToIgnoreCase(o2.getData().getName())),
				i18nCore.roomTypesTableName());

		// InventoryType Column
		table.addColumn(
				new DataColumn<RoomTypeDto>((object) -> getInventoryTypeText(object.getInventoryType()),
						(o1, o2) -> o1.getData().getInventoryType().toString()
								.compareToIgnoreCase(o2.getData().getInventoryType().toString()),
						HideOn.HIDE_ON_MED_DOWN),
				i18nCore.roomTypesTableInventoryType());

		// NumberOfRooms Column
		table.addColumn(new DataColumn<RoomTypeDto>((object) -> object.getNumberOfRooms().toString()),
				i18nCore.roomTypesTableNumberOfRooms());

		// Active Column
		table.addColumn(new DataColumn<RoomTypeDto>((object) -> {
			if (object.getActive()) {
				return i18nCore.roomTypesTableActive();
			} else {
				return i18nCore.roomTypesTableInactive();
			}
		}), i18nCore.roomTypesTableActive());

		// Edit Column
		table.addColumn(new ActionColumn<RoomTypeDto>((object) -> getUiHandlers().edit(object)));
	}

	private String getInventoryTypeText(InventoryType type) {
		if (type == null)
			return "";
		return cnstCore.inventoryTypeMap().get(type.toString());
	}

	@Override
	public void setData(List<RoomTypeDto> data) {
		logger.info("RoomTypeTableView().setData()");
		table.setData(data);
	}
}
