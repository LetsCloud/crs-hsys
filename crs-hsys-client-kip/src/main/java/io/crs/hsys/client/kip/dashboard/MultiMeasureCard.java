/**
 * 
 */
package io.crs.hsys.client.kip.dashboard;

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
public class MultiMeasureCard extends Composite {

	private static MeasureCardUiBinder uiBinder = GWT.create(MeasureCardUiBinder.class);

	interface MeasureCardUiBinder extends UiBinder<Widget, MultiMeasureCard> {
	}

	/**
	 */

	@UiField
	MaterialIcon icon;

	@UiField
	Label title, sumValue, partTitle1, partValue1, partTitle2, partValue2, partTitle3, partValue3;

	public MultiMeasureCard() {
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

	public void setPartTitle1(String text) {
		partTitle1.setText(text);
	}

	public void setPartValue1(String text) {
		partValue1.setText(text);
	}

	public void setPartTitle2(String text) {
		partTitle2.setText(text);
	}

	public void setPartValue2(String text) {
		partValue2.setText(text);
	}

	public void setPartTitle3(String text) {
		partTitle3.setText(text);
	}

	public void setPartValue3(String text) {
		partValue3.setText(text);
	}
}
