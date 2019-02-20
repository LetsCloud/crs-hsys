/**
 * 
 */
package io.crs.hsys.client.kip.browser.hktaskgroup;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.browser.AbstractBrowserView;
import io.crs.hsys.client.core.ui.browser.ActionColumn;
import io.crs.hsys.client.core.ui.browser.DataColumn;
import io.crs.hsys.shared.dto.task.TaskGroupDto;

/**
 * @author robi
 *
 */
public class HkTaskGroupBrowserView extends ViewWithUiHandlers<HkTaskGroupBrowserUiHandlers>
		implements HkTaskGroupBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(HkTaskGroupBrowserView.class.getName());

	private final AbstractBrowserView<TaskGroupDto> table;

	private final CoreMessages i18nCore;
	private final CoreConstants cnstCore;

	/**
	* 
	*/
	@Inject
	HkTaskGroupBrowserView(AbstractBrowserView<TaskGroupDto> table, CoreMessages i18nCore, CoreConstants cnstCore) {
		logger.info("HkTaskGroupBrowserView()");
		initWidget(table);

		this.table = table;
		this.i18nCore = i18nCore;
		this.cnstCore = cnstCore;

		bindSlot(HkTaskGroupBrowserPresenter.SLOT_FILTER, table.getFilterPanel());

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
				new DataColumn<TaskGroupDto>((object) -> object.getCode(),
						(o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())),
				i18nCore.roomTypesTableCode());

		// Description Column
		table.addColumn(
				new DataColumn<TaskGroupDto>((object) -> object.getDescription(),
						(o1, o2) -> o1.getData().getDescription().compareToIgnoreCase(o2.getData().getDescription())),
				i18nCore.roomTypesTableName());

		// Active Column
		table.addColumn(new DataColumn<TaskGroupDto>((object) -> {
			if (object.getActive()) {
				return i18nCore.roomTypesTableActive();
			} else {
				return i18nCore.roomTypesTableInactive();
			}
		}), i18nCore.roomTypesTableActive());

		// Edit Column
		table.addColumn(new ActionColumn<TaskGroupDto>((object) -> getUiHandlers().edit(object)));
	}

	@Override
	public void setData(List<TaskGroupDto> data) {
		logger.info("RoomTypeTableView().setData()");
		table.setData(data);
	}
}
