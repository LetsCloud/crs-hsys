/**
 * 
 */
package io.crs.hsys.client.admin.browser.firebase;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.browser.AbstractBrowserView;
import io.crs.hsys.client.core.ui.browser.ActionColumn;
import io.crs.hsys.client.core.ui.browser.DataColumn;
import io.crs.hsys.shared.dto.GlobalConfigDto;

/**
 * @author robi
 *
 */
public class FirebaseBrowserView extends ViewWithUiHandlers<FirebaseBrowserUiHandlers>
		implements FirebaseBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(FirebaseBrowserView.class.getName());

	private final AbstractBrowserView<GlobalConfigDto> table;

	private final CoreMessages i18nCore;

	/**
	* 
	*/
	@Inject
	FirebaseBrowserView(AbstractBrowserView<GlobalConfigDto> table, CoreMessages i18nCore) {
		logger.info("FirebaseBrowserView()");
		initWidget(table);

		this.table = table;
		this.i18nCore = i18nCore;

		bindSlot(FirebaseBrowserPresenter.SLOT_FILTER, table.getFilterPanel());
		bindSlot(FirebaseBrowserPresenter.SLOT_EDITOR, table.getEditorPanel());

		initTable();
	}

	private void initTable() {

		table.setTableTitle(i18nCore.marketGroupBrowserTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		table.getDeleteIcon().addClickHandler(e -> {
			getUiHandlers().delete(table.getSelected());
		});

		// Code Column
		table.addColumn(
				new DataColumn<GlobalConfigDto>((object) -> object.getKey(),
						(o1, o2) -> o1.getData().getKey().compareToIgnoreCase(o2.getData().getKey())),
				i18nCore.roomTypesTableCode());

		// Name Column
		table.addColumn(
				new DataColumn<GlobalConfigDto>((object) -> object.getValue(),
						(o1, o2) -> o1.getData().getValue().compareToIgnoreCase(o2.getData().getValue())),
				i18nCore.roomTypesTableName());

		// Edit Column
		table.addColumn(new ActionColumn<GlobalConfigDto>((object) -> getUiHandlers().edit(object)));
	}

	@Override
	public void setData(List<GlobalConfigDto> data) {
		logger.info("FirebaseBrowserView().setData()");
		table.setData(data);
	}
}
