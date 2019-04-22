/**
 * 
 */
package io.crs.hsys.client.core.browser.usergroup;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.client.core.browser.AbstractBrowserView;
import io.crs.hsys.client.core.browser.ActionColumn;
import io.crs.hsys.client.core.browser.DataColumn;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
public class UserGroupBrowserView extends ViewWithUiHandlers<UserGroupBrowserUiHandlers>
		implements UserGroupBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(UserGroupBrowserView.class.getName());

	private final AbstractBrowserView<UserGroupDto> table;

	private final CoreMessages i18nCore;

	/**
	 * 
	 */
	@Inject
	UserGroupBrowserView(AbstractBrowserView<UserGroupDto> table, CoreMessages i18nCore) {
		logger.info("UserGroupTableView()");
		initWidget(table);

		this.table = table;
		this.i18nCore = i18nCore;

		bindSlot(UserGroupBrowserPresenter.SLOT_EDITOR, table.getEditorPanel());

		initTable();
	}

	private void initTable() {

		table.setTableTitle(i18nCore.userGroupBrowserTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		// Name Column
		table.addColumn(
				new DataColumn<UserGroupDto>((object) -> object.getName(),
						(o1, o2) -> o1.getData().getName().compareToIgnoreCase(o2.getData().getName())),
				i18nCore.userGroupBrowserName());

		// Edit Column
		table.addColumn(new ActionColumn<UserGroupDto>((object) -> getUiHandlers().edit(object)));
	}

	@Override
	public void setData(List<UserGroupDto> data) {
		table.setData(data);
	}
}
