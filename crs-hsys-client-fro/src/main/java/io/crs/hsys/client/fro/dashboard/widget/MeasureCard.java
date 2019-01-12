/**
 * 
 */
package io.crs.hsys.client.fro.dashboard.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialProgress;
import gwt.material.design.client.ui.html.Div;

/**
 * @author robi
 *
 */
public class MeasureCard extends Composite {

	private static MeasureCardUiBinder uiBinder = GWT.create(MeasureCardUiBinder.class);

	interface MeasureCardUiBinder extends UiBinder<Widget, MeasureCard> {
	}

	/**
	 */

	@UiField
	MaterialColumn basePanel;

	@UiField
	Div frame;
	
	@UiField
	Label value, label, progressLabel, fulfilledLabel, remainingLabel;

	@UiField
	MaterialIcon icon;

	@UiField
	MaterialProgress progress;

	public MeasureCard() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setValue(String text) {
		value.setText(text);
	}

	public void setLabel(String text) {
		label.setText(text);
	}

	public void setIconType(IconType iconType) {
		icon.setIconType(iconType);
	}

	public void setIconColor(Color iconColor) {
		icon.setIconColor(iconColor);
		progress.setColor(iconColor);
	}

	public void setGrid(String grid) {
		basePanel.setGrid(grid);
	}

	public void setPercent(Integer percent) {
		progress.setPercent(percent);
		progressLabel.setText(percent + "%");
	}

	public void setFulfilledText(String fulfilledTextl) {
		fulfilledLabel.setText(fulfilledTextl);
	}

	public void setRemainingText(String text) {
		remainingLabel.setText(text);
	}

	public void setBackgroundColor(Color iconColor) {
		frame.setBackgroundColor(iconColor);
	}

}
