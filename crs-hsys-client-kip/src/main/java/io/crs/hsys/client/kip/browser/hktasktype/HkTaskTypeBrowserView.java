/**
 * 
 */
package io.crs.hsys.client.kip.browser.hktasktype;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.client.core.browser.room.RoomBrowserPresenter;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.browser.AbstractBrowserView;
import io.crs.hsys.client.core.ui.browser.ActionColumn;
import io.crs.hsys.client.core.ui.browser.DataColumn;
import io.crs.hsys.shared.dto.task.TaskTypeDto;

/**
 * @author robi
 *
 */
public class HkTaskTypeBrowserView extends ViewWithUiHandlers<HkTaskTypeBrowserUiHandlers>
		implements HkTaskTypeBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(HkTaskTypeBrowserView.class.getName());

	private final AbstractBrowserView<TaskTypeDto> table;

	private final CoreMessages i18nCore;

	/**
	* 
	*/
	@Inject
	HkTaskTypeBrowserView(AbstractBrowserView<TaskTypeDto> table, CoreMessages i18nCore) {
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
				new DataColumn<TaskTypeDto>((object) -> object.getCode(),
						(o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())),
				i18nCore.roomsTableCode());

		// Description Column
		table.addColumn(
				new DataColumn<TaskTypeDto>((object) -> object.getDescription(),
						(o1, o2) -> o1.getData().getDescription().compareToIgnoreCase(o2.getData().getDescription())),
				i18nCore.roomsTableFloor());

		// Type Column
		table.addColumn(new DataColumn<TaskTypeDto>((object) -> {
			if (object.getTaskGroup() != null) {
				return object.getTaskGroup().getCode();
			} else {
				return null;
			}
		}), i18nCore.roomsTableType());

		// Edit Column
		table.addColumn(new ActionColumn<TaskTypeDto>((object) -> getUiHandlers().edit(object)));
	}

	@Override
	public void setData(List<TaskTypeDto> data) {
		logger.info("RoomTypeTableView().setData()");
		table.setData(data);
	}
}
