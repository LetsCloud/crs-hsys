/**
 * 
 */
package io.crs.hsys.client.kip.browser.oooroom;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

import gwt.material.design.client.ui.MaterialPanel;

/**
 * @author robi
 *
 */
public class ValueDisplay extends Composite {
	
	MaterialPanel panel = new MaterialPanel();
	Label valueLabel = new Label();
	Label valueText = new Label();
	

	public ValueDisplay() {
		initWidget(panel);
		valueLabel.getElement().getStyle().setFontSize(10, Unit.PX);
		valueLabel.getElement().getStyle().setColor("#9e9e9e");
		panel.add(valueLabel);
		valueText.getElement().getStyle().setFontSize(14, Unit.PX);
		valueText.getElement().getStyle().setColor("#000");
		panel.add(valueText);
	}
	
	public void setLabel(String text) {
		valueLabel.setText(text);
	}
	
	public void setValue(String text) {
		valueText.setText(text);
	}
	
	public void setGrid(String grid) {
		panel.setGrid(grid);
	}
}
