/**
 * 
 */
package io.crs.hsys.client.cfg.browser.marketgroup;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.client.core.browser.AbstractBrowserView;
import io.crs.hsys.client.core.browser.ActionColumn;
import io.crs.hsys.client.core.browser.DataColumn;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.dto.hotel.MarketGroupDto;

/**
 * @author robi
 *
 */
public class MarketGroupBrowserView extends ViewWithUiHandlers<MarketGroupBrowserUiHandlers>
		implements MarketGroupBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(MarketGroupBrowserView.class.getName());

	private final AbstractBrowserView<MarketGroupDto> table;

	private final CoreMessages i18nCore;

	/**
	* 
	*/
	@Inject
	MarketGroupBrowserView(AbstractBrowserView<MarketGroupDto> table, CoreMessages i18nCore) {
		logger.info("MarketGroupTableView()");
		initWidget(table);

		this.table = table;
		this.i18nCore = i18nCore;

		bindSlot(MarketGroupBrowserPresenter.SLOT_FILTER, table.getFilterPanel());
		bindSlot(MarketGroupBrowserPresenter.SLOT_EDITOR, table.getEditorPanel());

		initTable();
	}

	private void initTable() {

		table.setTableTitle(i18nCore.marketGroupBrowserTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		table.getDeleteIcon().addClickHandler(e -> {
			getUiHandlers().delete(table.getSelectedItems());
		});

// Code Column
		table.addColumn(
				new DataColumn<MarketGroupDto>((object) -> object.getCode(),
						(o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())),
				i18nCore.roomTypesTableCode());

// Name Column
		table.addColumn(
				new DataColumn<MarketGroupDto>((object) -> object.getDescription(),
						(o1, o2) -> o1.getData().getDescription().compareToIgnoreCase(o2.getData().getDescription())),
				i18nCore.roomTypesTableName());

// Active Column
		table.addColumn(new DataColumn<MarketGroupDto>((object) -> {
			if (object.getActive()) {
				return i18nCore.roomTypesTableActive();
			} else {
				return i18nCore.roomTypesTableInactive();
			}
		}), i18nCore.roomTypesTableActive());

// Edit Column
		table.addColumn(new ActionColumn<MarketGroupDto>((object) -> getUiHandlers().edit(object)));
	}

	@Override
	public void setData(List<MarketGroupDto> data) {
		logger.info("MarketGroupTableView().setData()");
		table.setData(data);
	}
}
