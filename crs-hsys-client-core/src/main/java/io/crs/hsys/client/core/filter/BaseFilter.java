/**
 * 
 */
package io.crs.hsys.client.core.filter;

import java.util.logging.Logger;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.web.bindery.event.shared.EventBus;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialChip;

/**
 * @author robi
 *
 */
public abstract class BaseFilter extends Composite {
	private static Logger logger = Logger.getLogger(BaseFilter.class.getName());

	protected Panel chipPanel;
	protected MaterialChip chip = new MaterialChip();
	protected String chipLabel = "";
	protected EventBus eventBus;

	private Boolean chipEnabled = true;

	protected BaseFilter(EventBus eventBus) {
		logger.info("BaseFilter()");
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
		logger.info("BaseFilter().setChipText()->text=" + text);
		if (chip.isAttached()) {
			logger.info("BaseFilter().setChipText()->(chip.isAttached())");
			if ((text == null) || (text.isEmpty()) || (!chipEnabled)) {
				logger.info("BaseFilter().setChipText()->((text == null) || (text.isEmpty()) || (!chipEnabled))");
				chipPanel.remove(chip);
				return;
			}
			chip.setText(text);
		} else {
			logger.info("BaseFilter().setChipText()->(chip.isNotAttached())");
			if (!chipEnabled)
				return;
			if ((text != null) && (!text.isEmpty())) {
				chip.setText(text);
				chipPanel.add(chip);
			}
		}
	}

	public void setChipEnabled(Boolean chipEnabled) {
		this.chipEnabled = chipEnabled;
	}

	public void setChipLetter(String letter) {
		chip.setLetter(letter);
	}

	public void setChipLetterColor(Color color) {
		chip.setLetterColor(color);
	}

	public void setChipLetterBackgroundColor(Color color) {
		chip.setLetterBackgroundColor(color);
	}

	public void setChipIconType(IconType type) {
		chip.setIconType(type);
		chip.getIcon().setMarginRight(0);
		chip.getIcon().setPaddingLeft(0);
		chip.setPaddingLeft(0);
	}

	public void setChipIconPosition(IconPosition position) {
		chip.setIconPosition(position);
	}

	public void setChipIconColor(Color color) {
		chip.setIconColor(color);
	}

	public void setChipIconPrefix(boolean prefix) {
		chip.setIconPrefix(prefix);
	}

	public void setChipIconSize(IconSize size) {
		chip.setIconSize(size);
	}

	public void setChipIconFontSize(Double size, Unit unit) {
		chip.setIconFontSize(size, unit);
	}

}
