/**
 * 
 */
package io.crs.hsys.client.cfg.browser.profilegroup;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.client.core.browser.AbstractBrowserView;
import io.crs.hsys.client.core.browser.ActionColumn;
import io.crs.hsys.client.core.browser.DataColumn;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.dto.profile.ProfileGroupDto;

/**
 * @author robi
 *
 */
public class ProfileGroupBrowserView extends ViewWithUiHandlers<ProfileGroupBrowserUiHandlers>
		implements ProfileGroupBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(ProfileGroupBrowserView.class.getName());

	private final AbstractBrowserView<ProfileGroupDto> table;

	private final CoreMessages i18nCore;

	/**
	* 
	*/
	@Inject
	ProfileGroupBrowserView(AbstractBrowserView<ProfileGroupDto> table, CoreMessages i18nCore) {
		logger.info("ProfielGroupBrowserView()");
		initWidget(table);

		this.table = table;
		this.i18nCore = i18nCore;

		bindSlot(ProfileGroupBrowserPresenter.SLOT_FILTER, table.getFilterPanel());
		bindSlot(ProfileGroupBrowserPresenter.SLOT_EDITOR, table.getEditorPanel());

		initTable();
	}

	private void initTable() {

		table.setTableTitle(i18nCore.profileGroupBrowserTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		table.getDeleteIcon().addClickHandler(e -> {
			getUiHandlers().delete(table.getSelected());
		});

		// Code Column
		table.addColumn(
				new DataColumn<ProfileGroupDto>((object) -> object.getCode(),
						(o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())),
				i18nCore.profileGroupBrowserCode());

		// Name Column
		table.addColumn(
				new DataColumn<ProfileGroupDto>((object) -> object.getDescription(),
						(o1, o2) -> o1.getData().getDescription().compareToIgnoreCase(o2.getData().getDescription())),
				i18nCore.profileGroupBrowserDescription());

		// Active Column
		table.addColumn(new DataColumn<ProfileGroupDto>((object) -> {
			if (object.getActive()) {
				return i18nCore.roomTypesTableActive();
			} else {
				return i18nCore.roomTypesTableInactive();
			}
		}), i18nCore.profileGroupBrowserActive());

		// Edit Column
		table.addColumn(new ActionColumn<ProfileGroupDto>((object) -> getUiHandlers().edit(object)));
	}

	@Override
	public void setData(List<ProfileGroupDto> data) {
		table.setData(data);
	}
}
