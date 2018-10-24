/**
 * 
 */
package io.crs.hsys.client.cfg.browser.room;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.client.cfg.browser.AbstractBrowserView;
import io.crs.hsys.client.cfg.browser.ActionColumn;
import io.crs.hsys.client.cfg.browser.DataColumn;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.dto.hotel.RoomDto;

/**
 * @author robi
 *
 */
public class RoomBrowserView extends ViewWithUiHandlers<RoomBrowserUiHandlers> implements RoomBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(RoomBrowserView.class.getName());

	private final AbstractBrowserView<RoomDto> table;

	private final CoreMessages i18nCore;

	/**
	* 
	*/
	@Inject
	RoomBrowserView(AbstractBrowserView<RoomDto> table, CoreMessages i18nCore) {
		logger.info("RoomBrowserView()");
		initWidget(table);

		this.table = table;
		this.i18nCore = i18nCore;

		bindSlot(RoomBrowserPresenter.SLOT_FILTER, table.getFilterPanel());

		initTable();
	}

	private void initTable() {

		table.setTableTitle(i18nCore.roomBrowserTitle());

		table.getAddButton().addClickHandler(e -> getUiHandlers().addNew());
		table.getDeleteIcon().addClickHandler(e -> getUiHandlers().delete(table.getSelected()));

		// Code Column
		table.addColumn(
				new DataColumn<RoomDto>((object) -> object.getCode(),
						(o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())),
				i18nCore.roomsTableCode());

		// Floor Column
		table.addColumn(
				new DataColumn<RoomDto>((object) -> object.getFloor(),
						(o1, o2) -> o1.getData().getFloor().compareToIgnoreCase(o2.getData().getFloor())),
				i18nCore.roomsTableFloor());

		// Type Column
		table.addColumn(new DataColumn<RoomDto>((object) -> {
			if (object.getRoomType() != null) {
				return object.getRoomType().getCode();
			} else {
				return null;
			}
		}), i18nCore.roomsTableType());

		// Edit Column
		table.addColumn(new ActionColumn<RoomDto>((object) -> getUiHandlers().edit(object)));
	}

	@Override
	public void setData(List<RoomDto> data) {
		logger.info("RoomTypeTableView().setData()");
		table.setData(data);
	}
}
