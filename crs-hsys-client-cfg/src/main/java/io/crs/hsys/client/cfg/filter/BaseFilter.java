/**
 * 
 */
package io.crs.hsys.client.cfg.filter;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.web.bindery.event.shared.EventBus;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.ui.MaterialChip;

/**
 * @author robi
 *
 */
public abstract class BaseFilter extends Composite {

	protected Panel chipPanel;
	protected MaterialChip chip = new MaterialChip();
	protected String chipLabel = "";
	protected EventBus eventBus;

	protected BaseFilter(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	protected abstract MaterialWidget getFilterWidget();

	public void setChipPanel(Panel panel) {
		this.chipPanel = panel;
	}

	public String getChipLabel() {
		return chipLabel;
	}

	public void setChipLabel(String label) {
		chipLabel = label;
	}

	public void setGrid(String grid) {
		getFilterWidget().setGrid(grid);
	}

	protected void setChipText(String text) {
		if (chip.isAttached()) {
			if ((text == null) || (text.isEmpty())) {
				chipPanel.remove(chip);
				return;
			}
			chip.setText(text);
		} else {
			if ((text != null) && (!text.isEmpty())) {
				chip.setText(text);
				chipPanel.add(chip);
			}
		}
	}

}
