/**
 * 
 */
package io.crs.hsys.client.fro.browser.ratecode;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.client.core.browser.AbstractBrowserView;
import io.crs.hsys.client.core.browser.ActionColumn;
import io.crs.hsys.client.core.browser.DataColumn;
import io.crs.hsys.client.core.browser.roomtype.RoomTypeBrowserPresenter;
import io.crs.hsys.client.core.i18n.CoreConstants;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.fro.i18n.FroMessages;
import io.crs.hsys.shared.dto.hotel.RateCodeDto;

/**
 * @author robi
 *
 */
public class RateCodeBrowserView extends ViewWithUiHandlers<RateCodeBrowserUiHandlers>
		implements RateCodeBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(RateCodeBrowserView.class.getName());

	private final AbstractBrowserView<RateCodeDto> table;

	private final FroMessages i18n;
	private final CoreMessages i18nCore;
	private final CoreConstants cnstCore;

	/**
	* 
	*/
	@Inject
	RateCodeBrowserView(AbstractBrowserView<RateCodeDto> table, FroMessages i18n, CoreMessages i18nCore, CoreConstants cnstCore) {
		logger.info("RateCodeBrowserView()");
		initWidget(table);

		this.table = table;
		this.i18n = i18n;
		this.i18nCore = i18nCore;
		this.cnstCore = cnstCore;

		bindSlot(RoomTypeBrowserPresenter.SLOT_FILTER, table.getFilterPanel());

		initTable();
	}

	private void initTable() {

		table.setTableTitle(i18n.rateCodeBrowserTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		table.getDeleteIcon().addClickHandler(e -> {
			getUiHandlers().delete(table.getSelectedItems());
		});

		// Code Column
		table.addColumn(
				new DataColumn<RateCodeDto>((object) -> object.getCode(),
						(o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())),
				i18nCore.roomTypesTableCode());

		// Description Column
		table.addColumn(
				new DataColumn<RateCodeDto>((object) -> object.getDescription(),
						(o1, o2) -> o1.getData().getDescription().compareToIgnoreCase(o2.getData().getDescription())),
				i18nCore.roomTypesTableName());

		// Edit Column
		table.addColumn(new ActionColumn<RateCodeDto>((object) -> getUiHandlers().edit(object)));
	}

	@Override
	public void setData(List<RateCodeDto> data) {
		logger.info("RoomTypeTableView().setData()");
		table.setData(data);
	}
}
