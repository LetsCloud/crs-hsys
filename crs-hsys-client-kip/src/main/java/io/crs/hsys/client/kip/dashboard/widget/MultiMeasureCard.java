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

	private void setPartLabel(Label partValue, String text) {
		if (text.isEmpty()) {
			text = "*";
			partValue.getElement().getStyle().setColor("#f5f5f5");
		}
		partValue.setText(text);
	}

	public void setPartTitle1(String text) {
		setPartLabel(partTitle1, text);
	}
	
	public void setPartValue1(String text) {
		setPartLabel(partValue1, text);
	}

	public void setPartTitle2(String text) {
		setPartLabel(partTitle2, text);
	}

	public void setPartValue2(String text) {
		setPartLabel(partValue2, text);
	}

	public void setPartTitle3(String text) {
		setPartLabel(partTitle3, text);
	}

	public void setPartValue3(String text) {
		setPartLabel(partValue3, text);
	}
}
