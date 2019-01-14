/**
 * 
 */
package io.crs.hsys.client.fro.dashboard.widget;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Panel;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.constants.TableCssName;
import gwt.material.design.client.ui.table.AbstractTableScaffolding;
import gwt.material.design.client.ui.table.Table;
import gwt.material.design.client.ui.table.XScrollPanel;

/**
 * @author robi
 *
 */
public class ReservationTableScaffolding extends AbstractTableScaffolding {
    @Override
    public MaterialWidget createTableBody() {
        MaterialWidget tableBody = new MaterialWidget(DOM.createDiv());
        tableBody.addStyleName(TableCssName.TABLE_BODY);
        return tableBody;
    }

    @Override
    public MaterialWidget createTopPanel() {
        MaterialWidget topPanel = new MaterialWidget(DOM.createDiv());
        topPanel.addStyleName(TableCssName.TOP_PANEL);
        return topPanel;
    }

    @Override
    public MaterialWidget createInfoPanel() {
        MaterialWidget infoPanel = new MaterialWidget(DOM.createDiv());
        infoPanel.addStyleName(TableCssName.INFO_PANEL);
        return infoPanel;
    }

    @Override
    public MaterialWidget createToolPanel() {
        MaterialWidget toolPanel = new MaterialWidget(DOM.createDiv());
        toolPanel.addStyleName(TableCssName.TOOL_PANEL);
        return toolPanel;
    }

    @Override
    protected Table createTable() {
        Table table = new Table();
        table.addStyleName(TableCssName.TABLE);
        return table;
    }

    @Override
    protected XScrollPanel createXScrollPanel(Panel container) {
        return new XScrollPanel(container);
    }
}
