/**
 * 
 */
package io.crs.hsys.client.fro.reservation.room;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;

/**
 * @author CR
 *
 */
public class ValueDisplay extends Composite {

	private static ValueDisplayUiBinder uiBinder = GWT.create(ValueDisplayUiBinder.class);

	interface ValueDisplayUiBinder extends UiBinder<Widget, ValueDisplay> {
	}

	@UiField
	MaterialPanel panel;

	@UiField
	FlowPanel badgePanel;

	@UiField
	MaterialIcon icon;

	@UiField
	MaterialLabel label, title, subTitle;

	/**
	 * 
	 */
	public ValueDisplay() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setGrid(String grid) {
		panel.setGrid(grid);
	}

	public void setIconType(IconType iconType) {
		icon.setIconType(iconType);
	}

	public void setIconColor(Color color) {
		icon.setIconColor(color);
	}
	
	public void setLabel(String text) {
		label.setText(text);
	}

	public void setText(String text) {
		title.setText(text);
	}

	public void setSubText(String text) {
		subTitle.setText(text);
	}
	
	public void addBadge(Widget widget) {
		badgePanel.add(widget);
	}
}
