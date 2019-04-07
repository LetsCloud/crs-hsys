/**
 * 
 */
package io.crs.hsys.client.cfg.browser.qtnstatus;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.client.core.browser.AbstractBrowserView;
import io.crs.hsys.client.core.browser.ActionColumn;
import io.crs.hsys.client.core.browser.DataColumn;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.dto.doc.QuotationStatusDto;

/**
 * @author robi
 *
 */
public class QuotationStatusBrowserView extends ViewWithUiHandlers<QuotationStatusBrowserUiHandlers>
		implements QuotationStatusBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(QuotationStatusBrowserView.class.getName());

	private final AbstractBrowserView<QuotationStatusDto> table;

	private final CoreMessages i18nCore;

	/**
	* 
	*/
	@Inject
	QuotationStatusBrowserView(AbstractBrowserView<QuotationStatusDto> table, CoreMessages i18nCore) {
		logger.info("QuotationStatusTableView()");
		initWidget(table);

		this.table = table;
		this.i18nCore = i18nCore;

		bindSlot(QuotationStatusBrowserPresenter.SLOT_FILTER, table.getFilterPanel());
		bindSlot(QuotationStatusBrowserPresenter.SLOT_EDITOR, table.getEditorPanel());

		initTable();
	}

	private void initTable() {
		table.setTableTitle(i18nCore.quotationStatusBrowserTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		table.getDeleteIcon().addClickHandler(e -> {
			getUiHandlers().delete(table.getSelected());
		});

		// Code Column
		table.addColumn(
				new DataColumn<QuotationStatusDto>((object) -> object.getCode(),
						(o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())),
				i18nCore.quotationStatusBrowserCode());

		// Description Column
		table.addColumn(
				new DataColumn<QuotationStatusDto>((object) -> object.getDescription(),
						(o1, o2) -> o1.getData().getDescription().compareToIgnoreCase(o2.getData().getDescription())),
				i18nCore.quotationStatusBrowserDescription());

		//Active Column
		table.addColumn(new DataColumn<QuotationStatusDto>((object) -> {
			if (object.getActive()) {
				return i18nCore.comActive();
			} else {
				return i18nCore.comInactive();
			}
		}), i18nCore.comActive());

		//Edit Column
		table.addColumn(new ActionColumn<QuotationStatusDto>((object) -> getUiHandlers().edit(object)));
	}

	@Override
	public void setData(List<QuotationStatusDto> data) {
		table.setData(data);
	}
}
