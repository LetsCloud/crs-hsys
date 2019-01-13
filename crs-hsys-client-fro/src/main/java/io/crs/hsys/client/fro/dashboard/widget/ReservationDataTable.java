/**
 * 
 */
package io.crs.hsys.client.fro.dashboard.widget;

import com.google.gwt.user.client.ui.Panel;

import gwt.material.design.client.constants.Display;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.table.MaterialDataTable;
import gwt.material.design.incubator.client.search.InlineSearch;
import gwt.material.design.incubator.client.search.constants.Theme;

/**
 * @author robi
 *
 */
public class ReservationDataTable<T> extends MaterialDataTable<T> {

	@Override
	protected void build() {
		Panel infoPanel = scaffolding.getInfoPanel();
		Panel toolPanel = scaffolding.getToolPanel();

//		MaterialIcon tableIcon = new MaterialIcon(IconType.VIEW_LIST);
//		infoPanel.add(tableIcon);

		InlineSearch search = new InlineSearch();
		search.setTheme(Theme.LIGHT);
		search.setWidth("50%");
		search.setPlaceholder("Keresés");
//		search.setDisplay(Display.INLINE);

		toolPanel.add(search);
	}
}
