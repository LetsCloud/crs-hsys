/**
 * 
 */
package io.crs.hsys.client.cfg.browser.appuser;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.client.core.ui.browser.AbstractBrowserView;
import io.crs.hsys.client.core.ui.browser.ActionColumn;
import io.crs.hsys.client.core.ui.browser.DataColumn;
import io.crs.hsys.shared.api.AppUserResource;
import io.crs.hsys.shared.dto.common.AppUserDto;

/**
 * @author CR
 *
 */
public class AppUserBrowserView extends ViewWithUiHandlers<AppUserBrowserUiHandlers>
		implements AppUserBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(AppUserBrowserView.class.getName());

	private final AbstractBrowserView<AppUserDto> table;

	private final CoreMessages i18n;

	/**
	 * 
	 */
	@Inject
	AppUserBrowserView(AbstractBrowserView<AppUserDto> table, ResourceDelegate<AppUserResource> usersDelegate,
			CoreMessages i18n) {
		logger.info("AppUserBrowserView()");
		initWidget(table);

		this.table = table;
		this.i18n = i18n;

		initTable();
	}

	private void initTable() {
		table.setTableTitle(i18n.userBrowserTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		table.getDeleteIcon().addClickHandler(e -> {
			getUiHandlers().delete(table.getSelected());
		});

		// Code Column
		table.addColumn(
				new DataColumn<AppUserDto>((object) -> object.getCode(),
						(o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())),
				i18n.userEditorCode());

		// Name Column
		table.addColumn(
				new DataColumn<AppUserDto>((object) -> object.getName(),
						(o1, o2) -> o1.getData().getName().compareToIgnoreCase(o2.getData().getName())),
				i18n.userEditorName());

		// Title Column
		table.addColumn(new DataColumn<AppUserDto>((object) -> object.getTitle()), i18n.userEditorTitle());

		// Email Column
		table.addColumn(new DataColumn<AppUserDto>((object) -> object.getEmailAddress()), i18n.usersTableEmail());

		// Edit Column
		table.addColumn(new ActionColumn<AppUserDto>((object) -> getUiHandlers().edit(object)));
	}

	@Override
	public void setData(List<AppUserDto> data) {
		table.setData(data);
	}
}
