/**
 * 
 */
package io.crs.hsys.client.kip.dashboard.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialIcon;

/**
 * @author robi
 *
 */
public class MultiMeasureCard2 extends Composite {

	private static MultiMeasureCard2UiBinder uiBinder = GWT.create(MultiMeasureCard2UiBinder.class);

	interface MultiMeasureCard2UiBinder extends UiBinder<Widget, MultiMeasureCard2> {
	}

	@UiField
	MaterialIcon icon, icon2;

	@UiField
	Label title, title2, sumValue, sumValue2;

	/**
	 */
	public MultiMeasureCard2() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setTitleText(String text) {
		title.setText(text);
	}

	public void setIconType(IconType iconType) {
		icon.setIconType(iconType);
	}

	public void setIconColor(Color iconColor) {
		icon.setIconColor(iconColor);
	}

	public void setSumValue(String text) {
		sumValue.setText(text);
	}

	public void setTitle2Text(String text) {
		title2.setText(text);
	}

	public void setIcon2Type(IconType iconType) {
		icon2.setIconType(iconType);
	}

	public void setIcon2Color(Color iconColor) {
		icon2.setIconColor(iconColor);
	}

	public void setSumValue2(String text) {
		sumValue2.setText(text);
	}

}
