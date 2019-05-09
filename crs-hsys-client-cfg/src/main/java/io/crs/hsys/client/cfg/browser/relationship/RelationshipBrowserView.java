/**
 * 
 */
package io.crs.hsys.client.cfg.browser.relationship;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import io.crs.hsys.client.core.browser.AbstractBrowserView;
import io.crs.hsys.client.core.browser.ActionColumn;
import io.crs.hsys.client.core.browser.DataColumn;
import io.crs.hsys.client.core.i18n.CoreMessages;
import io.crs.hsys.shared.dto.profile.RelationshipDto;

/**
 * @author robi
 *
 */
public class RelationshipBrowserView extends ViewWithUiHandlers<RelationshipBrowserUiHandlers>
		implements RelationshipBrowserPresenter.MyView {
	private static Logger logger = Logger.getLogger(RelationshipBrowserView.class.getName());

	private final AbstractBrowserView<RelationshipDto> table;

	private final CoreMessages i18nCore;

	/**
	* 
	*/
	@Inject
	RelationshipBrowserView(AbstractBrowserView<RelationshipDto> table, CoreMessages i18nCore) {
		logger.info("RelationshipBrowserView()");
		initWidget(table);

		this.table = table;
		this.i18nCore = i18nCore;

		bindSlot(RelationshipBrowserPresenter.SLOT_FILTER, table.getFilterPanel());
		bindSlot(RelationshipBrowserPresenter.SLOT_EDITOR, table.getEditorPanel());

		initTable();
	}

	private void initTable() {

		table.setTableTitle(i18nCore.relationshipBrowserTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		table.getDeleteIcon().addClickHandler(e -> {
			getUiHandlers().delete(table.getSelectedItems());
		});

		// Forward Column
		table.addColumn(
				new DataColumn<RelationshipDto>((object) -> object.getForward(),
						(o1, o2) -> o1.getData().getForward().compareToIgnoreCase(o2.getData().getForward())),
				i18nCore.relationshipBrowserColForward());

		// Reverse Column
		table.addColumn(
				new DataColumn<RelationshipDto>((object) -> object.getReverse(),
						(o1, o2) -> o1.getData().getReverse().compareToIgnoreCase(o2.getData().getReverse())),
				i18nCore.relationshipBrowserColReverse());

		// Active Column
		table.addColumn(new DataColumn<RelationshipDto>((object) -> {
			if (object.getActive()) {
				return i18nCore.comActive();
			} else {
				return i18nCore.comInactive();
			}
		}), i18nCore.comActive());

		// Edit Column
		table.addColumn(new ActionColumn<RelationshipDto>((object) -> getUiHandlers().edit(object)));
	}

	@Override
	public void setData(List<RelationshipDto> data) {
		table.setData(data);
	}
}
