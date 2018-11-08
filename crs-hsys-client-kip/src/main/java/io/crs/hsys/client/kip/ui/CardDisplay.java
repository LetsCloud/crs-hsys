/**
 * 
 */
package io.crs.hsys.client.kip.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.HeadingSize;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialCardContent;
import gwt.material.design.client.ui.MaterialCardTitle;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;

/**
 * @author robi
 *
 */
public class CardDisplay extends Composite {

	private MaterialCard card;
	private MaterialCardContent content;
	private MaterialCardTitle title;

	public CardDisplay() {
		card = new MaterialCard();
		content = new MaterialCardContent();
		content.setTextAlign(TextAlign.CENTER);
		title = new MaterialCardTitle();
		title.setTruncate(true);
		card.add(content);
		content.add(title);
		initWidget(card);
	}

	public void setBackgroundColor(Color color) {
		card.setBackgroundColor(color);
	}

	public void setTextColor(Color color) {
		content.setTextColor(color);
	}

	public void setTitleText(String text) {
		title.setText(text);
	}

	public void setIconType(IconType iconType) {
		title.setIconType(iconType);
	}

	public void setIconPosition(IconPosition iconPosition) {
		title.setIconPosition(iconPosition);
	}

	public void addItem(String labelText, String value) {
		MaterialPanel panel = new MaterialPanel();
		panel.setTextAlign(TextAlign.CENTER);

		MaterialLabel label = new MaterialLabel(labelText);
		label.setFontSize(1, Unit.EM);
		panel.add(label);

		MaterialLabel valueLabel = new MaterialLabel(value);
		valueLabel.setFontSize(2, Unit.EM);
		panel.add(valueLabel);		

		content.add(panel);
	}
	
}
